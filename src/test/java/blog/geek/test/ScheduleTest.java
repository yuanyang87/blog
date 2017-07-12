package blog.geek.test;

import blog.geek.entity.Schedule;
import blog.geek.manager.service.MngScheduleService;
import blog.geek.user.service.GuestScheduleService;
import blog.geek.utils.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 课程表测试
 */
public class ScheduleTest extends BaseActionTest{

    @Autowired
    private MngScheduleService mngScheduleService;

    @Autowired
    private GuestScheduleService guestScheduleService;

    @Test
    public void insert(){

    }

}
