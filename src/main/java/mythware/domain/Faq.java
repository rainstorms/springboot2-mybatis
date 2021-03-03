package mythware.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import lombok.*;

import java.util.Set;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@TableName(value = "website_faq")
public class Faq {
    private String id;  // 主键
    private Integer questionCategory; // 问题类别
    private String question; // 问题
    private Integer answerCategory; // 答案类别
    private String answer; // 答案

    @TableField(fill = FieldFill.INSERT)
    public Long createTime;

    @JsonIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    public Long updateTime;

    @JsonIgnore
    @TableLogic
    public Long deleteTime;

    public static Set<Integer> getValidAnswerCategories() {
        return Sets.newHashSet(AnswerCategory.LINK.code, AnswerCategory.WORD.code);
    }

    public static Set<Integer> getValidQuestionCategories() {
        return Sets.newHashSet(QuestionCategory.教室云盒.code, QuestionCategory.电子书包课堂管理系统.code,
                QuestionCategory.电子教室系统.code, QuestionCategory.数字语音系统.code, QuestionCategory.平板云管理.code,
                QuestionCategory.平板云防盗.code, QuestionCategory.ClassHub.code);
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