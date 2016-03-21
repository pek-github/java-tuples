package application;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.pek.tuple.Tuple1;
import org.pek.tuple.Tuple2;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SetTest {

    @Test
    public void test01WhenInsertingToSetSameTuple1ThenSetDoesAcceptIt() {
        Set<Tuple1<Integer>> set = new HashSet<>();
        set.add(Tuple1.of(100));
        set.add(Tuple1.of(10 * 10));
        set.add(Tuple1.of(10 + 90));

        assertEquals("must hold: set has size 1", 1, set.size());
    }

    @Test
    public void test02WhenInsertingToSetSameTuple2ThenSetDoesAcceptIt() {
        Set<Tuple2<Integer, String>> set = new HashSet<>();
        set.add(Tuple2.of(100, "something"));
        set.add(Tuple2.of(10 + 90, "something"));
        set.add(Tuple2.of(10 * 10, "some" + "thing"));

        assertEquals("must hold: set has size 1", 1, set.size());
    }

    @Test
    public void test03WhenInsertingToSetSameTupleThenSetDoesAcceptIt() {
        Set<Tuple1<?>> set = new HashSet<>();
        set.add(Tuple1.of(100));
        set.add(Tuple1.of(100));
        set.add(Tuple1.of("something"));
        set.add(Tuple1.of("something"));
        set.add(Tuple1.of(12.34));
        set.add(Tuple1.of(12.34));

        assertEquals("must hold: set has size 3", 3, set.size());
    }

}
