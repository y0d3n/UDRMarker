package udrmarker;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

public class UDRMarker implements BurpExtension {
    @Override
    public void initialize(MontoyaApi api) {
        api.extension().setName("User Driven Request Marker");

        api.http().registerHttpHandler(new UDRMarkerHandler());
    }
}
