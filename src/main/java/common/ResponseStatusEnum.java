package common;

import client.UI.controllers.MainFormController;

import java.io.Serializable;
import java.util.ResourceBundle;

public enum ResponseStatusEnum implements Serializable {

    LAB_SUCCESSFULLY_ADDED("labSuccessfullyAdded"),
    LAB_NOT_ADDED("labNotAdded"),
    MUST_BE_WITHOUT_AN_ARGUMENT("mustBeWithoutAnArgument"),
    ERROR_WHEN_TRYING_TO_CLEAR_THE_COLLECTION("errorWhenTryingToClearTheCollection"),
    COLLECTION_CLEANUP_WAS_SUCCESSFUL("collectionCleanupWasSuccessful"),
    COLLECTION_IS_EMPTY("collectionIsEmpty"),
    LABORATORY_WORK_WITH_THIS_ID_HAS_BEEN_DELETED("laboratoryWorkWithThisIdHasBeenDeleted"),
    LACK_OF_RIGHTS_OR_OR_LABORATORY_DOES_NOT_EXIST("lackOfRightsOrLaboratoryDoesNotExist"),
    LACK_OF_ARGUMENT("lackOfArgument"),
    INVALID_ARGUMENT("invalidArgument"),
    NO_SUCH_LABORATORY("noSuchLaboratory"),
    ERROR("error"),
    REMOVE_GREATER("removeGreater"),
    REMOVE_LOWER("removeLower"),
    READING_ERROR("readingError"),
    GOOD_UPDATE("goodUpdate"),
    BAD_UPDATE("badUpdate"),
    INFO("info"),
    AVERAGE("average"),
    SUM_OF_MINIMAL_POINT("sumOfMinimalPoint");


    private final String bundleObjectName;

    ResponseStatusEnum(String bundleObjectName) {
        this.bundleObjectName = bundleObjectName;
    }

    @Override
    public String toString() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("client.UI.resourcebundles.commandstatusbundles.CommandStatusRB", MainFormController.getCurrentLocale().get().getLocale());
        return resourceBundle.getString(bundleObjectName);
    }
}