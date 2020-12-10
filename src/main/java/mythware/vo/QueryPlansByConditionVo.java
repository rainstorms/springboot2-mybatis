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
public class QueryPlansByConditionVo {
    private List<PlanListVo> plans;
    private Page page;

    public static QueryPlansByConditionVo convert(PageInfo<Plan> pageInfo) {
        return QueryPlansByConditionVo.builder()
                .page(Page.convert(pageInfo))
                .plans(PlanListVo.convert(pageInfo.getList()))
                .build();
    }
}
