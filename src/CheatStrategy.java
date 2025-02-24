import java.util.List;

public class CheatStrategy implements Strategy {
    @Override
    public String determineMove(List<String> playerHistory) {
        if (playerHistory.isEmpty()) {
            return "Rock";
        }
        return getCheatingMove(playerHistory.get(playerHistory.size() - 1));
    }

    private String getCheatingMove(String playerMove) {
        switch (playerMove) {
            case "Rock": return "Paper";
            case "Paper": return "Scissors";
            case "Scissors": return "Rock";
            default: return "Rock";
        }
    }
}
