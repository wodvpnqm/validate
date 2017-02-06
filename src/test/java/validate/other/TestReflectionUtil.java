package validate.other;

import com.ipx.common.validator.util.ReflectionUtil;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;
import validate.TestExtendDefaultValidate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wodvpn on 2017/2/6.
 */
public class TestReflectionUtil {

    @Test
    public void test1() {
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(TestExtendDefaultValidate.User.class);
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }

    @Test
    public void test2() {
        List<Field> fields = new ArrayList<>();
        ReflectionUtils.doWithFields(TestExtendDefaultValidate.User.class,
                new ReflectionUtils.FieldCallback() {
                    @Override
                    public void doWith(final Field field) throws IllegalArgumentException,
                            IllegalAccessException {
                        fields.add(field);
                    }
                },
                new ReflectionUtils.FieldFilter() {
                    @Override
                    public boolean matches(final Field field) {
                        final int modifiers = field.getModifiers();
                        // no static fields please
                        return !Modifier.isStatic(modifiers);
                    }
                });
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }

    @Test
    public void test3() {
        Method[] methods = ReflectionUtil.getAllMethod(TestExtendDefaultValidate.User.class);
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }

    @Test
    public void test4() {
        List<Field> fields = ReflectionUtil.getAllField(TestExtendDefaultValidate.User.class);
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }


}
