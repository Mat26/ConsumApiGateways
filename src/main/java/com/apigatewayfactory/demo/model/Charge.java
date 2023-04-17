package com.apigatewayfactory.demo.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Builder
@Data
public class Charge {
  private int id;
  private String idChargeUUID;
  private int subscriptionId;
  private Status status;
  private String token;
  private Date currentPeriodStart;
  private Date currentPeriodEnd;
  @NonNull
  private String currency;
  private int consumption;
  @NonNull
  private int amount;
  private int amountExponent;
  private String description;
  private RequestStatus requestStatus;
  private Date gatewayResponseDate;
  private Date gatewayResponseCode;
  private Date gatewayResponseMessage;
  @NonNull
  private String source;
  @NonNull
  private String customerId;
}
