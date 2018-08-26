package cn.fcsca.SpringBootRabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringbootrabbitmqApplication
 * @EnableRabbit 开启基于Rabbitmq相关事物监听注解
 *
 * @author Fcscanf@樊乘乘
 * @date 上午 10:43 2018-08-25
 */
@EnableRabbit
@SpringBootApplication
public class SpringbootrabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootrabbitmqApplication.class, args);
    }
}
