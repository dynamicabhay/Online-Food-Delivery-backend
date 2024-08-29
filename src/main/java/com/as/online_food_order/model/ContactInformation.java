package com.as.online_food_order.model;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Embeddable
public class ContactInformation {
    private String email;
    private String contactNumber;
    private String twitterHandle;
    private String facebookHandle;
}
