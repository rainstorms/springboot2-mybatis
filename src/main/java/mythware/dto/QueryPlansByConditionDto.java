package mythware.dto;

import lombok.Data;
import mythware.domain.Page;

@Data
public class QueryPlansByConditionDto {
    private Page page;
    private Integer state;
    private Integer category;
}
