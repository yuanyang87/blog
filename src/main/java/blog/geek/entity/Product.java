package blog.geek.entity;

/**
 * 健身房产品
 * @author yuanyang
 * @version 1.0
 */
public class Product {

    private String productId; //编号
    private String productName; //名称
    private String productContent; //内容
    private String productPrice;    //产品价格
    private String productType; //产品类型
    private String productImg; //产品图片

    //start getter and setter
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    //end getter and setter
}
