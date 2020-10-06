package com.example.bdrailticket;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bdrailticket.model.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment implements View.OnClickListener{

   private EditText fullname, mobile, email, password, cPassword;

    public SignUpFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        Button buttonReg = view.findViewById(R.id.signupbtn);
        buttonReg.setOnClickListener(this);

        fullname = view.findViewById(R.id.signupFullnameInputId);
        mobile= view.findViewById(R.id.signupMobileInputId);
        email= view.findViewById(R.id.signupEmailInputId);
        password= view.findViewById(R.id.signupPassInputId);
        cPassword= view.findViewById(R.id.signupConfirmPassInputId);

        return view;
    }

    @Override
    public void onClick(View v) {

        try{

        if(v.getId()==R.id.signupbtn) {

            String textFullname = fullname.getText().toString();
            String textMobile = mobile.getText().toString();
            String textEmail = email.getText().toString();
            String textPassword = password.getText().toString();
            String textcPassword = cPassword.getText().toString();

            User user = new User();
            user.setFull_name(textFullname);
            user.setMobile(Integer.parseInt(textMobile));
            user.setEmail(textEmail);
            user.setPassword(textPassword);
            user.setConfirm_password(textcPassword);

            DBHelper dbHelper = new DBHelper(getActivity());
            long i = dbHelper.addUser(user);

            if (i > 0) {
                Toast.makeText(getActivity(), "User sign-up completed successfully.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "User already sign-up/have some error.", Toast.LENGTH_SHORT).show();
            }

            fullname.setText("");
            mobile.setText("");
            email.setText("");
            password.setText("");
            cPassword.setText("");
        }
        }catch (Exception e){
            Toast.makeText(getActivity(), e.getCause().toString(), Toast.LENGTH_SHORT).show();
            Log.d(" this is the cause",e.getCause().toString());
        }
    }
}