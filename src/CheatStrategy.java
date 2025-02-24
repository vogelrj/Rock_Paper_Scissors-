import java.util.List;

public class CheatStrategy implements Strategy {
    @Override
    public String determineMove(List<String> playerHistory) {
        if (playerHistory.isEmpty()) {
            return "Paper";
        }
        String playerMove = playerHistory.get(playerHistory.size() - 1);
        return playerMove.equals("Rock") ? "Paper" :
                playerMove.equals("Paper") ? "Scissors" : "Rock";
    }
}
