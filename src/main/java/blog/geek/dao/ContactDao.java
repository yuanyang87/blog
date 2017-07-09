package blog.geek.dao;

import blog.geek.entity.Contact;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 联系健身房Dao:增/删/查
 * @author yuanyang
 * @version 1.0
 */
@Repository
public interface ContactDao {

    //增加一条记录
    int insertContact(Contact contact);

    //删除一条记录
    int deleteContact(String contactId);

    //批量删除记录
    int deleteContacts(String[] contactIds);

    //全部删除
    int deleteAllContacts();

    //查找所有联系记录
    List<Contact> findAllContacts();

    /*
    //按电话查找
    List<Contact> findContactByPhone(String contactPhone);
    //按姓名查找
    List<Contact> findContactByName(String contactName);
    //按内容关键字查找
    List<Contact> findContactByContent(String keyWord);
    */

}
