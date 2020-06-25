package com.example.uploadimageinlistview;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Detailview extends AppCompatActivity {

    TextView studno;
    TextView name;
    TextView surname;
    TextView department;
    TextView section;
    TextView team;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailview);

        studno = findViewById(R.id.appid);
        name = findViewById(R.id.empid);
        surname = findViewById(R.id.nameid);
        department = findViewById(R.id.deptid);
        section = findViewById(R.id.descid);
        team = findViewById(R.id.teamid);
        imageView = findViewById(R.id.images);


        final Intent intent = getIntent();
        String str1 = intent.getStringExtra("roll");
        String str2 = intent.getStringExtra("leavedetail");
        String str3 = intent.getStringExtra("studno");
        String str4 = intent.getStringExtra("reason");
        String str5 = intent.getStringExtra("session");
        String str6 = intent.getStringExtra("name");


        studno.setText(str1);
        name.setText(str2);
        surname.setText(str3);
        department.setText(str4);
        section.setText(str5);
        team.setText(str6);

        Bitmap bitmap = downloadBitmap("http://File/path/imagename/"+studno+".jpg");
        imageView.setImageBitmap(bitmap);
    }

    private Bitmap downloadBitmap(String url) {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();

            final int responseCode = urlConnection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return null;
            }

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (Exception e) {
            urlConnection.disconnect();
            Log.w("ImageDownloader", "Errore durante il download da " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }
}
