package com.example.gutenbergproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.example.gutenbergproject.utils.JsonObjectRequestWithHeader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.gutenbergproject.utils.service.books_API;

public class MainActivity extends AppCompatActivity {
         ImageView btnItem_FICTION,btnItem_DRAMA,btnItem_HUMOR,btnItem_POLITICS,btnItem_PHILOSOPHY,btnItem_HISTORY,btnItem_ADVENTURE;
    private Context mContext = this;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnItem_FICTION= findViewById(R.id.btnItem_FICTION);

        btnItem_FICTION.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getAllBooks();



                Intent intent = new Intent(MainActivity.this, Second_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               // intent.putExtra("FICTION","Fiction");
                @SuppressLint({"NewApi", "LocalSuppress"}) Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.pull_in_left, R.anim.pull_out_right).toBundle();
                startActivity(intent, bndlanimation);
                finish();




            }
        });

        btnItem_DRAMA= findViewById(R.id.btnItem_DRAMA);

        btnItem_DRAMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getAllBooks();
                Intent intent = new Intent(MainActivity.this, Second_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               // intent.putExtra("DRAMA","Drama");
                @SuppressLint({"NewApi", "LocalSuppress"}) Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.pull_in_left, R.anim.pull_out_right).toBundle();
                startActivity(intent, bndlanimation);
                finish();
            }
        });

        btnItem_HUMOR= findViewById(R.id.btnItem_HUMOR);
        btnItem_HUMOR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getAllBooks();
                Intent intent = new Intent(MainActivity.this, Second_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //intent.putExtra("HUMOR","Humorous stories");
                @SuppressLint({"NewApi", "LocalSuppress"}) Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.pull_in_left, R.anim.pull_out_right).toBundle();
                startActivity(intent, bndlanimation);
                finish();
            }
        });

        btnItem_POLITICS= findViewById(R.id.btnItem_POLITICS);
        btnItem_POLITICS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getAllBooks();
                Intent intent = new Intent(MainActivity.this, Second_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //intent.putExtra("POLITICS","Politics");
                @SuppressLint({"NewApi", "LocalSuppress"}) Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.pull_in_left, R.anim.pull_out_right).toBundle();
                startActivity(intent, bndlanimation);
                finish();
            }
        });

        btnItem_PHILOSOPHY= findViewById(R.id.btnItem_PHILOSOPHY);
        btnItem_PHILOSOPHY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getAllBooks();
                Intent intent = new Intent(MainActivity.this, Second_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               // intent.putExtra("PHILOSOPHY","Philosophy");
                @SuppressLint({"NewApi", "LocalSuppress"}) Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.pull_in_left, R.anim.pull_out_right).toBundle();
                startActivity(intent, bndlanimation);
                finish();
            }
        });



        btnItem_HISTORY= findViewById(R.id.btnItem_HISTORY);
        btnItem_HISTORY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getAllBooks();
                Intent intent = new Intent(MainActivity.this, Second_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               // intent.putExtra("HISTORY","History");
                @SuppressLint({"NewApi", "LocalSuppress"}) Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.pull_in_left, R.anim.pull_out_right).toBundle();
                startActivity(intent, bndlanimation);
                finish();
            }
        });



        btnItem_ADVENTURE= findViewById(R.id.btnItem_ADVENTURE);
        btnItem_ADVENTURE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getAllBooks();
                Intent intent = new Intent(MainActivity.this, Second_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //intent.putExtra("ADVENTURE","Adventure");
                @SuppressLint({"NewApi", "LocalSuppress"}) Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.pull_in_left, R.anim.pull_out_right).toBundle();
                startActivity(intent, bndlanimation);
                finish();
            }
        });



    }


    @Override
    public void onBackPressed() {

            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }

}
