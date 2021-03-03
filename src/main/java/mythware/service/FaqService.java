package mythware.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import mythware.dao.FaqDao;
import mythware.domain.Faq;
import mythware.domain.PageModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FaqService {

    @Autowired FaqDao dao;

    @Transactional
    public int addFaq(Faq faq) {
        return dao.insert(faq);
    }

    @Transactional
    public int updateFaq(Faq faq) {
        return dao.updateById(faq);
    }

    public Faq findFaq(String id) {
        return dao.selectById(id);
    }

    public IPage<Faq> queryFaqByCategory(Integer category, PageModel pageParams) {
        Page<Faq> objectPage = new Page<>(pageParams.getPageNum(), pageParams.getPageSize());
        LambdaQueryWrapper<Faq> w = Wrappers.lambdaQuery();
        w.eq(Faq::getQuestionCategory, category);
        w.orderByAsc(Faq::getCreateTime);
        return dao.selectPage(objectPage, w);
    }

    public IPage<Faq> queryFaqByCondition(Integer category, String searchValue, PageModel pageParams) {
        LambdaQueryWrapper<Faq> w = Wrappers.lambdaQuery();
        w.eq(category != 0, Faq::getQuestionCategory, category);
        w.like(StringUtils.isNotEmpty(searchValue), Faq::getQuestion, searchValue);
        w.orderByAsc(Faq::getCreateTime);
        Page<Faq> objectPage = new Page<>(pageParams.getPageNum(), pageParams.getPageSize());
        return dao.selectPage(objectPage, w);
    }

    @Transactional
    public int deleteFaq(String id) {
        return dao.deleteById(id);
    }
}
