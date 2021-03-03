package mythware.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import lombok.*;

import java.util.HashSet;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@TableName(value = "website_news")
public class News {
    private String id;  // 注解
    private String title; // 标题名称
    private String introduction; // 简介
    private String content; // 内容
    private Long showTime; // 新闻展示时间
    private Integer state; // 状态 详情见 NewsState

    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    @JsonIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    @JsonIgnore
    @TableLogic
    private Long deleteTime;

    public static HashSet<Integer> getValidStates() {
        return Sets.newHashSet(News.NewsState.PUBLISHED.getCode(), News.NewsState.SAVED.getCode());
    }

    @AllArgsConstructor @NoArgsConstructor
    public enum NewsState {
        PUBLISHED(1, "发布"),
        SAVED(2, "暂存");

        @Getter
        Integer code;
        String desc;
    }
}
