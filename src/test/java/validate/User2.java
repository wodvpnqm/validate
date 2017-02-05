package validate;

import com.ipx.common.validator.annotation.*;

public class User2 {
    @WhichTime(order = 2)
    @NotNull(messageKey = "ID不能为空")
    private Integer id;

    @WhichTime(order = 1)
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
