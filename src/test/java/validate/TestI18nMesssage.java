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
 * 没有国际化
 * Created by Administrator on 2017/1/10.
 */
public class TestI18nMesssage {


    public static class User {
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


    public static class User1 {
        @NotNull(messageKey = "user.id.notnull")
        private Integer id;

        @WhichTime(type = "*",order = 1)
        @NotNull(messageKey = "user.name.notnull")
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
     * 1个参数的情况
     * 测试国际化的情况 -中文
     * 默认情况
     *
     */
    @Test
    public void testDefaultMessageDefaultParams(){
        Locale locale = Locale.CHINA;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/resources", locale,
                this.getClass().getClassLoader());
        ThreadMap.getInstance().setResouceBundle(resourceBundle);
        User user = new User();
        ValidateResult vr = AnnoUtil.parse(user,"zh");
        System.out.println(vr);
        Assert.assertTrue(vr.hasErrors());
        Assert.assertTrue(vr.getMapMessage().size() == 2);
        Assert.assertTrue(vr.getMapMessage().get("id").equals("ID不能为空"));
        Assert.assertTrue(vr.getMapMessage().get("name").equals("用户名不能为空"));
    }




    /**
     *
     * 2个参数的情况
     * 测试国际化的情况 - 英文
     * 默认情况
     *
     */
    @Test
    public void testDefaultMessageDefaultParams1(){
        Locale locale = Locale.US;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/resources", locale,
                this.getClass().getClassLoader());
        ThreadMap.getInstance().setResouceBundle(resourceBundle);
        User user = new User();
        ValidateResult vr = AnnoUtil.parse(user,"en");
        Assert.assertTrue(vr.hasErrors());
        Assert.assertTrue(vr.getMapMessage().size() == 2);
        Assert.assertTrue(vr.getMapMessage().get("id").equals("id can not be null"));
        Assert.assertTrue(vr.getMapMessage().get("name").equals("user name can not be null"));
    }


    /**
     *
     *  排序的情况
     * 默认排序情况
     *
     */
    @Test
    public void testDefaultMessageDefaultParams2(){
        Locale locale = Locale.US;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/resources", locale,
                this.getClass().getClassLoader());
        ThreadMap.getInstance().setResouceBundle(resourceBundle);
        User user = new User();
        ValidateResult vr = AnnoUtil.parse(user,"insert","en",true);
        Assert.assertTrue(vr.hasErrors());
        Assert.assertTrue(vr.getMapMessage().size() == 1);
        Assert.assertTrue(vr.getMapMessage().get("id").equals("id can not be null"));
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
    public void testDefaultMessageDefaultParams3(){
        Locale locale = Locale.US;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/resources", locale,
                this.getClass().getClassLoader());
        ThreadMap.getInstance().setResouceBundle(resourceBundle);
        User1 user = new User1();
        ValidateResult vr = AnnoUtil.parse(user,"insert","en",true);
        Assert.assertTrue(vr.hasErrors());
        Assert.assertTrue(vr.getMapMessage().size() == 1);
        Assert.assertTrue(vr.getMapMessage().get("name").equals("user name can not be null"));
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
    public void testDefaultMessageDefaultParams4(){
        Locale locale = Locale.US;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/resources", locale,
                this.getClass().getClassLoader());
        ThreadMap.getInstance().setResouceBundle(resourceBundle);
        User1 user = new User1();
        user.setName("sdfsd*(*()");
        ValidateResult vr = AnnoUtil.parse(user,"insert","en",true);
        Assert.assertTrue(vr.hasErrors());
        Assert.assertTrue(vr.getMapMessage().size() == 1);
        Assert.assertTrue(vr.getMapMessage().get("name").equals("user name format is not correct, must be letters, Numbers and underscore, followed by letter underlined"));
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
    public void testDefaultMessageDefaultParams5(){
        Locale locale = Locale.US;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/resources", locale,
                this.getClass().getClassLoader());
        ThreadMap.getInstance().setResouceBundle(resourceBundle);
        User1 user = new User1();
        user.setName("222222222222222222222");
        Map<String,String> params = new HashMap<String,String>();
        params.put("length",""+20);
        ThreadMap.getInstance().setContext(params);
        ValidateResult vr = AnnoUtil.parse(user,"insert","en",true);
        Assert.assertTrue(vr.hasErrors());
        Assert.assertTrue(vr.getMapMessage().size() == 1);
        Assert.assertTrue(vr.getMapMessage().get("name").equals("user name must less than or equal to 20"));
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
    public void testDefaultMessageDefaultParams6(){
        Locale locale = Locale.US;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/resources", locale,
                this.getClass().getClassLoader());
        ThreadMap.getInstance().setResouceBundle(resourceBundle);
        User1 user = new User1();
        user.setId(2);
        user.setName("aaaaaaaaaaaaaaaaaaaa");
        Map<String,String> params = new HashMap<String,String>();
        params.put("length",""+20);
        ThreadMap.getInstance().setContext(params);
        ValidateResult vr = AnnoUtil.parse(user,"insert","en",true);
        Assert.assertTrue(!vr.hasErrors());
        Assert.assertTrue(vr.getMapMessage().size() == 0);
    }


}
