package ua.vholovetskyi.exchangerate.infrastructure.client.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Setter
@Getter
public class QueryPropertyBase implements QueryProperty {

    private String baseUrl;
    private Map<String, String> headers = new HashMap<>();

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(headers);
    }

}
