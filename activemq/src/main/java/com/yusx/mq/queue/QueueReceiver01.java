package com.yusx.mq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
//自动确认
public class QueueReceiver01 {

    public static void main(String[] args) {

        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageConsumer messageConsumer = null;
        TextMessage message = null;

        String broker_url = "tcp://47.92.239.77:61616";
        String queue_name = "myQueue";
        String msg = "你好啊，梦想家";

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(broker_url);

        try {
            connection = connectionFactory.createConnection();

            session=connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);

            destination = session.createQueue(queue_name);

            messageConsumer = session.createConsumer(destination);

            connection.start();

            //message = messageConsumer.receive();

            //轮训实现多次消费
            while (true){
                message = (TextMessage) messageConsumer.receive();
                if (null != message) {
                    System.out.println("收到消息" + message.getText());
                } else {
                    break;
                }
            }
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                //关闭连接释放资源
                if (null != messageConsumer) {
                    messageConsumer.close();
                }
                if (null != session) {
                    session.close();
                }
                if (null != connection) {
                    connection.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
