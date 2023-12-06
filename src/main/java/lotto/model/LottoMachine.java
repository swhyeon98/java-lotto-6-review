package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {

    private final RandomNumberGenerator lottoGenerator;

    public LottoMachine(RandomNumberGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public List<Lotto> buyLottos(Money purchaseAmount) {
        int lottoCount = purchaseAmount.getAmount() / 1000;
        return IntStream.range(0, lottoCount)
                .mapToObj(i -> createSortedLotto())
                .collect(Collectors.toList());
    }

    private Lotto createSortedLotto() {
        List<Integer> numbers = new ArrayList<>(lottoGenerator.generate());
        Collections.sort(numbers);
        return new Lotto(numbers);
    }
}
