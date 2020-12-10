package mythware.dto;

import lombok.Data;
import mythware.domain.Page;

@Data
public class QueryShowPlansDto {
    private Integer category;
    private Page page;
}
