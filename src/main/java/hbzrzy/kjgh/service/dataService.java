package hbzrzy.kjgh.service;
import hbzrzy.kjgh.dao.UserInfoDao;
import hbzrzy.kjgh.entity.UserInfo;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class dataService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    /********************    mybatis begin      *************************/
    public UserInfo findOne(UserInfo user) {
        return userInfoDao.findOne(user);
    }

    public List<UserInfo> findAll() {
        List<UserInfo> ux = userInfoDao.findAll();
        return ux;
    }
    /********************    mybatis end       *************************/

    /********************    oracle 存储过程 begin      *************************/
    public List<Map<String, Object>> queryUsers() {
        return testproc();
    }

    public List<Map<String, Object>> testproc() {
        CallableStatementCreator ctor = new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                String storedProc = "{call sp_list_table(?,?)}";// 调用的sql
                CallableStatement cs = con.prepareCall(storedProc);
                cs.setString(1, "p1");// 设置输入参数的值
                cs.registerOutParameter(2, OracleTypes.CURSOR);// 注册输出参数的类型
                return cs;
            }
        };
        CallableStatementCallback cbk = new CallableStatementCallback() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                List resultsMap = new ArrayList();
                cs.execute();
                ResultSet rs = (ResultSet) cs.getObject(2);// 获取游标一行的值
                while (rs.next()) {// 转换每行的返回值到Map中
                    Map rowMap = new HashMap();
                    rowMap.put("id", rs.getString("UserID"));
                    rowMap.put("name", rs.getString("UserName"));
                    resultsMap.add(rowMap);
                }
                rs.close();
                return resultsMap;
            }
        };
        try {
            List resultList = (List) jdbcTemplate.execute(ctor, cbk);
            for (int i = 0; i < resultList.size(); i++) {
                Map rowMap = (Map) resultList.get(i);
                String id = rowMap.get("id").toString();
                String name = rowMap.get("name").toString();
                System.out.println("id=" + id + ";name=" + name);

            }
            return resultList;
        } catch (Exception ex) {
            String s = ex.getMessage();
            return null;
        }
    }
    /********************    oracle 存储过程 end       *************************/

    /********************    oracle 查询单值 begin       *************************/
    public String getCorn() {
        String s = (String)jdbcTemplate.queryForObject("select cron from cron",java.lang.String.class);
        return s;
    }
    /********************    oracle 查询单值 end       *************************/

}
