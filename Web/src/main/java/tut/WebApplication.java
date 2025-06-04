package tut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.tut", "com","tut"})
@EntityScan(basePackages = {"com.tut.common.entity","com.tut.repository"})
@EnableJpaRepositories(basePackages = {"com.tut.repository","com.repository"})
public class WebApplication {
    public static void main(String arg []){
        SpringApplication.run(WebApplication.class,arg);
    }
}
