package mythware.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mythware.dao.NewsDao;
import mythware.domain.News;
import mythware.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NewsService {

    @Autowired NewsDao dao;

    @Transactional
    public int addNews(News banner) {
        return dao.addNews(banner);
    }

    @Transactional
    public int updateNews(News banner) {
        return dao.updateNews(banner);
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
        return dao.changeNewsState(id, oldState, newState);
    }

    public News findNews(String id) {
        return dao.findNews(id);
    }

    public PageInfo<News> queryNewsesByState(Integer state, Page page) {
        PageHelper.startPage(page);
        return new PageInfo<>(dao.queryNewsesByState(state));
    }
}
