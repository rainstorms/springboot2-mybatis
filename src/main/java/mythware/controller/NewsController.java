package mythware.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Sets;
import mythware.domain.News;
import mythware.domain.Page;
import mythware.dto.EditNewsDto;
import mythware.dto.QueryNewsesByStateDto;
import mythware.service.NewsService;
import mythware.vo.NewsVo;
import mythware.vo.QueryNewsesByStateVo;
import mythware.vo.QueryShowNewsesVo;
import mythware.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/NewsController")
public class NewsController {

    @Autowired NewsService service;

    private static final int TITLE_EMPTY = 511;
    private static final int TITLE_SIZE_OVER_30 = 512;
    private static final int INTRODUCTION_EMPTY = 513;
    private static final int CONTENT_EMPTY = 514;
    private static final int WRONG_STATE = 515;

    /**
     * 添加和修改 news
     *
     * @param newsDto
     * @return
     */
    @PostMapping("/editNews")
    public ResultVo editNews(@RequestBody EditNewsDto newsDto) {
        // 校验参数
        ResultVo result = checkEditNews(newsDto);
        if (null != result) return result;

        if (null == newsDto.getId()) { // 没有id 说明是新的news
            News news = newsDto.toAddNews();
            int i = service.addNews(news);
            return i > 0 ? ResultVo.ok("添加成功") : ResultVo.fail("添加失败");
        }

        News news = newsDto.toNews();
        int i = service.updateNews(news);
        return i > 0 ? ResultVo.ok("修改成功") : ResultVo.fail("修改失败");
    }

    private ResultVo checkEditNews(EditNewsDto newsDto) {
        int titleLength = StringUtils.trim(newsDto.getTitle()).length();
        if (titleLength == 0)
            return ResultVo.fail(TITLE_EMPTY, "title不能为空");

        if (titleLength > 30)
            return ResultVo.fail(TITLE_SIZE_OVER_30, "title不能超过30个字");

        if (StringUtils.isBlank(newsDto.getIntroduction()))
            return ResultVo.fail(INTRODUCTION_EMPTY, "introduction 不能为空");

        if (StringUtils.isBlank(newsDto.getContent()))
            return ResultVo.fail(CONTENT_EMPTY, "content 不能为空");

        int state = newsDto.getState();
        if (!News.getValidStates().contains(state))
            return ResultVo.fail(WRONG_STATE, "state 值不正确");

        return null;
    }


    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("/findNews/{id}")
    public NewsVo findNews(@PathVariable("id") String id) {
        News news = service.findNews(id);
        return NewsVo.convert(news);
    }

    /**
     * 按状态查询 news
     *
     * @return
     */
    @PostMapping("/queryNewsesByState")
    public QueryNewsesByStateVo queryNewsesByState(@RequestBody QueryNewsesByStateDto dto) {
        Integer state = dto.getState();
        HashSet<Integer> validStates = News.getValidStates();
        if (!validStates.contains(state))
            throw new RuntimeException("state 值不正确");

        PageInfo<News> news = service.queryNewsesByState(state, dto.getPage());
        return QueryNewsesByStateVo.convert(news);
    }

    /**
     * 首页查询展示的新闻
     *
     * @param page
     * @return
     */
    @PostMapping("/queryShowNewses")
    public QueryShowNewsesVo queryShowNewses(@RequestBody Page page) {
        PageInfo<News> news = service.queryNewsesByState(News.NewsState.PUBLISHED.getCode(), page);
        return QueryShowNewsesVo.convert(news);
    }

    /**
     * 发布 news
     *
     * @param id
     * @return
     */
    @GetMapping("/publishNews/{id}")
    public ResultVo publishNews(@PathVariable("id") String id) {
        HashSet<Integer> validStates = Sets.newHashSet(News.NewsState.SAVED.getCode());
        int i = service.changeNewsState(id, validStates, News.NewsState.PUBLISHED.getCode());
        return i > 0 ? ResultVo.ok("删除成功") : ResultVo.fail("删除失败");
    }

    /**
     * 删除news
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteNews/{id}")
    public ResultVo deleteNews(@PathVariable("id") String id) {
        HashSet<Integer> validStates = News.getValidStates();
        int i = service.changeNewsState(id, validStates, News.NewsState.DELETE.getCode());
        return i > 0 ? ResultVo.ok("删除成功") : ResultVo.fail("删除失败");
    }

}
