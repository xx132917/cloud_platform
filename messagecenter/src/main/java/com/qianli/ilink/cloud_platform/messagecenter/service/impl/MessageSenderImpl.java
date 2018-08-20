package com.qianli.ilink.cloud_platform.messagecenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.qianli.ilink.cloud_platform.messagecenter.mq.kafka.KafkaMessageSender;
import com.qianli.ilink.cloud_platform.messagecenter.mq.kafka.KafkaProducerConfig;
import com.qianli.ilink.cloud_platform.messagecenter.pojo.dto.Message;
import com.qianli.ilink.cloud_platform.messagecenter.service.MessageSender;
import com.qianli.ilink.cloud_platform.messagecenter.utils.IdGenerater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class MessageSenderImpl implements MessageSender {

    @Autowired
    private KafkaMessageSender kafkaMessageSender;

    @Autowired
    private KafkaProducerConfig kafkaConfig;

    @Async(value = "messageExecutor")
    @Override
    public void send(Message message) {
        System.out.println("----------"+Thread.currentThread().getId()+"."+Thread.currentThread().getName());
        kafkaMessageSender.execute(kafkaConfig.getMessageTopic(),IdGenerater.kafkaKey(), JSON.toJSONString(message));
    }
}