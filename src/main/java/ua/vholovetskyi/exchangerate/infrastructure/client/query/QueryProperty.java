package ua.vholovetskyi.exchangerate.infrastructure.client.query;


import java.util.Map;

public interface QueryProperty {

    String getBaseUrl();

    Map<String, String> getHeaders();

}
