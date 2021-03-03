package mythware.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mythware.domain.PageModel;
import mythware.domain.Region;

import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class QueryRegionsVo {
    private PageModel pageModel;
    private List<RegionVo> regions;

    public static QueryRegionsVo convert(IPage<Region> regions) {
        return QueryRegionsVo.builder()
                .pageModel(PageModel.convert(regions))
                .regions(RegionVo.convert(regions.getRecords()))
                .build();
    }
}
