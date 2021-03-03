package mythware.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mythware.domain.Faq;
import mythware.domain.PageModel;

import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class QueryFaqsByConditionVo {
    private List<FaqVo> faq;
    private PageModel pageModel;

    public static QueryFaqsByConditionVo convert(IPage<Faq> pageInfo) {
        return QueryFaqsByConditionVo.builder()
                .pageModel(PageModel.convert(pageInfo))
                .faq(FaqVo.convert(pageInfo.getRecords()))
                .build();
    }
}
