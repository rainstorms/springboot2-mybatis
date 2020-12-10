package mythware.vo;

import com.google.common.collect.Lists;
import lombok.Data;
import mythware.domain.News;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class NewsListVo {
    private String id;  // 注解
    private String title; // 标题名称
    private String introduction; // 简介

    public static List<NewsListVo> convert(List<News> news) {
        if (CollectionUtils.isEmpty(news)) return Lists.newArrayList();

        return news.stream().map(NewsListVo::convert).collect(Collectors.toList());
    }

    public static NewsListVo convert(News news) {
        NewsListVo newsVo = new NewsListVo();
        BeanUtils.copyProperties(news, newsVo);
        return newsVo;
    }
}
