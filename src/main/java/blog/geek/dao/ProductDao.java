package blog.geek.dao;

import blog.geek.entity.Image;
import blog.geek.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 健身房产品Dao
 * @author yuanyang
 * @version 1.0
 */
@Repository
public interface ProductDao {

    /**
     * 添加产品以及对应图片
     * @param product
     * @param image
     * @return
     */
    int insertProduct(@Param("product") Product product, @Param("image") Image image);

    /**
     * 删除产品及其图片
     * @param productId
     * @return
     */
    int deleteProduct(String productId);

    /**
     * 更新产品及其图片
     * @param product
     * @param image
     * @return
     */
    int updateProduct(@Param("product") Product product,@Param("image") Image image);

    /**
     * 查找产品
     * @return
     */
    List<Product> findAllProducts(int offSet,int limit);

    /**
     * 通过类型查找产品
     * @param productType
     * @return
     */
    List<Product> findProductByType(String productType,int offSet,int limit);

    /**
     * 获取总条数
     * @return
     */
    int getTotal();
}
