package lotto.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.global.LottoConstants.LOTTO_PRICE;

public class LottoResult {

    public Map<Rank, Long> calculateResults(Lotto winningLotto, List<Lotto> purchasedLottos, int bonusNumber) {
        Map<Rank, Long> results = new HashMap<>();
        for (Lotto purchasedLotto : purchasedLottos) {
            Rank rank = getRank(winningLotto, purchasedLotto, bonusNumber);
            if (rank != Rank.NO_LUCK) {
                results.put(rank, results.getOrDefault(rank, 0L) + 1);
            }
        }
        return results;
    }

    private Rank getRank(Lotto winningLotto, Lotto purchasedLotto, int bonusNumber) {
        boolean isBonusMatched = purchasedLotto.hasBonusNumber(bonusNumber);
        int matchCount = winningLotto.getMatchCount(purchasedLotto);
        return Rank.checkWinningStatus(matchCount, isBonusMatched);
    }

    public double calculateProfit(List<Lotto> purchasedLottos, Map<Rank, Long> results) {
        long purchaseAmount = purchasedLottos.size() * LOTTO_PRICE;
        long totalPrize = calculateTotalPrize(results);
        return ((double) totalPrize / purchaseAmount) * 100;
    }

    private long calculateTotalPrize(Map<Rank, Long> results) {
        long totalPrize = 0;
        for (Map.Entry<Rank, Long> entry : results.entrySet()) {
            totalPrize += entry.getValue() * entry.getKey().getPrizeMoney();
        }
        return totalPrize;
    }
}
