public class NumMatrix {
    int[][] matrix;
    int[][] sums;
    int col;
    int row;

    public NumMatrix(int[][] matrix) {
        if (matrix != null) {
            this.row = matrix.length;
            this.col = matrix[0].length;
            this.matrix = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    this.matrix[i][j] = matrix[i][j];
                }
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (j == 0) sums[i][j] = matrix[i][j];
                    else sums[i][j] = sums[i][j - 1] + matrix[i][j];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            if (col1 == 0) sum += matrix[i][col2];
            else sum += sums[i][col2] - sums[i][col1] + matrix[i][col1];
        }
        return sum;
    }
}
