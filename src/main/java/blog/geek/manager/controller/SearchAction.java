package blog.geek.manager.controller;

import blog.geek.manager.service.SearchService;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户搜索请求
 */
@RestController
public class SearchAction {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/search/type/{key}",method = RequestMethod.GET)
    public Result searchByKeyWord(@PathVariable String type,@PathVariable String key,int pageIndex,int pageSize){
        List<Object> objects = searchService.searchAll(type,"%" + key + "%",pageIndex,pageSize);
        return ResultUtil.successResult(objects);
    }

}
