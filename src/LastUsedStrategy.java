import java.util.List;

public class LastUsedStrategy implements Strategy {
    @Override
    public String determineMove(List<String> playerHistory) {
        return playerHistory.isEmpty() ? "Rock" : playerHistory.get(playerHistory.size() - 1);
    }
}
