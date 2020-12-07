/*
 *用    途:获取项目资源数据的控制器
 *创 建 人:跃海
 *版 本 号：v1.0.0
 *创建时间：2020-11-11
 */

package hbzrzy.kjgh.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hbzrzy.kjgh.KjghApplication;
import hbzrzy.kjgh.entity.UserInfo;
import hbzrzy.kjgh.service.dataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import java.io.FileNotFoundException;

@RestController
@EnableAutoConfiguration
public class dataController {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(KjghApplication.class);

    @Resource
    private dataService svc;

    @RequestMapping(value = "/findOne",method = RequestMethod.GET)
    public UserInfo findOne(UserInfo user){
        logger.info("findOne");

        String spath = System.getProperty("user.dir");
        logger.info("获取路径:" + spath);

        UserInfo result = svc.findOne(user);
        return result;
    }

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public List<UserInfo> findAll() {
        logger.info("findAll");
        List<UserInfo> ux = svc.findAll();
        return ux;
    }

    @RequestMapping(value = "/findpage/{pgnum}",method = RequestMethod.GET)
    public Object findpage(@PathVariable(name = "pgnum") int pgnum, @RequestParam(name = "pgsize") int pgsize){
        try {
            logger.info("findpage");
            ModelMap map = new ModelMap();
            PageHelper.startPage(pgnum, pgsize);
            List<UserInfo> pageInfo=svc.findAll();
            PageInfo<UserInfo> pageInfo1 = new PageInfo<>(pageInfo);

            map.put("msg", "查询所有成功");
            map.put("data", pageInfo1);
            map.put("code", 0);

            return map;
        }
        catch (Exception ex) {
            List<UserInfo> t = null;
            UserInfo u = new UserInfo();
            u.setUserID(ex.getMessage());
            t.add(u);
            return t;

        }
    }

    @RequestMapping(value = "/queryUsers",method = RequestMethod.GET)
    public List<Map<String, Object>> queryUsers(){
        try {

            return svc.queryUsers();
        }
        catch (Exception ex) {
            List<UserInfo> t = null;
            UserInfo u = new UserInfo();
            u.setUserID(ex.getMessage());
            t.add(u);
            return null;

        }

    }


    @CrossOrigin
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        logger.info("调用了hello");
        return "hello Spring boot";
    }
}
