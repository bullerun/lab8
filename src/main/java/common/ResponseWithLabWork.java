package common;

import common.data.LabWork;

import java.io.Serializable;

public class ResponseWithLabWork implements Serializable {
    private final String response;
    private final LabWork labWork;

    public ResponseWithLabWork(String response, LabWork labWork) {
        this.response = response;
        this.labWork = labWork;
    }
    public String getResponse() {
        return response;
    }

    public LabWork getLabWork() {
        return labWork;
    }
}
