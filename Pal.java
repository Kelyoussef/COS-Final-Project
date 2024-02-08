class PAL {
    public static void main(String[] args) {
        int ROWS = 8;
        int COLS = 8;
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setYscale(-1, 9);
        StdDraw.setXscale(-1, 9);
        int row = 0;
        int col = 0;
        for (row = 0; row <= ROWS; row++)
            for (col = row; col == row; col++) {
                StdDraw.filledCircle(row, col, 0.25);
            }
        for (row = 0; row <= ROWS; row++)
            for (col = COLS; col == COLS - row - 1; col--) {
                StdDraw.filledCircle(row, col, 0.25);
            }
    }
}
