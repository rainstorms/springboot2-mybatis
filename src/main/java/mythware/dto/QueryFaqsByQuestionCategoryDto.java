package mythware.dto;

import lombok.Data;
import mythware.domain.PageModel;

@Data
public class QueryFaqsByQuestionCategoryDto {
    private PageModel page;
    private Integer category;
}
