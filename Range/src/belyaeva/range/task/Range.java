package belyaeva.range.task;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        if (range.from >= to || range.to <= from) {
            return null;
        }
        if (range.from >= from && range.to <= to) {
            return new Range(range.from, range.to);
        }
        if (range.from < from && range.to > to) {
            return new Range(from, to);
        }
        if (range.from < from && range.to <= to) {
            return new Range(from, range.to);
        }
        return new Range(range.from, to);
    }

    public Range[] getUnion(Range range) {
        if (range.from > to || range.to < from) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }
        if (range.from >= from && range.to <= to) {
            return new Range[]{new Range(from, to)};
        }
        if (range.from < from && range.to > to) {
            return new Range[]{new Range(range.from, range.to)};
        }
        if (range.from < from && range.to <= to) {
            return new Range[]{new Range(range.from, to)};
        }
        return new Range[]{new Range(from, range.to)};
    }

    public Range[] getDifference(Range range) {
        if (range.from > to || range.to < from) {
            return new Range[]{new Range(from, to)};
        }
        if (range.from >= from && range.to <= to) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }
        if (range.from < from && range.to > to) {
            return new Range[]{};
        }
        if (range.from < from && range.to <= to) {
            return new Range[]{new Range(range.to, to)};
        }
        return new Range[]{new Range(from, range.from)};
    }
}
