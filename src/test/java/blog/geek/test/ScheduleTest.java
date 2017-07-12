package blog.geek.test;

import blog.geek.manager.service.MngScheduleService;
import blog.geek.guest.service.GuestScheduleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
