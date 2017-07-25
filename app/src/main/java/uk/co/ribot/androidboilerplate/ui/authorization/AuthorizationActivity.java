package uk.co.ribot.androidboilerplate.ui.authorization;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;

public class AuthorizationActivity extends BaseActivity implements AuthorizationMvpView {

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, AuthorizationActivity.class);
        return intent;
    }

    @Inject
    AuthorizationPresenter mAuthorizationPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.eMail)
    EditText eMail;

    @BindView(R.id.password)
    EditText mPassword;

    @BindView(R.id.login)
    Button login;

    @BindView(R.id.text_email)
    TextInputLayout errorEmail;

    @BindView(R.id.textPassword)
    TextInputLayout errorPassword;

    String email;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization);
        ButterKnife.bind(this);
        activityComponent().inject(this);
        mAuthorizationPresenter.attachView(this);
        initToolbar();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = eMail.getText().toString();
                password = mPassword.getText().toString();

                mAuthorizationPresenter.startLogin(email, password);
            }
        });

    }

    private void initToolbar() {
        toolbar.setTitle(getString(R.string.autorization));
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.greyish_brown));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24_px);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.item1:
                        mAuthorizationPresenter.onActionCreateClick();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }


    @Override
    public void showPasswordError() {
        errorPassword.setError(getString(R.string.passwor_error));
    }

    @Override
    public void showEmailError() {

        errorEmail.setError(getString(R.string.incorect_email));
    }

    @Override
    public void hideEmailError() {
        errorEmail.setError("");

    }

    @Override
    public void hidePasswordError() {
        errorPassword.setError("");

    }

    @Override
    public void showWeather(String weather) {
        Snackbar.make(toolbar, weather, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(login.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void create() {
        Toast.makeText(this, R.string.create, Toast.LENGTH_SHORT).show();
    }

}