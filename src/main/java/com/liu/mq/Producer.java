package com.liu.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        // 设置主机
        connectionFactory.setHost("127.0.0.1");

        // 设置连接端口号默认5672
        connectionFactory.setPort(5672);

        // 设置虚拟主机名
        connectionFactory.setVirtualHost("/");

        // 设置连接用户名
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        // 创建连接 类似于连接池
        Connection connection = connectionFactory.newConnection();
        // 创建频道，从类似于线程池取线程
        Channel channel = connection.createChannel();

        channel.queueDeclare("simple_queue",true,false,false,null);
        String message = "第一条MQ啦";
        channel.basicPublish("","simple_queue",null,message.getBytes());
        System.out.println("已发送消息:" + message);

        // 关闭资源
        channel.close();
        connection.close();
    }
}
