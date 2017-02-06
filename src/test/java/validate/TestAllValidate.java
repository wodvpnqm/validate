package validate;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Administrator on 2017/1/13.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestExtendDefaultValidate.class,
        TestDefaultNestValidate.class,
        TestDefaultListValidate.class,
        TestDefaultMesssageSort.class,
        TestI18nMesssage.class,
        TestMuitiType.class



})
public class TestAllValidate {
}

