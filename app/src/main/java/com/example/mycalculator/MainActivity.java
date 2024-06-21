package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
TextView solutiontexTv, resultTv;
MaterialButton buttonC,buttonOpen,buttonClose;
MaterialButton button0,button1,button2, button3,button4,button5,button6,button7,button8,button9;
MaterialButton buttonPlus,buttonSub,buttonmulty,buttonDivide,buttonEqual;
MaterialButton buttonAC,buttonDot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        solutiontexTv=findViewById(R.id.solution_tv);
        resultTv=findViewById(R.id.result_tv);


        assignId(buttonC,R.id.button_c);
        assignId(buttonOpen,R.id.button_open_bracket);
        assignId(buttonClose,R.id.button_close_bracket);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonPlus,R.id.button_plus);
        assignId(buttonSub,R.id.button_subtract);
        assignId(buttonmulty,R.id.button_multiply);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonEqual,R.id.button_equal);
        assignId(buttonAC,R.id.button_ac);
        assignId(buttonDot,R.id.button_Dot);


    }

   void assignId(MaterialButton btn, int id){
btn=findViewById(id);
btn.setOnClickListener(this);

    }
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        MaterialButton button =(MaterialButton)v;
        String buttonText= button.getText().toString();
        String dataCalculate=solutiontexTv.getText().toString();
if (buttonText.equals("AC")){
    solutiontexTv.setText("");
    resultTv.setText("0");
    return;
}
if (buttonText.equals("=")){
    solutiontexTv.setText(resultTv.getText());
    return;
}
if (buttonText.equals("C")){
    dataCalculate=dataCalculate.substring(0,dataCalculate.length()-1);

}
else{ dataCalculate=dataCalculate+buttonText;}

        solutiontexTv.setText(dataCalculate);

String finalresult=getresult(dataCalculate);
if (!finalresult.equals("err")){
    resultTv.setText(finalresult);
}
    }

    String getresult(String data){
        try{
            Context context =Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable =context.initSafeStandardObjects();
            String finalresult=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalresult.endsWith(".0")){
                finalresult=finalresult.replace(".0","");
            }
            return finalresult;
        }
        catch (Exception e){
            return "err";
        }
    }


}