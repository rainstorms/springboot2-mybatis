package mythware.vo;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mythware.domain.News;
import mythware.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class QueryShowNewsesVo {
    private List<NewsListVo> newses;
    private Page page;

    public static QueryShowNewsesVo convert(PageInfo<News> pageInfo) {
        List<News> news = pageInfo.getList().stream()
                .filter(news1 -> null == news1.getShowTime() || news1.getShowTime().isBefore(LocalDateTime.now()))
                .peek(news1 -> {
                    if (null == news1.getShowTime()) {
                        news1.setShowTime(news1.getUpdateTime());
                    }
                }).sorted((o1, o2) -> o1.getShowTime().isAfter(o2.getShowTime()) ? 1 : -1)
                .collect(Collectors.toList());

        return QueryShowNewsesVo.builder()
                .page(Page.convert(pageInfo))
                .newses(NewsListVo.convert(news))
                .build();
    }


}