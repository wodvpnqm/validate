package validate.other;

import com.ipx.common.validator.annotation.Combination;
import com.ipx.common.validator.annotation.NotNull;

/**
 * 以后要删除的,验证框架,国际化提示消息测试类
 */
public class User1 {
    @NotNull(messageKey = "V001")
    private Integer id;

    private String name;

    private String phone;

    private String email;

    private Integer sex;

    private String password;

    private String passwordConfirm;

    private Integer age;

    
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


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    public String getEmail() {
        return email;
    }

    
    public void setEmail(String email) {
        this.email = email;
    }

    
    public Integer getSex() {
        return sex;
    }

    
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    
    public String getPassword() {
        return password;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }

    
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    
    public Integer getAge() {
        return age;
    }

    
    public void setAge(Integer age) {
        this.age = age;
    }

    
    @Combination(type = "query")
    public String validatePass() {
        if (this.password.equals(this.passwordConfirm))
            return null;
        return "密码长度不相等";
    }

}
