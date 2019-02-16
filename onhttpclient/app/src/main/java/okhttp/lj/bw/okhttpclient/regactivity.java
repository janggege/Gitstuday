package okhttp.lj.bw.okhttpclient;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class regactivity extends AppCompatActivity {
    String path = "http://172.17.8.100/small/user/v1/register";
    private EditText ph;
    private EditText pwd1;
    private String phone;
    private String pwd;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String s = (String) msg.obj;
                    Toast.makeText(regactivity.this, s, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(regactivity.this, MainActivity.class));
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regactivity);
        ph = findViewById(R.id.name1);
        pwd1 = findViewById(R.id.pwd1);

        Button lj = findViewById(R.id.lizc);
        lj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = ph.getText().toString();
                pwd = pwd1.getText().toString();
                getGetdata();

            }
        });

    }

    private void getGetdata() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody builder = new FormBody.Builder()
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
