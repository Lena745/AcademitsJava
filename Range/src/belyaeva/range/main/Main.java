package belyaeva.range.main;

import belyaeva.range.task.Range;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(5, 12);
        Range range2 = new Range(3, 17.6);
        double number = 9.4;

        System.out.println("Length = " + range1.getLength());
        System.out.println("IsInside = " + range1.isInside(number));
        System.out.printf("Distance = %.2f%n", range1.getDistance(range2));
        System.out.println();

        Range iRange = range1.getIntersection(range2);

        if (iRange != null) {
            System.out.println("IntersectionFrom = " + iRange.getFrom());
            System.out.println("IntersectionTo = " + iRange.getTo());
        } else {
            System.out.println("Intersection = " + null);
        }
        System.out.println();

        range1.setFrom(5);
        range1.setTo(12);

        Range uRange = range1.getUnion(range2);

        System.out.println("UnionFrom = " + uRange.getFrom());
        System.out.println("UnionTo = " + uRange.getTo());
        System.out.println();

        range1.setFrom(5);
        range1.setTo(12);

        Range[] dRange = range1.getDifference(range2);

        for (Range range : dRange) {
            if (range != null) {
                System.out.println("DifferenceFrom = " + range.getFrom());
                System.out.println("DifferenceTo = " + range.getTo());
            } else {
                System.out.println("Difference = " + null);
            }
        }
    }
}
