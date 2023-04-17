package com.apigatewayfactory.demo.connectionstrategy;

import com.apigatewayfactory.demo.model.Charge;
import com.apigatewayfactory.demo.model.ChargeResponse;
import com.apigatewayfactory.demo.model.GatewayType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StripeConnection implements GatewayConnectionStrategy {

  @Value("${stripe.api.key}")
  private String apiKey;

  private static final GatewayType GATEWAY_TYPE = GatewayType.STRIPE;

  @Override
  public GatewayType getType() {
    return GATEWAY_TYPE;
  }

  @Override
  public String sendCharge(Charge charge) {
    Stripe.apiKey = apiKey;
    buildCharge(charge);
    return "Here Stripe";
  }

  private ChargeResponse buildCharge(Charge charge) {
    ChargeResponse response = new ChargeResponse();
    Map<String, Object> params = new HashMap<>();
    params.put("amount", charge.getAmount());
    params.put("currency", charge.getCurrency());
    params.put("customer", charge.getCustomerId());
    params.put("source", charge.getSource());
    params.put("description", charge.getDescription());
    createChargeStripe(params);
    return response;
  }


  private void createChargeStripe(Map<String, Object> params) {
    com.stripe.model.Charge charge = null;
    try {
      charge = com.stripe.model.Charge.create(params);
    } catch (StripeException e) {
      e.printStackTrace();
    }
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    System.out.println(gson.toJson(charge));
  }



/*
  //Test Code
  private Customer createCustomer() {
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("email", "test4@gmail.com");
      params.put("name", "test8");
      params.put(
          "description",
          "My First Test Customer (created for API docs at https://www.stripe.com/docs/api)"
      );
      Customer customer = Customer.create(params);
      System.out.println("Customer: " + customer.getId());
      return customer;
    } catch (StripeException e) {
      System.out.println("ERROR: " + e);
    }
    return null;
  }

  private void retrieveCustomer(String customerId) {
    try {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      System.out.println(gson.toJson(Customer.retrieve(customerId)));
    } catch (StripeException e) {
      System.out.println("ERROR: " + e);
    }
  }

  private void addCard(String customerId) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    try {
      Customer customer = Customer.retrieve(customerId);
      //*Start to create card Token
      Map<String, Object> cardParam = new HashMap<>();
      cardParam.put("name", "TestCard");
      cardParam.put("number", "4242424242424242");
      cardParam.put("exp_month", 2);
      cardParam.put("exp_year", 2024);
      cardParam.put("cvc", "314");

      Map<String, Object> tokenParam = new HashMap<>();
      tokenParam.put("card", cardParam);

      Token token = Token.create(tokenParam);
      System.out.println(gson.toJson(token));
      //*End to create card Token
      //*Start Update the customer with the cardToken into the source
      Map<String, Object> source = new HashMap<>();
      source.put("source", token.getId());

      customer.update(source);
      //*End Update the customer with the cardToken into the source
      System.out.println(gson.toJson(customer));

    } catch (StripeException e) {
      System.out.println("ERROR: " + e);
    }
  }

  private void createBankAccount() {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    try {
      //Start Create a bank account token
      Map<String, Object> bankAccount = new HashMap<>();
      bankAccount.put("country", "US");
      bankAccount.put("currency", "usd");
      bankAccount.put(
          "account_holder_name",
          "Jenny Rosen"
      );
      bankAccount.put(
          "account_holder_type",
          "individual"
      );
      bankAccount.put("routing_number", "110000000");
      bankAccount.put("account_number", "000123456789");
      Map<String, Object> params = new HashMap<>();
      params.put("bank_account", bankAccount);

      Token token = Token.create(params);
      System.out.println(gson.toJson(token));
      System.out.println("*********************************************");
      //End Create a bank account token
     /* Map<String, Object> retrieveParams =
          new HashMap<>();
      List<String> expandList = new ArrayList<>();
      expandList.add("sources");
      retrieveParams.put("expand", expandList);
      Customer customer =
          Customer.retrieve(
              "cus_NJz7PiEDkE8kUZ",
              retrieveParams,
              null
          );

      Map<String, Object> params2 = new HashMap<>();
      params.put(
          "source",
          token.getId()
      );
      BankAccount bankAccount2 =
          (BankAccount) customer.getSources().create(
              params2
          );
      System.out.println(gson.toJson(bankAccount2));
    } catch (StripeException e) {
      System.out.println("ERROR: " + e);
    }
  }
*/

}
