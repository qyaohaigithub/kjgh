/*
 *用    途:项目页面控制器
 *创 建 人:跃海
 *版 本 号：v1.0.0
 *创建时间：2020-11-11
 */

package hbzrzy.kjgh.controller;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import hbzrzy.kjgh.entity.loginparm;

@Controller
public class indexController {

    /* 访问子目录下的页面 */
    @RequestMapping("/")
    public String home(){
        return "html/default";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(loginparm loginuser) {
        System.out.println("userid="+loginuser.getUserid());
        System.out.println("randomnum="+loginuser.getRandomnum());
        System.out.println("time="+loginuser.getTime());
        System.out.println("str="+loginuser.getStr());
        String spwd = "61234566";
        String md5Password = DigestUtils.md5DigestAsHex(spwd.getBytes());
        System.out.println("md5Password="+md5Password.toUpperCase());
        if (loginuser.getUserid() != null)
            return "html/default";
        else
            return "index";
    }
}
