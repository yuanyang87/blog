package blog.geek.user.controller;

import blog.geek.entity.Product;
import blog.geek.user.service.GuestProductService;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 客户端产品请求处理
 * @author yuanyang
 * @version 1.0
 */
@RestController
public class GuestProductAction {

    @Autowired
    private GuestProductService guestProductService;

    /**
     * 获取所有产品
     * @return
     */
    @RequestMapping(value = "/findAllProducts",method = RequestMethod.GET)
    public Result findAllProduct(){
        List<Product> products = guestProductService.findAllProducts();
        return ResultUtil.successResult(products);
    }

    /**
     * 通过类别获取产品
     * @param productType
     * @return
     */
    @RequestMapping(value = "/findProductByType/{productType}",method = RequestMethod.GET)
    public Result findProductByType(@PathVariable String productType){
        List<Product> products = guestProductService.findProductByType(productType);
        return ResultUtil.successResult(products);
    }

}
