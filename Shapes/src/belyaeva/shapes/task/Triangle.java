package belyaeva.shapes.task;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    private static double getLength(double a, double b, double c) {
        return Math.max(a, Math.max(b, c)) - Math.min(a, Math.min(b, c));
    }

    @Override
    public double getWidth() {
        return getLength(x1, x2, x3);
    }

    @Override
    public double getHeight() {
        return getLength(y1, y2, y3);
    }

    private static double getDistance(double beginX, double beginY, double endX, double endY) {
        return Math.sqrt(Math.pow(endX - beginX, 2) + Math.pow(endY - beginY, 2));
    }

    @Override
    public double getPerimeter() {
        return getDistance(x1, y1, x2, y2) + getDistance(x2, y2, x3, y3) + getDistance(x1, y1, x3, y3);
    }

    @Override
    public double getArea() {
        double side1 = getDistance(x1, y1, x2, y2);
        double side2 = getDistance(x2, y2, x3, y3);
        double side3 = getDistance(x1, y1, x3, y3);
        double halfPerimeter = getPerimeter() / 2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - side1) * (halfPerimeter - side2) * (halfPerimeter - side3));
    }

    @Override
    public String toString() {
        return "Triangle: (x1, y1) = " + "(" + x1 + ", " + y1 + "), " + "(x2, y2) = " + "(" + x2 + ", " + y2 + "), " + "(x3, y3) = " + "(" + x3 + ", " + y3 + "), " + "area = " + getArea() + ", perimeter = " + getPerimeter();
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Triangle t = (Triangle) o;

        return x1 == t.x1 && y1 == t.y1 && x2 == t.x2 && y2 == t.y2 && x3 == t.x3 && y3 == t.y3;
    }
}
