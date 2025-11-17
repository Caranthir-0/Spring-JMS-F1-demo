package edu.pja.sri.kobrebski.sri04jms.producer;

import edu.pja.sri.kobrebski.sri04jms.config.JmsConfig;
import edu.pja.sri.kobrebski.sri04jms.model.PitStopRequest;
import edu.pja.sri.kobrebski.sri04jms.model.PitStopResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PitStopProducer {

    private final JmsTemplate jmsTemplate;
    private final JmsMessagingTemplate jmsMessagingTemplate;

    private final static Logger LOG = LoggerFactory.getLogger(PitStopProducer.class);

    @Scheduled(fixedRate = 15000)
    public void sendAndReceive() {
        PitStopRequest request = PitStopRequest.withRandomRequest();
        jmsMessagingTemplate.setJmsTemplate(jmsTemplate);
        LOG.info("Wysyłam prośbę o PitStop!: " + request);
        PitStopResponse response = jmsMessagingTemplate.convertSendAndReceive(
                JmsConfig.QUEUE_SEND_AND_RECEIVE,
                request,
                PitStopResponse.class
        );
        LOG.info("Otrzymałem odpowiedź: zgoda=" + response.isApproved() +
                 ", comment='" + response.getComment() + "'\tconvertedMessage: " + response);
    }
}