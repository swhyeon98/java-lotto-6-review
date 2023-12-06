package lotto.model;

import java.util.Collections;
import java.util.List;

public class LottoMachine {

    private final RandomNumberGenerator lottoGenerator;

    public LottoMachine(RandomNumberGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    private Lotto createSortedLotto() {
        List<Integer> numbers = lottoGenerator.generate();
        Collections.sort(numbers);
        return new Lotto(numbers);
    }
}
