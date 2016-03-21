package functionality;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.pek.tuple.Tuple1;
import org.pek.tuple.Tuple2;

import java.util.Objects;

import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EqualsTest {

    @Test
    public void test01WhenComparedToNullThenNotEqual() {
        Tuple2<String, Integer> t1 = Tuple2.of("something", 100);
        Tuple2<String, Integer> t2 = null;

        assertTrue("must hold: t1 not equals t2 (null)", !t1.equals(t2));
        assertTrue("must hold: t2 (null) not equals t1", !Objects.equals(t2, t1));
    }

    @Test
    public void test02WhenSameObjectsThenEqual() {
        Tuple2<String, Integer> t1 = Tuple2.of("something", 100);
        Tuple2<String, Integer> t2 = t1;

        assertTrue("must hold: t1 equals t2", t1.equals(t2));
        assertTrue("must hold: t2 equals t1", t2.equals(t1));
    }

    @Test
    public void test03WhenSameTypesSameDataThenEqual() {
        Tuple2<String, Integer> t1 = Tuple2.of("something", 100);
        Tuple2<String, Integer> t2 = Tuple2.of("some" + "thing", 10 * 10);

        assertTrue("must hold: t1 equals t2", t1.equals(t2));
        assertTrue("must hold: t2 equals t1", t2.equals(t1));
    }

    @Test
    public void test04WhenSameRuntimeTypesSameDataThenEqual() {
        Tuple2<String, Integer> t1 = Tuple2.of("something", 100);
        Tuple2<CharSequence, Integer> t2 = Tuple2.of("something", 100);

        assertTrue("must hold: t1 equals t2", t1.equals(t2));
        assertTrue("must hold: t2 equals t1", t2.equals(t1));
    }

    @Test
    public void test05WhenDifferentTypesDespiteInheritanceThenUnequal() {

        class A {
            private long number;

            A (long n) { number = n; }
        }

        class B extends A {
            private int num;

            B (int n) {
                super (n);
                num = n;
            }
        }

        Tuple2<A, Integer> t1 = Tuple2.of(new A(10L), 999);
        Tuple2<B, Integer> t2 = Tuple2.of(new B(10), 999);

        assertTrue("must hold: t1 not equals t2", !t1.equals(t2));
        assertTrue("must hold: t2 not equals t1", !t2.equals(t1));
    }

    @Test
    public void test06WhenDifferentTypesDespiteMinimalInheritanceThenUnequal() {

        class A {
            private int number;

            A (int n) { number = n; }
        }

        class B extends A {
            B (int n) { super (n); }
        }

        Tuple2<A, Integer> t1 = Tuple2.of(new A(10), 999);
        Tuple2<B, Integer> t2 = Tuple2.of(new B(10), 999);

        assertTrue("must hold: t1 not equals t2", !t1.equals(t2));
        assertTrue("must hold: t2 not equals t1", !t2.equals(t1));
    }

    @Test
    public void test07WhenSameTypesDifferentDataThenUnequal() {
        Tuple2<String, Integer> t1 = Tuple2.of("something", 100);
        Tuple2<String, Integer> t2 = Tuple2.of("something", 101);

        assertTrue("must hold: t1 not equals t2", !t1.equals(t2));
        assertTrue("must hold: t2 not equals t1", !t2.equals(t1));
    }

    @Test
    public void test08WhenSameTypesWithoutOverridingEqualsAndEqualDataThenUnequal() {

        class A {
            private int number;

            A (int n) { number = n; }
        }

        Tuple2<String, A> t1 = Tuple2.of("something", new A(100));
        Tuple2<String, A> t2 = Tuple2.of("something", new A(100));

        assertTrue("must hold: t1 not equals t2", !t1.equals(t2));
        assertTrue("must hold: t2 not equals t1", !t2.equals(t1));
    }

    @Test
    public void test09WhenSameTypesWithOverridingEqualsAndEqualDataThenEqual() {

        class A {
            private int number;

            A (int n) { number = n; }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) { return true; }

                if (obj == null || this.getClass() != obj.getClass()) {
                    return false;
                }

                A that = (A) obj;

                return Objects.equals(this.number, that.number);
            }

            @Override
            public int hashCode() {
                return Objects.hashCode(this.number);
            }
        }

        Tuple2<String, A> t1 = Tuple2.of("something", new A(100));
        Tuple2<String, A> t2 = Tuple2.of("something", new A(100));

        assertTrue("must hold: t1 equals t2", t1.equals(t2));
        assertTrue("must hold: t2 equals t1", t2.equals(t1));
    }

    @Test
    public void test10WhenSameTypesWithOverridingEqualsAndUnequalDataThenUnequal() {

        class A {
            private int number;

            A (int n) { number = n; }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) { return true; }

                if (obj == null || this.getClass() != obj.getClass()) {
                    return false;
                }

                A that = (A) obj;

                return Objects.equals(this.number, that.number);
            }

            @Override
            public int hashCode() {
                return Objects.hashCode(this.number);
            }
        }

        Tuple2<String, A> t1 = Tuple2.of("something", new A(100));
        Tuple2<String, A> t2 = Tuple2.of("something", new A(101));

        assertTrue("must hold: t1 not equals t2", !t1.equals(t2));
        assertTrue("must hold: t2 not equals t1", !t2.equals(t1));
    }

    @Test
    public void test11WhenHavingDifferentLengthsThenUnequal() {
        Tuple1<String> t1 = Tuple1.of("something");
        Tuple2<String, Integer> t2 = Tuple2.of("something", 100);

        assertTrue("must hold: t1 not equals t2", !t1.equals(t2));
        assertTrue("must hold: t2 not equals t1", !t2.equals(t1));
    }

    @Test
    public void test12WhenSameTypesSameDataEvenNullThenEqual() {
        Tuple2<String, Integer> t1 = Tuple2.of("something", null);
        Tuple2<String, Integer> t2 = Tuple2.of("some" + "thing", null);

        assertTrue("must hold: t1 equals t2", t1.equals(t2));
        assertTrue("must hold: t2 equals t1", t2.equals(t1));
    }

    @Test
    public void test13WhenDifferentTypesAlmostSameDataThenUnequal() {
        Tuple2<String, Integer> t1 = Tuple2.of("something", 10);
        Tuple2<String, Short> t2 = Tuple2.of("something", (short) 10);

        assertTrue("must hold: t1 not equals t2", !t1.equals(t2));
        assertTrue("must hold: t2 not equals t1", !t2.equals(t1));
    }

    @Test
    public void test14WhenDifferentTypesThenUnequal() {
        Tuple2<String, Integer> t1 = Tuple2.of("something", 10);
        Tuple2<String, String> t2 = Tuple2.of("something", "more");

        assertTrue("must hold: t1 not equals t2", !t1.equals(t2));
        assertTrue("must hold: t2 not equals t1", !t2.equals(t1));
    }

}
