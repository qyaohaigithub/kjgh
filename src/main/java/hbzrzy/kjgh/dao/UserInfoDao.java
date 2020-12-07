package hbzrzy.kjgh.dao;

import hbzrzy.kjgh.entity.UserInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoDao {
    /**
     * 查询一条数据
     * @param user
     * @return
     */
    UserInfo findOne(UserInfo user);

    /**
     * 查所有
     * @return
     */
    List<UserInfo> findAll();
}
