package blog.geek.manager.controller;

import blog.geek.entity.Article;
import blog.geek.manager.service.MngArticleService;
import blog.geek.utils.JsonUtil;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 随笔管理请求处理
 * @author yuanyang
 * @version 1.0
 */
@RestController
@RequestMapping("/management")
public class MngArticleAction {

    @Autowired
    private MngArticleService mngArticleService;

    @RequestMapping(value = "/insertArticle",method = RequestMethod.POST)
    public Result insertArticle(Article article, MultipartFile[] pictures){
        mngArticleService.insertArticle(article,pictures);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/deleteArticle/{articleId}",method = RequestMethod.DELETE)
    public Result deleteArticle(@PathVariable String articleId){
        mngArticleService.deleteArticle(articleId);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/deleteArticles/{articleIdJson}",method = RequestMethod.DELETE)
    public Result deleteArticles(@PathVariable String articleIdJson){
        mngArticleService.deleteArticles((List<String>) JsonUtil.toPOJO(articleIdJson,new ArrayList<String>().getClass()));
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/updateArticle",method = RequestMethod.POST)
    public Result updateArticle(Article article,MultipartFile[] pictures){
        mngArticleService.updateArticle(article,pictures);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/findAllArticles",method = RequestMethod.GET)
    public Result findAllArticles(){
        List<Article> articles = mngArticleService.findAllArticles();
        return ResultUtil.successResult(articles);
    }

}
