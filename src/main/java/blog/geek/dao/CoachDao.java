package blog.geek.dao;

import blog.geek.entity.Coach;
import blog.geek.entity.Image;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachDao {

    /**
     * 添加教练
     * @param coach
     * @param image
     * @return
     */
    int insertCoach(@Param("coach") Coach coach, @Param("image") Image image);

    /**
     * 通过id删除教练
     * @param coachId
     * @return
     */
    int deleteCoach(String coachId);

    /**
     * 更新教练
     * @param coach
     * @param image
     * @return
     */
    int updateCoach(@Param("coach") Coach coach, @Param("image") Image image);

    /**
     * 查找教练
     * @param offSet
     * @param limit
     * @return
     */
    List<Coach> selectAllCoaches(int offSet,int limit);

    /**
     * 获取记录总条数
     * @return
     */
    int getTotal();
}
