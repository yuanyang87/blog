package blog.geek.manager.controller;

import blog.geek.manager.service.SearchService;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashMap;

/**
 * 用户搜索请求
 */
@RestController
public class SearchAction {

    @Autowired
    private SearchService searchService;

    /**
     * 搜索可以按时间和标题
     * @param type
     * @param key
     * @return
     */
    @RequestMapping(value = "/search/{type}",method = RequestMethod.GET)
    public Result searchByKeyWord(@PathVariable String type,String key) throws ParseException {
        HashMap<String,Object> objects = searchService.searchAll(type,key);
        return ResultUtil.successResult(objects);
    }

}
