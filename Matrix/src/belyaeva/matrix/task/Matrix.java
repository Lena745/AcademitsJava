package belyaeva.matrix.task;

import belyaeva.vector.task.Vector;

public class Matrix {
    private Vector[] matrix;

    public Matrix(int n, int m) {
        matrix = new Vector[n];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new Vector(m);
        }
    }

    public Matrix(Matrix newMatrix) {
        this.matrix = newMatrix.matrix;
    }

    public Matrix(double[][] matrix) {
        Vector[] v = new Vector[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            v[i] = new Vector(matrix[i]);
        }
        this.matrix = v;
    }

    public Matrix(Vector[] v) {
        matrix = v;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{");

        for (int i = 0; i < matrix.length; i++) {
            if (i > 0) {
                s.append(", ");
            }
            s.append(matrix[i]);
        }
        s.append("}");

        return s.toString();
    }

    public int getStringSize() {
        return matrix.length;
    }

    public int getColumnsSize() {
        return matrix[0].getSize();
    }

    public void setStringVector(int stringIndex, Vector v) {
        if (stringIndex < 0 || stringIndex >= matrix.length) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < " + matrix.length);
        }
        if (v.getSize() != matrix[0].getSize()) {
            throw new IllegalArgumentException("Vector length must be = " + matrix[0].getSize());
        }
        matrix[stringIndex] = v;
    }

    public Vector getStringVector(int stringIndex) {
        if (stringIndex < 0 || stringIndex >= matrix.length) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < " + matrix.length);
        }
        return matrix[stringIndex];
    }

    public Vector getColumnVector(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= matrix[0].getSize()) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < " + matrix[0].getSize());
        }
        double[] columnComponents = new double[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            columnComponents[i] = matrix[i].getValue(columnIndex);
        }
        return new Vector(columnComponents);
    }

    public Matrix transpose() {
        Vector[] v = new Vector[matrix[0].getSize()];

        for (int i = 0; i < v.length; i++) {
            v[i] = getColumnVector(i);
        }
        return new Matrix(v);
    }

    public void multiplyMatrixByScalar(double scalar) {
        for (Vector vector : matrix) {
            vector.multiplyByScalar(scalar);
        }
    }

    private double getMinor(int column) {
        double[][] minorMatrix = new double[matrix.length - 1][matrix.length - 1];

        int stringCount = 0;
        int columnSize = matrix[0].getSize();

        for (int i = 0; i < matrix.length; i++) {
            if (i != 0) {
                int columnCount = 0;
                for (int j = 0; j < columnSize; j++) {
                    if (j != column) {
                        minorMatrix[stringCount][columnCount] = matrix[i].getValue(j);
                        columnCount++;
                    }
                }
                stringCount++;
            }
        }
        return new Matrix(minorMatrix).getDeterminant();
    }

    public double getDeterminant() {
        if (matrix.length != matrix[0].getSize()) {
            throw new IllegalArgumentException("Matrix size must be m x m");
        }
        double determinant = 0;

        if (matrix.length == 1) {
            return matrix[0].getValue(0);
        }
        if (matrix.length == 2) {
            return matrix[0].getValue(0) * matrix[1].getValue(1) - matrix[0].getValue(1) * matrix[1].getValue(0);
        }
        for (int i = 0; i < matrix.length; i++) {
            determinant += matrix[0].getValue(i) * Math.pow(-1, i) * getMinor(i);
        }
        return determinant;
    }

    public Vector multiplyByVector(Vector v) {
        if (v.getSize() != matrix.length) {
            throw new IllegalArgumentException("Vector must be = " + matrix.length);
        }
        double[] vectorArray = new double[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++)
                vectorArray[i] += matrix[i].getValue(j) * v.getValue(j);
        }
        return new Vector(vectorArray);
    }

    public void addMatrix(Matrix newMatrix) {
        if (matrix.length != newMatrix.getStringSize() || matrix[0].getSize() != newMatrix.getColumnsSize()) {
            throw new IllegalArgumentException("Matrices size must be equal");
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[i].addVector(newMatrix.getStringVector(i));
        }
    }

    public void subMatrix(Matrix newMatrix) {
        if (matrix.length != newMatrix.getStringSize() || matrix[0].getSize() != newMatrix.getColumnsSize()) {
            throw new IllegalArgumentException("Matrices size must be equal");
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[i].subVector(newMatrix.getStringVector(i));
        }
    }

    public static Matrix addMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getStringSize() != matrix2.getStringSize() || matrix1.getColumnsSize() != matrix2.getColumnsSize()) {
            throw new IllegalArgumentException("Matrices size must be equal");
        }
        Matrix copyMatrix1 = new Matrix(matrix1);
        copyMatrix1.addMatrix(matrix2);
        return copyMatrix1;
    }

    public static Matrix subMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getStringSize() != matrix2.getStringSize() || matrix1.getColumnsSize() != matrix2.getColumnsSize()) {
            throw new IllegalArgumentException("Matrices size must be equal");
        }
        Matrix copyMatrix1 = new Matrix(matrix1);
        copyMatrix1.subMatrix(matrix2);
        return copyMatrix1;
    }

    public static Matrix multiplyMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsSize() != matrix2.getStringSize()) {
            throw new IllegalArgumentException("Matrices column size must be equal");
        }
        double[] result = new double[matrix1.getStringSize()];
        Vector[] v = new Vector[matrix1.getStringSize()];

        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v.length; j++) {
                Vector vector1 = matrix1.getStringVector(i);
                Vector vector2 = matrix2.getColumnVector(j);
                result[j] = Vector.getScalarProduct(vector1, vector2);
            }
            v[i] = new Vector(result);
        }
        return new Matrix(v);
    }
}

