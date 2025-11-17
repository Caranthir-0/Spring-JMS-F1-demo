package edu.pja.sri.kobrebski.sri04jms.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PitStopRequest {
    private String carId;
    private String reason;

    public static PitStopRequest withRandomRequest() {
        return PitStopRequest.builder()
                .carId("CAR-" + System.currentTimeMillis())
                .reason(Math.random() > 0.5 ? "Tyre change" : "Engine check")
                .build();
    }
}
