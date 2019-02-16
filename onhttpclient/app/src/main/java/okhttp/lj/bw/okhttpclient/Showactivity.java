package okhttp.lj.bw.okhttpclient;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Showactivity extends AppCompatActivity {

    private ListView listView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String s = (String) msg.obj;
                    Gson gson = new Gson();
                    Bean bean = gson.fromJson(s, Bean.class);
                    List<Bean.ResultBean> result = bean.getResult();
                    adapter adapter = new adapter(result, Showactivity.this);
                    listView.setAdapter(adapter);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showactivity);
        listView = findViewById(R.id.listview);
        getGetdata();


    }

    private void getGetdata() {
        String path = "http://172.17.8.100/small/commodity/v1/bannerShow";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder().url(path).build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message message = new Message();
                message.what = 1;
                message.obj = string;
                handler.sendMessage(message);


            }
        });
    }
}
