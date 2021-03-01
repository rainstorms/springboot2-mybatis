package mythware.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@TableName(value = "website_banner")
public class Banner {
    private String id;  // 注解
    private String title; // 标题名称
    private String image2560; // 2560 px的图
    private String image1440; // 1440 px的图
    private String image1024; // 1024 px的图
    private String image768; // 768 px的图
    private Long startTime; // banner生效的开始时间
    private Long endTime; // banner生效的结束时间
    private Integer sequenceNo; // 展示序号
    private Integer state; // 状态 详情见 BannerState

    @TableField(fill = FieldFill.INSERT)
    public Long createTime;

    @JsonIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    public Long updateTime;

    @JsonIgnore
    @TableLogic
    public Long deleteTime;

    @AllArgsConstructor @NoArgsConstructor
    public enum BannerState {
        ENABLE(1, "启用"),
        DISABLE(2, "禁用");

        @Getter
        Integer code;
        String desc;
    }
}
