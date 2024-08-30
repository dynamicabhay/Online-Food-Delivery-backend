package com.as.online_food_order.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ContactInformation {
    private String email;
    private String contactNumber;
    private String twitterHandle;
    private String facebookHandle;
}
