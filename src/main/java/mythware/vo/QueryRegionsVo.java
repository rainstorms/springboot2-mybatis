package mythware.vo;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mythware.domain.Page;
import mythware.domain.Region;

import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class QueryRegionsVo {
    private Page page;
    private List<RegionVo> regions;

    public static QueryRegionsVo convert(PageInfo<Region> regions) {
        return QueryRegionsVo.builder()
                .page(Page.convert(regions))
                .regions(RegionVo.convert(regions.getList()))
                .build();
    }
}
