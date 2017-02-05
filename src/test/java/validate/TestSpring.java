package validate;

import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import java.beans.PropertyDescriptor;

/**
 * @author wonder
 * @create 2017-01-20 13:23
 **/
public class TestSpring {

    @Test
    public void testGetAllFields() throws Exception {
        User user = new User();
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(user);
        PropertyDescriptor pd = bw.getPropertyDescriptor("id");
    }
}
