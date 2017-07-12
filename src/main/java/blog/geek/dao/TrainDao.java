package blog.geek.dao;

import blog.geek.entity.Train;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 训练Dao
 * @author yuanyang
 * @version 1.0
 */
@Repository
public interface TrainDao {

    int insertTrain(Train train);

    int deleteTrain(String trainId);

    int deleteTrains(@Param("trainIds") List<String> trainIds);

    int updateTrain(Train train);

    List<Train> findAllTrains();

}
