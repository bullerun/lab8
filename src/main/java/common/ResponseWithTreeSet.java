package common;

import common.data.LabWork;

import java.io.Serializable;
import java.util.NavigableSet;

public class ResponseWithTreeSet extends Response implements Serializable {

    private final NavigableSet<LabWork> labWork;

    public ResponseWithTreeSet(ResponseStatusEnum response, NavigableSet<LabWork> labWork) {
        super(response);
        this.labWork = labWork;
    }

    public NavigableSet<LabWork> getLabWork() {
        return labWork;
    }
}