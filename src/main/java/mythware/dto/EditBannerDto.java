package mythware.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import mythware.domain.Banner;
import mythware.utils.Id;
import org.springframework.beans.BeanUtils;

@ApiModel
@Data
public class EditBannerDto {
    private String id;
    private String title;
    private String image2560; // 2560px的图
    private String image1440; // 1440px的图
    private String image1024; // 1024px的图
    @ApiModelProperty(value = "768px的图")
    private String image768; // 768px的图
    @ApiModelProperty(value = "banner生效开始时间")
    private Long startTime;
    @ApiModelProperty(value = "banner生效结束时间")
    private Long endTime;

    public Banner toAddBanner() {
        Banner banner = toBanner();

        banner.setId(Id.next());
        banner.setSequenceNo(999); // 默认序号999
        banner.setState(Banner.BannerState.ENABLE.getCode());// 新建的记录状态是 1：启用
        return banner;
    }

    public Banner toBanner() {
        Banner banner = new Banner();
        BeanUtils.copyProperties(this, banner);
        return banner;
    }
}
