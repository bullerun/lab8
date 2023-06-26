package common;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseExecuteScript implements Serializable {
    public ArrayList<ResponseStatusEnum> response;

    public ResponseExecuteScript(ArrayList<ResponseStatusEnum> response) {
        this.response = response;
    }

    public ArrayList<ResponseStatusEnum> getResponse() {
        return response;
    }


}