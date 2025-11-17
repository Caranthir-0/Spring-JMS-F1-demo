package edu.pja.sri.kobrebski.sri04jms.receiver;


import edu.pja.sri.kobrebski.sri04jms.config.JmsConfig;
import edu.pja.sri.kobrebski.sri04jms.model.PitStopRequest;
import edu.pja.sri.kobrebski.sri04jms.model.PitStopResponse;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
@RequiredArgsConstructor
public class PitStopReceiver {

    private final JmsTemplate jmsTemplate;
    private final static Logger LOG = LoggerFactory.getLogger(PitStopReceiver.class);
    private final Random random = new Random();

    @JmsListener(destination = JmsConfig.QUEUE_SEND_AND_RECEIVE)
    public void receiveAndRespond(
            @Payload PitStopRequest convertedMessage,
            @Headers MessageHeaders headers,
            Message message
    ) throws JMSException {
        LOG.info("Otrzymałem prośbę o PitStop: " + convertedMessage);

        boolean approved = random.nextBoolean();
        String comment = approved ? "Kieruj się do Pit-Stop'u!" : "Kontyuuj jazdę!";

        PitStopResponse response = PitStopResponse.builder()
                .carId(convertedMessage.getCarId())
                .approved(approved)
                .comment(comment)
                .build();

        Destination replyTo = message.getJMSReplyTo();
        jmsTemplate.convertAndSend(replyTo, response);
    }
}