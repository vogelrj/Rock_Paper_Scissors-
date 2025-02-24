import java.util.List;

public interface Strategy {
    String determineMove(List<String> playerHistory);
}
