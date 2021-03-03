package mythware.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mythware.domain.News;
import mythware.domain.PageModel;

import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class QueryNewsesByStateVo {
    private List<NewsListVo> newses;
    private PageModel pageModel;

    public static QueryNewsesByStateVo convert(IPage<News> pageInfo) {
        return QueryNewsesByStateVo.builder()
                .pageModel(PageModel.convert(pageInfo))
                .newses(NewsListVo.convert(pageInfo.getRecords()))
                .build();
    }
}