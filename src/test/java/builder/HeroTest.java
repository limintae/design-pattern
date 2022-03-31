package builder;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class HeroTest {

    @Test
    void missingName() {
        Assertions.assertThatCode(() -> new Hero.Builder("")
                .withWeapon(Weapon.SWORD)
                .build())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource
    void buildHeroIsSucceed(String heroName, Weapon weapon) {
        final var hero = new Hero.Builder(heroName)
                .withWeapon(weapon)
                .build();

        Assertions.assertThat(hero.getName()).isEqualTo(heroName);
        Assertions.assertThat(hero.getWeapon()).isEqualTo(Weapon.SWORD);
    }

    static Stream<Arguments> buildHeroIsSucceed() {
        return Stream.of(
                Arguments.of("swordMan", Weapon.SWORD)
        );
    }

}
