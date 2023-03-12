package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserResults {

    private final HashMap<User, List<Result>> results;

    private UserResults(HashMap<User, List<Result>> results) {
        this.results = results;
    }

    public static UserResults of(Dealer dealer, Players players) {
        return new UserResults(calculateResults(dealer, players));
    }

    private static HashMap<User, List<Result>> calculateResults(Dealer dealer, Players players) {
        HashMap<User, List<Result>> userResults = initializeResults(dealer, players);
        int dealerScore = dealer.getTotalScore();
        for (Player player : players.getPlayers()) {
            Result playerResult = Result.calculateResult(player.getTotalScore(), dealerScore);
            Result dealerResult = playerResult.ofOppositeResult();
            userResults.get(player).add(playerResult);
            userResults.get(dealer).add(dealerResult);
        }
        return userResults;
    }

    private static HashMap<User, List<Result>> initializeResults(Dealer dealer, Players players) {
        HashMap<User, List<Result>> userResults = new HashMap<>();
        userResults.put(dealer, new ArrayList<>());
        for (Player player : players.getPlayers()) {
            userResults.put(player, new ArrayList<>());
        }
        return userResults;
    }

    public HashMap<User, List<Result>> getResults() {
        return results;
    }
}
