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
public class DriverReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(DriverReceiver.class);

    @JmsListener(destination = JmsConfig.DRIVER_QUEUE)
    public void receiveDriverMessage(@Payload MonitorPokladowyBolidu message,
                                     @Headers MessageHeaders headers,
                                     Message jmsMessage) {
        LOG.info("Odebrano wiadomość dla kierowcy: {}", message);
    }
}