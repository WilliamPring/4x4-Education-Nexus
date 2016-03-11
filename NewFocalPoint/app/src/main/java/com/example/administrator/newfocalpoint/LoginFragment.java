package com.example.administrator.newfocalpoint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LoginFragment extends Fragment implements View.OnClickListener {
    Button loginBttn;
    Button createAcc;
    Button forgotPass;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Inflate the layout for this fragment
        loginBttn = (Button) view.findViewById(R.id.log_in);
        loginBttn.setOnClickListener(this);

        createAcc = (Button) view.findViewById(R.id.create_account);
        createAcc.setOnClickListener(this);

        forgotPass = (Button) view.findViewById(R.id.forgot_pass);
        forgotPass.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        switch (v.getId()) {

            case R.id.log_in:
                // do your code
                break;

            case R.id.forgot_pass:
                Uri uriUrl = Uri.parse("http://williampring.com/forgotpass/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;

            case R.id.create_account:
                Fragment newFragment = new accountCreation();
                ft = getFragmentManager().beginTransaction();
                ft.addToBackStack(String.valueOf(newFragment));
                ft.replace(R.id.main_menu, newFragment).commit();
                break;

            default:
                break;
        }
    }
}
