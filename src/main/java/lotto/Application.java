package lotto;

import lotto.controller.LottoController;
import lotto.model.RandomLottoGenerator;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new RandomLottoGenerator());
        lottoController.play();
    }
}
