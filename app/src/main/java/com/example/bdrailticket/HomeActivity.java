package com.example.bdrailticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navmenu);
        View header = navigationView.getHeaderView(0);
        TextView fullname, mobile, email;
        fullname = header.findViewById(R.id.navheaderFullname);
        mobile = header.findViewById(R.id.navheaderMobile);
        email = header.findViewById(R.id.navheaderEmail);

        Bundle bundle = getIntent().getExtras();

        String tFullname, tMobile, tEmail;
        tFullname = bundle.get("fullname").toString();
        tMobile= "Mobile : " + bundle.get("mobile").toString();
        tEmail = "Email : " + bundle.get("email").toString();

        fullname.setText(tFullname);
        mobile.setText(tMobile);
        email.setText(tEmail);

        // Navigation work

        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav = findViewById(R.id.navmenu);
        drawerLayout = findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.menu_home :
                        Toast.makeText(getApplicationContext(), "Home panel is Opened.", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_setting :
                        Toast.makeText(getApplicationContext(), "Setting panel is Opened.", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_about :
                        Toast.makeText(getApplicationContext(), "About panel is Opened.", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

                return true;
            }
        });

    }
}