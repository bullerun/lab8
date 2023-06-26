package client.UI.resourcebundles.runtimeoutputsbundles;

import java.util.ListResourceBundle;

public class RuntimeOutputRB_en_US extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] content = {
                {"fileListenerCanNotReadFromFile", "Can not read from file!"},
                {"fileListenerProblemWithScriptFile", "Something went wrong with script file..."},
                {"canNonInitComponent", "Can not initialize component!"},
                {"anyFieldsDoesNotValid", "Any fields does not valid!"},
                {"modelWasNotSelectedInTable", "Select model in table."},
                {"columnWasNotSelected", "Select column!"},
                {"signWasNotSelected", "Select sign!"},
                {"valueWasNotEntered", "Enter value!"}
        };
        return content;
    }
}
