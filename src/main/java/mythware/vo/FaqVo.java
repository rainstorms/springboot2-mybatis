package mythware.vo;

import com.google.common.collect.Lists;
import lombok.Data;
import mythware.domain.Faq;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class FaqVo {
    private String id;  // 主键
    private int questionCategory; // 问题类别
    private String question; // 问题
    private int answerCategory; // 答案类别
    private String answer; // 答案

    public static FaqVo convert(Faq faq) {
        if (null == faq) return null;

        FaqVo faqVo = new FaqVo();
        BeanUtils.copyProperties(faq, faqVo);

        return faqVo;
    }

    public static List<FaqVo> convert(List<Faq> faq) {
        if (CollectionUtils.isEmpty(faq)) return Lists.newArrayList();

        return faq.stream().map(FaqVo::convert).collect(Collectors.toList());
    }
}
