package com.example.hp.splashprj.Fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hp.splashprj.Activity.QueryExpress;
import com.example.hp.splashprj.R;

/**
 * Created by hp on 2017/10/26.
 */

public class ExpreFragment extends Fragment{
    // UI references.
    private EditText expressNum;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.express_fragement,null);
        // Set up the login form.
        expressNum = (EditText) view.findViewById(R.id.express);
        Button queryb_utton = (Button) view.findViewById(R.id.email_sign_in_button);
        queryb_utton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        return view;
    }
    private void attemptLogin() {
        //expressNum .setError(null);
        // Store values at the time of the login attempt.
        String exNu= expressNum .getText().toString();

        // Check for a valid
        if (TextUtils.isEmpty(exNu)) {
            expressNum .setError(getString(R.string.error_required));
        }else if(exNu.length()<10){
            expressNum .setError(getString(R.string.error_kd));
        }else if(exNu.matches("")){
           // String url = "https://v.juhe.cn/exp/index?key=185ffd4c9295e36d59ab735fc35e57fd&com=yt&no=887227303180327573";

        }else{
            Intent intent=new Intent(getActivity(),QueryExpress.class);
            intent.putExtra("expressNum",exNu);
            startActivity(intent);
        }

    }

}
