package uk.co.ribot.androidboilerplate.ui.authorization;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.WetherResponse;
import uk.co.ribot.androidboilerplate.data.remote.ApiWeather;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.util.RxUtil;

public class AuthorizationPresenter extends BasePresenter<AuthorizationMvpView> {

    public static final String PASSWORD_PATTERN = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,}$";

    private final DataManager mDataManager;
    private Subscription weatherSubscription;

    @Inject
    public AuthorizationPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(AuthorizationMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        RxUtil.unsubscribe(weatherSubscription);
        super.detachView();
    }

    public void startLogin(String email, String password) {
        checkViewAttached();

        boolean isEmailValid = !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
        if (isEmailValid) {
            getMvpView().hideEmailError();
        } else {
            getMvpView().showEmailError();
        }

        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        boolean isPasswordValid = matcher.matches();
        if (isPasswordValid) {
            getMvpView().hidePasswordError();
        } else {
            getMvpView().showPasswordError();
        }
        if (isEmailValid && isPasswordValid) {
            getMvpView().hideKeyboard();
            strartWeather();
        }
    }

    public void strartWeather() {
        checkViewAttached();

        RxUtil.unsubscribe(weatherSubscription);
        weatherSubscription = mDataManager.getWeather(ApiWeather.CITY, ApiWeather.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WetherResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @SuppressLint("DefaultLocale")
                    @Override
                    public void onNext(WetherResponse wetherResponse) {
                        getMvpView().showWeather(
                                String.format(
                                        "%s %.1f",
                                        wetherResponse.getName(),
                                        wetherResponse.getMain().getTemp() - 273.15
                                )
                        );
                    }
                });
    }

    public void onActionCreateClick() {
        getMvpView().create();
    }
}
