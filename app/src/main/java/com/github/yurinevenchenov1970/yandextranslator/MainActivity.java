package com.github.yurinevenchenov1970.yandextranslator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.yurinevenchenov1970.yandextranslator.presenter.MainPresenter;
import com.github.yurinevenchenov1970.yandextranslator.presenter.MainPresenterImpl;
import com.github.yurinevenchenov1970.yandextranslator.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.input_edit_text)
    EditText mInputEditText;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.translation_text_view)
    TextView mTranslationTextView;

    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainPresenter = new MainPresenterImpl(this);
    }

    @OnClick(R.id.translate_button)
    void translate() {
        String input = mInputEditText.getText().toString();
        if (input.length() > 0) {
            mMainPresenter.processTranslation(input);
        }
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showConnectionError() {
        Toast.makeText(this, R.string.no_connection, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showTranslation(String translatedText) {
        mTranslationTextView.setText(translatedText);
    }
}