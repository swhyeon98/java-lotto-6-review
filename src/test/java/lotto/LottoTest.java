package lotto;

import lotto.model.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력한 숫자의 개수가 올바르지 않습니다. 6개의 숫자를 입력해야 합니다.");
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 중복된 숫자가 존재합니다. 1부터 45 사이의 서로 다른 숫자 6개를 입력해주세요.");
    }

    @DisplayName("로또 번호에 0보다 작거나 45보다 큰 숫자가 있으면 예외가 발생한다.")
    @ValueSource(ints = {-1, 0, 46, 47})
    @ParameterizedTest
    void createLottoByOutRangeNumber(int invalidNumber) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, invalidNumber); // 마지막 숫자를 유효하지 않은 숫자로 설정

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
    }

    @Test
    void 당첨_번호와_구매_번호가_모두_일치할_경우_일치하는_숫자의_개수가_정확히_계산된다() throws Exception {
        // given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when
        int winningCount = winningLotto.getMatchCount(purchasedLotto);

        // then
        assertThat(winningCount).isEqualTo(6);
    }

    @Test
    void 당첨_번호와_구매_번호가_5개만_일치할_경우_일치하는_숫자의_개수가_정확히_계산된다() throws Exception {
        // given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        // when
        int winningCount = winningLotto.getMatchCount(purchasedLotto);

        // then
        assertThat(winningCount).isEqualTo(5);
    }

    @Test
    void 당첨_번호와_구매_번호가_모두_일치하지_않을_경우_일치하는_숫자의_개수가_정확히_계산된다() throws Exception {
        // given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto purchasedLotto = new Lotto(List.of(7, 8, 9, 10, 11, 12));

        // when
        int winningCount = winningLotto.getMatchCount(purchasedLotto);

        // then
        assertThat(winningCount).isEqualTo(0);
    }
}