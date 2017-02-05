package validate;

import com.ipx.common.validator.annotation.*;
import com.ipx.common.validator.parser.AnnoUtil;
import com.ipx.common.validator.result.ValidateResult;
import com.ipx.common.validator.threadlocal.ThreadMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2017/1/10.
 */
public class TestMuitiType {


    public static class User {
        @NotNull(messageKey = "user.id.notnull")
        private Integer id;


        @NotNull(messageKey = "user.name.notnull")
        @NotBlank(message = "user.name.notempty")
        @Length(max = 30, message = "user.name.toolarge",type="insert,en_US")
        @Length(max = 20, message = "user.name.toolarge",type="insert,zh_CN")
        @Reg(pattern = "[a-zA-Z_][a-zA-Z_1-9]*",message = "user.name.pattern")
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }


    public static class User1 {
        @NotNull(messageKey = "user.id.notnull")
        private Integer id;

        @WhichTime(type = "update",order = 1)
        @NotNull(messageKey = "user.name.notnull",type = "insert")
        @NotBlank(message = "user.name.notempty")
        @Length(max = 20, message = "user.name.toolarge")
        @Reg(pattern = "[a-zA-Z_][a-zA-Z_1-9]*",message = "user.name.pattern")
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class User2 {
        @NotNull(messageKey = "user.id.notnull")
        private Integer id;

        @NotNull(messageKey = "user.name.notnull")
        @NotBlank(message = "user.name.notempty")
        @Length(max = 20, message = "user.name.toolarge")
        @Reg(pattern = "[a-zA-Z_][a-ZA-Z_1-9]*",message = "user.name.pattern")
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }


    /**
     *
     * 4个参数的情况,排序,有语言
     * 测试国际化的情况
     * 默认情况
     * 指定顺序
     *
     */
    @Test
    public void testDefaultMessageDefaultParams1(){
        Locale locale = Locale.US;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/resources", locale,
                this.getClass().getClassLoader());
        ThreadMap.getInstance().setResouceBundle(resourceBundle);
        User user = new User();
        user.setId(2);
        user.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Map<String,String> params = new HashMap<String,String>();
        params.put("length",""+30);
        ThreadMap.getInstance().setContext(params);
        ValidateResult vr = AnnoUtil.parse(user,"insert,en_US","en",true);
        System.out.println(vr);
        Assert.assertTrue(vr.hasErrors());
        Assert.assertTrue(vr.getMapMessage().size() == 1);
        Assert.assertTrue(vr.getMapMessage().get("name").equals("user name must less than or equal to 30"));
    }


    /**
     *
     * 4个参数的情况,排序,有语言
     * 测试国际化的情况-中文
     * 默认情况
     * 指定顺序
     * 通过验证
     *
     */
    @Test
    public void testDefaultMessageDefaultParams2(){
        Locale locale = Locale.US;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/resources", locale,
                this.getClass().getClassLoader());
        ThreadMap.getInstance().setResouceBundle(resourceBundle);
        User user = new User();
        user.setId(2);
        user.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Map<String,String> params = new HashMap<String,String>();
        params.put("length",""+30);
        ThreadMap.getInstance().setContext(params);
        ValidateResult vr = AnnoUtil.parse(user,"insert,en_US","en",true);
        System.out.println(vr);
        Assert.assertTrue(!vr.hasErrors());
    }

    /**
     *
     * 4个参数的情况,排序,有语言
     * 测试国际化的情况
     * 默认情况
     * 指定顺序
     * 另外一个国家
     *
     */
    @Test
    public void testDefaultMessageDefaultParams3(){
        Locale locale = Locale.CHINA;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/resources", locale,
                this.getClass().getClassLoader());
        ThreadMap.getInstance().setResouceBundle(resourceBundle);
        User user = new User();
        user.setId(2);
        user.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        ValidateResult vr = AnnoUtil.parse(user,"insert,zh_CN","zh",true);
        System.out.println(vr);
        Assert.assertTrue(vr.hasErrors());
        Assert.assertTrue(vr.getMapMessage().size() == 1);
        Assert.assertTrue(vr.getMapMessage().get("name").equals("用户名长度不能大于20,您的输入字符串aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa,字符串长度30"));
    }

    /**
     *
     * 4个参数的情况,排序,有语言
     * 测试国际化的情况
     * 默认情况
     * 指定顺序
     * 另外一个国家
     * 不存在的国家
     *
     */
    @Test
    public void testDefaultMessageDefaultParams4(){
        Locale locale = Locale.CANADA;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/resources", locale,
                this.getClass().getClassLoader());
        ThreadMap.getInstance().setResouceBundle(resourceBundle);
        User user = new User();
        user.setId(2);
        user.setName("aaaa");
        Map<String,String> params = new HashMap<String,String>();
        params.put("maxLength",""+20);
        ThreadMap.getInstance().setContext(params);
        ValidateResult vr = AnnoUtil.parse(user,"insert,en-CA","en",true);
        Assert.assertTrue(!vr.hasErrors());
    }

    /**
     *
     * 4个参数的情况,排序,有语言
     * 测试国际化的情况
     * 默认情况
     * 指定顺序
     * 另外一个国家
     * 不存在的语言
     *
     */
    @Test
    public void testDefaultMessageDefaultParams5(){
        Locale locale = Locale.CHINA;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/resources", locale,
                this.getClass().getClassLoader());
        ThreadMap.getInstance().setResouceBundle(resourceBundle);
        User user = new User();
        user.setId(2);
        user.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        Map<String,String> params = new HashMap<String,String>();
//        params.put("maxLength",""+20);
//        ThreadMap.getInstance().setContext(params);
        ValidateResult vr = AnnoUtil.parse(user,"insert,zh_CN","zh",true);
        Assert.assertTrue(vr.hasErrors());
        Assert.assertTrue(vr.getMapMessage().get("name").equals("用户名长度不能大于20,您的输入字符串aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa,字符串长度42"));
    }


}
