package blog.geek.manager.controller;

import blog.geek.entity.Contact;
import blog.geek.manager.service.MngContactService;
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
@RequestMapping("/management")
public class MngContactAction {

    @Autowired
    private MngContactService mngContactService;

    @RequestMapping(value = "/deleteContact/{contactId}",method = RequestMethod.DELETE)
    public Result deleteContact(@PathVariable String contactId) {
        mngContactService.deleteContact(contactId);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/deleteContacts/{jsonString}")
    public Result deleteContacts(@PathVariable String jsonString){
        mngContactService.deleteContacts((String[]) JsonUtil.toPOJO(jsonString,String[].class));
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/deleteAllContacts",method = RequestMethod.DELETE)
    public Result deleteAllContacts(){
        mngContactService.deleteAllContacts();
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/findAllContacts",method = RequestMethod.GET)
    public Result findAllContacts(){
        List<Contact> contacts = mngContactService.findAllContacts();
        return ResultUtil.successResult(contacts);
    }

}
