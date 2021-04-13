package com.example.lab4;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CheckActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        initview();
    }
    public void initview(){
        //signInButton = findViewById(R.id.button1);
        usernameEditText = findViewById(R.id.ett);
    }
    public void ForgetRequest(final String username, final String password){
        //请求地址
        String url = "http://hnusit.cn:9999/server/loginServlet";
        String tag = "Login";
        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);
        //创建StringRequest，定义字符串请求的请求方式为POST(省略第一个参数会默认为GET方式)
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");
                            String result = jsonObject.getString("Result");
                            //System.out.println(result);
                            if (result.equals("TheUserDoesNotExist")) {
                                Toast.makeText(CheckActivity.this, "Username does not exist", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(CheckActivity.this, "Username exists, jump to forgot password interface", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CheckActivity.this, ForgetPasswordActivity.class);
                                intent.putExtra("username",username);
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                            Log.e("TAG", e.getMessage(), e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //做自己的响应错误操作，如Toast提示（“请稍后重试”等）
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }
    public void Forget(View view){
        username = usernameEditText.getText().toString();
        if(username.isEmpty()){
            Toast.makeText(CheckActivity.this,"Please Input Username!",Toast.LENGTH_SHORT).show();
        }else{
            ForgetRequest(username,"888888");
        }
    }

}
