package blog.geek.manager.service;

import blog.geek.dao.ImageDao;
import blog.geek.dao.ProductDao;
import blog.geek.entity.Image;
import blog.geek.entity.Pager;
import blog.geek.entity.Product;
import blog.geek.exception.ErrorException;
import blog.geek.utils.FileUtil;
import blog.geek.utils.RandomStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 管理员产品服务类
 * @author yuanyang
 * @version 1.0
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private FileUtil fileUtil;

    /**
     * 添加一个产品
     * @param product
     * @param picture
     */
    public void insertProduct(Product product, MultipartFile picture){
        if (picture == null)
            throw new ErrorException("为您的产品选个好看的图片吧~");

        product.setProductId(RandomStringUtil.unrepeatableString(8));
        Image image = new Image(RandomStringUtil.unrepeatableString(8),
                    picture.getOriginalFilename(),product.getProductId());

        if (!fileUtil.saveImage(picture,product.getClass().getSimpleName(),product.getProductId()))
            throw new ErrorException("服务器在开小差,图片存放失败");

        image.setImageAddress(fileUtil.getVirtualPath());

        if (productDao.insertProduct(product,image) != 1){
            fileUtil.deleteImage(image.getImageAddress());
            throw new ErrorException("添加产品失败,请重新操作");
        }
    }

    /**
     * 删除产品和图片
     * @param productId
     */
    public void deleteProduct(String productId){
        List<String> imagePath = imageDao.getImagePath(productId);
        if (productDao.deleteProduct(productId) != 1)
            throw new ErrorException("删除产品操作失败");
        fileUtil.deleteImage(imagePath.get(0));
    }

    /**
     * 更新产品和图片
     * @param product
     * @param picture
     */
    public void updateProduct(Product product,MultipartFile picture){
        if (picture == null){
            productDao.updateProduct(product,new Image());
            return;
        }
        List<String> imagePath = imageDao.getImagePath(product.getProductId());
        Image image = new Image(RandomStringUtil.unrepeatableString(8),
                picture.getOriginalFilename(),product.getProductId());

        fileUtil.saveImage(picture,Product.class.getSimpleName(),product.getProductId());

        image.setImageAddress(fileUtil.getVirtualPath());

        if (productDao.updateProduct(product,image) != 1){
            fileUtil.deleteImage(fileUtil.getRealPath());
            throw new ErrorException("数据库出错啦,请重新操作");
        }

        fileUtil.deleteImage(imagePath.get(0));
    }

    /**
     * 查找所有产品
     * @return
     * @param pageIndex
     * @param pageSize
     */
    public Pager<Product> findAllProducts(int pageIndex, int pageSize){
        Pager<Product> productPager = new Pager<Product>(pageIndex,pageSize,productDao.getTotal());

        List<Product> products = productDao.findAllProducts(productPager.getOffSet(),pageSize);

        if (products == null || products.size() == 0)
            throw new ErrorException("没有找到你要的数据");

        productPager.setResult(products);
        return productPager;
    }

    /**
     *
     * @param productType
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public Pager<Product> findProductByType(String productType, int pageIndex, int pageSize){
        Pager<Product> productPager = new Pager<Product>(pageIndex,pageSize,productDao.getTotal());

        List<Product> products = productDao.findProductByType(productType,productPager.getOffSet(),pageSize);
        if (products == null || products.size() == 0)
            throw new ErrorException("没有找到你要的数据");

        productPager.setResult(products);

        return productPager;
    }

    public Product findProductById(String productId) {

        Product product = productDao.findProductById(productId);
        if (product == null){
            throw new ErrorException("查找失败");
        }
        return product;

    }
}
