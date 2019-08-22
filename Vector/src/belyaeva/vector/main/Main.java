package belyaeva.vector.main;

import belyaeva.vector.task.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(3);
        Vector vector2 = new Vector(3, new double[]{2, 6, 4});
        Vector vector3 = new Vector(5, new double[]{6, 9, 5});
        Vector vector4 = new Vector(new double[]{1, 2, 3, 6, 0});
        Vector vector5 = new Vector(vector2);
        Vector vector6 = new Vector(5, new double[]{6, 9, 5});

        System.out.println("Vector(int n) = " + vector1);
        System.out.println("Vector(Vector v) = " + vector5);
        System.out.println("Vector(double[] vector) = " + vector4);
        System.out.println("Vector(int n, double[] vector) = " + vector3);
        System.out.println("getSize = " + vector4.getSize());
        vector2.addVector(vector4);
        System.out.println("addVector = " + vector2);
        vector4.subVector(vector3);
        System.out.println("subVector = " + vector4);

        double scalar = 0.9;

        vector2.multiplyByScalar(scalar);
        System.out.println("multiplyByScalar = " + vector2);

        vector5.revert();
        System.out.println("revert = " + vector5);
        System.out.println("getLength = " + vector2.getLength());
        vector4.setValue(2, 4);
        System.out.println("after set value = " + vector4);
        System.out.println("getValue = " + vector5.getValue(2));
        System.out.println("equals = " + vector3.equals(vector6));
        System.out.println(vector1.hashCode() == vector4.hashCode());

        System.out.println("static addVector = " + Vector.addVector(vector5, vector3));
        System.out.println("static subVector = " + Vector.subVector(vector6, vector4));
        System.out.println("static getScalarProduct = " + Vector.getScalarProduct(vector3, vector2));
    }
}
