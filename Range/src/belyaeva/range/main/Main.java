package belyaeva.range.main;

import belyaeva.range.task.Range;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(5, 12);
        Range range2 = new Range(2, 5);
        double number = 9.4;

        System.out.println("Length = " + range1.getLength());
        System.out.println("IsInside = " + range1.isInside(number));
        System.out.println();

        Range iRange = range2.getIntersection(range1);

        if (iRange != null) {
            System.out.println("IntersectionFrom = " + iRange.getFrom());
            System.out.println("IntersectionTo = " + iRange.getTo());
        } else {
            System.out.println("Intersection = " + null);
        }
        System.out.println();

        range1.setFrom(5);
        range1.setTo(12);

        Range[] uRange = range1.getUnion(range2);

        for (Range range : uRange) {
            System.out.println("UnionFrom = " + range.getFrom());
            System.out.println("UnionTo = " + range.getTo());
        }
        System.out.println();

        range1.setFrom(5);
        range1.setTo(12);

        Range[] dRange = range1.getDifference(range2);

        if (dRange.length == 0) {
            System.out.println("Difference = 0");
        } else {
            for (Range range : dRange) {
                System.out.println("DifferenceFrom = " + range.getFrom());
                System.out.println("DifferenceTo = " + range.getTo());
            }
        }
    }
}

