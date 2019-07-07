package com.prod.weatherinthecities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

public class CityListActivity extends FragmentActivity {
    public static final String TEXT = "Text";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_list);
        //объявляем текствью в который выведем текст
        //TextView text = (TextView) findViewById(R.id.textView);
        //принимаем интент посланый из первой активности
        //Bundle extras = getIntent().getExtras();
        //выводим что получили
        //text.setText(extras.getString(TEXT));
    }
}
