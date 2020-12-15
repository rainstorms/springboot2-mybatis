package mythware.dto;

import lombok.Data;
import mythware.domain.Faq;
import mythware.utils.Id;
import org.springframework.beans.BeanUtils;

@Data
public class EditFaqDto {
    private String id;  // 主键
    private int questionCategory; // 问题类别
    private String question; // 问题
    private int answerCategory; // 答案类别
    private String answer; // 答案

    public Faq toAddFaq() {
        Faq faq = toFaq();

        faq.setId(Id.next());
        return faq;
    }

    public Faq toFaq() {
        Faq faq = new Faq();
        BeanUtils.copyProperties(this, faq);
        return faq;
    }
}
