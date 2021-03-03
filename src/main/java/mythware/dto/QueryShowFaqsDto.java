package mythware.dto;

import lombok.Data;
import mythware.domain.PageModel;

@Data
public class QueryShowFaqsDto {
    private PageModel page;
    private Integer category;
    private String searchValue;
}
