package common;

import common.data.LabWork;

import java.io.Serializable;
import java.util.NavigableSet;
import java.util.TreeSet;

public class ResponseWithTreeSet extends Response implements Serializable {

    private final NavigableSet<LabWork> labWork;

    public ResponseWithTreeSet(String response, NavigableSet<LabWork> labWork) {
        super(response);
        this.labWork = labWork;
    }

    public NavigableSet<LabWork> getLabWork() {
        return labWork;
    }
}