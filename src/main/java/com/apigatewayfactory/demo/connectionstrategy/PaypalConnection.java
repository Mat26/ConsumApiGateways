package com.apigatewayfactory.demo.connectionstrategy;

import com.apigatewayfactory.demo.model.Charge;
import com.apigatewayfactory.demo.model.GatewayType;
import org.springframework.stereotype.Component;

@Component
public class PaypalConnection implements GatewayConnectionStrategy {
  private static final GatewayType GATEWAY_TYPE = GatewayType.PAYPAL;

  @Override
  public GatewayType getType() {
    return GATEWAY_TYPE;
  }

  @Override
  public String sendCharge(Charge charge) {
    return "Here Paypal";
  }
}
