package server.command;

import common.ResponseWithLabWork;
import common.ResponseWithTreeSet;
import common.data.LabWork;

public interface CommandWithLabWork {
    String getName();

    ResponseWithTreeSet execute(LabWork labWork, Long client);
}
