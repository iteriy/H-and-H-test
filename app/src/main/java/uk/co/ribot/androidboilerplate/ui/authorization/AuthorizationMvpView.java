package uk.co.ribot.androidboilerplate.ui.authorization;

import uk.co.ribot.androidboilerplate.ui.base.MvpView;

public interface AuthorizationMvpView extends MvpView {


    void showPasswordError();

    void showEmailError();

    void hideEmailError();

    void hidePasswordError();

    void showWeather(String weather);

    void hideKeyboard();

    void create();

}