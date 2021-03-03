package mythware.dto;

import lombok.Data;
import mythware.domain.PageModel;

@Data
public class QueryPlansByConditionDto {
    private PageModel pageModel;
    private Integer state;
    private Integer category;
}
