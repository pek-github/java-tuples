package suites;

import application.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    SetTest.class,
    MutabilityTest.class
})
public class ApplicationSuite {
}
