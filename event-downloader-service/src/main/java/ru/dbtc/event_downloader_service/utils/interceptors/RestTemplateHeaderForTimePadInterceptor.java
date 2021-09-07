package ru.dbtc.event_downloader_service.utils.interceptors;

import com.ctc.wstx.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RestTemplateHeaderForTimePadInterceptor implements ClientHttpRequestInterceptor {

    private static final String TOKEN = "0736656bcc376ae7c3e21a44fc860321fb802a77";

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        if (StringUtils.containsIgnoreCase(httpRequest.getURI().getHost(), "timepad")) {
            httpRequest.getHeaders().setBearerAuth(TOKEN);
        }
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
