package mythware.controller;

import com.github.pagehelper.PageInfo;
import mythware.domain.Faq;
import mythware.dto.EditFaqDto;
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

@RestController
@RequestMapping("/FaqController")
public class FaqController {

    @Autowired FaqService service;

    private static final int QUESTION_EMPTY = 511;
    private static final int WRONG_QUESTION_CATEGORIES = 512;
    private static final int ANSWER_EMPTY = 513;
    private static final int WRONG_ANSWER_CATEGORIES = 515;

    /**
     * 添加和修改faq
     *
     * @param faqDto
     * @return
     */
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


    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("/findFaq/{id}")
    public FaqVo findFaq(@PathVariable("id") String id) {
        Faq faq = service.findFaq(id);
        return FaqVo.convert(faq);
    }

    /**
     * 按问题分类 条件查询 faq
     *
     * @return
     */
    @PostMapping("/queryFaqsByQuestionCategory")
    public QueryFaqsByConditionVo queryFaqs(@RequestBody QueryFaqsByQuestionCategoryDto dto) {
        Integer category = dto.getCategory();
        Set<Integer> validStates = Faq.getValidQuestionCategories();
        if (!validStates.contains(category))
            throw new RuntimeException("question category 值不正确");

        PageInfo<Faq> faqs = service.queryFaqByCategory(category, dto.getPage());
        return QueryFaqsByConditionVo.convert(faqs);
    }

    /**
     * 首页 展示查询接口
     *
     * @param queryShowFaqsDto
     * @return
     */
    @PostMapping("/queryShowFaqs")
    public QueryFaqsByConditionVo queryShowFaqs(@RequestBody QueryShowFaqsDto queryShowFaqsDto) {
        Integer category = queryShowFaqsDto.getCategory();
        Set<Integer> validStates = Faq.getValidQuestionCategories();
        validStates.add(0);
        if (!validStates.contains(category))
            throw new RuntimeException("category 值不正确");

        PageInfo<Faq> showFaqs = service.queryFaqByCondition(category, queryShowFaqsDto.getSearchValue(),
                queryShowFaqsDto.getPage());
        return QueryFaqsByConditionVo.convert(showFaqs);
    }

    /**
     * 删除faq
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteFaq/{id}")
    public ResultVo deleteFaq(@PathVariable("id") String id) {
        int i = service.changeFaqState(id, Faq.FaqState.NORMAL.getCode(), Faq.FaqState.DELETE.getCode());
        return i > 0 ? ResultVo.ok("删除成功") : ResultVo.fail("删除失败");
    }

}
