package config;

public enum MapArrangement {
    MAP1(3,0,2,0),
    MAP2(0,0,0,1),
    MAP3(0,1,0,0),
    MAP4(0,0,0,0),
    ;

    private int up;
    private int down;
    private int right;
    private int left;

    MapArrangement(int up, int down, int right, int left) {
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
    }

    public int getUp() {
        return up;
    }

    public int getDown() {
        return down;
    }

    public int getRight() {
        return right;
    }

    public int getLeft() {
        return left;
    }
}
