package application;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.pek.tuple.Tuple2;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MutabilityTest {

    @Test
    public void test01TupleBehavesLikeScalaTupleRegardingMutability() {

        Tuple2<String, AtomicInteger> t = Tuple2.of("something", new AtomicInteger(0));

        // modification
        AtomicInteger x = t.get(1);
        x.set(111);

        String expectedValue0 = "something";
        assertEquals("First values must be equal", expectedValue0, t.get(0));

        Integer expectedValue1 = 111;
        Integer realValue1 = ((AtomicInteger) t.get(1)).get();
        assertEquals("Second values must be equal", expectedValue1, realValue1);
    }

    /*
    Scala code
    (with some redundant type declaration for value t):

    import java.util.concurrent.atomic.AtomicInteger;

    val t : Tuple2[String, AtomicInteger] = ("something", new AtomicInteger(0))
    println(t) // prints: ("something", 0)

    t._2.set(111)
    println(t) // prints: ("something", 111)
    */
}
