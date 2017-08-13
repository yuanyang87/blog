package blog.geek.manager.service;

import blog.geek.dao.ContactDao;
import blog.geek.entity.Contact;
import blog.geek.entity.Pager;
import blog.geek.exception.ErrorException;
import blog.geek.utils.RandomStringUtil;
import blog.geek.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员管理联系我们模块
 * @author yuanyang
 * @version 1.0
 */
@Service
public class ContactService {

    @Autowired
    private ContactDao contactDao;

    /**
     * 增加一条联系
     * @param contact
     */
    public void insertContact(Contact contact){
        contact.setContactId(RandomStringUtil.unrepeatableString(8));   //随机生成8位id
        if (contactDao.insertContact(contact) != 1){
            throw new ErrorException("服务器繁忙,请稍候再试~");
        }
    }

    /**
     * 删除一条联系
     * @param contactId
     */
    public void deleteContact(String contactId){
        if (contactDao.deleteContact(contactId) != 1){
            throw new ErrorException("删除失败,服务器可能出错啦~,请稍候再试 ");
        }
    }

    /**
     * 批量删除联系
     * @param contactIds
     */
    public void deleteContacts(String[] contactIds){
        if (contactDao.deleteContacts(contactIds) != contactIds.length)
            throw new ErrorException("删除失败,服务器可能出错啦~,请稍候再试");
    }

    /**
     * 删除所有的联系
     */
    public void deleteAllContacts(){
        if (contactDao.deleteAllContacts() < 0)
            throw new ErrorException("删除失败,服务器出故障,请联系管理人员!");
    }

    /**
     * 查找所有的联系我们
     * @param pageIndex
     * @param pageSize
     */
    public Pager<Contact> findAllContacts(int pageIndex, int pageSize){
        Pager<Contact> contactPager = new Pager<Contact>(pageIndex,pageSize,contactDao.getTotal());

        List<Contact> contacts = contactDao.findAllContacts(contactPager.getOffSet(),pageSize);

        if (contacts == null || contacts.size() == 0){
            throw new ErrorException("暂时没有信息");
        }
        contactPager.setResult(contacts);

        return contactPager;
    }

    /**
     * 后台输入验证,防止前端未进行验证
     * @param contact
     */
    public void isValidate(Contact contact){
        if (ValidateUtil.isEmpty(contact.getContactName()))
            throw new ErrorException("您的姓名输入有误,请重新输入~");
        if (!ValidateUtil.matches("^1[3|4|5|8][0-9]\\d{8}$",contact.getContactPhone()))
            throw new ErrorException("您的手机号输入有误,请重新输入~");
        if (ValidateUtil.isEmpty(contact.getContactContent()))
            throw new ErrorException("您还没有输入内容哟~");
    }


}
