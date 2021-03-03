package mythware.domain;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class PageModel<T> {
    /**
     * 当前页码
     */
    private long pageNum;
    /**
     * 每页数量
     */
    private long pageSize;
    /**
     * 页码总数
     */
    private long total;

    public static <T> PageModel<T> convert(IPage<T> page) {
        PageModel<T> model = new PageModel<T>();
        model.total = page.getTotal();
        model.pageNum = page.getCurrent();
        model.pageSize = page.getSize();
        return model;
    }
}
