package common;

import java.io.Serializable;

public class Response implements Serializable {
    public String response;
    public Response(String response){
        this.response = response;
    }
    public String getResponse(){
        return response;
    }
}
