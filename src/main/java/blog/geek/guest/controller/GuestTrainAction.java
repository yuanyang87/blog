package blog.geek.guest.controller;

import blog.geek.entity.Train;
import blog.geek.guest.service.GuestTrainService;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 客户端训练请求
 * @author yuanyang
 * @version 1.0
 */
public class GuestTrainAction {

    private GuestTrainService guestTrainService;

    @RequestMapping(value = "/findAllTrains",method = RequestMethod.GET)
    public Result findAllTrains(){
        List<Train> trains = guestTrainService.findAllTrains();
        return ResultUtil.successResult(trains);
    }

}
