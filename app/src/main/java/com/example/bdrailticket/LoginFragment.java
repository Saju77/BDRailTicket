package com.example.bdrailticket;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bdrailticket.model.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    private EditText mobile, password;

    public LoginFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button buttonReg = view.findViewById(R.id.loginbtn);
        buttonReg.setOnClickListener(this);

        mobile= view.findViewById(R.id.loginMobileInputId);
        password= view.findViewById(R.id.loginPassInputId);

        return view;

    }

    @Override
    public void onClick(View v) {

        try {


        if (v.getId() == R.id.loginbtn) {

            String textMobile = mobile.getText().toString();
            String textPassword = password.getText().toString();

            User user = new User();
            user.setMobile(Integer.parseInt(textMobile));
            user.setPassword(textPassword);

            DBHelper dbHelper = new DBHelper(getActivity());
            User user1 = dbHelper.login(user);


            if (user1 != null) {
                Intent i = new Intent(getActivity(), HomeActivity.class);
                i.putExtra("fullname",user1.getFull_name());
                i.putExtra("mobile", user1.getMobile());
                i.putExtra("email", user1.getEmail());
                startActivity(i);
                Toast.makeText(getActivity(), "User Successfully Logged in.", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getActivity(), "Username/Password Incorrect", Toast.LENGTH_SHORT).show();
                password.setText("");

            }

        }
        }catch (Exception e){
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}