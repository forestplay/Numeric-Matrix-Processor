package processor;

import java.util.Scanner;

public class MatrixSize {
    private final int rows;
    private final int cols;

    public MatrixSize(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public MatrixSize(String prompt, Scanner scanner) {
        System.out.print(prompt);
        this.rows = scanner.nextInt();
        this.cols = scanner.nextInt();
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
