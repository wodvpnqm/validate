package validate;

import com.alibaba.fastjson.JSONObject;
import com.ipx.common.validator.annotation.Length;
import com.ipx.common.validator.annotation.NotNull;
import com.ipx.common.validator.annotation.WhichTime;
import com.ipx.common.validator.parser.AnnoUtil;
import com.ipx.common.validator.result.ValidateResult;
import com.ipx.common.validator.threadlocal.ThreadMap;
import com.ipx.common.validator.util.BeanUtils;
import com.ipx.common.validator.util.Constant;
import com.jayway.jsonpath.JsonPath;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * 没有国际化
 * Created by Administrator on 2017/1/10.
 */
public class TestDefaultMesssageSort {


    public static class User {
        @NotNull(messageKey = "ID不能为空")
        private Integer id;

        @Length(max = 20, message = "长度不能大于20")
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
        @NotNull(messageKey = "ID不能为空")
        private Integer id;

        @WhichTime(type = Constant.DEFAULT_TYPE,order = 1)
        @Length(max = 20, message = "长度不能大于20")
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
        @NotNull(messageKey = "ID不能为空")
        private Integer id;

        @WhichTime(type = Constant.DEFAULT_TYPE,order = 1)
        @Length(max = 20, message = "名称的长度不能大于20,您的输入的实际值:{name},实际长度{length}")
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
     * 测试非国际化的情况
     * 默认情况
     *
     */
    @Test
    public void testDefaultMessageDefaultParams(){
        User user = new User();
        ValidateResult vr = AnnoUtil.parse(user);
        Assert.assertTrue(vr.hasErrors());
        Assert.assertTrue(vr.getMapMessage().size() == 1);
        String result = JSONObject.toJSONString(vr);
        String error = JsonPath.read(result, "$.mapMessage.id");
        Assert.assertTrue(error.equals("ID不能为空"));
    }


    /**
     *
     * 2个参数的情况
     * 测试非国际化的情况
     * 默认情况
     *
     */
    @Test
    public void testDefaultMessageDefaultParams1(){
        User user = new User();
        ValidateResult vr = AnnoUtil.parse(user,"insert",null,false);
        Assert.assertTrue(vr.hasErrors());
        Assert.assertTrue(vr.getMapMessage().size() == 1);
        String result = JSONObject.toJSONString(vr);
        String error = JsonPath.read(result, "$.mapMessage.id");
        Assert.assertTrue(error.equals("ID不能为空"));
    }


    /**
     *
     * 4个参数的情况,不排序,没语言
     * 测试非国际化的情况
     * 默认情况
     *
     */
    @Test
    public void testDefaultMessageDefaultParams2(){
        User user = new User();
        user.setName("ddddddddddddddddddddddddddddddddddddddddddddddd");
        ValidateResult vr = AnnoUtil.parse(user,"add",null,false);
        System.out.println(vr);
        Assert.assertTrue(vr.hasErrors());
        Assert.assertTrue(vr.getMapMessage().size() == 2);
        String result = JSONObject.toJSONString(vr);
        System.out.println(result);
        String idError = JsonPath.read(result, "$.mapMessage.id");
        String nameError = JsonPath.read(result, "$.mapMessage.name");
        Assert.assertTrue(idError.equals("ID不能为空"));
        Assert.assertTrue(nameError.equals("长度不能大于20"));
    }


    /**
     *
     * 4个参数的情况,排序,没语言
     * 测试非国际化的情况
     * 默认情况
     * 不指定顺序
     *
     */
    @Test
    public void testDefaultMessageDefaultParams3(){
        User user = new User();
        user.setName("ddddddddddddddddddddddddddddddddddddddddddddddd");
        ValidateResult vr = AnnoUtil.parse(user,"add",null,true);
        System.out.println(vr);
        Assert.assertTrue(vr.hasErrors());
        Assert.assertTrue(vr.getMapMessage().size() == 1);
        String result = JSONObject.toJSONString(vr);
        System.out.println(result);
        String idError = JsonPath.read(result, "$.mapMessage.id");
        Assert.assertTrue(idError.equals("ID不能为空"));
    }


    /**
     *
     * 4个参数的情况,排序,没语言
     * 测试非国际化的情况
     * 默认情况
     * 指定顺序
     *
     */
    @Test
    public void testDefaultMessageDefaultParams4(){
        User1 user = new User1();
        user.setName("ddddddddddddddddddddddddddddddddddddddddddddddd");
        ValidateResult vr = AnnoUtil.parse(user,"add",null,true);
        Assert.assertTrue(vr.hasErrors());
        Assert.assertTrue(vr.getMapMessage().size() == 1);
        String result = JSONObject.toJSONString(vr);
        System.out.println(result);
        String nameError = JsonPath.read(result, "$.mapMessage.name");
        Assert.assertTrue(nameError.equals("长度不能大于20"));
    }


    /**
     *
     * 4个参数的情况,排序,没语言
     * 测试非国际化的情况
     * 默认情况
     * 指定顺序
     * 有占位符的情况
     *
     */
    @Test
    public void testDefaultMessageDefaultParams5(){
        User2 user = new User2();
        user.setName("ddddddddddddddddddddddddddddddddddddddddddddddd");
        Map<String,String> map = BeanUtils.transBean2Map(user);
        map.put("length",""+47);
        ThreadMap.getInstance().setContext(map);
        ValidateResult vr = AnnoUtil.parse(user,"add",null,true);
        Assert.assertTrue(vr.hasErrors());
        Assert.assertTrue(vr.getMapMessage().size() == 1);
        System.out.println(vr);
        //Assert.assertTrue(vr.getMapMessage().get(null).equals("名称的长度不能大于20,您的输入的实际值:ddddddddddddddddddddddddddddddddddddddddddddddd,实际长度47"));

        String result = JSONObject.toJSONString(vr);
        System.out.println(result);
        String nameError = JsonPath.read(result, "$.mapMessage.name");
        Assert.assertTrue(nameError.equals("名称的长度不能大于20,您的输入的实际值:ddddddddddddddddddddddddddddddddddddddddddddddd,实际长度47"));
    }


}
