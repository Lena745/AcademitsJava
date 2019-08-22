package belyaeva.vector.task;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Size must be > 0");
        }
        components = new double[n];
    }

    public Vector(Vector v) {
        this(v.components);
    }

    public Vector(double[] components) {
        if (components.length <= 0) {
            throw new IllegalArgumentException("Array length must be > 0");
        }
        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int n, double[] components) {
        if (n <= 0) {
            throw new IllegalArgumentException("Size must be > 0");
        }
        this.components = Arrays.copyOf(components, n);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{");

        for (int i = 0; i < components.length; i++) {
            if (i > 0) {
                s.append(", ");
            }
            s.append(components[i]);
        }
        s.append("}");
        return s.toString();
    }

    private double[] getMaxVector(Vector v) {
        if (components.length >= v.components.length) {
            return this.components;
        }
        return v.components;
    }

    private double[] getMinVector(Vector v) {
        if (components.length < v.components.length) {
            return this.components;
        }
        return v.components;
    }

    public void addVector(Vector v) {
        int minVectorLength = getMinVector(v).length;
        int maxVectorLength = getMaxVector(v).length;

        if (v.components == getMaxVector(v)) {
            this.components = Arrays.copyOf(components, maxVectorLength);
            minVectorLength = maxVectorLength;
        }

        for (int i = 0; i < minVectorLength; i++) {
            components[i] += v.components[i];
        }
    }

    public void subVector(Vector v) {
        int maxVectorLength = getMaxVector(v).length;
        int minVectorLength = getMinVector(v).length;

        if (v.components == getMaxVector(v)) {
            this.components = Arrays.copyOf(components, maxVectorLength);
            minVectorLength = maxVectorLength;
        }

        for (int i = 0; i < minVectorLength; i++) {
            components[i] -= v.components[i];
        }
    }


    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void revert() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double squaredLength = 0;

        for (double v : components) {
            squaredLength += v * v;
        }
        return Math.sqrt(squaredLength);
    }

    public void setValue(int index, double value) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < " + components.length);
        }
        components[index] = value;
    }

    public double getValue(int index) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < " + components.length);
        }
        return components[index];
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Vector v = (Vector) o;

        return Arrays.equals(components, v.components);
    }

    public static Vector addVector(Vector v1, Vector v2) {
        Vector copyV1 = new Vector(v1);
        copyV1.addVector(v2);
        return copyV1;
    }

    public static Vector subVector(Vector v1, Vector v2) {
        Vector copyV1 = new Vector(v1);
        copyV1.subVector(v2);
        return copyV1;
    }

    public static double getScalarProduct(Vector v1, Vector v2) {
        double result = 0;
        int minVectorLength = v1.getMinVector(v2).length;

        for (int i = 0; i < minVectorLength; i++) {
            result += v1.components[i] * v2.components[i];
        }
        return result;
    }
}
