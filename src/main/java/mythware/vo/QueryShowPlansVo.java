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
public class QueryShowPlansVo {
    private List<PlanListVo> plans;
    private PageModel pageModel;

    public static QueryShowPlansVo convert(IPage<Plan> showPlans) {
        List<Plan> plans = showPlans.getRecords();
        return QueryShowPlansVo.builder()
                .pageModel(PageModel.convert(showPlans))
                .plans(PlanListVo.convert(plans))
                .build();
    }
}
