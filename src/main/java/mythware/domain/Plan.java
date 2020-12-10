package mythware.domain;

import com.google.common.collect.Sets;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class Plan {
    private String id;  // 主键
    private String title; // 标题名称
    private String introduction; // 简介
    private String content; // 内容
    private String cover; // 封面
    private int category; // 类别 1高等院校 2中小学 3其它
    private int state; // 状态 详情见 PlanState
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间

    public static HashSet<Integer> getValidStates() {
        return Sets.newHashSet(Plan.PlanState.PUBLISHED.getCode(), Plan.PlanState.SAVED.getCode());
    }

    @AllArgsConstructor @NoArgsConstructor
    public enum PlanState {
        DELETE(0, "删除"),
        PUBLISHED(1, "发布"),
        SAVED(2, "暂存");

        @Getter
        Integer code;
        String desc;
    }
}
