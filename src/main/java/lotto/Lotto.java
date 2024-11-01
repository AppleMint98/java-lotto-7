package lotto;

import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        validateSize(numbers);
        validateEachNumber(numbers);
        this.numbers = numbers;
    }

    public static Lotto createLotto(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    public String printLotto() {
        String joinNumbers = String.join(", ", numbers.stream()
            .map(String::valueOf)
            .toList());
        return "[" + joinNumbers + "]";
    }

    public LottoResult calculatePrize(Set<Integer> winningNumbers, int bonusNumber) {
        int winningCount = 0;
        int bonusCount = 0;
        for (Integer number : numbers) {
            if (winningNumbers.contains(number)) {
                winningCount++;
            }
            if (number == bonusNumber) {
                bonusCount++;
            }
        }
        if (winningCount == 6) {
            return LottoResult.FIRST_PRIZE;
        }
        if (winningCount == 5 && bonusCount == 1) {
            return LottoResult.SECOND_PRIZE;
        }
        if (winningCount == 5) {
            return LottoResult.THIRD_PRIZE;
        }
        if (winningCount == 4) {
            return LottoResult.FOURTH_PRIZE;
        }
        if (winningCount == 3) {
            return LottoResult.FIFTH_PRIZE;
        }
        return LottoResult.NO_PRIZE;
    }


    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateEachNumber(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number <= 0) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1 이상의 양수로 입력해 주세요.");
            }
            if (number > 45) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
        }
    }
}
