package com.liaoguoyin.aipao.api;

import com.liaoguoyin.aipao.api.Entity.InfoEntity;
import com.liaoguoyin.aipao.api.Entity.LoginEntity;
import com.liaoguoyin.aipao.api.Entity.runningEntity;
import com.liaoguoyin.aipao.api.Entity.uploadEntity;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.liaoguoyin.aipao.api.utils.encrypt;
import static com.liaoguoyin.aipao.api.utils.randomUtils;

public class AipaoClinet {
    private ApiService apiService;
    private String token;
    private String runid;
    private String gender;
    private int distance;
    private int time;

    public AipaoClinet() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://client3.aipao.me/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }


    public void imeiLogin(String imeicode) throws IOException {
        System.out.println("IMEICode: \t\t" + imeicode);
        Call<LoginEntity> dataEntityCall = apiService.imeilogin(imeicode);
        token = Objects.requireNonNull(dataEntityCall.execute().body()).getData().getToken();
        System.out.println("正在获取 token: \t" + token);
    }

    public void getBasicInfo() throws IOException {
        Call<InfoEntity> infoEntityCall = apiService.getinfo(token);
        InfoEntity info = infoEntityCall.execute().body();

        assert info != null;
        System.out.print("正在获取个人信息: \t");
        System.out.println(info.toString());
        gender = info.getData().getUser().getSex();
        System.out.println(info.getData().getUser().getNickName() + gender);
    }

    public void running() throws IOException {
        Map<String, String> locationmap = new HashMap<>();
        locationmap.put("S1", "40.62825");
        locationmap.put("S2", "120.79107");

        if (gender.equals("男")) {
            locationmap.put("S3", "3000");
            distance = randomUtils(3000, 3008);
            time = randomUtils(833, 1388);
        } else if (gender.equals("女")) {
            locationmap.put("S3", "2500");
            distance = randomUtils(2500, 2506);
            time = randomUtils(708, 1500);
        }

        Call<runningEntity> running = apiService.startRunning(token, locationmap);

        System.out.print("开始跑步 runid: \t");
        runningEntity runningEntity = running.execute().body();
        assert runningEntity != null;
        runid = runningEntity.getData().getRunId();
        System.out.println(runningEntity.getData().getRunId());
        System.out.println("性别: " + gender);
        System.out.println("路程: (m)" + distance);
        System.out.println("时间: (s)" + time);
    }

    public void uploadRecord() throws IOException {
        Map<String, String> record = new HashMap<>();
        record.put("S1", runid);// 本次跑步记录的id
        record.put("S4", encrypt(time));// 跑步时间 s
        record.put("S5", encrypt(distance));// 跑步距离 m
        record.put("S6", "");
        record.put("S7", "1");
        record.put("S8", "czplgyznba");// 加密原字段
        record.put("S9", encrypt(randomUtils(1998, 2389)));// 跑步步数

        System.out.print("正在上传跑步记录: \t");
        Call<uploadEntity> uploadRecord = apiService.uploadRecord(token, record);
        System.out.println(Objects.requireNonNull(uploadRecord.execute().body()).getData());
    }

}
