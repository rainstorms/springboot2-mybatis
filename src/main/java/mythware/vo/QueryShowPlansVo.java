package mythware.vo;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mythware.domain.Page;
import mythware.domain.Plan;

import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class QueryShowPlansVo {
    private List<PlanListVo> plans;
    private Page page;

    public static QueryShowPlansVo convert(PageInfo<Plan> showPlans) {
        List<Plan> plans = showPlans.getList();
        return QueryShowPlansVo.builder()
                .page(Page.convert(showPlans))
                .plans(PlanListVo.convert(plans))
                .build();
    }
}
