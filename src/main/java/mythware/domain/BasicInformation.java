package mythware.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@TableName(value = "website_basic_information")
public class BasicInformation {
    private String id; // id
    private String companyName; // 公司名称
    private String telephone; // 电话
    private String fax; // 传真
    private String email; // 邮箱
    private String address; // 地址

    @TableField(fill = FieldFill.INSERT)
    public Long createTime;

    @JsonIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    public Long updateTime;

    @JsonIgnore
    @TableLogic
    public Long deleteTime;
}

