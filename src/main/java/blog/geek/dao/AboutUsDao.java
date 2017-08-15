package blog.geek.dao;

import blog.geek.entity.AboutUs;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface AboutUsDao {

    int updateAboutUs(AboutUs aboutUs);

    AboutUs findAboutUs();

}
