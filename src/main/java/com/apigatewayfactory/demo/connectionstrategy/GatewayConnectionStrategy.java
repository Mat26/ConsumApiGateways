package com.apigatewayfactory.demo.connectionstrategy;

import com.apigatewayfactory.demo.model.Charge;
import com.apigatewayfactory.demo.model.GatewayType;

public interface GatewayConnectionStrategy {

  String sendCharge(Charge charge);

  GatewayType getType();
}
