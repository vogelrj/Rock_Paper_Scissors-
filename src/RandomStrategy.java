import java.util.List;
import java.util.Random;

public class RandomStrategy implements Strategy {
    private final Random random = new Random();
    private final String[] moves = {"Rock", "Paper", "Scissors"};

    @Override
    public String determineMove(List<String> playerHistory) {
        return moves[random.nextInt(3)];
    }
}
