package com.liaoguoyin.aipao.api;

import com.liaoguoyin.aipao.bean.InfoBean;
import com.liaoguoyin.aipao.bean.LoginBean;
import com.liaoguoyin.aipao.bean.RunningInfoBean;
import com.liaoguoyin.aipao.bean.UploadBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface ApiService {

    @GET("{token}/QM_Users/Login_AndroidSchool")
    Call<LoginBean> imeilogin(@Query("IMEICode") String imeicode);

    @GET("{token}/QM_Users/GS")
    Call<InfoBean> getinfo(@Path("token") String token);

    @GET("{token}/QM_Runs/SRS")
    Call<RunningInfoBean> startRunning(@Path("token") String token, @QueryMap Map<String, String> locationmap);

    @GET("{token}/QM_Runs/ES")
    Call<UploadBean> uploadRecord(@Path("token") String token, @QueryMap Map<String, String> record);

}
