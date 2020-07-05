package processor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean continueLoop = true;
        while (continueLoop) {

            System.out.print("1. Add matrices\n" +
                    "2. Multiply matrix to a constant\n" +
                    "3. Multiply matrices\n" +
                    "4. Transpose matrix\n" +
                    "5. Calculate a determinant\n" +
                    "6. Inverse matrix\n" +
                    "0. Exit\n" +
                    "Your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: {   // Add matrices
                    Matrix matrixA = new Matrix(new MatrixSize("Enter size of first matrix: ", scanner));
                    matrixA.read("Enter first matrix: ", scanner);

                    Matrix matrixB = new Matrix(new MatrixSize("Enter size of second matrix: ", scanner));
                    matrixB.read("Enter second matrix: ", scanner);

                    try {
                        Matrix matrixSum = matrixA.sum(matrixB);
                        System.out.println("The addition result is:");
                        System.out.println(matrixSum);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
                case 2: {   // Multiply matrix to a constant
                    Matrix matrixA = new Matrix(new MatrixSize("Enter size of matrix: ", scanner));
                    matrixA.read("Enter matrix: ", scanner);

                    System.out.println("Enter constant: ");
                    int constant = scanner.nextInt();

                    Matrix matrixProduct = matrixA.product(constant);
                    System.out.println("The multiplication result is:");
                    System.out.println(matrixProduct);
                }
                break;
                case 3: {   // Multiply matrices
                    Matrix matrixA = new Matrix(new MatrixSize("Enter size of first matrix: ", scanner));
                    matrixA.read("Enter first matrix: ", scanner);

                    Matrix matrixB = new Matrix(new MatrixSize("Enter size of second matrix: ", scanner));
                    matrixB.read("Enter second matrix: ", scanner);

                    try {
                        Matrix matrixProduct = matrixA.product(matrixB);
                        System.out.println("The multiplication result is:");
                        System.out.println(matrixProduct);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
                case 4: {   // Transpose matrix
                    System.out.print("1. Main diagonal\n" +
                            "2. Side diagonal\n" +
                            "3. Vertical line\n" +
                            "4. Horizontal line\n" +
                            "Your choice: ");
                    int transposeChoice = scanner.nextInt();

                    Matrix matrixA = new Matrix(new MatrixSize("Enter matrix size: ", scanner));
                    matrixA.read("Enter matrix: ", scanner);

                    int size = matrixA.getMatrixSize().getRows();
                    Matrix matrixTranspose = new Matrix(new MatrixSize(size, size));
                    switch (transposeChoice) {
                        case 1: // Main diagonal
                            try {
                                matrixTranspose = matrixA.transposeDiagonal();
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 2: // Side diagonal
                            try {
                                matrixTranspose = matrixA.transposeSideDiagonal();
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 3: // Vertical line
                            try {
                                matrixTranspose = matrixA.transposeVertical();
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 4: // Horizontal line
                            try {
                                matrixTranspose = matrixA.transposeHorizontal();
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                    }
                    System.out.println("The result is:");
                    System.out.println(matrixTranspose);
                }
                break;
                case 5: {   // Calculate a determinant
                    Matrix matrixA = new Matrix(new MatrixSize("Enter matrix size: ", scanner));
                    matrixA.read("Enter matrix: ", scanner);

                    System.out.println("The result is:");
                    try {
                    System.out.println(matrixA.determinant());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
                case 6: {   // Inverse matrix
                    Matrix matrixA = new Matrix(new MatrixSize("Enter matrix size: ", scanner));
                    matrixA.read("Enter matrix: ", scanner);

                    System.out.println("The result is:");
                    try {
                        System.out.println(matrixA.inverse());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
                case 0: // exit
                    continueLoop = false;
                    break;
            }
        }
    }
}


