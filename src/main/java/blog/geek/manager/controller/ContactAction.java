package blog.geek.manager.controller;

import blog.geek.entity.Contact;
import blog.geek.entity.Pager;
import blog.geek.manager.service.ContactService;
import blog.geek.utils.JsonUtil;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 管理员管理联系我们模块----请求捕获
 * @author yuanyang
 * @version 1.0
 */
@RestController
public class ContactAction {

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/management/deleteContact/{contactId}",method = RequestMethod.DELETE)
    public Result deleteContact(@PathVariable String contactId) {
        contactService.deleteContact(contactId);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/management/deleteContacts/{jsonString}",method = RequestMethod.DELETE)
    public Result deleteContacts(@PathVariable String jsonString){
        contactService.deleteContacts((String[]) JsonUtil.toPOJO(jsonString,String[].class));
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/management/deleteAllContacts",method = RequestMethod.DELETE)
    public Result deleteAllContacts(){
        contactService.deleteAllContacts();
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/management/findAllContacts",method = RequestMethod.GET)
    public Result findAllContacts(int pageIndex,int pageSize){
        Pager<Contact> contactPager = contactService.findAllContacts(pageIndex,pageSize);
        return ResultUtil.successResult(contactPager);
    }

    @RequestMapping(value = "/addContact",method = RequestMethod.POST)
    public Result insertContact(Contact contact){
        contactService.insertContact(contact);
        return ResultUtil.successResult(null);
    }
}
