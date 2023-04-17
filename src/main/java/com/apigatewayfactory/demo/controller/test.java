package com.apigatewayfactory.demo.controller;

import com.apigatewayfactory.demo.apigateway.GatewayConnectionService;
import com.apigatewayfactory.demo.model.Charge;
import com.apigatewayfactory.demo.model.GatewayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

  private GatewayConnectionService connectionService;

  @Autowired
  public test(GatewayConnectionService connectionService) {
    this.connectionService = connectionService;
  }

  @GetMapping("/test")
  public ResponseEntity<String> testEndPoint() {
    Charge charge = test.chargeWithCardId();
    String chargeOutput = connectionService.generateCharge(GatewayType.STRIPE, charge);
    return new ResponseEntity<>(chargeOutput, HttpStatus.OK);

  }

  private static Charge chargeWithCardId() {
    return Charge.builder()
        .amount(103000)
        .currency("usd")
        .source("card_1MZLDVAvq9ZcdyaBvuhmOUdy")
        .customerId("cus_NJz7PiEDkE8kUZ")
        .description("My First Test Charge (created for API docs at https://www.stripe.com/docs/api)")
        .build();
  }
}
