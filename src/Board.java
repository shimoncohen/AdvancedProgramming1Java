// 315383133 shimon cohen
// 302228275 Nadav Spitzer
/*
 * The console game board.
 * holdes and manages the board.
 */
public class Board {
    // members
    final int DEFAULTSIZE = 4;
    enum boardChar {black, white, space};
    private boardChar[][] board;
    private int boardSize;

    /*
     * function name: Board.
     * input: board size.
     * output: none.
     * operation: constructor. initializes the board to given size.
     */
    Board(int size) {
        this.boardSize = size;
        this.board = new boardChar[size][size];
        //fill the board cells with spaces to indicate free spaces.
        for(int i = 0; i < size; i++) {
            for(int k = 0; k < size; k++) {
                this.board[i][k] = boardChar.space;
            }
        }
        this.board[size / 2 - 1][size / 2 - 1] = boardChar.white;
        this.board[size / 2 - 1][size / 2] = boardChar.black;
        this.board[size / 2][size / 2 - 1] = boardChar.black;
        this.board[size / 2][size / 2] = boardChar.white;
    }

    /*
     * function name: Board.
     * input: none.
     * output: none.
     * operation: constructor. initializes the board to size 8 on 8.
     */
    Board() {
        this.boardSize = this.DEFAULTSIZE;
        this.board = new boardChar[this.DEFAULTSIZE][this.DEFAULTSIZE];
        //fill the board cells with spaces to indicate free spaces.
        for(int i = 0; i < this.DEFAULTSIZE; i++) {
            for(int k = 0; k < this.DEFAULTSIZE; k++) {
                board[i][k] = boardChar.space;
            }
        }
        // initializing the board
        this.board[this.DEFAULTSIZE / 2 - 1][this.DEFAULTSIZE / 2 - 1] = boardChar.white;
        this.board[this.DEFAULTSIZE / 2 - 1][this.DEFAULTSIZE / 2] = boardChar.black;
        this.board[this.DEFAULTSIZE / 2][this.DEFAULTSIZE / 2 - 1] = boardChar.black;
        this.board[this.DEFAULTSIZE / 2][this.DEFAULTSIZE / 2] = boardChar.white;
    }

    /*
     * function name: Board.
     * input: board.
     * output: none.
     * operation: constructor. initializes the board from a different board object.
     */
    Board(Board board1) {
        this.boardSize = board1.getSize();
        this.board = new boardChar[this.boardSize][this.boardSize];
        // copy the given boards cell values to current board.
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                if (board1.checkCell(i, j) == 'x') {
                    this.board[i][j] = boardChar.black;
                } else if (board1.checkCell(i, j) == 'o') {
                    this.board[i][j] = boardChar.white;
                } else {
                    this.board[i][j] = boardChar.space;
                }
            }
        }
    }

    /*
     * function name: getSize.
     * input: none.
     * output: the size of the board.
     * operation: returns the boards size.
     */
    public int getSize() {
        return this.boardSize;
    }

    /*
     * function name: checkCell.
     * input: x and y values (row and column).
     * output: the char in the cell.
     * operation: returnes the char in a given cell.
     */
    public char checkCell(final int x, final int y) {
        //if the cell is out of the boards bounds.
        if (x < 0 || x >= this.boardSize || y < 0 || y >= this.boardSize) {
            return ' ';
        }
        // return the cells value.
        if (this.board[x][y] == boardChar.black) {
            return 'x';
        } else if (this.board[x][y] == boardChar.white) {
            return 'o';
        }
        return ' ';
    }

    /*
     * function name: putTile.
     * input: x and y values of a cell and a type to put in the cell.
     * output: none.
     * operation: puts a players piece in a given cell of the board.
     */
    public void putTile(final int x, final int y, final Enum.type type) {
        // put a value in the cell according to type.
        if(type == Enum.type.blackPlayer) {
            this.board[x][y] = boardChar.black;
        } else if(type == Enum.type.whitePlayer) {
            this.board[x][y] = boardChar.white;
        } else {
            this.board[x][y] = boardChar.space;
        }
    }

    /*
     * function name: equals.
     * input: 2 board objects.
     * output: a boolean value indicating if boards are equal.
     * operation: returns true if boards are equal, false otherwise.
     */
    public boolean equals(Board board1, Board board2) {
        int i = 0, k = 0;
        // go over the board and compare each cell.
        for(i = 0; i < board1.getSize(); i++) {
            for(k = 0; k < board1.getSize(); k++) {
                // if even one cell is diffrent return false.
                if(board1.checkCell(i,k) != board2.checkCell(i, k)) {
                    return false;
                }
            }
        }
        // if all cells in both boards are the same return true.
        return true;
    }
}
