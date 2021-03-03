package mythware.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mythware.domain.News;
import mythware.domain.PageModel;
import mythware.utils.DateTimes;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class QueryShowNewsesVo {
    private List<NewsListVo> newses;
    private PageModel pageModel;

    public static QueryShowNewsesVo convert(IPage<News> pageInfo) {
        List<News> news = pageInfo.getRecords().stream()
                .filter(news1 -> null == news1.getShowTime() || news1.getShowTime() < DateTimes.currentTimeSeconds())
                .peek(news1 -> {
                    if (null == news1.getShowTime()) {
                        news1.setShowTime(news1.getUpdateTime());
                    }
                }).sorted(Comparator.comparing(News::getShowTime))
                .collect(Collectors.toList());

        return QueryShowNewsesVo.builder()
                .pageModel(PageModel.convert(pageInfo))
                .newses(NewsListVo.convert(news))
                .build();
    }


}