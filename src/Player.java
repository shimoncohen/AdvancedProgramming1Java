public class Player {

    private Enum.type type;
    private Integer score;

    public Player(Enum.type type) {
        this.type = type;
    }

    public Enum.type getType() {
        return type;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}