package common;

import common.data.LabWork;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.NavigableSet;

public class ResponseWithTreeSet extends Response implements Serializable {

    private final NavigableSet<LabWork> labWork;
    private final ArrayList<String> args;

    public ResponseWithTreeSet(ResponseStatusEnum response, NavigableSet<LabWork> labWork, ArrayList<String> args) {
        super(response);
        this.labWork = labWork;
        this.args = args;
    }

    public NavigableSet<LabWork> getLabWork() {
        return labWork;
    }

    public ArrayList<String> getArgs() {
        return args;
    }
}