package belyaeva.vector.task;

import java.util.Arrays;

public class Vector {
    private double[] vectorArray;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Size must be > 0");
        }
        vectorArray = new double[n];
    }

    public Vector(Vector v) {
        this(v.vectorArray);
    }

    public Vector(double[] vectorArray) {
        this.vectorArray = Arrays.copyOf(vectorArray, vectorArray.length);
    }

    public Vector(int n, double[] vectorArray) {
        if (n <= 0) {
            throw new IllegalArgumentException("Size must be > 0");
        }
        this.vectorArray = Arrays.copyOf(vectorArray, n);
    }

    public int getSize() {
        return vectorArray.length;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{");
        for (int i = 0; i < vectorArray.length; i++) {
            if (i > 0) {
                s.append(", ");
            }
            s.append(vectorArray[i]);
        }
        s.append("}");
        return s.toString();
    }

    public Vector addVector(Vector v) {
        int maxVector = Math.max(vectorArray.length, v.vectorArray.length);
        int minVector = Math.min(vectorArray.length, v.vectorArray.length);
        for (int i = 0, j = i; i < maxVector; i++, j++) {
            if (maxVector == v.vectorArray.length) {
                if (j > minVector - 1) {
                    break;
                } else {
                    v.vectorArray[i] = vectorArray[i] + v.vectorArray[j];
                }
            } else {
                if (j > minVector - 1) {
                    break;
                } else {
                    vectorArray[i] = vectorArray[i] + v.vectorArray[j];
                }
            }
        }
        if (maxVector == v.vectorArray.length) {
            return v;
        }
        return this;
    }

    public Vector subVector(Vector v) {
        int maxVectorArray = Math.max(vectorArray.length, v.vectorArray.length);
        int minVectorArray = Math.min(vectorArray.length, v.vectorArray.length);
        for (int i = 0, j = i; i < maxVectorArray; i++, j++) {
            if (maxVectorArray == v.vectorArray.length) {
                if (j > minVectorArray - 1) {
                    v.vectorArray[i] = 0 - v.vectorArray[i];
                } else {
                    v.vectorArray[i] = vectorArray[i] - v.vectorArray[j];
                }
            } else {
                if (j > minVectorArray - 1) {
                    break;
                } else {
                    vectorArray[i] = vectorArray[i] - v.vectorArray[j];
                }
            }
        }
        if (maxVectorArray == v.vectorArray.length) {
            return v;
        }
        return this;
    }

    public Vector multiplyByScalar(double scalar) {
        for (int i = 0; i < vectorArray.length; i++) {
            vectorArray[i] *= scalar;
        }
        return this;
    }

    public Vector revert() {
        multiplyByScalar(-1);
        return this;
    }

    public double getLength() {
        double squaredLength = 0;
        for (double v : vectorArray) {
            squaredLength += v * v;
        }
        return Math.sqrt(squaredLength);
    }

    public void setValue(int index, double value) {
        if (index >= vectorArray.length) {
            throw new IllegalArgumentException("Index must be < " + vectorArray.length);
        }
        vectorArray[index] = value;
    }

    public double getValue(int index) {
        return vectorArray[index];
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(vectorArray);
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

        return Arrays.equals(vectorArray, v.vectorArray);
    }

    public static Vector addVector(Vector v1, Vector v2) {
        return new Vector(v1.addVector(v2));
    }

    public static Vector subVector(Vector v1, Vector v2) {
        return new Vector(v1.subVector(v2));
    }

    public static double getScalarProduct(Vector v1, Vector v2) {
        double result = 0;
        int minVectorArray = Math.min(v1.vectorArray.length, v2.vectorArray.length);
        for (int i = 0, j = i; i < minVectorArray; i++, j++) {
            result += v1.vectorArray[i] * v2.vectorArray[j];
        }
        return result;
    }
}
