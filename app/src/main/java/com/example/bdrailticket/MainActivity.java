package com.example.bdrailticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bdrailticket.model.User;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity{

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.mainTabLayoutId);
        viewPager = findViewById(R.id.mainViewPagerId);

        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    class MyPagerAdapter extends FragmentPagerAdapter {

        String[] text = {"Login","Sign-up","Forgot Password!"};

        public MyPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            if (position==0){
                return new LoginFragment();
            }
            if (position==1){
                return new SignUpFragment();
            }
            if (position==2){
                return new ForgotPasswordFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return text.length;
        }

        public CharSequence getPageTitle(int position){
            return text[position];
        }

    }
}