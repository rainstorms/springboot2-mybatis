package mythware.vo;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mythware.domain.News;
import mythware.domain.Page;

import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class QueryNewsesByStateVo {
    private List<NewsListVo> newses;
    private Page page;

    public static QueryNewsesByStateVo convert(PageInfo<News> pageInfo) {
        return QueryNewsesByStateVo.builder()
                .page(Page.convert(pageInfo))
                .newses(NewsListVo.convert(pageInfo.getList()))
                .build();
    }
}