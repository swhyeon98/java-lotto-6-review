package lotto.controller;

import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.model.LottoResult;
import lotto.model.Money;
import lotto.model.RandomNumberGenerator;
import lotto.model.Rank;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoController {

    private final RandomNumberGenerator lottoGenerator;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(RandomNumberGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void play() {
        Money purchaseAmount = readInput(this::readPurchaseAmount, "[ERROR] 금액은 숫자로 입력해야 합니다.");
        List<Lotto> purchasedLottos = new LottoMachine(lottoGenerator).buyLottos(purchaseAmount);
        outputView.printLottos(purchasedLottos);

        Lotto winningLotto = readInput(this::readWinningLotto, "[ERROR] 당첨 번호는 숫자로만 입력해야 합니다.");
        int bonusNumber = readInput(() -> readBonusNumber(winningLotto), "[ERROR] 보너스 번호는 숫자로만 입력해야 합니다.");

        LottoResult result = new LottoResult();
        Map<Rank, Long> rankMap = result.calculateResults(winningLotto, purchasedLottos, bonusNumber);
        double profit = result.calculateProfit(purchasedLottos, rankMap);
        outputView.printResult(rankMap, profit);
    }

    private <T> T readInput(Supplier<T> inputSupplier, String errorMessage) {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (NumberFormatException e) {
                outputView.printError(errorMessage);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private Money readPurchaseAmount() {
        int amount = Integer.parseInt(inputView.readPurchaseAmount());
        return new Money(amount);
    }

    private Lotto readWinningLotto() {
        String winningNumber = inputView.readWinningNumber();
        List<Integer> winningLottoNumbers = Stream.of(winningNumber.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return new Lotto(winningLottoNumbers);
    }

    private int readBonusNumber(Lotto winningLotto) {
        int bonusNumber = Integer.parseInt(inputView.readBonusNumber());
        winningLotto.validateBonusNumber(bonusNumber);
        return bonusNumber;
    }
}
