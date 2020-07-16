package com.example.encrypttexts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import es.dmoral.toasty.Toasty;

public class Main2Activity extends AppCompatActivity {


    String decryptstring=new String();
    EditText display,key;
    Button decrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        decrypt=findViewById(R.id.enterbutton);
        display=findViewById(R.id.displayvalue);
        key=findViewById(R.id.keyvalue);
        display.setMovementMethod(new ScrollingMovementMethod());

        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=key.getText().toString();
                String s2=display.getText().toString();
                int x= Integer.parseInt(s1);



                if(s1.equals("") || s2.equals(""))
                    Toasty.warning(Main2Activity.this,"please enter the values ",Toasty.LENGTH_SHORT).show();
                else
                {
                    for(int i=0;i<s2.length();i++)
                        decryptstring += (char) (s2.charAt(i) - (x % 256));

                    display.setText("");
                    display.setText(decryptstring);
                    Toasty.info(Main2Activity.this,"decrypted the string ",Toasty.LENGTH_SHORT).show();

                }
            }
        });




    }
}
