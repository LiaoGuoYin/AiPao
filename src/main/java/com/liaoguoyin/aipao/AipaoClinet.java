package com.liaoguoyin.aipao;

import com.liaoguoyin.aipao.api.ApiService;
import com.liaoguoyin.aipao.bean.InfoBean;
import com.liaoguoyin.aipao.bean.LoginBean;
import com.liaoguoyin.aipao.bean.RunningInfoBean;
import com.liaoguoyin.aipao.bean.UploadBean;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.liaoguoyin.aipao.Utils.encrypt;
import static com.liaoguoyin.aipao.Utils.randomUtils;

public class AipaoClinet {
    public int UserId;
    public StringBuilder output = new StringBuilder("\n");
    private ApiService apiService;
    private String token;
    private String runid;
    private int distance;
    private int time;
    private double minSpeed;
    private double maxSpeed;
    private Retrofit retrofitAndroid;
    private Retrofit retrofitIOS;

    public AipaoClinet() {
        retrofitAndroid = new Retrofit.Builder()
                .baseUrl("http://client3.aipao.me/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitIOS = new Retrofit.Builder()
                .baseUrl("http://client4.aipao.me/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofitAndroid.create(ApiService.class);
    }

    public void imeiLogin(String imeicode) throws IOException {
        System.out.println("IMEICode: \t\t" + imeicode);
        Call<LoginBean> dataEntityCall = apiService.imeilogin(imeicode);
        Response<LoginBean> responseLogin = dataEntityCall.execute();
        LoginBean loginBean = responseLogin.body();

        if (!loginBean.isSuccess()) {
            apiService = retrofitIOS.create(ApiService.class);
            dataEntityCall = apiService.imeilogin(imeicode);
            responseLogin = dataEntityCall.execute();
            loginBean = responseLogin.body();
        }

        token = loginBean.getData().getToken();
        UserId = loginBean.getData().getUserId();
        output.append(loginBean.toString());
        System.out.println("Login: \t" + loginBean.toString());
    }

    public void getBasicInfo() throws IOException {
        Call<InfoBean> infoEntityCall = apiService.getinfo(token);
        InfoBean infoBean = infoEntityCall.execute().body();

        output.append(infoBean.toString());
        distance = infoBean.getData().getSchoolRun().getLengths();
        minSpeed = infoBean.getData().getSchoolRun().getMinSpeed();
        maxSpeed = infoBean.getData().getSchoolRun().getMaxSpeed();
        System.out.println("正在获取个人信息: \t" + infoBean.toString());
    }

    public void running() throws IOException {
        Map<String, String> locationmap = new HashMap<>();
        locationmap.put("S1", "40.62825");
        locationmap.put("S2", "120.79107");
        locationmap.put("S3", String.valueOf(distance));
        distance = randomUtils(distance, distance + 5);
        time = randomUtils(distance / maxSpeed, distance / minSpeed);

        System.out.println("distance / maxSpeed:" + distance / maxSpeed);
        System.out.println("distance / min:" + distance / minSpeed);

        Call<RunningInfoBean> running = apiService.startRunning(token, locationmap);
        RunningInfoBean RunningInfoBean = running.execute().body();

        output.append(RunningInfoBean.toString());
        runid = RunningInfoBean.getData().getRunId();
        System.out.println("获取本次跑步信息: " + RunningInfoBean.toString());
        System.out.print("开始跑步, 取得RunId: \t");
        System.out.print("本次路程: (米)" + distance);
        System.out.println("\t用时: (秒)" + time);
    }

    public void uploadRecord() throws IOException {
        Map<String, String> record = new HashMap<>();
        record.put("S1", runid);// 本次跑步记录的id
        record.put("S4", encrypt(time));// 跑步时间 s
        record.put("S5", encrypt(distance));// 跑步距离 m
        record.put("S6", "A0A2A1A3A0");// 跑步关键点 形似: A0A2A1A3A0
        record.put("S7", "1");// 本次跑步的状态 1表示成功，0、2等非1值表示失败
        record.put("S8", "xfvdmyirsg");// 加密原字段
        record.put("S9", encrypt(randomUtils(1198, 1889)));// 跑步步数

        System.out.print("正在上传跑步记录: \t");
        Call<UploadBean> uploadRecord = apiService.uploadRecord(token, record);
        System.out.println(uploadRecord.execute().body().getData());
    }

}
