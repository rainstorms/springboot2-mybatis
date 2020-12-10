package mythware.domain;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class Page {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;
    /**
     * 记录总数
     */
    private long totalSize;
    /**
     * 页码总数
     */
    private int totalPages;

    public static Page convert(PageInfo<?> showPlans) {
        Page page = new Page();
        BeanUtils.copyProperties(showPlans, page);

        page.setTotalPages(showPlans.getPages());
        page.setTotalSize(showPlans.getTotal());
        return page;
    }
}
