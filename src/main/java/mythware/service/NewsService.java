package mythware.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import mythware.dao.NewsDao;
import mythware.domain.News;
import mythware.domain.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class NewsService {

    @Autowired NewsDao dao;

    @Transactional
    public int addNews(News banner) {
        return dao.insert(banner);
    }

    @Transactional
    public int updateNews(News banner) {
        return dao.updateById(banner);
    }

    /**
     * 修改 banner state
     *
     * @param id
     * @param oldState 原来的state 状态管理：原状态不对不能修改
     * @param newState 要改成的新的state
     * @return
     */
    @Transactional
    public int changeNewsState(String id, Set<Integer> oldState, Integer newState) {
        LambdaUpdateWrapper<News> w = Wrappers.lambdaUpdate();
        w.eq(News::getId, id);
        w.in(News::getState, oldState);
        w.set(News::getState, newState);
        return dao.update(null, w);
    }

    public News findNews(String id) {
        return dao.selectById(id);
    }

    public IPage<News> queryNewsesByState(Integer state, PageModel pageParams) {
        Page<News> objectPage = new Page<>(pageParams.getPageNum(), pageParams.getPageSize());
        LambdaQueryWrapper<News> w = Wrappers.lambdaQuery();
        w.orderByAsc(News::getCreateTime);
        w.eq(News::getState, state);
        return dao.selectPage(objectPage, w);
    }

    public int deleteNews(String id) {
        return dao.deleteById(id);
    }
}
