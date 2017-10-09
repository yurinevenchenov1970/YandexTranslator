package com.github.yurinevenchenov1970.yandextranslator;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.yurinevenchenov1970.yandextranslator.bean.TranslationBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.github.yurinevenchenov1970.yandextranslator.ApiClient.KEY;

public class MainActivity extends AppCompatActivity {

    private static final String LANG = "en-ru";
    private static final String FORMAT = "plain";
    private static final String OPTIONS = "1";

    @BindView(R.id.input_edit_text)
    EditText mInputEditText;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.translation_text_view)
    TextView mTranslationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.translate_button)
    void translate() {
        String input = mInputEditText.getText().toString();
        if (input.length() > 0) {
            mProgressBar.setVisibility(View.VISIBLE);
            if(hasConnection(getApplicationContext())) {
                getTranslation(input);
            } else {
                Toast.makeText(getApplicationContext(), R.string.no_connection, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void getTranslation(String input) {
        ApiClient apiClient = new ApiClient();
        Call<TranslationBean> beanCall = apiClient.getTranslationService().getTranslation(KEY, input, LANG, FORMAT, OPTIONS);
        beanCall.enqueue(new Callback<TranslationBean>() {
            @Override
            public void onResponse(Call<TranslationBean> call, Response<TranslationBean> response) {
                if (response.isSuccessful()) {
                    TranslationBean bean = response.body();
                    if (bean != null) {
                        List<String> translations = bean.getTranslationList();
                        if (!translations.isEmpty()) {
                            String translation = translations.get(0);
                            showTranslation(translation);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<TranslationBean> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showTranslation(String text) {
        mProgressBar.setVisibility(View.GONE);
        mTranslationTextView.setText(text);
    }

    public boolean hasConnection(Context context) {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        }
        return connected;
    }
}