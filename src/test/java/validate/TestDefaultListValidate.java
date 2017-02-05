package validate;

import com.alibaba.fastjson.JSONObject;
import com.ipx.common.validator.annotation.ListValidate;
import com.ipx.common.validator.annotation.NotNull;
import com.ipx.common.validator.parser.AnnoUtil;
import com.ipx.common.validator.result.ValidateResult;
import com.jayway.jsonpath.JsonPath;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 没有国际化
 * Created by Administrator on 2017/1/10.
 */
public class TestDefaultListValidate {


    public static class Name{
        @NotNull(messageKey = "名称前面部分不能为空")
        private String firstName;

        @NotNull(messageKey = "名称后面部分不能为空")
        private String lastName;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }


    public static class User {
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public List<Name> getNameList() {
            return nameList;
        }

        public void setNameList(List<Name> nameList) {
            this.nameList = nameList;
        }

        @NotNull(messageKey = "ID不能为空")
        private Integer id;

        @ListValidate
        private List<Name> nameList;

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
        List<Name> nameList = new ArrayList<>();

        Name name = new Name();
        name.setFirstName("first");
        nameList.add(name);

        name = new Name();
        name.setLastName("lastName");
        nameList.add(name);

        user.setNameList(nameList);

        ValidateResult vr = AnnoUtil.parse(user,"add",null,false);
        System.out.println(vr);
        Assert.assertTrue(vr.hasErrors());
        Assert.assertTrue(vr.getMapMessage().size() == 2);
        String result = JSONObject.toJSONString(vr);
        System.out.println(result);
        String idError = JsonPath.read(result, "$.mapMessage.nameList.0.mapMessage.lastName");
        String nameError = JsonPath.read(result, "$.mapMessage.nameList.1.mapMessage.firstName");
        Assert.assertTrue(idError.equals("名称后面部分不能为空"));
        Assert.assertTrue(nameError.equals("名称前面部分不能为空"));
    }


  


}
