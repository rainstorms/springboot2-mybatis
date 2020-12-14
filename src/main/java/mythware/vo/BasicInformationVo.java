package mythware.vo;

import lombok.Data;
import mythware.domain.BasicInformation;
import org.springframework.beans.BeanUtils;

@Data
public class BasicInformationVo {
    private String companyName; // 公司名称
    private String telephone; // 电话
    private String fax; // 传真
    private String email; // 邮箱
    private String address; // 地址

    public static BasicInformationVo convert(BasicInformation basicInformation) {
        if (null == basicInformation) return null;

        BasicInformationVo basicInformationVo = new BasicInformationVo();
        BeanUtils.copyProperties(basicInformation, basicInformationVo);

        return basicInformationVo;
    }
}
