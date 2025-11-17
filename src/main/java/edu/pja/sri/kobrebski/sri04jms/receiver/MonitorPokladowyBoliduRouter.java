package edu.pja.sri.kobrebski.sri04jms.receiver;

import edu.pja.sri.kobrebski.sri04jms.config.JmsConfig;
import edu.pja.sri.kobrebski.sri04jms.model.MonitorPokladowyBolidu;
import jakarta.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MonitorPokladowyBoliduRouter {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public MonitorPokladowyBoliduRouter(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(
            destination = JmsConfig.TOPIC_ONBOARD_MONITOR,
            containerFactory = "topicConnectionFactory"
    )
    public void receiveMonitorMessage(@Payload MonitorPokladowyBolidu message,
                                      @Headers MessageHeaders headers,
                                      Message jmsMessage) {

        if (message.getEngineTemp() > 90 || message.getOilPressure() < 2) {
            jmsTemplate.convertAndSend(JmsConfig.MECHANIC_TOPIC, message);
        }


        if (message.getEngineTemp() > 120 || message.getOilPressure() < 1) {
            jmsTemplate.convertAndSend(JmsConfig.DRIVER_QUEUE, message);
        }
    }
}