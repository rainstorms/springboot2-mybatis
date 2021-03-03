package mythware.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mythware.domain.Faq;
import mythware.dto.EditFaqDto;
import mythware.dto.IdDto;
import mythware.dto.QueryFaqsByQuestionCategoryDto;
import mythware.dto.QueryShowFaqsDto;
import mythware.service.FaqService;
import mythware.vo.FaqVo;
import mythware.vo.QueryFaqsByConditionVo;
import mythware.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Api(tags = {"技术支持"})
@RestController
@RequestMapping("/FaqController")
public class FaqController {

    @Autowired FaqService service;

    private static final int QUESTION_EMPTY = 511;
    private static final int WRONG_QUESTION_CATEGORIES = 512;
    private static final int ANSWER_EMPTY = 513;
    private static final int WRONG_ANSWER_CATEGORIES = 515;

    @ApiOperation("添加和修改")
    @PostMapping("/editFaq")
    public ResultVo editFaq(@RequestBody EditFaqDto faqDto) {
        // 校验参数
        ResultVo result = checkEditFaq(faqDto);
        if (null != result) return result;

        if (null == faqDto.getId()) { // 没有id 说明是新的faq
            Faq faq = faqDto.toAddFaq();
            int i = service.addFaq(faq);
            return i > 0 ? ResultVo.ok("添加成功") : ResultVo.fail("添加失败");
        }

        Faq faq = faqDto.toFaq();
        int i = service.updateFaq(faq);
        return i > 0 ? ResultVo.ok("修改成功") : ResultVo.fail("修改失败");
    }

    private ResultVo checkEditFaq(EditFaqDto faqDto) {
        int questionCategories = faqDto.getQuestionCategory();
        if (!Faq.getValidQuestionCategories().contains(questionCategories))
            return ResultVo.fail(WRONG_QUESTION_CATEGORIES, "question category 值不正确");

        if (StringUtils.isBlank(faqDto.getQuestion()))
            return ResultVo.fail(QUESTION_EMPTY, "question 不能为空");

        if (StringUtils.isBlank(faqDto.getAnswer()))
            return ResultVo.fail(ANSWER_EMPTY, "answer 不能为空");

        int answerCategories = faqDto.getAnswerCategory();
        if (!Faq.getValidAnswerCategories().contains(answerCategories))
            return ResultVo.fail(WRONG_ANSWER_CATEGORIES, "answer category 值不正确");

        return null;
    }


    @ApiOperation("详情")
    @GetMapping("/findFaq/{id}")
    public FaqVo findFaq(@PathVariable("id") String id) {
        Faq faq = service.findFaq(id);
        return FaqVo.convert(faq);
    }

    @ApiOperation("查询 - 按问题分类查 ")
    @PostMapping("/queryFaqsByQuestionCategory")
    public QueryFaqsByConditionVo queryFaqs(@RequestBody QueryFaqsByQuestionCategoryDto dto) {
        Integer category = dto.getCategory();
        Set<Integer> validStates = Faq.getValidQuestionCategories();
        if (!validStates.contains(category))
            throw new RuntimeException("question category 值不正确");

        IPage<Faq> faqs = service.queryFaqByCategory(category, dto.getPage());
        return QueryFaqsByConditionVo.convert(faqs);
    }

    @ApiOperation("查询 - 首页展示 ")
    @PostMapping("/queryShowFaqs")
    public QueryFaqsByConditionVo queryShowFaqs(@RequestBody QueryShowFaqsDto queryShowFaqsDto) {
        Integer category = queryShowFaqsDto.getCategory();
        Set<Integer> validStates = Faq.getValidQuestionCategories();
        validStates.add(0);
        if (!validStates.contains(category))
            throw new RuntimeException("category 值不正确");

        IPage<Faq> showFaqs = service.queryFaqByCondition(category, queryShowFaqsDto.getSearchValue(),
                queryShowFaqsDto.getPage());
        return QueryFaqsByConditionVo.convert(showFaqs);
    }

    @ApiOperation("删除")
    @PostMapping("/deleteFaq")
    public ResultVo deleteFaq(@RequestBody IdDto idDto) {
        int i = service.deleteFaq(idDto.getId());
        return i > 0 ? ResultVo.ok("删除成功") : ResultVo.fail("删除失败");
    }

}
