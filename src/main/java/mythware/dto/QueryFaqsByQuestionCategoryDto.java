package mythware.dto;

import lombok.Data;
import mythware.domain.Page;

@Data
public class QueryFaqsByQuestionCategoryDto {
    private Page page;
    private Integer category;
}
