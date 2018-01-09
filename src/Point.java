public class Point {
    // members
    private int x,y;

    //constructor
    Point(int a, int b) {
        this.x = a;
        this.y = b;
    }

    int[] getPoint() {
        int[] temp = new int[2];
        temp[0] = this.x;
        temp[1] = this.y;
        return temp;
    }

    int getX() {
        return  this.x;
    }

    int getY() {
        return this.y;
    }

    boolean equals(Point other) {
        if (this.x == other.getX() && this.y == other.getY()) {
            return true;
        }
        return false;
    }
}
