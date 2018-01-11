import javafx.scene.paint.Color;

public class Player {

    private Enum.type type;
    private Color color;
    private Integer score;

    public Player(Enum.type type, Color color) {
        this.type = type;
        this.color = color;
    }

    public Enum.type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}