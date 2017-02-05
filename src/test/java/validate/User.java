package validate;

import com.ipx.common.validator.annotation.*;

public class User {
    @WhichTime(order = 2)
    @NotNull(messageKey = "ID不能为空")
    private Integer id;

    @WhichTime(order = 1)
    @Length(max = 20, message = "长度不能大于20")
    private String name;

    @WhichTime(order = 3)
    @NotBlank
    @Reg(pattern = "((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}", message = "手机号码格式不正确")
    private String phone;

    @Reg(pattern = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", message = "email格式不正确")
    private String email;

    @NotBlank(message = "性别不能为空")
    @SelectInteger(options = {0, 1}, message = "性别必须是0或者1")
    private Integer sex;

    @NotBlank(message = "密码长度为空")
    @Length(min = 6, max = 20, message = "密码长度必须在6到20之间")
    private String password;

    @NotBlank(message = "密码确认长度不能为空")
    @Length(min = 6, max = 20, message = "密码确认长度必须在6到20之间")
    private String passwordConfirm;

    @NotNull
    @Length(min = 18, max = 120)
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
