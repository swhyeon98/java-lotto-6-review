package lotto.model;

public class Money {

    private final int amount;

    public Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        validateMinimumAmount(amount);
        validateAmountUnit(amount);
    }

    private void validateMinimumAmount(int amount) {
        if (amount < 1000) {
            throw new IllegalArgumentException("[ERROR] 금액은 최소 1000원 이상이어야 합니다.");
        }
    }

    private void validateAmountUnit(int amount) {
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 1000원 단위여야 합니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
