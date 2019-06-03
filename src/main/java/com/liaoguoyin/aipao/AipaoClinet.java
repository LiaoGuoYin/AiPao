package com.liaoguoyin.aipao;

import com.liaoguoyin.aipao.api.ApiService;
import com.liaoguoyin.aipao.api.RetrofitManager;
import com.liaoguoyin.aipao.bean.InfoBean;
import com.liaoguoyin.aipao.bean.LoginBean;
import com.liaoguoyin.aipao.bean.RunningInfoBean;
import com.liaoguoyin.aipao.bean.UploadBean;
import retrofit2.Call;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.liaoguoyin.aipao.Utils.encrypt;
import static com.liaoguoyin.aipao.Utils.randomUtils;

public class AipaoClinet {
    private static ApiService apiService;
    private static RetrofitManager retrofitManager;

    public Map<Object, Object> info = new HashMap<>();
    public StringBuilder output = new StringBuilder();

    private String imeicode;
    private int distance, time;
    private double minSpeed, maxSpeed;

    public AipaoClinet(String imeicode) {
        super();
        this.imeicode = imeicode;
        selectAPI(imeicode);
    }

    private static void selectAPI(String imeicode) {

        try {
            retrofitManager = new RetrofitManager("http://client3.aipao.me/api/");
            apiService = retrofitManager.getApiService();
            LoginBean TestResult = apiService.imeilogin("Login_AndroidSchool", imeicode).execute().body();

            if (!Objects.requireNonNull(TestResult).isSuccess()) {
                retrofitManager = new RetrofitManager("http://client4.aipao.me/api/");
                apiService = retrofitManager.getApiService();
            }

        } catch (Exception e) {
            System.out.println("IMEICode has overdue");
        }

    }

    public void login() throws IOException {
        String apiUrl = retrofitManager.getRetrofit().baseUrl().toString();
        Call<LoginBean> loginBeanCall;

        if (apiUrl.equals("http://client4.aipao.me/api/")) {
            loginBeanCall = apiService.imeilogin("LoginSchool", imeicode);
        } else {
            loginBeanCall = apiService.imeilogin("Login_AndroidSchool", imeicode);
        }
        LoginBean loginBean = loginBeanCall.execute().body();

        System.out.println(loginBeanCall.request());
        System.out.println("Login: " + Objects.requireNonNull(loginBean).toString());

        info.put("token", loginBean.getData().getToken());
        info.put("userId", loginBean.getData().getUserId());
        output.append(loginBean.toString());
    }

    public void getBasicInfo() throws IOException {
        Call<InfoBean> infoBeanCall = apiService.getinfo(info.get("token").toString());
        InfoBean infoBean = infoBeanCall.execute().body();

        distance = Objects.requireNonNull(infoBean).getData().getSchoolRun().getLengths();
        minSpeed = infoBean.getData().getSchoolRun().getMinSpeed();
        maxSpeed = infoBean.getData().getSchoolRun().getMaxSpeed();

        System.out.println(infoBeanCall.request());
        System.out.println("Getting the basic infomations: \t" + infoBean.toString());
        output = new StringBuilder();
        output.append(infoBean.toString());
    }

    public void running() throws IOException {
        Map<String, String> locationmap = new HashMap<>();
        locationmap.put("S1", "40.62825");
        locationmap.put("S2", "120.79107");
        locationmap.put("S3", String.valueOf(distance));
        distance = randomUtils(distance, distance + 5);
        time = randomUtils(distance / maxSpeed, distance / minSpeed);

        Call<RunningInfoBean> running = apiService.startRunning(info.get("token").toString(), locationmap);
        RunningInfoBean RunningInfoBean = running.execute().body();
        info.put("runid", Objects.requireNonNull(RunningInfoBean).getData().getRunId());

        System.out.println(running.request());
        System.out.println("Getting this recorder: " + RunningInfoBean.toString());
        System.out.format("Time Scope: [%.1f, %.1f]", distance / maxSpeed, distance / minSpeed);
        System.out.format("%nRunning Distance: %s(米), Cost Time: %s(秒)%n", distance, time);
        output = new StringBuilder();
        output.append(RunningInfoBean.toString());
    }

    public void uploadRecord() throws IOException {
        Map<String, String> record = new HashMap<>();
        record.put("S1", info.get("runid").toString());// 本次跑步记录的id
        record.put("S4", encrypt(time));// 跑步时间 s
        record.put("S5", encrypt(distance));// 跑步距离 m
        record.put("S6", "A0A2A1A3A0");// 跑步关键点 形似: A0A2A1A3A0
        record.put("S7", "1");// 本次跑步的状态 1表示成功，0、2等非1值表示失败
        record.put("S8", "xfvdmyirsg");// 加密原字段
        record.put("S9", encrypt(randomUtils(1198, 1889)));// 跑步步数

        Call<UploadBean> uploadRecord = apiService.uploadRecord(info.get("token").toString(), record);
        System.out.println(Objects.requireNonNull(uploadRecord.execute().body()).getData());
    }

}
