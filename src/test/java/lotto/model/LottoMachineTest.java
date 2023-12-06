package lotto.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class LottoMachineTest {

    private RandomNumberGenerator lottoGenerator;

    @BeforeEach
    void setUp() {
        lottoGenerator = new RandomLottoGenerator();
    }

    @Test
    void 로또는_구매금액만큼_발행되어야_한다() throws Exception {
        // given
        int purchaseAmount = 3000;
        LottoMachine lottoMachine = new LottoMachine(lottoGenerator);

        // when
        List<Lotto> lottos = lottoMachine.buyLottos(purchaseAmount);// 3장 발행

        // then
        Assertions.assertThat(lottos).hasSize(3);
    }

}