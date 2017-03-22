package com.nbs.lutfi.mailapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.nbs.lutfi.mailapp.adapter.MailAdapter;
import com.nbs.lutfi.mailapp.api.RestClient;
import com.nbs.lutfi.mailapp.api.RestInteface;
import com.nbs.lutfi.mailapp.model.MailModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<MailModel> messages = new ArrayList<>();
    private RecyclerView mailRV;
    private MailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mailRV = (RecyclerView) findViewById(R.id.mail_rv);
        mAdapter = new MailAdapter(messages,this);
        mailRV.setLayoutManager(new LinearLayoutManager(this));
        RestInteface apiService =
                RestClient.getClient().create(RestInteface.class);

        Call<List<MailModel>> call = apiService.getInbox();
        call.enqueue(new Callback<List<MailModel>>() {
            @Override
            public void onResponse(Call<List<MailModel>> call, Response<List<MailModel>> response) {
                List<MailModel> mails = response.body();
                messages.addAll(mails);
                /*for (MailModel mails : response.body()) {
                    messages.add(mails);
                }*/
                mailRV.setAdapter(mAdapter);
            }
            @Override
            public void onFailure(Call<List<MailModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Unable to fetch json: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
