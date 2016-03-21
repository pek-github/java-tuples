package org.pek.tuple;

final public class Tuple1<T0> extends Tuple implements Comparable<Tuple1<T0>> {

    private static final Integer LENGTH = 1;

    private Tuple1() {
        super(LENGTH);
    }

    public static <T0> Tuple1<T0> of (T0 e0) {
        Tuple1<T0> t = new Tuple1<>();
        t.append(e0);
        return t;
    }

    @Override
    public Integer length() {
        return LENGTH;
    }
    // -----------------------------------

    public <E> E get () {
        return get(0);
    }

    /**
     * @throws ClassCastException when T0 is not Comparable
     */
    @Override
    public int compareTo (final Tuple1<T0> other) {
        return compareToTuple(other);
    }

}
