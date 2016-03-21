package org.pek.tuple;

final public class Tuple3<T0, T1, T2> extends Tuple implements Comparable<Tuple3<T0, T1, T2>> {

    private static final Integer LENGTH = 3;

    private Tuple3() {
        super(LENGTH);
    }

    public static <T0, T1, T2> Tuple3<T0, T1, T2> of (T0 e0, T1 e1, T2 e2) {
        Tuple3<T0, T1, T2> t = new Tuple3<>();
        t.append(e0);
        t.append(e1);
        t.append(e2);
        return t;
    }

    @Override
    public Integer length() {
        return LENGTH;
    }

    // -----------------------------------

    /**
     * @throws ClassCastException when T0, T1, T2 are not Comparable
     */
    @Override
    public int compareTo (final Tuple3<T0, T1, T2> other) {
        return compareToTuple(other);
    }

}
