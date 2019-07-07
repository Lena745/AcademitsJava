package belyaeva.range.main;

import belyaeva.range.task.Range;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(7.2, 15);
        double number = 9.4;

        System.out.println(range.getLength());
        System.out.println(range.isInside(number));

        range.setFrom(6.5);
        range.setTo(10.8);

        System.out.println(range.getFrom());
        System.out.println(range.getTo());
    }
}
