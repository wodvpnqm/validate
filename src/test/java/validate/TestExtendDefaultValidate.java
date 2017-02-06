package validate;

import com.alibaba.fastjson.JSONObject;
import com.ipx.common.validator.annotation.Length;
import com.ipx.common.validator.annotation.NotNull;
import com.ipx.common.validator.annotation.Range;
import com.ipx.common.validator.annotation.SelectInteger;
import com.ipx.common.validator.parser.AnnoUtil;
import com.ipx.common.validator.result.ValidateResult;
import com.jayway.jsonpath.JsonPath;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by wodvpn on 2017/2/6.
 */
public class TestExtendDefaultValidate {


    public static class BaseBean{


        public Integer getStart() {
            return start;
        }

        public void setStart(Integer start) {
            this.start = start;
        }

        public Integer getLength() {
            return length;
        }

        public void setLength(Integer length) {
            this.length = length;
        }

        public Integer getVersion() {
            return version;
        }

        public void setVersion(Integer version) {
            this.version = version;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Integer getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(Integer delFlag) {
            this.delFlag = delFlag;
        }

        @Range(minValue = 0,message = "分页最小为0")
        private Integer start;
        private Integer length;

        @Range(minValue = 1,message = "版本最小为1")
        private Integer version;
        @NotNull(messageKey = "操作用户ID不能为空")
        private String createUser;
        private String createTime;
        @SelectInteger(options = {0,1},message = "删除标志只能是0或者1")
        private Integer delFlag;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        private Integer id;


    }

    public static class User extends BaseBean{
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
        System.out.println(vr);
        Assert.assertTrue(vr.hasErrors());
        Assert.assertTrue(vr.getMapMessage().size() == 2);
        String result = JSONObject.toJSONString(vr);
        String error = JsonPath.read(result, "$.mapMessage.createUser");
        Assert.assertTrue(error.equals("操作用户ID不能为空"));
        String error1 = JsonPath.read(result, "$.mapMessage.id");
        Assert.assertTrue(error1.equals("ID不能为空"));
    }






}
