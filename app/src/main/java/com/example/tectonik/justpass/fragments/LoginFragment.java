package com.example.tectonik.justpass.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.tectonik.justpass.R;

public class LoginFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        CheckBox autoLogin = (CheckBox) rootView.findViewById(R.id.login_checkbox);
        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getActivity()
                            .getPreferences(Context.MODE_PRIVATE)
                            .edit().putBoolean(getString(R.string.autoLog), true)
                            .commit();
                } else {
                    getActivity()
                            .getPreferences(Context.MODE_PRIVATE)
                            .edit().putBoolean(getString(R.string.autoLog), false)
                            .commit();
                }
            }
        });


        return rootView;
    }

}
