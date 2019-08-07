package belyaeva.matrix.task;

import belyaeva.vector.task.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] matrixRows;

    public Matrix(int rows, int columns) {
        if (rows <= 0) {
            throw new IllegalArgumentException("Matrix size must be > 0");
        }
        matrixRows = new Vector[rows];

        for (int i = 0; i < matrixRows.length; i++) {
            matrixRows[i] = new Vector(columns);
        }
    }

    public Matrix(Matrix matrix) {
        this.matrixRows = Arrays.copyOf(matrix.matrixRows, matrix.matrixRows.length);
    }

    public Matrix(double[][] matrix) {
        if (matrix.length <= 0) {
            throw new IllegalArgumentException("double[][] size must be > 0");
        }
        Vector[] v = new Vector[matrix.length];
        int max = 0;

        for (double[] doubles : matrix) {
            if (doubles.length > max) {
                max = doubles.length;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            v[i] = new Vector(max, matrix[i]);
        }
        this.matrixRows = v;
    }

    public Matrix(Vector[] v) {
        if (v.length <= 0) {
            throw new IllegalArgumentException("Vector[] size must be > 0");
        }
        int max = 0;

        for (Vector vector : v) {
            if (vector.getSize() > max) {
                max = vector.getSize();
            }
        }
        for (Vector vector : v) {
            if (vector.getSize() < max) {
                vector.addVector(new Vector(max));
            }
        }
        matrixRows = Arrays.copyOf(v, v.length);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{");

        for (int i = 0; i < matrixRows.length; i++) {
            if (i > 0) {
                s.append(", ");
            }
            s.append(matrixRows[i]);
        }
        s.append("}");

        return s.toString();
    }

    public int getRowsCount() {
        return matrixRows.length;
    }

    public int getColumnsCount() {
        return matrixRows[0].getSize();
    }

    public void setRowVector(int rowIndex, Vector v) {
        if (rowIndex < 0 || rowIndex >= matrixRows.length) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < " + matrixRows.length);
        }
        if (v.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Vector length must be = " + getColumnsCount());
        }
        matrixRows[rowIndex] = new Vector(v);
    }

    public Vector getRowVector(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= matrixRows.length) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < " + matrixRows.length);
        }
        return new Vector(matrixRows[rowIndex]);
    }

    public Vector getColumnVector(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < " + getColumnsCount());
        }
        double[] columnComponents = new double[matrixRows.length];

        for (int i = 0; i < matrixRows.length; i++) {
            columnComponents[i] = matrixRows[i].getValue(columnIndex);
        }
        return new Vector(columnComponents);
    }

    public void transpose() {
        Vector[] v = new Vector[getColumnsCount()];

        for (int i = 0; i < v.length; i++) {
            v[i] = getColumnVector(i);
        }
        matrixRows = v;
    }

    public void multiplyMatrixByScalar(double scalar) {
        for (Vector vector : matrixRows) {
            vector.multiplyByScalar(scalar);
        }
    }

    private double getMinor(int column) {
        double[][] minorMatrix = new double[matrixRows.length - 1][matrixRows.length - 1];

        int rowsCount = 0;
        int columnsLength = getColumnsCount();

        for (int i = 1; i < matrixRows.length; i++) {
            int columnsCount = 0;
            for (int j = 0; j < columnsLength; j++) {
                if (j != column) {
                    minorMatrix[rowsCount][columnsCount] = matrixRows[i].getValue(j);
                    columnsCount++;
                }
            }
            rowsCount++;
        }
        return new Matrix(minorMatrix).getDeterminant();
    }

    public double getDeterminant() {
        if (matrixRows.length != getColumnsCount()) {
            throw new IllegalArgumentException("Matrix size must be m x m");
        }
        double determinant = 0;

        if (matrixRows.length == 1) {
            return matrixRows[0].getValue(0);
        }
        if (matrixRows.length == 2) {
            return matrixRows[0].getValue(0) * matrixRows[1].getValue(1) - matrixRows[0].getValue(1) * matrixRows[1].getValue(0);
        }
        for (int i = 0; i < matrixRows.length; i++) {
            determinant += matrixRows[0].getValue(i) * Math.pow(-1, i) * getMinor(i);
        }
        return determinant;
    }

    public Vector multiplyByVector(Vector v) {
        int columnsLength = getColumnsCount();

        if (v.getSize() != columnsLength) {
            throw new IllegalArgumentException("Vector must be = " + columnsLength);
        }
        double[] components = new double[matrixRows.length];

        for (int i = 0; i < matrixRows.length; i++) {
            for (int j = 0; j < columnsLength; j++) {
                components[i] += matrixRows[i].getValue(j) * v.getValue(j);
            }
        }
        return new Vector(components);
    }

    public void addMatrix(Matrix newMatrix) {
        if (matrixRows.length != newMatrix.getRowsCount() || getColumnsCount() != newMatrix.getColumnsCount()) {
            throw new IllegalArgumentException("Matrices size must be equal");
        }
        for (int i = 0; i < matrixRows.length; i++) {
            matrixRows[i].addVector(newMatrix.getRowVector(i));
        }
    }

    public void subMatrix(Matrix newMatrix) {
        if (matrixRows.length != newMatrix.getRowsCount() || getColumnsCount() != newMatrix.getColumnsCount()) {
            throw new IllegalArgumentException("Matrices size must be equal");
        }
        for (int i = 0; i < matrixRows.length; i++) {
            matrixRows[i].subVector(newMatrix.getRowVector(i));
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
        int matrix1RowsLength = matrix1.getRowsCount();
        int matrix1ColumnsLength = matrix1.getColumnsCount();
        int matrix2RowsLength = matrix2.getRowsCount();
        int matrix2ColumnsLength = matrix2.getColumnsCount();

        if (matrix1ColumnsLength != matrix2RowsLength) {
            throw new IllegalArgumentException("Matrices columns size must be equal");
        }

        double[][] resultMatrix = new double[matrix1RowsLength][matrix2ColumnsLength];

        for (int i = 0; i < matrix1RowsLength; i++) {
            for (int j = 0; j < matrix2ColumnsLength; j++) {
                for (int k = 0; k < matrix2RowsLength; k++) {
                    resultMatrix[i][j] += matrix1.getRowVector(i).getValue(k) * matrix2.getColumnVector(j).getValue(k);
                }
            }
        }
        return new Matrix(resultMatrix);
    }
}