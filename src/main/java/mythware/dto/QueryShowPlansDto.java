package mythware.dto;

import lombok.Data;
import mythware.domain.PageModel;

@Data
public class QueryShowPlansDto {
    private Integer category;
    private PageModel pageModel;
}
