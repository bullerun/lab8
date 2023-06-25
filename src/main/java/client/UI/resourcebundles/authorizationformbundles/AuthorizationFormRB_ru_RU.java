package client.UI.resourcebundles.authorizationformbundles;

import java.util.ListResourceBundle;

public class AuthorizationFormRB_ru_RU extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] contents = {
                {"authorizationLabel", "Авторизация"},
                {"logInTextField", "Введите логин"},
                {"passwordTextField", "Введите пароль"},
                {"settingsMenu", "Настройки"},
                {"languageMenuItem", "Язык"},
                {"loginLabel", "Логин"},
                {"passwordLabel", "Пароль"},
                {"signInButton", "Войти"},
                {"signUpButton", "Зарегистрироваться"}
        };
        return contents;
    }
}
