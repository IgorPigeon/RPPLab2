package com.example.Lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    TextView startText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startText = findViewById(R.id.startText);

    }
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            String jsonString = ""; //строка в которой будет храниться весь json файл
            // Создаем связь со вторым активити
            Intent intent = new Intent(this, SecondActivity.class);
            // Считываем json файл
            try {
                jsonString = getJsonToString();
                startText.setText("Программа открывается");
                // Передаем строку
                intent.putExtra("jsonString", jsonString);
            } catch (IOException e) {
                e.printStackTrace();
                startText.setText(e.toString());
                // Передаем ошибку
                intent.putExtra("jsonString", e.toString());
            }

            //установка флагов для активити
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            // Переход во второе активити
            startActivity(intent);
        }
    }


    // Функция для считывания json файла из res/raw и перевод его в строку
    private String getJsonToString()throws ClassCastException, IOException {
        // Открываем поток для считывания файла
        InputStream jsonStream = getResources().openRawResource(R.raw.myjsonfile);
        // Записываем содержимое файла в экземпляр класса StringBuilder чтобы дописывать в неё элементы
        int c;
        StringBuilder jsonString = new StringBuilder("");
        while((c=jsonStream.read())!=-1){
            jsonString.append((char)c);
        }
        // Возвращаем строку
        return jsonString.toString();
    }
}
