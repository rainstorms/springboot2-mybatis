package mythware.dto;

import lombok.Data;
import mythware.domain.News;
import mythware.utils.Id;
import org.springframework.beans.BeanUtils;

@Data
public class EditNewsDto {
    private String id;  // 主键
    private String title; // 标题名称
    private String introduction; // 简介
    private String content; // 内容
    private Long showTime; // 新闻展示时间
    private Integer state; // 状态 详情见 NewsState

    public News toAddNews() {
        News news = toNews();
        news.setId(Id.next());
        return news;
    }

    public News toNews() {
        News news = new News();
        BeanUtils.copyProperties(this, news);
        return news;
    }
}
