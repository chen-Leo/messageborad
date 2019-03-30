package djc.messageboard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("djc.messageboard.Mapper")
@SpringBootApplication
public class MessageboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageboardApplication.class, args);
    }

}
