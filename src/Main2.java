//import java.util.Scanner;
//
//public class Main2 {
//    public static void main(String[] args) {
//        // members
//        final int ENDPORTWORD = 5, ENDIPWORD = 3;
//        Scanner scanner = new Scanner(System.in);
//        String IPAddress;
//        int choice = -1, port;
//        //int size;
//        //settingsReading(&port, IPAddress);
//        Printer printer = new ConsolePrinter();
//        printer.printGameOpenning();
//        // ***for future use when board size will be decided by the user***
//        // getting board size from the user
//        // printer->boardSizeMessage();
//        // cin >> size;
//
//        // creating game logic
//        GameLogic gameLogic = new StandardGameLogic();
//        Player1 first;
//        Player1 second;
//        Game1 g;
//        // creating the second player according to the game style (Player/ AI).
//        // create a game object and give it a board, players and a logic to play by.
//        first = new HumanPlayer();
//        second = new HumanPlayer();
//        g = new Game1(gameLogic, first, second);
//        // if there was no error starting the game.
//        if(second != null) {
//            try {
//                g.runGame();
//            } catch (Exception msg) {
//                printer.failedMessage(msg);
//            }
//        }
//    }
//}
