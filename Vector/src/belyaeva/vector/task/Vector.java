package belyaeva.vector.task;

import java.util.Arrays;

public class Vector {
    private double[] vector;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Size must be > 0");
        }
        vector = new double[n];
    }

    public Vector(Vector v) {
        this(v.vector);
    }

    public Vector(double[] vector) {
        if (vector.length <= 0) {
            throw new IllegalArgumentException("Size must be > 0");
        }
        this.vector = vector;
    }

    public Vector(int n, double[] vector) {
        if (n <= 0 || vector.length <= 0) {
            throw new IllegalArgumentException("Size must be > 0");
        }
        this.vector = Arrays.copyOf(vector, n);
    }

    public int getSize() {
        return vector.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(vector);
    }

    public Vector addVector(Vector v) {
        double[] vectorRes = new double[Math.max(vector.length, v.vector.length)];
        for (int i = 0; i < vectorRes.length; i++) {
            for (int j = i; j < i + 1; j++) {
                if (j > Math.min(vector.length, v.vector.length) - 1) {
                    if (Math.max(vector.length, v.vector.length) == v.vector.length) {
                        vectorRes[i] = v.vector[i];
                    } else {
                        vectorRes[i] = vector[i];
                    }
                } else {
                    vectorRes[i] = vector[i] + v.vector[j];
                }
            }
        }
        return new Vector(vectorRes);
    }

    public Vector subVector(Vector v) {
        double[] vectorRes = new double[Math.max(vector.length, v.vector.length)];
        for (int i = 0; i < vectorRes.length; i++) {
            for (int j = i; j < i + 1; j++) {
                if (j > Math.min(vector.length, v.vector.length) - 1) {
                    if (Math.max(vector.length, v.vector.length) == v.vector.length) {
                        vectorRes[i] = 0 - v.vector[i];
                    } else {
                        vectorRes[i] = vector[i];
                    }
                } else {
                    vectorRes[i] = vector[i] - v.vector[j];
                }
            }
        }
        return new Vector(vectorRes);
    }

    public Vector multiplyByScalar(double scalar) {
        for (int i = 0; i < vector.length; i++) {
            vector[i] = vector[i] * scalar;
        }
        return new Vector(vector);
    }

    public Vector turn() {
        for (int i = 0; i < vector.length; i++) {
            vector[i] = vector[i] * -1;
        }
        return new Vector(vector);
    }

    public double getLength() {
        double lengthSquared = 0;
        for (double v : vector) {
            lengthSquared += v * v;
        }
        return Math.sqrt(lengthSquared);
    }

    public Vector setAndGetValue(int index, double value) {
        vector[index] = value;
        return new Vector(vector);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(vector);
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

        return Arrays.equals(vector, v.vector);
    }

    public static Vector addVector(Vector v1, Vector v2) {
        double[] vectorRes = new double[Math.max(v1.vector.length, v2.vector.length)];
        for (int i = 0; i < vectorRes.length; i++) {
            for (int j = i; j < i + 1; j++) {
                if (j > Math.min(v1.vector.length, v2.vector.length) - 1) {
                    if (Math.max(v1.vector.length, v2.vector.length) == v2.vector.length) {
                        vectorRes[i] = v2.vector[i];
                    } else {
                        vectorRes[i] = v1.vector[i];
                    }
                } else {
                    vectorRes[i] = v1.vector[i] + v2.vector[j];
                }
            }
        }
        return new Vector(vectorRes);
    }

    public static Vector subVector(Vector v1, Vector v2) {
        double[] vectorRes = new double[Math.max(v1.vector.length, v2.vector.length)];
        for (int i = 0; i < vectorRes.length; i++) {
            for (int j = i; j < i + 1; j++) {
                if (j > Math.min(v1.vector.length, v2.vector.length) - 1) {
                    if (Math.max(v1.vector.length, v2.vector.length) == v2.vector.length) {
                        vectorRes[i] = 0 - v2.vector[i];
                    } else {
                        vectorRes[i] = v1.vector[i];
                    }
                } else {
                    vectorRes[i] = v1.vector[i] - v2.vector[j];
                }
            }
        }
        return new Vector(vectorRes);
    }

    public static double getScalarProduct(Vector v1, Vector v2) {
        double result = 0;
        for (int i = 0; i < Math.min(v1.vector.length, v2.vector.length); i++) {
            for (int j = i; j < i + 1; j++) {
                result += v1.vector[i] * v2.vector[j];
            }
        }
        return result;
    }
}
