package com.supportmania.project4_trongbbfx02929funixeduvn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.MenuItem;
import android.widget.GridView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MoveList extends AppCompatActivity {

    private String TAG = MoveList.class.getSimpleName();
    ArrayList<Movies> listMovies = new ArrayList<Movies>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_list);

        // intent put extras
        final String typeLogin;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                typeLogin = null;
            } else {
                typeLogin = extras.getString("Login");
            }
        } else {
            typeLogin = (String) savedInstanceState.getSerializable("Login");
        }

        // bottom navigation
        BottomNavigationView navigationView = findViewById(R.id.bottom_menu);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // chuyển qua các activity.
                switch (item.getItemId()) {
                    case R.id.navigation_movies:
                        Intent intent = new Intent(getApplicationContext(), MoveList.class);
                        startActivity(intent);
                        return true;
                    case R.id.navigation_profile:
                        Intent intent1 = new Intent(getApplicationContext(), Profile.class);
                        intent1.putExtra("Login", typeLogin);
                        startActivity(intent1);
                        return true;
                    case R.id.navigation_logout:
                        Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent2);
                        return true;
                }
                return false;
            }
        });

        new getMovie().execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class getMovie extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String data = HttpHandler.makeServiceCall ("https://api.androidhive.info/json/movies_2017.json");
                JSONArray jsonArray = new JSONArray(data);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    listMovies.add(new Movies(jsonObject.getString("title"), jsonObject.getString("image"),
                            jsonObject.getString("price")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            GridView gridView = findViewById(R.id.girdView);
            gridView.setAdapter(new AdapterMovies(listMovies));
        }
    }
}
