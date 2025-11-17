package edu.pja.sri.kobrebski.sri04jms.producer;

import edu.pja.sri.kobrebski.sri04jms.config.JmsConfig;
import edu.pja.sri.kobrebski.sri04jms.model.MonitorPokladowyBolidu;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MonitorPokladowyBoliduProducer {
    private final JmsTemplate jmsTemplate;
    private static final Logger LOG = LoggerFactory.getLogger(MonitorPokladowyBoliduProducer.class);

    @Scheduled(fixedRate = 10000)
    public void sendStatus() {
        MonitorPokladowyBolidu message = MonitorPokladowyBolidu.withRandomData();
        jmsTemplate.convertAndSend(JmsConfig.TOPIC_ONBOARD_MONITOR, message);
        LOG.info("Sent message: " + message);
    }
}