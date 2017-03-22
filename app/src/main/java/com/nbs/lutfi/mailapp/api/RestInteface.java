package com.nbs.lutfi.mailapp.api;

import com.nbs.lutfi.mailapp.model.MailModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Lutfi on 3/22/2017.
 */

public interface RestInteface {
    @GET("inbox.json")
    Call<List<MailModel>> getInbox();
}
