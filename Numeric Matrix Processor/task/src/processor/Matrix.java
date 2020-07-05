package processor;

import java.util.ArrayList;
import java.util.Scanner;

public class Matrix {
    protected MatrixSize matrixSize;
    protected double[][] matrix;

    public Matrix(MatrixSize matrixSize) {
        this.matrixSize = matrixSize;
        this.matrix = new double[matrixSize.getRows()][matrixSize.getCols()];
    }

    public void read(String prompt, Scanner scanner) {
        System.out.println(prompt);
        this.read(scanner);
    }

    public void read(Scanner scanner) {
        for (int r = 0; r < matrixSize.getRows(); r++)
            for (int c = 0; c < matrixSize.getCols(); c++) {
                this.matrix[r][c] = scanner.nextDouble();
            }
    }

    public MatrixSize getMatrixSize() {
        return this.matrixSize;
    }

//    public double getValueAt(int row, int col) {
//        return this.matrix[row][col];
//    }

    public void setValueAt(int row, int col, double value) {
        this.matrix[row][col] = value;
    }

    public double[] getRow(int row) throws Exception {
        if (row < 0 || row > this.matrixSize.getRows())
            throw new Exception("ERROR: Row outside bounds");
        return this.matrix[row];
    }

    public double[] getColumn(int col) throws Exception {
        if (col < 0 || col > this.matrixSize.getCols())
            throw new Exception("ERROR: Col outside bounds");
        double[] rtnValue = new double[matrixSize.getRows()];
        for (int i = 0; i < matrixSize.getRows(); i++)
            rtnValue[i] = this.matrix[i][col];
        return rtnValue;
    }

    public Matrix sum(Matrix other) throws Exception {
        Matrix rtnValue = new Matrix(new MatrixSize(this.matrixSize.getRows(), this.matrixSize.getCols()));
        if (this.matrixSize.getRows() != other.matrixSize.getRows() || this.matrixSize.getCols() != other.matrixSize.getCols()) {
            throw new Exception("ERROR: matrix mismatch, unable to creat sum");
        } else {
            for (int r = 0; r < matrixSize.getRows(); r++) {
                for (int c = 0; c < matrixSize.getCols(); c++) {
                    rtnValue.setValueAt(r, c, this.matrix[r][c] + other.matrix[r][c]);
                }
            }
        }
        return rtnValue;
    }

    public Matrix product(double value) {
        Matrix rtnValue = new Matrix(new MatrixSize(this.matrixSize.getRows(), this.matrixSize.getCols()));
        for (int r = 0; r < matrixSize.getRows(); r++) {
            for (int c = 0; c < matrixSize.getCols(); c++) {
                rtnValue.setValueAt(r, c, this.matrix[r][c] * value);
            }
        }
        return rtnValue;
    }

    public Matrix product(Matrix other) throws Exception {
        Matrix rtnValue;
        if (this.matrixSize.getCols() != other.matrixSize.getRows()) {
            throw new Exception("ERROR: matrix mismatch, unable to create product");
        } else {
            rtnValue = new Matrix(new MatrixSize(this.matrixSize.getRows(), other.matrixSize.getCols()));
            for (int r = 0; r < this.matrixSize.getRows(); r++) {
                for (int c = 0; c < other.matrixSize.getCols(); c++) {
                    double value = 0;
                    for (int i = 0; i < this.matrixSize.getCols(); i++) {
                        value += this.getRow(r)[i] * other.getColumn(c)[i];
                    }
                    rtnValue.setValueAt(r, c, value);
                }
            }
        }
        return rtnValue;
    }

    public Matrix transposeDiagonal() throws Exception {
        int size = matrixSize.getRows();
        if (size != matrixSize.getCols())
            throw new Exception("ERROR: non-square matrix, unable to transpose");
        Matrix rtnValue = new Matrix(new MatrixSize(size, size));
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++)
                rtnValue.setValueAt(c, r, this.matrix[r][c]);
        return rtnValue;
    }

    public Matrix transposeSideDiagonal() throws Exception {
        int size = matrixSize.getRows();
        if (size != matrixSize.getCols())
            throw new Exception("ERROR: non-square matrix, unable to transpose");
        Matrix rtnValue = new Matrix(new MatrixSize(size, size));
        for (int r = size - 1; r >= 0; r--)
            for (int c = 0; c < size; c++)
                rtnValue.setValueAt(size - c - 1, size - r - 1, this.matrix[r][c]);
        return rtnValue;
    }

    public Matrix transposeVertical() throws Exception {
        int size = matrixSize.getRows();
        if (size != matrixSize.getCols())
            throw new Exception("ERROR: non-square matrix, unable to transpose");
        Matrix rtnValue = new Matrix(new MatrixSize(size, size));
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++)
                rtnValue.setValueAt(r, size - c - 1, this.matrix[r][c]);
        return rtnValue;
    }

    public Matrix transposeHorizontal() throws Exception {
        int size = matrixSize.getRows();
        if (size != matrixSize.getCols())
            throw new Exception("ERROR: non-square matrix, unable to transpose");
        Matrix rtnValue = new Matrix(new MatrixSize(size, size));
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++)
                rtnValue.setValueAt(size - r - 1, c, this.matrix[r][c]);
        return rtnValue;
    }

    public double determinant() throws Exception {
        int size = matrixSize.getRows();
        if (size != matrixSize.getCols())
            throw new Exception("ERROR: non-square matrix, unable to calculate determinant");
        double determinant = 0;
        if (size == 2)
            determinant = (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
        else {
            for (int k = 0; k < size; k++) {
                determinant += matrix[0][k] * this.minor(0, k).determinant() * Math.pow(-1, k);
            }
        }
        return determinant;
    }

    public Matrix minor(int row, int col) throws Exception {
        int size = matrixSize.getRows();
        if (size != matrixSize.getCols())
            throw new Exception("ERROR: non-square matrix, unable to calculate minor");
        Matrix rtnValue = new Matrix(new MatrixSize(size - 1, size - 1));
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> cols = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (i != row) rows.add(i);
            if (i != col) cols.add(i);
        }
        for (int r = 0; r < size - 1; r++)
            for (int c = 0; c < size - 1; c++)
                rtnValue.setValueAt(r, c, matrix[rows.get(r)][cols.get(c)]);
        return rtnValue;
    }

    public Matrix inverse() throws Exception {
        int size = matrixSize.getRows();
        if (size != matrixSize.getCols())
            throw new Exception("ERROR: non-square matrix, unable to calculate inverse");
        Matrix rtnValue = new Matrix(new MatrixSize(size, size));
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++)
                rtnValue.setValueAt(r, c, this.minor(r, c).determinant() * Math.pow(-1, (r + 1) + (c + 1)));
        return rtnValue.transposeDiagonal().product(1 / this.determinant());
    }

    @Override
    public String toString() {
        StringBuilder rtnValue = new StringBuilder();
        for (int r = 0; r < matrixSize.getRows(); r++) {
            for (int c = 0; c < matrixSize.getCols(); c++) {
                rtnValue.append(String.format("%.2f", this.matrix[r][c])).append(" ");
            }
            if (r + 1 < matrixSize.getRows()) rtnValue.append("\n");
        }
        return rtnValue.toString();
    }
}