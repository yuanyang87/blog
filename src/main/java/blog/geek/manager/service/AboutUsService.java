package blog.geek.manager.service;

import blog.geek.dao.AboutUsDao;
import blog.geek.entity.AboutUs;
import blog.geek.exception.ErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutUsService {

    @Autowired
    private AboutUsDao aboutUsDao;

    public void updateAboutUs(AboutUs aboutUs){
        if (aboutUsDao.updateAboutUs(aboutUs) != 1) {
            throw new ErrorException("修改失败");
        }
    }

    public AboutUs findAboutUs(){
        AboutUs aboutUs = aboutUsDao.findAboutUs();
        if (aboutUs == null){
            throw new ErrorException("没有信息");
        }
        return aboutUs;
    }

}
