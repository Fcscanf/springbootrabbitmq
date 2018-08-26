package cn.fcsca.SpringBootRabbitmq;

import cn.fcsca.SpringBootRabbitmq.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootrabbitmqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createAmqp() {
        //创建Exchange
        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
        System.out.println("创建Exchange完成！");
        //创建Queue
        amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));
        System.out.println("创建Queue完成！");
        //消息队列和路由件绑定
        amqpAdmin.declareBinding(new Binding(
                "amqpadmin.queue",
                Binding.DestinationType.QUEUE,
                "amqpadmin.exchange",
                "amqp.db",
                null));
        System.out.println("绑定完成！");
    }

    /**
     * 单播-点对点 
     *
     * @param
     * @return 
     * @author Fcscanf@樊乘乘
     * @date 下午 22:26 2018-08-24 
     */
    @Test
    public void contextLoads() {
        /*
        * Message需要自己构造；定义消息体内容和消息头
        * rabbitTemplate.send(exchange,routeKey,message);
        *
        * Object默认当成消息体，只需要传入发送的对象，自动序列化发送给Rabbitmq；
        * rabbitTemplate.convertAndSend(exchange,routeKey,object);
        * */

        Map<String, Object> map = new HashMap<>();
        map.put("msg", "这是您的包裹!");
        map.put("data", Arrays.asList("helloworld", 123, true));
        rabbitTemplate.convertAndSend("exchange.direct","fcscanf.news",new Book("西游记","吴承恩"));
    }

    /**
     * 获取消息队列的消息
     *
     * @param 
     * @return 
     * @author Fcscanf@樊乘乘
     * @date 下午 23:06 2018-08-24
     */
    @Test
    public void receive() {
        Object convert = rabbitTemplate.receiveAndConvert("fcscanf.news");
        System.out.println(convert);
    }

    /**
     * 广播 - 一对多发送
     *
     * @param
     * @return
     * @author Fcscanf@樊乘乘
     * @date 下午 23:16 2018-08-24
     */
    @Test
    public void sendMsg() {
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("红楼梦","曹雪芹"));
    }
}
