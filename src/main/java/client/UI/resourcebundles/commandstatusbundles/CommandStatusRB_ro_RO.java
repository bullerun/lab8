package client.UI.resourcebundles.commandstatusbundles;

import java.util.ListResourceBundle;

public class CommandStatusRB_ro_RO extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"labSuccessfullyAdded", "Laboratoriu adăugat cu succes"},
                {"labNotAdded", "Laboratoriu neadaugat"},
                {"mustBeWithoutAnArgument", "Comanda este introdusă fără un argument"},
                {"errorWhenTryingToClearTheCollection", "Eroare la încercarea de a șterge colecția"},
                {"collectionCleanupWasSuccessful", "Colecția a fost curățată cu succes"},
                {"collectionIsEmpty", "Colecția este goală"},
                {"laboratoryWorkWithThisIdHasBeenDeleted", "Laboratorul cu acest Id a fost șters"},
                {"lackOfRightsOrLaboratoryDoesNotExist", "Nu există suficiente drepturi sau nu există un astfel de laboratoriu"},
                {"lackOfArgument", "Nu a fost introdus niciun argument"},
                {"invalidArgument", "Argument invalid"},
                {"noSuchLaboratory", "Nu există un astfel de laboratoriu"},
                {"error", "Eroare necunoscută"},
                {"removeGreater", "Ștergerea tuturor laboratoarelor cu un Id mai mare decât cel specificat a fost realizată cu succes"},
                {"removeLower", "Ștergerea tuturor laboratoarelor cu un Id mai mic decât cel specificat a fost realizată cu succes"},
                {"readingError", "Nu se poate citi scriptul"},
                {"goodUpdate", "Laboratorul a fost actualizat cu succes"},
                {"badUpdate", "Actualizarea laboratorului a eșuat"},
                {"info", "Tip: %s \n"
                        + "Număr de elemente: %s \n"
                        + "Dată de inițializare: %s"},
                {"average", "valoarea medie a câmpului Punct minim pentru toate elementele colecției = %s"},
                {"sumOfMinimalPoint", "suma valorilor câmpului Punct minim pentru toate elementele colecției = %s"}

        };
        return contents;
    }
}