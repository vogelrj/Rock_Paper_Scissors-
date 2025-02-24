import java.util.List;

public class LeastUsedStrategy implements Strategy {
    @Override
    public String determineMove(List<String> playerHistory) {
        long rock = playerHistory.stream().filter(m -> m.equals("Rock")).count();
        long paper = playerHistory.stream().filter(m -> m.equals("Paper")).count();
        long scissors = playerHistory.stream().filter(m -> m.equals("Scissors")).count();
        return rock <= paper && rock <= scissors ? "Paper" : (paper <= scissors ? "Scissors" : "Rock");
    }
}
