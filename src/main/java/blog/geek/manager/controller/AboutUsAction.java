package blog.geek.manager.controller;

import blog.geek.entity.AboutUs;
import blog.geek.manager.service.AboutUsService;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AboutUsAction {

    @Autowired
    private AboutUsService aboutUsService;

    @RequestMapping(value = "/management/updateAboutUs",method = RequestMethod.POST)
    public Result updateAboutUs(AboutUs aboutUs){
        aboutUsService.updateAboutUs(aboutUs);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/findAboutUs",method = RequestMethod.GET)
    public Result findAboutUs(){
        AboutUs aboutUs = aboutUsService.findAboutUs();
        return ResultUtil.successResult(aboutUs);
    }

}
