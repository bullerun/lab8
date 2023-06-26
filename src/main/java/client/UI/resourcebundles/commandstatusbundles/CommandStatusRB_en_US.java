package client.UI.resourcebundles.commandstatusbundles;

import java.util.ListResourceBundle;

public class CommandStatusRB_en_US extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"labSuccessfullyAdded", "Lab work successfully added"},
                {"labNotAdded", "Laboratory work not added"},
                {"mustBeWithoutAnArgument", "The command is entered without an argument"},
                {"errorWhenTryingToClearTheCollection", "Error when trying to clear the collection"},
                {"collectionCleanupWasSuccessful", "The collection has been cleared"},
                {"collectionIsEmpty", "The collection is empty"},
                {"laboratoryWorkWithThisIdHasBeenDeleted", "The lab with this Id has been deleted"},
                {"lackOfRightsOrLaboratoryDoesNotExist", "There are not enough rights or such laboratory work does not exist"},
                {"lackOfArgument", "No argument entered"},
                {"invalidArgument", "Invalid argument"},
                {"noSuchLaboratory", "There is no such laboratory work"},
                {"error", "Unknown error"},
                {"removeGreater", "Deleting all the lab greater the specified Id was successful"},
                {"removeLower", "Deleting all the labs below the specified Id was successful"},
                {"readingError", "Unable to read the script"},
                {"goodUpdate", "The laboratory work has been updated"},
                {"badUpdate", "The update of the laboratory work was unsuccessful"},
                {"info", "Type: %s \n"
                        + "Number of elements: %s \n"
                        + "Initialization date: %s"},
                {"average", "the average value of the minimal Point field for all elements of the collection = %s"},
                {"sumOfMinimalPoint", "the sum of the values of the minimal Point field for all elements of the collection = %s"}
        };
        return contents;
    }
}