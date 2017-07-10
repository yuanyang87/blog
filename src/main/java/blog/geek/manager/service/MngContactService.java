package blog.geek.manager.service;

import blog.geek.dao.ContactDao;
import blog.geek.entity.Contact;
import blog.geek.exception.ErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员管理联系我们模块
 * @author yuanyang
 * @version 1.0
 */
@Service
public class MngContactService {

    @Autowired
    private ContactDao contactDao;

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
     */
    public List<Contact> findAllContacts(){
        List<Contact> contacts = contactDao.findAllContacts();
        return contacts;
    }


}
