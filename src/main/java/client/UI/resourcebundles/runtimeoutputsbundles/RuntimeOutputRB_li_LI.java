package client.UI.resourcebundles.runtimeoutputsbundles;

import java.util.ListResourceBundle;

public class RuntimeOutputRB_li_LI extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"fileListenerCanNotReadFromFile", "Nepavyksta perskaityti iš failo!"},
                {"fileListenerProblemWithScriptFile", "Kažkas negerai su scenarijaus failu..."},
                {"canNonInitComponent", "Nepavyksta inicializuoti komponento!"},
                {"anyFieldsDoesNotValid", "Nė vienas laukas nėra tinkamas!"},
                {"modelWasNotSelectedInTable", "Pasirinkite modelį lentelėje."},
                {"columnWasNotSelected", "Pasirinkite stulpelį!"},
                {"signWasNotSelected", "Pasirinkite ženklą!"},
                {"valueWasNotEntered", "Įveskite reikšmę!"}
        };
        return contents;
    }
}