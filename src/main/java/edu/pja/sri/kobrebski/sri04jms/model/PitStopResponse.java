package edu.pja.sri.kobrebski.sri04jms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PitStopResponse {
    private String carId;
    private boolean approved;
    private String comment;
}