package mythware.vo;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mythware.domain.Faq;
import mythware.domain.Page;

import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class QueryFaqsByConditionVo {
    private List<FaqVo> faq;
    private Page page;

    public static QueryFaqsByConditionVo convert(PageInfo<Faq> pageInfo) {
        return QueryFaqsByConditionVo.builder()
                .page(Page.convert(pageInfo))
                .faq(FaqVo.convert(pageInfo.getList()))
                .build();
    }
}
