package edu.pja.sri.kobrebski.sri04jms.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonitorPokladowyBolidu {
    private double engineTemp;
    private double tyrePressure;
    private double oilPressure;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;


    public static MonitorPokladowyBolidu withRandomData() {
        return MonitorPokladowyBolidu.builder()
                .engineTemp(Math.random() * 60 + 50)
                .tyrePressure(Math.random() * 2 + 1)
                .oilPressure(Math.random() * 5 + 1)
                .createdAt(LocalDateTime.now())
                .build();
    }
}