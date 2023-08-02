package udrmarker;

import burp.api.montoya.core.Annotations;
import burp.api.montoya.core.HighlightColor;
import burp.api.montoya.http.handler.*;

import static burp.api.montoya.http.handler.RequestToBeSentAction.continueWith;
import static burp.api.montoya.http.handler.ResponseReceivedAction.continueWith;

class UDRMarkerHandler implements HttpHandler {
    @Override
    public RequestToBeSentAction handleHttpRequestToBeSent(HttpRequestToBeSent requestToBeSent) {
        Annotations annotations = requestToBeSent.annotations();
        if (requestHasSecFetchUserHeader(requestToBeSent)) {
            annotations = annotations.withHighlightColor(HighlightColor.BLUE);
        }
        return continueWith(requestToBeSent, annotations);
    }

    @Override
    public ResponseReceivedAction handleHttpResponseReceived(HttpResponseReceived httpResponseReceived) {
        return continueWith(httpResponseReceived);
    }

    private static boolean requestHasSecFetchUserHeader(HttpRequestToBeSent requestToBeSent) {
        return requestToBeSent.headers().stream().anyMatch(header -> header.name().equalsIgnoreCase("Sec-Fetch-User"));
    }
}
