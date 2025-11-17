package edu.pja.sri.kobrebski.sri04jms.receiver;

import edu.pja.sri.kobrebski.sri04jms.config.JmsConfig;
import edu.pja.sri.kobrebski.sri04jms.model.MonitorPokladowyBolidu;
import jakarta.jms.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RafałMechanicReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(RafałMechanicReceiver.class);

    @JmsListener(destination = JmsConfig.MECHANIC_TOPIC)
    public void receiveMechanicMessage(@Payload MonitorPokladowyBolidu message,
                                       @Headers MessageHeaders headers,
                                       Message jmsMessage) {
        LOG.info("Znowu Awaria?!?: {}", message);
    }
}