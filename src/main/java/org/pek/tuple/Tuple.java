package org.pek.tuple;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

abstract class Tuple {

    private static final Integer MAX_ALLOWED_TUPLE_LENGTH = 12;

    private final ArrayList<Object> elements;

    /**
     * @param length the desired Tuple length - it must be an integer in range [1, 12]
     * @throws IllegalArgumentException when the provided capacity is illegal
     */
    protected Tuple (Integer length) {
        guardConstructorLength(length);

        elements = new ArrayList<>(length);
    }

    private void guardConstructorLength (Integer maxLength) {
        if (maxLength == null
            || maxLength < 1
            || MAX_ALLOWED_TUPLE_LENGTH.compareTo(maxLength) < 0) {

            String template = "Tuple maximum length must be in closed range: [1, %d]";
            String msg = String.format(template, MAX_ALLOWED_TUPLE_LENGTH);
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * @param element The element to put into the tuple
     * @throws IllegalStateException when the tuple is already full
     */
    protected final void append (Object element) {
        guardLength();

        elements.add(element);
    }

    private void guardLength() {
        int len = this.length();

        if (elements.size() >= len) {
            String msg = "Tuple is full. Length is: " + len;
            throw new IllegalStateException(msg);
        }
    }

    // ----------------------------------------------------

    /**
     * @param index The index of the tuple element to return
     * @param <E> The type of the tuple element to return - it could be any of T0, T1, ...
     * @return The tuple element of the provided index
     * @throws IndexOutOfBoundsException when the provided index is out of range.
     * The proper closed-open range is [0, Z), where Z is the "Tuple Length"
     */
    @SuppressWarnings("unchecked")
    public final <E> E get(Integer index) {
        guardGet(index);

        return (E) elements.get(index);
    }

    private void guardGet (Integer index) {
        int len = this.length();

        if (index < 0 || index >= len) {
            String template = "Tuple index must be in closed range: [0, %d]";
            String msg = String.format(template, len - 1);
            throw new IndexOutOfBoundsException(msg);
        }
    }

    /**
     * Must be overridden to return a correct value
     * @return The tuple length
     */
    public abstract Integer length();

    // ----------------------------------------------------

    /**
     * A meaningful representation of the object
     * @implNote Similar to tuple in Python, Scala
     * @return A String representation of the object
     */
    @Override
    public final String toString() {
        return
            elements.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", ", "(", ")"));
    }

    /**
     * The equals() contract of such objects
     * @apiNote Now tuples can be fully used in data structures, such as Set, Map
     * @return \a true when the objects are equal; \a false otherwise
     */
    @Override
    public final boolean equals (final Object other) {
        if (this == other) { return true; }

        if (other == null
            || this.getClass() != other.getClass()) {

            return false;
        }

        @SuppressWarnings("unchecked")
        Tuple that = (Tuple) other;

        if (!this.length().equals(that.length())) { return false; }

        for (int i = 0; i < this.length(); i++) {
            if (!Objects.equals(this.get(i), that.get(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * The hashcode of such objects
     * @apiNote Now tuples can be fully used in data structures, such as Set, Map
     * @return The hashcode
     */
    @Override
    public final int hashCode() {
        final BinaryOperator<Integer> accumulator =
                (acc, h) -> 31 * acc + h;

        return
            elements.stream()
                    .map(Objects::hashCode)
                    .reduce(17, accumulator);
    }

    // ----------------------------------------------------

    /**
     * Compares this tuple to some other tuple
     * @apiNote Child classes should use this method, but not modify or override it
     * @apiNote Now tuples can be effectively used in Java 8 data structures, such as Set, Map
     * @implNote When comparing to a 'null' other tuple, 'this' is greater
     * @param other The other tuple to compare to
     * @throws ClassCastException when an element of either 'this' or the 'other' tuple is not Comparable
     */
    protected final Integer compareToTuple (final Tuple other) {
        if (other == null) { return 1; }

        for (int i = 0; i < this.length(); i++) {
            Comparable thisElement = this.get(i);
            Comparable otherElement = other.get(i);
            Integer cmp = compareElements(thisElement, otherElement);

            if (cmp != 0) {
                return cmp;
            }
        }

        return 0;
    }

    @SuppressWarnings("unchecked")
    private Integer compareElements (final Comparable a, final Comparable b) {
        if (a == null) {
            return (b == null) ? 0 : -1;
        } else {
            return (b == null) ? 1 : a.compareTo(b);
        }
    }

}
