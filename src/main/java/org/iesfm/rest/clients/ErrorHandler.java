package org.iesfm.rest.clients;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series;

public class ErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {
        return httpResponse.getStatusCode().series() == Series.CLIENT_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) {

    }
}