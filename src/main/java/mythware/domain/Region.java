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
@TableName(value = "website_region")
public class Region {
    private String id;  // 主键
    private String region; // 区域
    private String name; // 姓名
    private String mobile; // 移动电话
    private String email; // 邮箱
    private String position; // 职位

    @TableField(fill = FieldFill.INSERT)
    public Long createTime;

    @JsonIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    public Long updateTime;

    @JsonIgnore
    @TableLogic
    public Long deleteTime;
}
