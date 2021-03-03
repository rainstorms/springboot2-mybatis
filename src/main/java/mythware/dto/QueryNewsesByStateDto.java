package mythware.dto;

import lombok.Data;
import mythware.domain.PageModel;

@Data
public class QueryNewsesByStateDto {
    private PageModel pageModel;
    private Integer state;
}