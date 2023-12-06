package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String input(String message) {
        System.out.println(message);
        return Console.readLine();
    }

    public String readPurchaseAmount() {
        return input("구입금액을 입력해 주세요.");
    }

    public String readWinningNumber() {
        System.out.println();
        return input("당첨 번호를 입력해 주세요.");

    }

    public String readBonusNumber() {
        System.out.println();
        return input("보너스 번호를 입력해 주세요.");
    }
}
