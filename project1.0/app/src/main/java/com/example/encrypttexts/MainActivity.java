package com.example.encrypttexts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.SecureRandom;

import es.dmoral.toasty.Toasty;


public class MainActivity extends AppCompatActivity {


    Button sendbutton,encryptbutton,clearbutton,decryptbutton,encryptpassword;
    EditText mytext;
    String encryptedstring=new String();
    int randomvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendbutton=findViewById(R.id.sendbutton);
        encryptbutton=findViewById(R.id.encryptbutton);
        clearbutton=findViewById(R.id.clearbutton);
        decryptbutton=findViewById(R.id.decryptbutton);
        encryptpassword=findViewById(R.id.passwordbutton);
        mytext=findViewById(R.id.editText);
        mytext.setMovementMethod(new ScrollingMovementMethod());



        clearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1=mytext.getText().toString();
                if (s1.equals(""))
                    Toasty.warning(MainActivity.this,"its already cleared",Toasty.LENGTH_SHORT).show();

                else {
                    encryptedstring="";
                    mytext.setText("");
                    Toasty.info(MainActivity.this, "cleared the message", Toasty.LENGTH_SHORT).show();
                }
            }
        });



        encryptbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecureRandom random=new SecureRandom();
                randomvalue=random.nextInt(100000)+567;


                String s1=mytext.getText().toString();


                if(s1.equals(""))
                    Toasty.warning(MainActivity.this,"Please enter the text",Toasty.LENGTH_SHORT).show();
                else
                {
                    for(int i=0;i<s1.length();i++)
                        encryptedstring += (char) (s1.charAt(i) + (randomvalue % 256));

                    mytext.setText(encryptedstring);
                    Toasty.info(MainActivity.this,"data encrypted ",Toasty.LENGTH_SHORT).show();

                }
            }
        });



        decryptbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });


        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encryptedstring=encryptedstring+"\n key:";
                encryptedstring=encryptedstring+randomvalue;
                String shareBody = "Here is the share content body";
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, encryptedstring);
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.app_name)));

            }
        });









    }
}
