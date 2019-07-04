package belyaeva.range;

class Range {
    private double from;
    private double to;

    Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    double getLength() {
        return to - from;
    }

    boolean isInside(double number) {
        return number >= from && number <= to;
    }
}
