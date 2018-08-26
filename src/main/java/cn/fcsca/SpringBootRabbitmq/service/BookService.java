package cn.fcsca.SpringBootRabbitmq.service;

import cn.fcsca.SpringBootRabbitmq.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * BookService
 *
 * @author Fcscanf@樊乘乘
 * @description
 * @date 上午 10:35 2018-08-25
 */
@Service
public class BookService {

    /**
     * 获取消息队列里的消息
     * @RabbitListener(queues = "fcscanf.news") 该注解指定要监听的消息队列
     *
     * @param
     * @return
     * @author Fcscanf@樊乘乘
     * @date 上午 10:44 2018-08-25
     */
    public void receive(Book book) {
        System.out.println("收到消息：" + book);
    }

    @RabbitListener(queues = "fcscanf")
    public void receiveMsg(Message message) {
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
