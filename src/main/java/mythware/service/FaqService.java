package mythware.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mythware.dao.FaqDao;
import mythware.domain.Page;
import mythware.domain.Faq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class FaqService {

    @Autowired FaqDao dao;

    @Transactional
    public int addFaq(Faq faq) {
        return dao.addFaq(faq);
    }

    @Transactional
    public int updateFaq(Faq faq) {
        return dao.updateFaq(faq);
    }

    /**
     * 修改 faq state
     *
     * @param id
     * @param oldState 原来的state 状态管理：原状态不对不能修改
     * @param newState 要改成的新的state
     * @return
     */
    @Transactional
    public int changeFaqState(String id, Integer oldState, Integer newState) {
        return dao.changeFaqState(id, oldState, newState);
    }

    public Faq findFaq(String id) {
        return dao.findFaq(id);
    }

    public PageInfo<Faq> queryFaqByCategory(Integer category, Page page) {
        PageHelper.startPage(page);
        List<Faq> faqs = dao.queryFaqByCategory(category);
        return new PageInfo<>(faqs);
    }

    public PageInfo<Faq> queryFaqByCondition(Integer category, String searchValue, Page page) {
        PageHelper.startPage(page);
        List<Faq> faqs = dao.queryFaqByCondition(category, searchValue);
        return new PageInfo<>(faqs);
    }
}
