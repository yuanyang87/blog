package blog.geek.manager.controller;

import blog.geek.entity.Pager;
import blog.geek.entity.Train;
import blog.geek.manager.service.TrainService;
import blog.geek.utils.JsonUtil;
import blog.geek.utils.Result;
import blog.geek.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理训练请求处理
 * @author yuanyang
 * @version 1.0
 */
@RestController
public class TrainAction {

    @Autowired
    private TrainService trainService;

    @RequestMapping(value = "/management/insertTrain",method = RequestMethod.POST)
    public Result insertTrain(Train train) throws ParseException {
        trainService.insertTrain(train);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/management/deleteTrain/{trainId}",method = RequestMethod.DELETE)
    public Result deleteTrain(@PathVariable String trainId){
        trainService.deleteTrain(trainId);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/management/deleteTrains/{trainIdsJson}",method = RequestMethod.DELETE)
    public Result deleteTrains(@PathVariable String trainIdsJson){
        trainService.deleteTrains((List<String>) JsonUtil.toPOJO(trainIdsJson,new ArrayList<String>().getClass()));
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/management/updateTrain",method =RequestMethod.POST)
    public Result updateTrain(Train train){
        trainService.updateTrain(train);
        return ResultUtil.successResult(null);
    }

    @RequestMapping(value = "/findAllTrains",method = RequestMethod.GET)
    public Result findAllTrains(int pageIndex,int pageSize){
        Pager<Train> trainPager = trainService.findAllTrains(pageIndex,pageSize);
        return ResultUtil.successResult(trainPager);
    }

    @RequestMapping(value = "/management/findTrain/{trainId}",method = RequestMethod.GET)
    public Result findTrain(@PathVariable String trainId){
        Train train = trainService.findTrainById(trainId);
        return ResultUtil.successResult(train);
    }

}
