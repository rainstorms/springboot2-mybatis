package mythware.dto;

import lombok.Data;
import mythware.domain.BasicInformation;
import org.springframework.beans.BeanUtils;

@Data
public class UpdateBasicInformationDto {
    private String id; // 公司名称
    private String companyName; // 公司名称
    private String telephone; // 电话
    private String fax; // 传真
    private String email; // 邮箱
    private String address; // 地址

    public BasicInformation toBasicInformation() {
        BasicInformation information = new BasicInformation();
        BeanUtils.copyProperties(this, information);
        return information;
    }
}
