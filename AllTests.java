import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ MemberTest.class, GymWorkerTest.class })
public class AllTests {

}
