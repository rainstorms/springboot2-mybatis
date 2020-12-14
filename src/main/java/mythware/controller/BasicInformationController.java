package mythware.controller;

import mythware.domain.BasicInformation;
import mythware.dto.UpdateBasicInformationDto;
import mythware.service.BasicInformationService;
import mythware.vo.BasicInformationVo;
import mythware.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/BasicInformationController")
public class BasicInformationController {

    @Autowired BasicInformationService service;

    private static final int COMPANYNAME_EMPTY = 511;
    private static final int TELEPHONE_EMPTY = 512;
    private static final int FAX_EMPTY = 513;
    private static final int WRONG_EMAIL = 514;
    private static final int ADDRESS_EMPTY = 515;

    /**
     * 修改 basicInformation
     *
     * @return
     */
    @PostMapping("/updateBasicInformation")
    public ResultVo updateBasicInformation(@RequestBody UpdateBasicInformationDto dto) {
        // 校验参数
        ResultVo result = checkUpdateBasicInformation(dto);
        if (null != result) return result;

        BasicInformation basicInformation = dto.toBasicInformation();
        int i = service.updateBasicInformation(basicInformation);
        return i > 0 ? ResultVo.ok("修改成功") : ResultVo.fail("修改失败");
    }

    private ResultVo checkUpdateBasicInformation(UpdateBasicInformationDto basicInformationDto) {
        if (StringUtils.isBlank(basicInformationDto.getCompanyName()))
            return ResultVo.fail(COMPANYNAME_EMPTY, "companyName 不能为空");

        if (StringUtils.isBlank(basicInformationDto.getTelephone()))
            return ResultVo.fail(TELEPHONE_EMPTY, "telephone 不能为空");

        if (StringUtils.isBlank(basicInformationDto.getFax()))
            return ResultVo.fail(FAX_EMPTY, "fax 不能为空");

        if (!basicInformationDto.getEmail().endsWith("@mythware.com"))
            return ResultVo.fail(WRONG_EMAIL, "email 格式不对");

        if (StringUtils.isBlank(basicInformationDto.getAddress()))
            return ResultVo.fail(ADDRESS_EMPTY, "address 格式不对");

        return null;
    }


    /**
     * 详情
     *
     * @return
     */
    @GetMapping("/findBasicInformation")
    public BasicInformationVo findBasicInformation() {
        BasicInformation basicInformation = service.findBasicInformation();
        return BasicInformationVo.convert(basicInformation);
    }
}
