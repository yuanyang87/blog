package blog.geek.entity;

/**
 * 图片对象,只用于在数据存储的时候使用
 * @author yuanyang
 * @version 1.0
 */
public class Image {

    private String imageId; //图片ID
    private String imageName;   //图片原名称
    private String imageAddress;    //图片地址
    private String imageRef;    //图片所指向的实体(外键关系)

    //constructor
    public Image(String imageId, String imageName, String imageRef) {
        this.imageId = imageId;
        this.imageName = imageName;
        this.imageRef = imageRef;
    }

    public Image(String imageId, String imageName, String imageAddress, String imageRef) {
        this.imageId = imageId;
        this.imageName = imageName;
        this.imageAddress = imageAddress;
        this.imageRef = imageRef;
    }

    //start getter and setter
    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getImageRef() {
        return imageRef;
    }

    public void setImageRef(String imageRef) {
        this.imageRef = imageRef;
    }
    //end getter and setter
}
