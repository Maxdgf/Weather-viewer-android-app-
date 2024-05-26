package com.example.weatherviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText txt_input;
    private Button button;
    private TextView result_txt;
    private TextView result_two_txt;
    private TextView result_three_txt;
    private TextView result_four_txt;
    private TextView result_five_txt;
    private TextView result_six_txt;
    private TextView result_seven_txt;
    private TextView result_eight_txt;
    private TextView textView3;
    private TextView textView4;
    private TextView result_nine_txt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_input = findViewById(R.id.txt_input);
        button = findViewById(R.id.button);
        result_txt = findViewById(R.id.result_txt);
        result_two_txt = findViewById(R.id.result_two_txt);
        result_three_txt = findViewById(R.id.result_three_txt);
        result_four_txt = findViewById(R.id.result_four_txt);
        result_five_txt = findViewById(R.id.result_five_txt);
        result_six_txt = findViewById(R.id.result_six_txt);
        result_seven_txt = findViewById(R.id.result_seven_txt);
        result_eight_txt = findViewById(R.id.result_eight_txt);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        result_nine_txt = findViewById(R.id.result_nine_txt);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt_input.getText().toString().trim().equals(""))
                    Toast.makeText(MainActivity.this, R.string.MainText, Toast.LENGTH_LONG).show();
                else {
                    String city = txt_input.getText().toString();
                    String key = "api key"; //создайте аккаунт на сайте openweathermap и активируйте апи ключ
                    String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + key + "&units=metric&lang=ru&lat&lon";

                    new GetResulUrl().execute(url);

                }



            }

        });
    }

    private class GetResulUrl extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            super.onPreExecute();
            result_txt.setText("Загрузка...");
            result_two_txt.setText("Загрузка...");
            result_three_txt.setText("Загрузка...");
            result_four_txt.setText("Загрузка...");
            result_five_txt.setText("Загрузка...");
            result_six_txt.setText("Загрузка...");
            result_seven_txt.setText("Загрузка...");
            result_eight_txt.setText("Загрузка...");
            textView3.setText("Загрузка...");
            textView4.setText("Загрузка...");
            result_nine_txt.setText("Загрузка...");


        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null)
                    buffer.append(line).append("\n");

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();
                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }


        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject object = new JSONObject(result);
                result_txt.setText("Температура: " + object.getJSONObject("main").getDouble("temp"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                JSONObject object_two = new JSONObject(result);
                result_two_txt.setText("Ощущается как: " + object_two.getJSONObject("main").getDouble("feels_like"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                JSONObject  object_three = new JSONObject(result);
                result_three_txt.setText("Влажность:" + object_three.getJSONObject("main").getDouble("humidity"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                JSONObject object_four = new JSONObject(result);
                result_four_txt.setText("Облачность(%):" + object_four.getJSONObject("clouds").getDouble("all"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                JSONObject object_five = new JSONObject(result);
                result_five_txt.setText("Скорость ветра:" + object_five.getJSONObject("wind").getDouble("speed"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                JSONObject object_five = new JSONObject(result);
                result_six_txt.setText("Макс. температура:" + object_five.getJSONObject("main").getDouble("temp_max"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                JSONObject object_five = new JSONObject(result);
                result_seven_txt.setText("Мин. температура:" + object_five.getJSONObject("main").getDouble("temp_min"));
            } catch (JSONException e) {
                e.printStackTrace();


            }

            try {
                JSONObject object_five = new JSONObject(result);
                textView3.setText("Долгота:" + object_five.getJSONObject("coord").getDouble("lon"));
            } catch (JSONException e) {
                e.printStackTrace();

            }

            try {
                JSONObject object_five = new JSONObject(result);
                textView4.setText("Широта:" + object_five.getJSONObject("coord").getDouble("lat"));
            } catch (JSONException e) {
                e.printStackTrace();

            }

            try {
                JSONObject object_five = new JSONObject(result);
                result_nine_txt.setText("Страна:" + object_five.getJSONObject("sys").getString("country"));
            } catch (JSONException e) {
                e.printStackTrace();

            }

            try {
                JSONObject object_five = new JSONObject(result);
                result_eight_txt.setText("Порыв ветра:" + object_five.getJSONObject("wind").getDouble("gust"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }}}
