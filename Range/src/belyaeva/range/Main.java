package belyaeva.range;

public class Main {
    public static void main(String[] args) {
        Range range= new Range(7.2, 15);
        double number=9.4;

        System.out.println(range.getLength());
        System.out.println(range.isInside(number));
    }
}
