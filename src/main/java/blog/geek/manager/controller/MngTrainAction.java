package blog.geek.manager.controller;

import blog.geek.entity.Train;
import blog.geek.manager.service.MngTrainService;
import blog.geek.utils.JsonUtil;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理训练请求处理
 * @author yuanyang
 * @version 1.0
 */
@RestController
@RequestMapping("/management")
public class MngTrainAction {

    @Autowired
    private MngTrainService mngTrainService;

    @RequestMapping(value = "/insertTrain",method = RequestMethod.POST)
    public Result insertTrain(Train train, MultipartFile[] pictures){
        mngTrainService.insertTrain(train,pictures);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/deleteTrain/{trainId}",method = RequestMethod.DELETE)
    public Result deleteTrain(@PathVariable String trainId){
        mngTrainService.deleteTrain(trainId);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/deleteTrains/{trainIdsJson}",method = RequestMethod.DELETE)
    public Result deleteTrains(@PathVariable String trainIdsJson){
        mngTrainService.deleteTrains((List<String>) JsonUtil.toPOJO(trainIdsJson,new ArrayList<String>().getClass()));
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/updateTrain",method =RequestMethod.POST)
    public Result updateTrain(Train train,MultipartFile[] pictures){
        mngTrainService.updateTrain(train,pictures);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/findAllTrains",method = RequestMethod.GET)
    public Result findAllTrains(){
        List<Train> trains = mngTrainService.findAllTrains();
        return ResultUtil.successResult(trains);
    }

}
