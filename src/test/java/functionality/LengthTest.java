package functionality;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.pek.tuple.Tuple1;
import org.pek.tuple.Tuple2;
import org.pek.tuple.Tuple3;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LengthTest {

    @Test
    public void test01Tuple1LengthIs1() {
        Tuple1<Integer> t = Tuple1.of(100);
        Integer expected = 1;

        assertEquals("must hold: t.length() == 1", expected, t.length());
    }

    @Test
    public void test02Tuple2LengthIs2() {
        Tuple2<Integer, Long> t = Tuple2.of(100, 300L);
        Integer expected = 2;

        assertEquals("must hold: t.length() == 2", expected, t.length());
    }

    @Test
    public void test03Tuple3LengthIs3() {
        Tuple3<Integer, Long, String> t = Tuple3.of(100, 300L, "something");
        Integer expected = 3;

        assertEquals("must hold: t.length() == 3", expected, t.length());
    }
}
