package mythware.dto;

import lombok.Data;
import mythware.domain.Page;

@Data
public class QueryShowFaqsDto {
    private Page page;
    private Integer category;
    private String searchValue;
}
