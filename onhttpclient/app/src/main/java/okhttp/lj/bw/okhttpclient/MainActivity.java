package okhttp.lj.bw.okhttpclient;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String path = "http://172.17.8.100/small/user/v1/login";
    private EditText name;
    private EditText pwd1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String s = (String) msg.obj;
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Showactivity.class));

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        pwd1 = findViewById(R.id.pwd);
        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.reg).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                getGetdata();

                break;
            case R.id.reg:
                startActivity(new Intent(MainActivity.this, regactivity.class));
                break;
        }

    }

    private void getGetdata() {
        String phone = name.getText().toString();
        String pwd = pwd1.getText().toString();

        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody builder = new FormBody.Builder()
                .add("phone", phone)
                .add("pwd", pwd)
                .build();
        Request build = new Request.Builder().url(path).post(builder).build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message message = new Message();
                message.obj = string;
                message.what = 1;
                handler.sendMessage(message);

            }
        });

    }
}
