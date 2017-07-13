package blog.geek.manager.controller;

import blog.geek.entity.Article;
import blog.geek.entity.Pager;
import blog.geek.manager.service.ArticleService;
import blog.geek.utils.JsonUtil;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 随笔管理请求处理
 * @author yuanyang
 * @version 1.0
 */
@RestController
public class ArticleAction {

    @Autowired
    private ArticleService articleService;

    /**
     * 添加一篇文章,上传文章的信息,并把使用到的图片Id返回
     * 因为上传的图片不一定都使用了,将未使用的删除以免占用存储空间
     * @param article
     * @return
     */
    @RequestMapping(value = "/management/insertArticle",method = RequestMethod.POST)
    public Result insertArticle(Article article){
        articleService.insertArticle(article);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/management/management/deleteArticle/{articleId}",method = RequestMethod.DELETE)
    public Result deleteArticle(@PathVariable String articleId){
        articleService.deleteArticle(articleId);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/management/deleteArticles/{articleIdJson}",method = RequestMethod.DELETE)
    public Result deleteArticles(@PathVariable String articleIdJson){
        articleService.deleteArticles((List<String>) JsonUtil.toPOJO(articleIdJson,new ArrayList<String>().getClass()));
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/management/updateArticle",method = RequestMethod.POST)
    public Result updateArticle(Article article){
        articleService.updateArticle(article);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/findAllArticles",method = RequestMethod.GET)
    public Result findAllArticles(int pageIndex, int pageSize){
        Pager<Article> articlePager = articleService.findAllArticles(pageIndex,pageSize);
        return ResultUtil.successResult(articlePager);
    }

}
