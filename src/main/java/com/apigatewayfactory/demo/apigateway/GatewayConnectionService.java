package com.apigatewayfactory.demo.apigateway;

import com.apigatewayfactory.demo.connectionstrategy.GatewayConnectionStrategy;
import com.apigatewayfactory.demo.model.Charge;
import com.apigatewayfactory.demo.model.GatewayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GatewayConnectionService {


  private final GatewayConnectionFactory gatewayConnectionFactory;

  @Autowired
  public GatewayConnectionService(GatewayConnectionFactory gatewayConnectionFactory){
    this.gatewayConnectionFactory = gatewayConnectionFactory;
  }

  public String generateCharge(GatewayType type, Charge charge){
    GatewayConnectionStrategy gatewayConnectionStrategy = gatewayConnectionFactory.getGatewayConnection(type);
    return gatewayConnectionStrategy.sendCharge(charge);
  }
}
