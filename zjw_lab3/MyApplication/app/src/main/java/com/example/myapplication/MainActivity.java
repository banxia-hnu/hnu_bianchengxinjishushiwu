package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText)this.findViewById(R.id.editText1);
    }

    public void click(View view){
        String str = et.getText().toString();
        str += view.getTag().toString();
        et.setText(str);
    }

    public void dial(View view){
        String str = et.getText().toString();
        if((str != null) && !str.trim().equals("")){
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + str));
            startActivity(intent);
        }else{
            Toast.makeText(MainActivity.this,"请点击拨号按钮",Toast.LENGTH_LONG).show();
        }
    }

    public void del(View view){
        String str = et.getText().toString();
        if((str != null) && !str.trim().equals("")){
            str = str.substring(0,str.length() - 1);
            et.setText(str);
        }
    }


}