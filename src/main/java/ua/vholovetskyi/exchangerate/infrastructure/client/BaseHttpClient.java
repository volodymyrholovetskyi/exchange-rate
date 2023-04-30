package ua.vholovetskyi.exchangerate.infrastructure.client;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ua.vholovetskyi.exchangerate.infrastructure.client.query.QueryProperty;

import java.util.Map;

@Slf4j
@AllArgsConstructor
abstract class BaseHttpClient implements CurrencyHttpClient {
    protected final RestTemplate restTemplate;
    protected final QueryProperty params;

    protected HttpEntity<HttpHeaders> getRequestEntity() {
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (!params.getHeaders().isEmpty()) {
            headers.addAll(parsHeader(params.getHeaders()));
        }
        return new HttpEntity<>(headers);
    }

    protected String getBaseUrl() {
        return UriComponentsBuilder.fromHttpUrl(params.getBaseUrl()).toUriString();
    }

    private MultiValueMap<String, String> parsHeader(Map<String, String> headerParams) {
        final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>(headerParams.size());
        for (Map.Entry<String, String> header : headerParams.entrySet()) {
            String key = header.getKey();
            String value = header.getValue();
            headers.add(key, String.valueOf(value));
        }
        return headers;
    }
}
