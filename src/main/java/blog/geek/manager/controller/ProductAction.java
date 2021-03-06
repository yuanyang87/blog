package blog.geek.manager.controller;

import blog.geek.entity.Pager;
import blog.geek.entity.Product;
import blog.geek.manager.service.ProductService;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 管理产品请求处理
 * @author yuanyang
 * @version 1.0
 */
@RestController
public class ProductAction {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/management/insertProduct",method = RequestMethod.POST)
    public Result insertProduct(Product product, MultipartFile picture){
        productService.insertProduct(product,picture);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/management/deleteProduct/{productId}",method = RequestMethod.DELETE)
    public Result deleteProduct(@PathVariable String productId){
        productService.deleteProduct(productId);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/management/updateProduct",method = RequestMethod.POST)
    public Result updateProduct(Product product, MultipartFile picture){
        productService.updateProduct(product,picture);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/findAllProducts",method = RequestMethod.GET)
    public Result findAllProducts(int pageIndex,int pageSize){
        Pager<Product> productPager = productService.findAllProducts(pageIndex,pageSize);
        return ResultUtil.successResult(productPager);
    }

    @RequestMapping(value = "/findProduct/{productId}",method = RequestMethod.GET)
    public Result findProduct(@PathVariable String productId){
        Product product = productService.findProductById(productId);
        return ResultUtil.successResult(product);
    }

    @RequestMapping(value = "/findProductByType/{productType}",method = RequestMethod.GET)
    public Result findProductByType(@PathVariable String productType,int pageIndex,int pageSize){
        Pager<Product> productPager = productService.findProductByType(productType,pageIndex,pageSize);
        return ResultUtil.successResult(productPager);
    }

}
