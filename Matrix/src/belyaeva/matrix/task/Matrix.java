package belyaeva.matrix.task;

import belyaeva.vector.task.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0 || columnsCount <= 0) {
            throw new IllegalArgumentException("Matrix size must be > 0");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        this.rows = Arrays.copyOf(matrix.rows, matrix.rows.length);
    }

    public Matrix(double[][] matrix) {
        if (matrix.length <= 0 || matrix[0].length <= 0) {
            throw new IllegalArgumentException("double[][] size must be > 0");
        }

        Vector[] rows = new Vector[matrix.length];
        int max = 0;

        for (double[] doubles : matrix) {
            if (doubles.length > max) {
                max = doubles.length;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            rows[i] = new Vector(max, matrix[i]);
        }
        this.rows = rows;
    }

    public Matrix(Vector[] rows) {
        if (rows.length <= 0) {
            throw new IllegalArgumentException("Vector[] size must be > 0");
        }

        this.rows = Arrays.copyOf(rows, rows.length);
        int max = 0;

        for (Vector row : rows) {
            if (row.getSize() > max) {
                max = row.getSize();
            }
        }

        for (Vector row : rows) {
            if (row.getSize() < max) {
                row.addVector(new Vector(max));
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{");

        for (int i = 0; i < rows.length; i++) {
            if (i > 0) {
                s.append(", ");
            }
            s.append(rows[i]);
        }
        s.append("}");

        return s.toString();
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public void setRow(int rowIndex, Vector row) {
        if (rowIndex < 0 || rowIndex >= rows.length) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < " + rows.length);
        }

        if (row.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Row length must be = " + getColumnsCount());
        }

        rows[rowIndex] = new Vector(row);
    }

    public Vector getRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= rows.length) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < " + rows.length);
        }

        return new Vector(rows[rowIndex]);
    }

    public Vector getColumn(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < " + getColumnsCount());
        }

        double[] column = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            column[i] = rows[i].getValue(columnIndex);
        }

        return new Vector(column);
    }

    public void transpose() {
        Vector[] rows = new Vector[getColumnsCount()];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = getColumn(i);
        }

        this.rows = rows;
    }

    public void multiplyMatrixByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    private double getMinor(int column) {
        double[][] minorMatrix = new double[rows.length - 1][rows.length - 1];

        int counterForRows = 0;
        int columnsCount = getColumnsCount();

        for (int i = 1; i < rows.length; i++) {
            int counterForColumns = 0;
            for (int j = 0; j < columnsCount; j++) {
                if (j != column) {
                    minorMatrix[counterForRows][counterForColumns] = rows[i].getValue(j);
                    counterForColumns++;
                }
            }
            counterForRows++;
        }

        return new Matrix(minorMatrix).getDeterminant();
    }

    public double getDeterminant() {
        if (rows.length != getColumnsCount()) {
            throw new IllegalArgumentException("Matrix size must be m x m");
        }

        double determinant = 0;

        if (rows.length == 1) {
            return rows[0].getValue(0);
        }
        if (rows.length == 2) {
            return rows[0].getValue(0) * rows[1].getValue(1) - rows[0].getValue(1) * rows[1].getValue(0);
        }

        for (int i = 0; i < rows.length; i++) {
            determinant += rows[0].getValue(i) * Math.pow(-1, i) * getMinor(i);
        }

        return determinant;
    }

    public Vector multiplyByVector(Vector vector) {
        int columnsCount = getColumnsCount();

        if (vector.getSize() != columnsCount) {
            throw new IllegalArgumentException("Vector must be = " + columnsCount);
        }

        double[] components = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < columnsCount; j++) {
                components[i] += rows[i].getValue(j) * vector.getValue(j);
            }
        }

        return new Vector(components);
    }

    public void addMatrix(Matrix newMatrix) {
        if (rows.length != newMatrix.getRowsCount() || getColumnsCount() != newMatrix.getColumnsCount()) {
            throw new IllegalArgumentException("Matrices size must be equal");
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].addVector(newMatrix.rows[i]);
        }
    }

    public void subMatrix(Matrix newMatrix) {
        if (rows.length != newMatrix.getRowsCount() || getColumnsCount() != newMatrix.getColumnsCount()) {
            throw new IllegalArgumentException("Matrices size must be equal");
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].subVector(newMatrix.rows[i]);
        }
    }

    public static Matrix addMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Matrices size must be equal");
        }

        Matrix copyMatrix1 = new Matrix(matrix1);
        copyMatrix1.addMatrix(matrix2);
        return copyMatrix1;
    }

    public static Matrix subMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Matrices size must be equal");
        }

        Matrix copyMatrix1 = new Matrix(matrix1);
        copyMatrix1.subMatrix(matrix2);
        return copyMatrix1;
    }

    public static Matrix multiplyMatrix(Matrix matrix1, Matrix matrix2) {
        int matrix1RowsCount = matrix1.getRowsCount();
        int matrix1ColumnsCount = matrix1.getColumnsCount();
        int matrix2RowsCount = matrix2.getRowsCount();
        int matrix2ColumnsCount = matrix2.getColumnsCount();

        if (matrix1ColumnsCount != matrix2RowsCount) {
            throw new IllegalArgumentException("Matrix1 size must be m x n, matrix2 size must be n x k");
        }

        double[][] resultMatrix = new double[matrix1RowsCount][matrix2ColumnsCount];

        for (int i = 0; i < matrix1RowsCount; i++) {
            for (int j = 0; j < matrix2ColumnsCount; j++) {
                for (int k = 0; k < matrix2RowsCount; k++) {
                    resultMatrix[i][j] += matrix1.rows[i].getValue(k) * matrix2.getColumn(j).getValue(k);
                }
            }
        }

        return new Matrix(resultMatrix);
    }
}