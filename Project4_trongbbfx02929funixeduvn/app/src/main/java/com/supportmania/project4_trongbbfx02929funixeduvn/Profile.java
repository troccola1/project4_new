package com.supportmania.project4_trongbbfx02929funixeduvn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {

    ImageView img_avatar;
    TextView tv_name, tv_email, tv_birthday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // ánh xạ
        tv_name = findViewById(R.id.txt_name);
        tv_email = findViewById(R.id.txt_email);
        tv_birthday = findViewById(R.id.txt_birthday);
        img_avatar = findViewById(R.id.image_user);

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

        BottomNavigationView navigationView = findViewById(R.id.bottom_menu);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_movies:
                        Intent intent = new Intent(getApplicationContext(), MoveList.class);
                        startActivity(intent);
                        return true;
                    case R.id.navigation_profile:
                        Intent intent1 = new Intent(getApplicationContext(), Profile.class);
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

        // info user
        tv_name.setText(UserProfile.name);
        tv_email.setText(UserProfile.email);
        if(typeLogin.equals("facebook")) {
            Picasso.get().load(UserProfile.imageUrl).into(img_avatar);
            tv_birthday.setText(UserProfile.birthday);
        } else if(typeLogin.equals("google")) {
            Picasso.get().load(UserProfile.imageUri).into(img_avatar);
        }
    }
}
