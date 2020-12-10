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
        News banner = toNews();

        banner.setId(Id.next());
        return banner;
    }

    public News toNews() {
        News banner = new News();
        BeanUtils.copyProperties(this, banner);

        if (StringUtils.isNotBlank(this.showTime))
            banner.setShowTime(DateTimes.parseyyyyMMddHHmmssString(this.showTime));

        return banner;
    }
}
