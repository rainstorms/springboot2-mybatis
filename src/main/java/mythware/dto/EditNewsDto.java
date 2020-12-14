package mythware.dto;

import lombok.Data;
import mythware.domain.News;
import mythware.utils.DateTimes;
import mythware.utils.Id;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

@Data
public class EditNewsDto {
    private String id;  // 主键
    private String title; // 标题名称
    private String introduction; // 简介
    private String content; // 内容
    private String showTime; // 新闻展示时间
    private int state; // 状态 详情见 NewsState

    public News toAddNews() {
        News news = toNews();

        news.setId(Id.next());
        return news;
    }

    public News toNews() {
        News news = new News();
        BeanUtils.copyProperties(this, news);

        if (StringUtils.isNotBlank(this.showTime))
            news.setShowTime(DateTimes.parseyyyyMMddHHmmssString(this.showTime));

        return news;
    }
}
