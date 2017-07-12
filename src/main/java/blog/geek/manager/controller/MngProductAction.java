package blog.geek.manager.controller;

import blog.geek.entity.Product;
import blog.geek.manager.service.MngProductService;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 管理产品请求处理
 * @author yuanyang
 * @version 1.0
 */
@RestController
@RequestMapping("/management")
public class MngProductAction {

    @Autowired
    private MngProductService mngProductService;

    @RequestMapping(value = "/insertProduct",method = RequestMethod.POST)
    public Result insertProduct(Product product, MultipartFile picture){
        mngProductService.insertProduct(product,picture);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/deleteProduct/{productId}",method = RequestMethod.DELETE)
    public Result deleteProduct(@PathVariable String productId){
        mngProductService.deleteProduct(productId);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/updateProduct",method = RequestMethod.POST)
    public Result updateProduct(Product product, MultipartFile picture){
        mngProductService.updateProduct(product,picture);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/findAllProducts",method = RequestMethod.GET)
    public Result findAllProducts(){
        List<Product> products = mngProductService.findAllProducts();
        return ResultUtil.successResult(products);
    }

    @RequestMapping(value = "/findProductByType/{productType}",method = RequestMethod.GET)
    public Result findProductByType(@PathVariable String productType){
        List<Product> products = mngProductService.findProductByType(productType);
        return ResultUtil.successResult(products);
    }

}
