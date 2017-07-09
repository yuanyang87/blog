package blog.geek.test;

import blog.geek.entity.Contact;
import blog.geek.manager.service.MngContactService;
import blog.geek.user.service.UserContactService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

/**
 * @author yuanyang
 */
@Component
public class ContactTest extends BaseActionTest{

    @Autowired
    private UserContactService userContactService;

    @Autowired
    private MngContactService mngContactService;

    @Test
    public void insertTest(){

        Contact contact = new Contact();

        contact.setContactName("yuanyang");
        contact.setContactPhone("18875062338");
        contact.setContactContent("This is a test");

        userContactService.insertContact(contact);

    }

    @Test
    public void deleteTest(){
        mngContactService.deleteContact("0tUCuJML");
    }

    @Test
    public void findTest(){
        List<Contact> contacts = mngContactService.findAllContacts();
        Iterator iterator = contacts.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void deleteAllTest(){
        mngContactService.deleteAllContacts();
    }

}
