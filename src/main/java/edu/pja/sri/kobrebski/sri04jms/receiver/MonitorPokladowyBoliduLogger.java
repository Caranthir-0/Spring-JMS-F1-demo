package edu.pja.sri.kobrebski.sri04jms.receiver;

import edu.pja.sri.kobrebski.sri04jms.config.JmsConfig;

import edu.pja.sri.kobrebski.sri04jms.model.MonitorPokladowyBolidu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MonitorPokladowyBoliduLogger {

    private final static Logger LOG = LoggerFactory.getLogger(MonitorPokladowyBoliduLogger.class);

    @JmsListener(
            destination = JmsConfig.TOPIC_ONBOARD_MONITOR,
            containerFactory = "topicConnectionFactory"
    )

    public void receiveMonitorMessage(@Payload MonitorPokladowyBolidu message, MessageHeaders headers, jakarta.jms.Message jmsMessage) {
        LOG.info("Odebrano Status Bolidu: {}", message);
    }

}