package belyaeva.shapes.main;

import belyaeva.shapes.task.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape square1 = new Square(3);
        Shape triangle1 = new Triangle(1, 1, 3, 2, 5, 2);
        Shape rectangle1 = new Rectangle(3, 5);
        Shape circle1 = new Circle(1.5);
        Shape square2 = new Square(3.5);
        Shape triangle2 = new Triangle(1, 2, 6, 4, 8, 1);
        Shape rectangle2 = new Rectangle(2.5, 3);
        Shape circle2 = new Circle(3);
        Shape circle3 = new Circle(3);

        Shape[] shapes = new Shape[]{square1, triangle1, rectangle1, circle1, square2, triangle2, rectangle2, circle2};

        Arrays.sort(shapes, new AreaComparator());
        System.out.println("Max shape area: " + shapes[shapes.length - 1]);

        Arrays.sort(shapes, new PerimeterComparator());
        System.out.println("Max-1 shape perimeter: " + shapes[shapes.length - 2]);
        System.out.println();

        System.out.println(circle2.equals(circle3));

        System.out.println(circle1.hashCode() == circle3.hashCode());
    }
}