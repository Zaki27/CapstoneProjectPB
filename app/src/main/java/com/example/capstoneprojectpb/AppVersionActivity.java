package com.example.capstoneprojectpb;

import static android.text.Html.fromHtml;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AppVersionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_version);

        TextView Contact = (TextView)findViewById(R.id.Contact);

        Contact.setText(fromHtml("</font><font color='#3b5998'>CONTACT US</font>"));

        Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setData(Uri.parse("mailto:"));
                email.setType("message/cfc822");
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"rizkyadeningsih18@gmail.com"});

                startActivity(Intent.createChooser(email, "Send Mail"));
            }
        });
    }
}