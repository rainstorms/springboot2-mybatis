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
        return Sets.newHashSet(AnswerCategory.LINK.code, AnswerCategory.WORD.code);
    }

    public static Set<Integer> getValidQuestionCategories() {
        return Sets.newHashSet(QuestionCategory.教室云盒.code, QuestionCategory.电子书包课堂管理系统.code,
                QuestionCategory.电子教室系统.code, QuestionCategory.数字语音系统.code, QuestionCategory.平板云管理.code,
                QuestionCategory.平板云防盗.code, QuestionCategory.ClassHub.code);
    }

    @AllArgsConstructor @NoArgsConstructor
    public enum FaqState {
        DELETE(0, "删除"),
        NORMAL(1, "正常");

        @Getter
        Integer code;
        String desc;
    }

    @AllArgsConstructor @NoArgsConstructor
    public enum AnswerCategory {
        LINK(1, "跳转连接"),
        WORD(2, "文字表述");

        @Getter
        Integer code;
        String desc;
    }

    @AllArgsConstructor @NoArgsConstructor
    public enum QuestionCategory {
        教室云盒(1, "教室云盒"),
        电子书包课堂管理系统(2, "电子书包课堂管理系统"),
        电子教室系统(3, "电子教室系统"),
        数字语音系统(4, "数字语音系统"),
        平板云管理(5, "平板云管理"),
        平板云防盗(5, "平板云防盗"),
        ClassHub(6, "ClassHub");

        @Getter
        Integer code;
        String desc;
    }
}