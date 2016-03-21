package org.pek.tuple;

final public class Tuple2<T0, T1> extends Tuple implements Comparable<Tuple2<T0, T1>> {

    private static final Integer LENGTH = 2;

    private Tuple2() {
        super(LENGTH);
    }

    public static <T0, T1> Tuple2<T0, T1> of (T0 e0, T1 e1) {
        Tuple2<T0, T1> t = new Tuple2<>();
        t.append(e0);
        t.append(e1);
        return t;
    }

    @Override
    public Integer length() {
        return LENGTH;
    }
    // -----------------------------------

    public <E> E first() {
        return get(0);
    }

    public <E> E second() {
        return get(1);
    }

    // -----------------------------------

    /**
     * @throws ClassCastException when T0, T1 are not Comparable
     */
    @Override
    public int compareTo (final Tuple2<T0, T1> other) {
        return compareToTuple(other);
    }

}
