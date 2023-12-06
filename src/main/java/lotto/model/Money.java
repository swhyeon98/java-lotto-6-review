package lotto.model;

import static lotto.global.LottoConstants.LOTTO_PRICE;
import static lotto.global.LottoConstants.MAX_LOTTO_PRICE;

public class Money {

    private final int amount;

    public Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        validateMinimumAmount(amount);
        validateAmountUnit(amount);
        validateMaximumAmount(amount);
    }

    private void validateMinimumAmount(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException("[ERROR] 금액은 최소 1000원 이상이어야 합니다.");
        }
    }

    private void validateAmountUnit(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 1000원 단위여야 합니다.");
        }
    }

    private void validateMaximumAmount(int amount) {
        if (amount > MAX_LOTTO_PRICE) {
            throw new IllegalArgumentException("[ERROR] 1인당 10만원을 초과할 수 없습니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
