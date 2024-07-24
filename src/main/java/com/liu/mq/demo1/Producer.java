package com.liu.mq.demo1;

import com.liu.mq.tuils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    public static final String QUEUE_NAME = "demo1";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);

        for (int i = 0; i < 10; i++) {
            String body = i + "hello RabbitMQ~~~";
            channel.basicPublish("",QUEUE_NAME,null,body.getBytes());
        }

        channel.close();
    }

}
