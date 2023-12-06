package lotto.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MoneyTest {

    @ValueSource(ints = {1000, 2000, 3000, 5000})
    @ParameterizedTest
    void 금액이_맞다면_정상적으로_실행되어야_한다(int invalidAmount) throws Exception {
        // when
        Money money = new Money(invalidAmount);

        // then
        assertThat(money.getAmount()).isEqualTo(invalidAmount);
    }

    @ValueSource(ints = {500, 0, -10900000, 990})
    @ParameterizedTest
    void 천원_미만의_금액은_예외가_발생_되어야_한다(int invalidAmount) throws Exception {
        // when & then
        assertThatThrownBy(() -> new Money(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 금액은 최소 1000원 이상이어야 합니다.");
    }

    @ValueSource(ints = {1300, 50600, 77770, 5500})
    @ParameterizedTest
    void 천원_단위가_아닌_금액은_예외가_발생_되어야_한다(int invalidAmount) throws Exception {
        // when & then
        assertThatThrownBy(() -> new Money(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 금액은 1000원 단위여야 합니다.");
    }

    @ValueSource(ints = {300000, 200000, 400000, 500000})
    @ParameterizedTest
    void 십만원이_넘는_금액은_예외가_발생_되어야_한다(int invalidAmount) throws Exception {
        // when & then
        assertThatThrownBy(() -> new Money(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 1인당 10만원을 초과할 수 없습니다.");
    }
}