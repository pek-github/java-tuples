package functionality;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.pek.tuple.Tuple1;
import org.pek.tuple.Tuple2;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Note:
 * When the expected outcome is inequality of hash codes, then
 * it is normal for unequal objects to have the same hash code,
 * so expect some false-positives.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HashcodeTest {

    @Test
    public void test01WhenSameObjectsThenSameHash() {
        Tuple2<String, Integer> t1 = Tuple2.of("something", 100);
        Tuple2<String, Integer> t2 = t1;

        assertEquals("must hold: H(t1) == H(t2)", t1.hashCode(), t2.hashCode());
    }

    @Test
    public void test02WhenSameTypesSameDataThenSameHash() {
        Tuple2<String, Integer> t1 = Tuple2.of("something", 100);
        Tuple2<String, Integer> t2 = Tuple2.of("some" + "thing", 10 * 10);

        assertEquals("must hold: H(t1) == H(t2)", t1.hashCode(), t2.hashCode());
    }

    @Test
    public void test03WhenSameRuntimeTypesSameDataThenSameHash() {
        Tuple2<String, Integer> t1 = Tuple2.of("something", 100);
        Tuple2<CharSequence, Integer> t2 = Tuple2.of("something", 100);

        assertEquals("must hold: H(t1) == H(t2)", t1.hashCode(), t2.hashCode());
    }

    @Test
    public void test04WhenDifferentTypesDespiteInheritanceThenNotSameHash() {

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

        assertNotEquals("must hold: H(t1) != H(t2)", t1.hashCode(), t2.hashCode());
    }

    @Test
    public void test05WhenDifferentTypesDespiteMinimalInheritanceThenNotSameHash() {

        class A {
            private int number;

            A (int n) { number = n; }
        }

        class B extends A {
            B (int n) { super (n); }
        }

        Tuple2<A, Integer> t1 = Tuple2.of(new A(10), 999);
        Tuple2<B, Integer> t2 = Tuple2.of(new B(10), 999);

        assertNotEquals("must hold: H(t1) != H(t2)", t1.hashCode(), t2.hashCode());
    }

    @Test
    public void test06WhenSameTypesDifferentDataThenNotSameHash() {
        Tuple2<String, Integer> t1 = Tuple2.of("something", 100);
        Tuple2<String, Integer> t2 = Tuple2.of("something", 101);

        assertNotEquals("must hold: H(t1) != H(t2)", t1.hashCode(), t2.hashCode());
    }

    @Test
    public void test07WhenSameTypesWithoutOverridingHashCodeAndEqualDataThenNotSameHash() {

        class A {
            private int number;

            A (int n) { number = n; }
        }

        Tuple2<String, A> t1 = Tuple2.of("something", new A(100));
        Tuple2<String, A> t2 = Tuple2.of("something", new A(100));

        assertNotEquals("must hold: H(t1) != H(t2)", t1.hashCode(), t2.hashCode());
    }

    @Test
    public void test08WhenSameTypesWithOverridingHashCodeAndEqualDataThenSameHash() {

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

        assertEquals("must hold: H(t1) == H(t2)", t1.hashCode(), t2.hashCode());
    }

    @Test
    public void test09WhenSameTypesWithOverridingEqualsAndUnequalDataThenNotSameHash() {

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

        assertNotEquals("must hold: H(t1) != H(t2)", t1.hashCode(), t2.hashCode());
    }

    @Test
    public void test10WhenHavingDifferentLengthsThenHashNotSame() {
        Tuple1<String> t1 = Tuple1.of("something");
        Tuple2<String, Integer> t2 = Tuple2.of("something", 100);

        assertNotEquals("must hold: H(t1) != H(t2)", t1.hashCode(), t2.hashCode());
    }

    @Test
    public void test11WhenSameTypesSameDataEvenNullThenSameHash() {
        Tuple2<String, Integer> t1 = Tuple2.of("something", null);
        Tuple2<String, Integer> t2 = Tuple2.of("some" + "thing", null);

        assertEquals("must hold: H(t1) == H(t2)", t1.hashCode(), t2.hashCode());
    }

}
