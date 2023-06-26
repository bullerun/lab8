package client.UI.resourcebundles.commandstatusbundles;

import java.util.ListResourceBundle;

public class CommandStatusRB_ru_Ru extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"labSuccessfullyAdded", "Лабораторная работа успешно добавлен"},
                {"labNotAdded", "Лабораторная работа не добавлена"},
                {"mustBeWithoutAnArgument", "Команда вводится без аргумента"},
                {"errorWhenTryingToClearTheCollection", "Ошибка при попытке очистить коллекцию"},
                {"collectionCleanupWasSuccessful", "Коллекция была очищена"},
                {"collectionIsEmpty", "Коллекция пуста"},
                {"laboratoryWorkWithThisIdHasBeenDeleted", "Лабораторная с этим Id была удалена"},
                {"lackOfRightsOrLaboratoryDoesNotExist", "Не хватает прав или такая лабораторная работа не существует"},
                {"lackOfArgument", "Аргумент не введен"},
                {"invalidArgument", "Неверный аргумент"},
                {"noSuchLaboratory", "Нет такой лабораторной работы"},
                {"error", "Неизвестная ошибка"},
                {"removeGreater", "Удаление всех лабораторных выше заданного Id прошло успешно"},
                {"removeLower", "Удаление всех лабораторных ниже заданного Id прошло успешно"},
                {"readingError", "Невозможно прочитать скрипт"},
                {"goodUpdate", "Лабораторная работа обновлена"},
                {"badUpdate", "Обновление лабораторной работы прошло неудачно"},
                {"info", "Тип %s"
                        + "Количество элементов: %s"
                        + "Дата инициализации: %s"},
                {"average", "среднее значение поля минимальный балл для всех элементов коллекции = %s"},
                {"sumOfMinimalPoint", "сумму значений поля минимальный балл для всех элементов коллекции = %s"}

        };
        return contents;
    }
}