package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

import static lotto.global.LottoConstants.LOTTO_NUMBER_SIZE;
import static lotto.global.LottoConstants.MAX_LOTTO_NUMBER;
import static lotto.global.LottoConstants.MIN_LOTTO_NUMBER;

public class RandomLottoGenerator implements RandomNumberGenerator{

    @Override
    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, LOTTO_NUMBER_SIZE);
    }
}
