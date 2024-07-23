package com.liu.mq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
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
        DefaultConsumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumerTag" + consumerTag);
                System.out.println("envelope" + envelope);
                System.out.println("properties" + properties);
                System.out.println("body" + new String(body));
            }
        };
        channel.basicConsume("simple_queue",true,consumer);

        // 关闭资源
        channel.close();
        connection.close();
    }
}
