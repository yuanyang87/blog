package blog.geek.guest.controller;

import blog.geek.entity.Article;
import blog.geek.guest.service.GuestArticleService;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 客户端查看文章请求处理
 * @author yuanyang
 * @version 1.0
 */
@RestController
public class GuestArticleAction {

    @Autowired
    private GuestArticleService guestArticleService;

    @RequestMapping(value = "/findAllArticles",method = RequestMethod.GET)
    public Result findAllArticles(){
        List<Article> articles = guestArticleService.findAllArticles();
        return ResultUtil.successResult(articles);
    }
}
