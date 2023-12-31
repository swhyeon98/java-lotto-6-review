package lotto.model;

import java.util.List;

import static lotto.global.LottoConstants.LOTTO_NUMBER_SIZE;
import static lotto.global.LottoConstants.MAX_LOTTO_NUMBER;
import static lotto.global.LottoConstants.MIN_LOTTO_NUMBER;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public void validateBonusNumber(int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호가 당첨 번호 리스트에 포함되어 있습니다.");
        }
    }

    public int getMatchCount(Lotto purchasedLotto) {
        List<Integer> winningLotto = numbers;
        return (int) purchasedLotto.numbers.stream()
                .filter(winningLotto::contains)
                .count();
    }

    public boolean hasBonusNumber(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    private void validate(List<Integer> numbers) {
        validateNumberSize(numbers);
        validateNumberInRange(numbers);
        validateDuplicate(numbers);
    }

    private void validateNumberSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("[ERROR] 입력한 숫자의 개수가 올바르지 않습니다. 6개의 숫자를 입력해야 합니다.");
        }
    }

    private void validateNumberInRange(List<Integer> numbers) {
        if (numbers.stream().anyMatch(number -> number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER)) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        long uniqueCount = numbers.stream().distinct().count();
        if (uniqueCount != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 숫자가 존재합니다. 1부터 45 사이의 서로 다른 숫자 6개를 입력해주세요.");
        }
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
