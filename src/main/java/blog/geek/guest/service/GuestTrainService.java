package blog.geek.guest.service;

import blog.geek.dao.TrainDao;
import blog.geek.entity.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户端获取训练服务类
 * @author yuanyang
 * @version 1.0
 */
@Service
public class GuestTrainService {

    @Autowired
    private TrainDao trainDao;

    public List<Train> findAllTrains(){
        List<Train> trains = trainDao.findAllTrains();
        return trains;
    }

}
