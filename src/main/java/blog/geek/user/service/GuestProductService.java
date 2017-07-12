package blog.geek.user.service;

import blog.geek.dao.ProductDao;
import blog.geek.entity.Product;
import blog.geek.exception.ErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户端获取产品列表
 * @author yuanyang
 * @version 1.0
 */
@Service
public class GuestProductService {

    @Autowired
    private ProductDao productDao;

    public List<Product> findAllProducts(){
        List<Product> products = productDao.findAllProducts();
        if (products == null)
            throw new ErrorException("没有任何产品");
        return products;
    }

    public List<Product> findProductByType(String productType){
        List<Product> products = productDao.findProductByType(productType);
        if (products == null)
            throw new ErrorException("该分类没有任何产品");
        return products;
    }

}
