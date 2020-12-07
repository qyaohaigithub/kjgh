package hbzrzy.kjgh;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
//@MapperScan("hbzrzy.kjgh.dao") 或者在 dao 接口增加@Mapper注解
public class KjghApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(KjghApplication.class);
    }

    public static void main(String[] args) {

        SpringApplication.run(KjghApplication.class, args);
    }

}
