package mythware.domain;

import com.google.common.collect.Sets;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class News {
    private String id;  // 注解
    private String title; // 标题名称
    private String introduction; // 简介
    private String content; // 内容
    private LocalDateTime showTime; // 新闻展示时间
    private int state; // 状态 详情见 NewsState
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间

    public static HashSet<Integer> getValidStates() {
        return Sets.newHashSet(News.NewsState.PUBLISHED.getCode(), News.NewsState.SAVED.getCode());
    }

    @AllArgsConstructor @NoArgsConstructor
    public enum NewsState {
        DELETE(0, "删除"),
        PUBLISHED(1, "发布"),
        SAVED(2, "暂存");

        @Getter
        Integer code;
        String desc;
    }
}
