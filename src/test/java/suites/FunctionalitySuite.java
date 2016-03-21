package suites;

import functionality.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    LengthTest.class,
    GetTest.class,
    EqualsTest.class,
    HashcodeTest.class,
    ComparableTest.class
})
public class FunctionalitySuite {
}
