package mythware.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@TableName(value = "website_plan")
public class Plan {
    private String id;  // 主键
    private String title; // 标题名称
    private String introduction; // 简介
    private String content; // 内容
    private String cover; // 封面
    private Integer category; // 类别 1高等院校 2中小学 3其它
    private Integer state; // 状态 详情见 PlanState
    @TableField(fill = FieldFill.INSERT)
    public Long createTime;

    @JsonIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    public Long updateTime;

    @JsonIgnore
    @TableLogic
    public Long deleteTime;

    public static HashSet<Integer> getValidStates() {
        return Sets.newHashSet(Plan.PlanState.PUBLISHED.getCode(), Plan.PlanState.SAVED.getCode());
    }

    @AllArgsConstructor @NoArgsConstructor
    public enum PlanState {
        PUBLISHED(1, "发布"),
        SAVED(2, "暂存");

        @Getter
        Integer code;
        String desc;
    }
}
