package blog.geek.test;

import blog.geek.entity.Contact;
import blog.geek.manager.service.ContactService;
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
    private ContactService contactService;

    @Test
    public void insertTest(){

        Contact contact = new Contact();

        contact.setContactName("yuanyang");
        contact.setContactPhone("18875062338");
        contact.setContactContent("This is a test");

        contactService.insertContact(contact);

    }

    @Test
    public void deleteTest(){
        contactService.deleteContact("0tUCuJML");
    }

    @Test
    public void deleteAllTest(){
        contactService.deleteAllContacts();
    }

}
