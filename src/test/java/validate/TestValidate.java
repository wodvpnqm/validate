package validate;

import com.ipx.common.validator.parser.AnnoUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2017/1/10.
 */
public class TestValidate {


    /**
     *
     * 测试非国际化的情况
     *
     */
    @Test
    public void testDefaultMessage(){
        User user = new User();
        //user.setId(10);
        user.setAge(12);
        user.setSex(1);
        user.setEmail("sdfds@sdf.com");
        user.setName("邱庙sdddddddddddddddddddddddddddsssssssssss");
        user.setPassword("23432");
        user.setPasswordConfirm("23d432");
        user.setPhone("15115904313");
        System.out.println(AnnoUtil.parse(user));
    }


    /**
     *测试非国际化环境的排序验证
     *
     */
    @Test
    public void testDefaultMessageSort(){
        User2 user = new User2();
        //user.setId(10);
        user.setName("邱庙sdddddddddddddddddddddddddddsssssssssss");
        System.out.println(AnnoUtil.parse(user,"insert","",false));
    }


    /**
     *测试非国际化环境的排序验证
     *
     */
    @Test
    public void testDefaultMessageSort1(){
        User2 user = new User2();
        //user.setId(10);
        user.setName("邱庙sdddddddddddddddddddddddddddsssssssssss");
        System.out.println(AnnoUtil.parse(user,"insert","",true));
    }
}
