package com.zumthezazaking.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView equationHolder;
    TextView outputArea;
    String ans = "";
    SharedPreferences myPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPreferences = this.getSharedPreferences("calc",MODE_PRIVATE);
        equationHolder = (TextView) findViewById(R.id.equationHolder);
        outputArea = (TextView) findViewById(R.id.outputArea);
        outputArea.setEnabled(false);
        ans = myPreferences.getString("ans","");

        Button buttonClear = findViewById(R.id.buttonClear);
        Button buttonLeftBracket = findViewById(R.id.buttonLeftBracket);
        Button buttonRightBracket = findViewById(R.id.buttonRightBracket);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button buttonSubtract = findViewById(R.id.buttonSubtract);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonAns = findViewById(R.id.buttonAns);
        Button button0 = findViewById(R.id.button0);
        Button buttonDot = findViewById(R.id.buttonDot);
        Button buttonEquals = findViewById(R.id.buttonEquals);

        buttonClear.setOnClickListener(this);
        buttonLeftBracket.setOnClickListener(this);
        buttonRightBracket.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        buttonSubtract.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        buttonAns.setOnClickListener(this);
        button0.setOnClickListener(this);
        buttonDot.setOnClickListener(this);
        buttonEquals.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonClear){
            outputArea.setText("");
            equationHolder.setText("");
        }else if(view.getId() == R.id.buttonLeftBracket){
            outputArea.append("(");
        }else if(view.getId() == R.id.buttonRightBracket){
            outputArea.append(")");
        }else if(view.getId() == R.id.buttonDivide){
            outputArea.append("/");
        }else if(view.getId() == R.id.button7){
            outputArea.append("7");
        }else if(view.getId() == R.id.button8){
            outputArea.append("8");
        }else if(view.getId() == R.id.button9){
            outputArea.append("9");
        }else if(view.getId() == R.id.buttonMultiply){
            outputArea.append("*");
        }else if(view.getId() == R.id.button4){
            outputArea.append("4");
        }else if(view.getId() == R.id.button5){
            outputArea.append("5");
        }else if(view.getId() == R.id.button6){
            outputArea.append("6");
        }else if(view.getId() == R.id.buttonSubtract){
            outputArea.append("-");
        }else if(view.getId() == R.id.button1){
            outputArea.append("1");
        }else if(view.getId() == R.id.button2){
            outputArea.append("2");
        }else if(view.getId() == R.id.button3){
            outputArea.append("3");
        }else if(view.getId() == R.id.buttonAdd){
            outputArea.append("+");
        }else if(view.getId() == R.id.buttonAns){
            outputArea.append(ans);
        }else if(view.getId() == R.id.button0){
           outputArea.append("0");
        }else if(view.getId() == R.id.buttonDot){
            outputArea.append(".");
        }else if(view.getId() == R.id.buttonEquals){
            if(!outputArea.getText().toString().isEmpty()){
                try{
                    String string = outputArea.getText().toString();
                    Expression expression = new ExpressionBuilder(string).build();
                    double result = expression.evaluate();
                    String resultString;
                    if(result % 1.0 > 0){
                        resultString = result+"";
                    }else{
                        resultString = (int)result+"";
                    }
                    outputArea.setText(resultString);
                    ans = resultString;

                    equationHolder.setText(string);

                    myPreferences = this.getSharedPreferences("calc",MODE_PRIVATE);
                    String tempHistory = myPreferences.getString("history","");
                    tempHistory = tempHistory + string+"="+resultString+",";
                    SharedPreferences.Editor editor = myPreferences.edit();
                    editor.putString("ans",ans);
                    editor.putString("history",tempHistory);
                    editor.apply();

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Error: "+e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void goToHistory(View view){
        Intent intent = new Intent(this,History.class);
        startActivity(intent);
    }
}