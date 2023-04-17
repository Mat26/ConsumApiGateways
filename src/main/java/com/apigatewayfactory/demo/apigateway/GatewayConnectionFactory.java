package com.apigatewayfactory.demo.apigateway;

import com.apigatewayfactory.demo.connectionstrategy.GatewayConnectionStrategy;
import com.apigatewayfactory.demo.model.GatewayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class GatewayConnectionFactory {

  private static Map<GatewayType, GatewayConnectionStrategy> gatewayMap;

  @Autowired
  private GatewayConnectionFactory(List<GatewayConnectionStrategy> gateways) {
    gatewayMap = gateways.stream()
        .collect(Collectors.toUnmodifiableMap(GatewayConnectionStrategy::getType, Function.identity()));
  }

  public static GatewayConnectionStrategy getGatewayConnection(GatewayType type) {
    return Optional.ofNullable(gatewayMap.get(type)).orElseThrow(IllegalArgumentException::new);
  }
}
