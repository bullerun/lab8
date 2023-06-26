package client.UI.resourcebundles.runtimeoutputsbundles;

import java.util.ListResourceBundle;

public class RuntimeOutputRB_ro_RO extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"fileListenerCanNotReadFromFile", "Nu se poate citi din fișier!"},
                {"fileListenerProblemWithScriptFile", "A apărut o problemă cu fișierul de script..."},
                {"canNonInitComponent", "Nu se poate inițializa componenta!"},
                {"anyFieldsDoesNotValid", "Niciun câmp nu este valid!"},
                {"modelWasNotSelectedInTable", "Selectați un model în tabel."},
                {"columnWasNotSelected", "Selectați o coloană!"},
                {"signWasNotSelected", "Selectați un semn!"},
                {"valueWasNotEntered", "Introduceți o valoare!"}
        };
        return contents;
    }
}