// 315383133 shimon cohen
// 302228275 Nadav Spitzer

import javafx.scene.paint.Color;
/*
 * the player class. holds the information about the player, such as color, type and score.
 */
public class Player {

    private Enum.type type;
    private Color color;
    private Integer score;

    /**
     * constructor.
     * @param type the type of the player.
     * @param color the color of the player.
     */
    public Player(Enum.type type, Color color) {
        this.type = type;
        this.color = color;
    }

    /**
     * returns the type of the current player.
     * @return the type of the player.
     */
    public Enum.type getType() {
        return type;
    }

    /**
     * returns the color of the current player.
     * @return the color of the player.
     */
    public Color getColor() {
        return color;
    }

    /**
     * returns the score of the current player.
     * @return the score of the player.
     */
    public Integer getScore() {
        return score;
    }

    /**
     * updates the score of the current player (the number of tiles of the player's color on the board).
     * @param score the current number of tiles of the player's color on the board.
     */
    public void setScore(int score) {
        this.score = score;
    }
}