package com.example.consumer.infra.aws;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.example.consumer.dto.entity.SqsMessage;
import com.example.consumer.infra.config.sqs.SqsInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SqsRepositoryImpl implements SqsRepository {

    private final AmazonSQS amazonSQS;

    @Override
    public void send(SqsMessage message) {

        SqsInfo info = message.getSqsInfo();

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(info.getUrl())
                .withMessageBody(message.getMessage())
                .withMessageGroupId(message.getGroupId())
                .withMessageDeduplicationId(message.getGroupId());

        amazonSQS.sendMessage(sendMessageRequest);
    }
}
