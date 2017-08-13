package blog.geek.dao;

import blog.geek.entity.Image;
import blog.geek.entity.Wod;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WodDao {

    /**
     * 添加Wod
     * @param wod
     * @param image
     * @return
     */
    int insertWod(@Param("wod") Wod wod, @Param("image") Image image);

    /**
     * 删除Wod
     * @param wodId
     * @return
     */
    int deleteWod(String wodId);

    /**
     * 更新Wod
     * @param wod
     * @param image
     * @return
     */
    int updateWod(@Param("wod") Wod wod, @Param("image") Image image);

    /**
     * 查找Wod
     * @param offSet
     * @param limit
     * @return
     */
    List<Wod> selectAllWod(int offSet,int limit);

    /**
     * 获取记录总条数
     * @return
     */
    int getTotal();
}
