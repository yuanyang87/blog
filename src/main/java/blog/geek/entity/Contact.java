package blog.geek.entity;

/**
 * 联系健身房
 * @author yuanyang
 * @version 1.0
 */
public class Contact {

    private String contactId; //编号
    private String contactName; //联系人姓名
    private String contactPhone;    //联系人电话
    private String contactContent;  //主要内容
    private String contactTitle;    //留言标题

    //start getter and setter
    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactContent() {
        return contactContent;
    }

    public void setContactContent(String contactContent) {
        this.contactContent = contactContent;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    //end getter and setter

}
