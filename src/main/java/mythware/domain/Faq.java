package mythware.domain;

import com.google.common.collect.Sets;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class Faq {
    private String id;  // 主键
    private int questionCategory; // 问题类别
    private String question; // 问题
    private int answerCategory; // 答案类别
    private String answer; // 答案
    private int state; // 状态 详情见 FaqState
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间

    public static Set<Integer> getValidAnswerCategories() {
        return Sets.newHashSet(1, 2);
    }

    public static Set<Integer> getValidQuestionCategories() {
        return Sets.newHashSet(1, 2, 3, 4, 5, 6);
    }

    @AllArgsConstructor @NoArgsConstructor
    public enum FaqState {
        DELETE(0, "删除"),
        NORMAL(1, "正常");

        @Getter
        Integer code;
        String desc;
    }
}