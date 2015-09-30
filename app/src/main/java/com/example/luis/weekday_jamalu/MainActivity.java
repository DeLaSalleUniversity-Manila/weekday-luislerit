package com.example.luis.weekday_jamalu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.*;
import android.widget.*;
import android.util.*;
import android.view.Menu;
import android.view.MenuItem;
import java.lang.String;

public class MainActivity extends AppCompatActivity {

    private Integer day;
    private Integer month;
    private Integer year;
    private Integer century;
    private Integer day_out;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void getInputValues(){

        EditText et_day = (EditText) findViewById(R.id.editTextDay);
        EditText et_month = (EditText) findViewById(R.id.editTextMonth);
        EditText et_year = (EditText) findViewById(R.id.editTextYear);

        try {
            day = Integer.parseInt(et_day.getText().toString());
        }
        catch (Exception e){
            day = 0;
        }

        try {
            month = Integer.parseInt(et_month.getText().toString());
        }
        catch (Exception e){
            month = 0;
        }

        if (month == 1)
            month = 13;
        else if (month == 2)
            month = 14;

         try {
            s = et_year.getText().toString();
            year = Integer.parseInt(s.substring(2));
            century =  Integer.parseInt(s.substring(0,2));
        }
        catch (Exception e){
            year = 0;
            century = 0;
        }

        // Update the TextViews (in case an entry was left out)
        et_day.setText(day.toString());
        et_month.setText(month.toString());
        et_year.setText(s);
    }

    private void computeDay(){


        Log.d("computeDay()", "s = " + s + ", year = " + Integer.toString(year) + ", " + "century = " + Integer.toString(century));
        century = year/100;
        day_out = (day + (int)(26 *(month + 1)/10.0) + year + (int)(year/4.0) + (int)(century/4.0) + 5 * century ) % 7;
    }

    public void onClickCompute(View view){
        getInputValues();
        computeDay();
        printDay();
    }

    private void printDay(){
        String day_text;
       // TextView et_result = (TextView) findViewById(R.id.textViewResult);
        switch(day_out) {
            case 0:
                day_text = "It's Saturday!";

                break;
            case 1:
                day_text = "It's Sunday!";
                break;
            case 2:
                day_text = "It's Monday!";
                break;
            case 3:
                day_text = "It's Tuesday!";
                break;
            case 4:
                day_text = "It's Wednesday!";
                break;
            case 5:
                day_text = "It's Thursday!";
                break;
            case 6:
                day_text = "It's Friday!";
                break;
            default:
                day_text = "Invalid day!";

                //et_result.setText(day_text);
        }
       // et_result.setText(day_text);
        TextView tv_out = (TextView) findViewById(R.id.textViewResult);
        tv_out.setText(day_text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
