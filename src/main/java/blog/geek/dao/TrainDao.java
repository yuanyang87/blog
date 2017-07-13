package blog.geek.dao;

import blog.geek.entity.Train;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 训练Dao
 * @author yuanyang
 * @version 1.1
 */
@Repository
public interface TrainDao {

    /**
     * 增
     * @param train
     * @return
     */
    int insertTrain(Train train);

    /**
     * 删
     * @param trainId
     * @return
     */
    int deleteTrain(String trainId);

    /**
     * 批量删
     * @param trainIds
     * @return
     */
    int deleteTrains(@Param("trainIds") List<String> trainIds);

    /**
     * 改
     * @param train
     * @return
     */
    int updateTrain(Train train);

    /**
     * 查找所有
     * @param offSet
     * @param limit
     * @return
     */
    List<Train> findAllTrains(int offSet,int limit);

    /**
     * 按关键字查询
     * @param key
     * @param offSet
     * @param limit
     * @return
     */
    List<Train> findTrainsByKeyWord(String key,int offSet,int limit);

    /**
     * 获得总条数
     * @return
     */
    int getTotal();
}
