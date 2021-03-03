package mythware.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mythware.domain.PageModel;
import mythware.domain.Plan;

import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class QueryPlansByConditionVo {
    private List<PlanListVo> plans;
    private PageModel pageModel;

    public static QueryPlansByConditionVo convert(IPage<Plan> pageInfo) {
        return QueryPlansByConditionVo.builder()
                .pageModel(PageModel.convert(pageInfo))
                .plans(PlanListVo.convert(pageInfo.getRecords()))
                .build();
    }
}
