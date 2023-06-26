package client.UI.resourcebundles.commandstatusbundles;

import java.util.ListResourceBundle;

public class CommandStatusRB_li_LI extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"labSuccessfullyAdded", "Laboratorinis darbas sėkmingai pridėtas"},
                {"labNotAdded", "Laboratorinis darbas nepridėtas"},
                {"mustBeWithoutAnArgument", "Komanda įvesta be argumento"},
                {"errorWhenTryingToClearTheCollection", "Klaida bandant išvalyti kolekciją"},
                {"collectionCleanupWasSuccessful", "Kolekcija išvalyta"},
                {"collectionIsEmpty", "Kolekcija yra tuščia"},
                {"laboratoryWorkWithThisIdHasBeenDeleted", "Šis laboratorinis darbas buvo ištrintas pagal Id"},
                {"lackOfRightsOrLaboratoryDoesNotExist", "Nepakanka teisių arba tokio laboratorinio darbo nėra"},
                {"lackOfArgument", "Nėra įvestas argumentas"},
                {"invalidArgument", "Neteisingas argumentas"},
                {"noSuchLaboratory", "Tokio laboratorinio darbo nėra"},
                {"error", "Nežinoma klaida"},
                {"removeGreater", "Sėkmingai ištrinti visi laboratoriniai darbai, kurių Id didesnis nei nurodytas"},
                {"removeLower", "Sėkmingai ištrinti visi laboratoriniai darbai, kurių Id mažesnis nei nurodytas"},
                {"readingError", "Nepavyksta perskaityti scenarijaus"},
                {"goodUpdate", "Laboratorinis darbas atnaujintas"},
                {"badUpdate", "Nepavyko atnaujinti laboratorinio darbo"},
                {"info", "Tipas: %s \n"
                        + "Elementų skaičius: %s \n"
                        + "Inicijavimo data: %s"},
                {"average", "vidutinė minimalaus taško reikšmė visiems kolekcijos elementams = %s"},
                {"sumOfMinimalPoint", "minimalaus taško reikšmių suma visiems kolekcijos elementams = %s"}

        };
        return contents;
    }
}