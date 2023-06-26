package common;

import java.io.Serializable;

public class Response implements Serializable {
    public ResponseStatusEnum response;
    public Response(ResponseStatusEnum response){
        this.response = response;
    }
    public ResponseStatusEnum getResponse(){
        return response;
    }
}
