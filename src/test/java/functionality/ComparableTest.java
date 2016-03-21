package functionality;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.pek.tuple.Tuple2;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ComparableTest {

    @Test
    public void test01WhenComparedToNullThenCmp1() {
        Tuple2<String, Integer> t1 = Tuple2.of("something", 100);
        Tuple2<String, Integer> t2 = null;

        assertEquals("must hold: t1.cmpTo (t2) == 1", t1.compareTo(t2), 1);
    }

    @Test
    public void test02WhenComparedToSameTypesSameDataThenCmp0() {
        Tuple2<String, Integer> t1 = Tuple2.of("something", 100);
        Tuple2<String, Integer> t2 = Tuple2.of("some" + "thing", 10 * 10);

        assertEquals("must hold: t1.cmpTo (t2) == 0", t1.compareTo(t2), 0);
    }

    @Test
    public void test03WhenComparedToSameTypesDifferentDataAndFirstGreaterThenCmp1() {
        Tuple2<String, Integer> t1 = Tuple2.of("thing", 100);
        Tuple2<String, Integer> t2 = Tuple2.of("something", 100);

        assertEquals("must hold: t1.cmpTo (t2) == 1", t1.compareTo(t2), 1);
    }

    @Test
    public void test04WhenComparedToSameTypesDifferentDataAndFirstLessThenCmpNeg1() {
        Tuple2<String, Integer> t1 = Tuple2.of("something", 100);
        Tuple2<String, Integer> t2 = Tuple2.of("thing", 100);

        assertEquals("must hold: t1.cmpTo (t2) == -1", t1.compareTo(t2), -1);
    }

    @Test
    public void test05WhenComparedToSameTypesDifferentDataAndSecondGreaterThenCmp1() {
        Tuple2<String, Integer> t1 = Tuple2.of("something", 100);
        Tuple2<String, Integer> t2 = Tuple2.of("something", 10);

        assertEquals("must hold: t1.cmpTo (t2) == 1", t1.compareTo(t2), 1);
    }

    @Test
    public void test06WhenComparedToSameTypesDifferentDataAndSecondLessThenCmpNeg1() {
        Tuple2<String, Integer> t1 = Tuple2.of("something", 10);
        Tuple2<String, Integer> t2 = Tuple2.of("something", 100);

        assertEquals("must hold: t1.cmpTo (t2) == -1", t1.compareTo(t2), -1);
    }

    @Test(expected = ClassCastException.class)
    public void test07WhenSameCustomTypesWithoutImplementingComparableAndEqualDataThenClassCastException() {

        class A {
            private int number;

            A (int n) { number = n; }
        }

        Tuple2<String, A> t1 = Tuple2.of("something", new A(100));
        Tuple2<String, A> t2 = Tuple2.of("something", new A(100));

        /* ClassCastException because A does not implement Comparable */
        t1.compareTo(t2);
    }

    @Test
    public void test08WhenSameCustomTypesImplementingComparableAndEqualDataThenCmp0() {

        class A implements Comparable<A> {
            private int number;

            A (int n) { number = n; }

            @Override
            public int compareTo (A other) {
                if (other == null) { return 1; }

                return ((Integer) this.number).compareTo(other.number);
            }
        }

        Tuple2<String, A> t1 = Tuple2.of("something", new A(100));
        Tuple2<String, A> t2 = Tuple2.of("something", new A(100));

        assertEquals("must hold: t1.cmpTo (t2) == 0", t1.compareTo(t2), 0);
    }

/*
Gracefully, we have compile-time errors when trying to compare things, such as:
(-) Tuple2<String, Integer> and Tuple2<String, String>
(-) Tuple2<String, Integer> and Tuple2<String, Number> (note: Integer implements Number)
(-) Tuple2<String, A> and Tuple2<String, B> where A, B custom classes, such that:
    A implements Comparable<A>, and
    B extends A implements Comparable<A>
(-) Tuple1<String> and Tuple2<String, String>
*/

}
