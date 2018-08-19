package com.qianli.ilink.cloud_platform.messagecenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.qianli.ilink.cloud_platform.messagecenter.mq.kafka.KafkaMessageSender;
import com.qianli.ilink.cloud_platform.messagecenter.mq.kafka.KafkaProducerConfig;
import com.qianli.ilink.cloud_platform.messagecenter.pojo.dto.Message;
import com.qianli.ilink.cloud_platform.messagecenter.service.MessageSender;
import com.qianli.ilink.cloud_platform.messagecenter.utils.IdGenerater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageSenderImpl implements MessageSender {

    @Autowired
    private KafkaMessageSender kafkaMessageSender;

    @Autowired
    private KafkaProducerConfig kafkaConfig;

    @Override
    public void send(Message message) {
        kafkaMessageSender.execute(kafkaConfig.getMessageTopic(),IdGenerater.kafkaKey(), JSON.toJSONString(message));
    }
}
