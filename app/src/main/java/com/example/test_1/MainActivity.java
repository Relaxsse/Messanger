package com.example.test_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private static int MAX_NIKNAME_LENGTH = 15;
    final String SAVED_TEXT = "saved_text";

    Button btnsend;
    EditText editText;
    SharedPreferences mPref;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.nikname);
        btnsend = findViewById(R.id.button_send_nikname);
        btnsend.setOnClickListener(this);

        loadtext();

    }



    @Override
    public void onClick(View v) {

        String msg_nikname = editText.getText().toString();

        if(msg_nikname.equals("")){
            Toast.makeText(getApplicationContext(), "Введите имя!", Toast.LENGTH_LONG).show();
            return;
        }

        if(msg_nikname.length()>MAX_NIKNAME_LENGTH){
            Toast.makeText(getApplicationContext(), "Слишком длинное имя!", Toast.LENGTH_LONG).show();
            return;
        }
        savetext();
        Intent i = new Intent(this, MainActivity2.class);
        i.putExtra(EXTRA_MESSAGE, msg_nikname);
        startActivity(i);
    }

    private void savetext() {
        mPref = getSharedPreferences("Nikname", MODE_PRIVATE);
        SharedPreferences.Editor ed = mPref.edit();
        ed.putString(SAVED_TEXT, editText.getText().toString());
        ed.commit();
    }
    private void loadtext() {
        mPref = getSharedPreferences("Nikname", MODE_PRIVATE);
        String saveText = mPref.getString(SAVED_TEXT, "");
        editText.setText(saveText);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        savetext();
    }

}