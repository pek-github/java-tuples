package functionality;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.pek.tuple.Tuple2;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetTest {

    @Test
    public void test01WhenSameTypesThenOk() {
        Tuple2<String, Integer> t = Tuple2.of("something", 100);

        String expFirst = "something";
        Integer expSecond = 100;

        String first = t.get(0);
        assertEquals(expFirst, first);

        Integer second = t.get(1);
        assertEquals(expSecond, second);
    }

    @Test(expected = ClassCastException.class)
    public void test02WhenWrongTypeTakenThenException() {
        Tuple2<String, Integer> t = Tuple2.of("something", 100);

        Long first = t.get(0);
    }

    @Test
    public void test03WhenParentTypesThenOk() {
        Tuple2<String, Integer> t = Tuple2.of("something", 100);

        String expFirst = "something";
        Integer expSecond = 100;

        CharSequence first = t.get(0);
        assertEquals(expFirst, first);

        Number second = t.get(1);
        assertEquals(expSecond, second);
    }

    @Test
    public void test04WhenChildTypesThenOk() {
        Tuple2<Object, Object> t = Tuple2.of("something", 100);

        String expFirst = "something";
        Integer expSecond = 100;

        String first = t.get(0);
        assertEquals(expFirst, first);

        Integer second = t.get(1);
        assertEquals(expSecond, second);
    }

    @Test
    public void test05WhenAbstractTypesThenOk() {
        Tuple2<Object, Object> t = Tuple2.of("something", 100);

        Object expFirst = "something";
        Object expSecond = 100;

        Object first = t.get(0);
        assertEquals(expFirst, first);

        Object second = t.get(1);
        assertEquals(expSecond, second);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test06WhenIllegalIndexGreaterAccessedThenException() {
        Tuple2<Object, Object> t = Tuple2.of("something", 100);

        t.get(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test07WhenIllegalIndexLessAccessedThenException() {
        Tuple2<Object, Object> t = Tuple2.of("something", 100);

        t.get(-1);
    }
}
