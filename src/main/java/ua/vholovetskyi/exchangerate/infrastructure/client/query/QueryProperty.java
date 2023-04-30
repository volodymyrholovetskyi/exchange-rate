package ua.vholovetskyi.exchangerate.infrastructure.client.query;


import java.util.Map;

public interface QueryParamiter {

    String getBaseUrl();

    Map<String, String> getHeaders();

}
