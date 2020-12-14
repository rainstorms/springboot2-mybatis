package mythware.dto;

import lombok.Data;
import mythware.domain.Region;
import mythware.utils.Id;
import org.springframework.beans.BeanUtils;

@Data
public class EditRegionDto {
    private String id;  // 主键
    private String region; // 区域
    private String name; // 姓名
    private String mobile; // 移动电话
    private String email; // 邮箱
    private String position; // 职位

    public Region toAddRegion() {
        Region region = toRegion();

        region.setId(Id.next());
        return region;
    }

    public Region toRegion() {
        Region region = new Region();
        BeanUtils.copyProperties(this, region);
        return region;
    }
}
