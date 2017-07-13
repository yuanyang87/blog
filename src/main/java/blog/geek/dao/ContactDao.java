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

    /**
     * 增加一条记录
     * @param contact
     * @return
     */
    int insertContact(Contact contact);

    /**
     * 删除一条记录
     * @param contactId
     * @return
     */
    int deleteContact(String contactId);

    /**
     * 批量删除记录
     * @param contactIds
     * @return
     */
    int deleteContacts(String[] contactIds);

    /**
     * 全部删除
     * @return
     */
    int deleteAllContacts();

    /**
     * 查找所有联系记录
     * @return
     */
    List<Contact> findAllContacts(int offSet,int limit);

    /**
     * 获取总条数
     * @return
     */
    int getTotal();
}
