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
        return switch (playerMove) {
            case "Rock" -> "Paper";
            case "Paper" -> "Scissors";
            case "Scissors" -> "Rock";
            default -> "Rock";
        };
    }
}
