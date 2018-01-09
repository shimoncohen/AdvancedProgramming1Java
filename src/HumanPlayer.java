import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer implements Player1 {
    // members
    private Enum.type playerType;

    //constructor
    HumanPlayer() {
        this.playerType = Enum.type.notDefined;
    }

    @Override
    public void assignType(Enum.type playerType1) {
       this.playerType = playerType1;
    }

    @Override
    public Enum.type getType() {
        return this.playerType;
    }

    public int[] makeMove(GameLogic gameLogic, Board board, ArrayList<Point> moves) {
        String temp1;
        int[] choice = new int[2];
        Scanner scanner = new Scanner(System.in);
        choice[0] = 0;
        choice[1] = 0;
        temp1 = scanner.nextLine();

        // if player wants to close the game
        if(temp1.compareTo("close") == 0) {
            choice[0] = -3;
            choice[1] = -3;
            return choice;
        }

        boolean notDigit;
        //check if what the user entered are numbers.
        notDigit = notADigit(temp1);
        if(notDigit) {
            return choice;
        }

        //if the user entered numbers the convert them to int.
        int j = 0;
        for(int i = 0; i < choice.length; i++) {
            while(j < temp1.length()) {
                if (!Character.isDigit(temp1.charAt(j))) {
                    j++;
                    break;
                }
                choice[i] *= 10;
                choice[i] += temp1.charAt(j) - 48;
                j++;
            }
        }
        choice[0] -= 1;
        choice[1] -= 1;
        return choice;
    }

    @Override
    public void recieveOpponentsMove(int x, int y) {
        return;
    }

    @Override
    public boolean needPrint() {
        return true;
    }

    private boolean notADigit(String temp) {
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) == ' ') {
                continue;
            }
            if (!Character.isDigit(temp.charAt(i)) ) {
                return true;
            }
        }
        return false;
    }
}
