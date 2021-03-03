package mythware.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Sets;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mythware.domain.News;
import mythware.domain.PageModel;
import mythware.dto.EditNewsDto;
import mythware.dto.IdDto;
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

@Api(tags = {"新闻"})
@RestController
@RequestMapping("/NewsController")
public class NewsController {

    @Autowired NewsService service;

    private static final int TITLE_EMPTY = 511;
    private static final int TITLE_SIZE_OVER_30 = 512;
    private static final int INTRODUCTION_EMPTY = 513;
    private static final int CONTENT_EMPTY = 514;
    private static final int WRONG_STATE = 515;

    @ApiOperation("添加和修改")
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


    @ApiOperation("详情")
    @GetMapping("/findNews/{id}")
    public NewsVo findNews(@PathVariable("id") String id) {
        News news = service.findNews(id);
        return NewsVo.convert(news);
    }

    @ApiOperation("查询 - 按状态查 ")
    @PostMapping("/queryNewsesByState")
    public QueryNewsesByStateVo queryNewsesByState(@RequestBody QueryNewsesByStateDto dto) {
        Integer state = dto.getState();
        HashSet<Integer> validStates = News.getValidStates();
        if (!validStates.contains(state))
            throw new RuntimeException("state 值不正确");

        IPage<News> news = service.queryNewsesByState(state, dto.getPageModel());
        return QueryNewsesByStateVo.convert(news);
    }

    @ApiOperation("查询 - 首页展示 ")
    @PostMapping("/queryShowNewses")
    public QueryShowNewsesVo queryShowNewses(@RequestBody PageModel pageModel) {
        IPage<News> news = service.queryNewsesByState(News.NewsState.PUBLISHED.getCode(), pageModel);
        return QueryShowNewsesVo.convert(news);
    }

    @ApiOperation("查询 - 发布 ")
    @GetMapping("/publishNews/{id}")
    public ResultVo publishNews(@PathVariable("id") String id) {
        HashSet<Integer> validStates = Sets.newHashSet(News.NewsState.SAVED.getCode());
        int i = service.changeNewsState(id, validStates, News.NewsState.PUBLISHED.getCode());
        return i > 0 ? ResultVo.ok("发布成功") : ResultVo.fail("发布失败");
    }

    @ApiOperation("删除")
    @PostMapping("/deleteNews/{id}")
    public ResultVo deleteNews(@RequestBody IdDto idDto) {
        int i = service.deleteNews(idDto.getId());
        return i > 0 ? ResultVo.ok("删除成功") : ResultVo.fail("删除失败");
    }

}
