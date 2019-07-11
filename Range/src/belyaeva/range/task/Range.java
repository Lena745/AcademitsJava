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

    public double getDistance(Range r) {
        return Math.sqrt(Math.pow((from - r.from), 2) + Math.pow((to - r.to), 2));
    }

    public Range getIntersection(Range r) {
        if (r.from > to || r.to < from) {
            return null;
        }
        if (isInside(r.from)) {
            from = r.from;
        }
        if (isInside(r.to)) {
            to = r.to;
        }
        return new Range(from, to);
    }

    public Range getUnion(Range r) {
        if (!isInside(r.from) && r.from < from) {
            from = r.from;
        }
        if (!isInside(r.to) && r.to > to) {
            to = r.to;
        }
        return new Range(from, to);
    }

    public Range[] getDifference(Range r) {
        double checkFrom = from;
        double checkTo = to;
        Range forCheck = new Range(checkFrom, checkTo);
        Range[] range = new Range[2];

        if (getIntersection(r) == null) {
            range[0] = forCheck;
        } else if (from > forCheck.from && to < forCheck.to) {
            double from1 = to + 0.1;
            double to1 = checkTo;
            checkTo = from - 0.1;
            range[0] = new Range(checkFrom, checkTo);
            range[1] = new Range(from1, to1);
        } else if (from > forCheck.from) {
            checkTo = from - 0.1;
            range[0] = new Range(checkFrom, checkTo);
        } else if (to < forCheck.to) {
            checkFrom = to + 0.1;
            range[0] = new Range(checkFrom, checkTo);
        }
        return range;
    }
}
