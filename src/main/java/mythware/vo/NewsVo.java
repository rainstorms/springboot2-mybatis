package mythware.vo;

import lombok.Data;
import mythware.domain.News;
import org.springframework.beans.BeanUtils;

@Data
public class NewsVo {
    private String id;  // 注解
    private String title; // 标题名称
    private String introduction; // 简介
    private String content; // 内容
    private Long showTime; // 新闻展示时间

    public static NewsVo convert(News news) {
        if (null == news) return null;

        NewsVo newsVo = new NewsVo();
        BeanUtils.copyProperties(news, newsVo);
        return newsVo;
    }
}
