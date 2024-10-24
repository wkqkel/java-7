package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class RaceTest {

    @Test
    void 제일_많이_전진한_사람이_우승자이다() {
        // given
        Car car1 = new Car("car1");
        Car car2 = new Car("car2");
        Car car3 = new Car("car2");
        List<Car> cars = List.of(car1, car2, car3);
        GenerateNumberStrategy strategy = new MockGenerateNumberStrategy(List.of(4, 1, 1));
        Race race = new Race(cars, strategy);

        // when
        race.moveAllCars();

        // then
        List<Car> winner = race.getWinner();
        assertThat(winner.size()).isEqualTo(1);
        assertThat(winner.getFirst()).isSameAs(car1);
    }

    @Test
    void 우승자는_복수_일_수_있다() {
        // given
        Car car1 = new Car("car1");
        Car car2 = new Car("car2");
        Car car3 = new Car("car2");
        List<Car> cars = List.of(car1, car2, car3);
        GenerateNumberStrategy strategy = new MockGenerateNumberStrategy(List.of(4, 4, 1));
        Race race = new Race(cars, strategy);

        // when
        race.moveAllCars();

        // then
        List<Car> winner = race.getWinner();
        assertThat(winner.size()).isEqualTo(2);
    }

    class MockGenerateNumberStrategy implements GenerateNumberStrategy {
        private final List<Integer> numbers;
        private int i = 0;

        MockGenerateNumberStrategy(List<Integer> numbers) {
            this.numbers = numbers;
        }

        @Override
        public int generate() {
            return numbers.get(i++ % numbers.size());
        }
    }
}