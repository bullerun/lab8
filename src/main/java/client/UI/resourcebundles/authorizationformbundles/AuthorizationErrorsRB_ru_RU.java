package client.UI.resourcebundles.authorizationformbundles;

import java.util.ListResourceBundle;

public class AuthorizationErrorsRB_ru_RU extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"nameLength", "Имя пользователя должно быть меньше 127 символов"},
                {"passwordLength", "Пароль должен быть равен 8 символам"},
                {"empty", "Поля не должны быть пустыми"},
                {"error", "Не удалось авторизовать пользователя"},
                {"nameIsTaking", "Данный логин уже существует"},
                {"invalid", "Неверный логин или пароль"}
        };
        return contents;
    }
}
