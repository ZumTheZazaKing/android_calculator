package com.zumthezazaking.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;

public class History extends AppCompatActivity {

    LinearLayout historyList;

    SharedPreferences myPreferences;
    String history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        myPreferences = this.getSharedPreferences("calc",MODE_PRIVATE);
        history = myPreferences.getString("history","");

        historyList = findViewById(R.id.historyList);

        loadHistory();
    }

    public void loadHistory(){
        historyList.removeAllViews();
        String[] historyArr = history.split(",");
        Collections.reverse(Arrays.asList(historyArr));

        Arrays.stream(historyArr).forEach(historyItem -> {
            historyItemView(historyItem,this);
        });
    }

    void historyItemView(String equation, Context context){
        if(!equation.isEmpty()){

            String[] equationSplit = equation.split("=");

            LinearLayout historyRow = new LinearLayout(context);
            historyRow.setOrientation(LinearLayout.VERTICAL);

            LinearLayout row = new LinearLayout(context);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setPadding(30,30,30,30);
            row.setVerticalGravity(Gravity.CENTER_VERTICAL);
            historyRow.addView(row);

            View viewDivider = new View(context);
            viewDivider.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (getResources().getDisplayMetrics().density * 2)));
            viewDivider.setBackgroundColor(Color.GRAY);
            historyRow.addView(viewDivider);

            TextView equationn = new TextView(context);
            equationn.setText(equationSplit[0]+" =");
            equationn.setTextColor(Color.BLACK);
            equationn.setTextSize(18);
            equationn.setLayoutParams(new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1f));
            row.addView(equationn);

            TextView answer = new TextView(context);
            answer.setText(equationSplit[1]);
            answer.setTextColor(Color.BLACK);
            answer.setTextSize(18);
            row.addView(answer);

            historyList.addView(historyRow);
        }
    }

    public void goBack(View view){
        finish();
    }
}