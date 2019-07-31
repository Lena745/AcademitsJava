package belyaeva.matrix.main;

import belyaeva.matrix.task.Matrix;
import belyaeva.vector.task.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(new Vector[]{new Vector(new double[]{1, 3, 4}), new Vector(new double[]{5, 7, 9}), new Vector(new double[]{6, 8, 2})});
        System.out.println("Matrix(Vector[] = " + matrix1);

        Matrix matrix2 = new Matrix(4, 2);
        System.out.println("Matrix(n, m) = " + matrix2);

        matrix2.setStringVector(0, new Vector(new double[]{2, 5}));
        matrix2.setStringVector(1, new Vector(new double[]{6, 8}));
        matrix2.setStringVector(2, new Vector(new double[]{1, 3}));
        matrix2.setStringVector(3, new Vector(new double[]{4, 6}));

        System.out.println("setStringVector = " + matrix2);
        System.out.println("getStringVector = " + matrix2.getStringVector(2));
        System.out.println("getColumnVector = " + matrix2.getColumnVector(0));

        Matrix matrix5 = matrix2.transpose();
        System.out.println("transpose() = " + matrix5);

        Matrix matrix3 = new Matrix(matrix2);
        System.out.println("Matrix(Matrix) = " + matrix3);

        double scalar = 3;

        matrix2.multiplyMatrixByScalar(scalar);
        System.out.println("multiplyMatrixByScalar = " + matrix2);

        System.out.println("multiplyByVector = " + matrix1.multiplyByVector(new Vector(new double[]{2, 5, 3})));

        Matrix matrix4 = new Matrix(new Vector[]{new Vector(new double[]{2, 5}), new Vector(new double[]{6, 8}), new Vector(new double[]{1, 3}), new Vector(new double[]{4, 6})});
        System.out.println("Matrix(Vector[]) = " + matrix4);

        matrix4.addMatrix(matrix3);
        System.out.println("addMatrix = " + matrix4);

        matrix4.subMatrix(matrix3);
        System.out.println("subMatrix = " + matrix4);

        System.out.println("static multiplyMatrix = " + Matrix.multiplyMatrix(matrix4, matrix5));
        System.out.println("static addMatrix = " + Matrix.addMatrix(matrix4, matrix3));
        System.out.println("static subMatrix = " + Matrix.subMatrix(matrix4, matrix3));
        System.out.println("getStringSize = " + matrix4.getStringSize());
        System.out.println("getColumnsSize() = " + matrix4.getColumnsSize());

        Matrix matrix6 = new Matrix(new double[][]{{2, 6, 4, 5, 1}, {8, 1, 4, 3, 2}, {5, 7, 6, 8, 9}, {1, 4, 2, 3, 6}, {2, 9, 7, 8, 5}});
        System.out.println("Matrix(double[][] = " + matrix6);

        Matrix matrix7 = new Matrix(new Vector[]{new Vector(new double[]{2})});
        System.out.println("getDeterminant() = " + matrix7.getDeterminant());
    }
}
