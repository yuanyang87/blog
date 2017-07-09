package blog.geek.user.controller;

import blog.geek.entity.Contact;
import blog.geek.user.service.UserContactService;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户使用联系我们模块----请求捕获
 * @author yuanyang
 * @version 1.0
 */
@RestController
public class UserContactAction {

    @Autowired
    private UserContactService userContactService;

    @RequestMapping(value = "/addContact",method = RequestMethod.POST)
    public Result insertContact(Contact contact){
        userContactService.insertContact(contact);
        return ResultUtil.successResult(null);
    }

}
