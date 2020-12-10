package mythware.dto;

import lombok.Data;
import mythware.domain.Page;

@Data
public class QueryNewsesByStateDto {
    private Page page;
    private Integer state;
}