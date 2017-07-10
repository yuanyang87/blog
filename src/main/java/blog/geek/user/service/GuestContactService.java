package blog.geek.user.service;

import blog.geek.dao.ContactDao;
import blog.geek.entity.Contact;
import blog.geek.exception.ErrorException;
import blog.geek.utils.RandomStringUtil;
import blog.geek.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户联系健身房服务类
 * @author yuanyang
 * @version 1.0
 */
@Service
public class GuestContactService {

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
