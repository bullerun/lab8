package common;

import java.io.Serializable;

public class ResponseWithBooleanType implements Serializable{
    private final AuthorizationError response;
    private final boolean auth;
    private final Authentication client;
    public ResponseWithBooleanType(AuthorizationError response, boolean auth, Authentication client) {
        this.response = response;
        this.auth = auth;
        this.client = client;
    }

    public AuthorizationError getResponse() {
        return response;
    }
    public boolean getAuth(){
        return auth;
    }

    public Authentication getClient() {
        return client;
    }
}
