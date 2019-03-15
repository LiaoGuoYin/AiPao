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

    /**
     * 将数字转换成字母，编码的时候不同数字对应的字母唯一
     *
     * @param i
     * @return 加密处理后的字符串
     */
    private static String encrypt(int i) {
        String encryptOrigin = "czplgyznba";// 任意10个不同的字符串
        StringBuilder result = new StringBuilder();
        char[] chars = String.valueOf(i).toCharArray();

        for (char each : chars) {
            result.append(encryptOrigin.charAt(each - '0'));
        }
        return result.toString();
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
        gender = info.getData().getUser().getSex();
        System.out.println(info.getData().getUser().getNickName() + gender);
    }

    public void running() throws IOException {
        Map<String, String> locationmap = new HashMap<>();
        locationmap.put("S1", "40.62825");
        locationmap.put("S2", "120.79107");

        if (gender.equals("男")) {
            locationmap.put("S3", "3000");
            distance = 3000 + (int) (Math.random() * 5);
            time = distance / 3;
        } else if (gender.equals("女")) {
            locationmap.put("S3", "2500");
            distance = 3000 + (int) (Math.random() * 5);
            time = distance / 3;
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
        record.put("S9", encrypt(2205));// 跑步步数

        System.out.print("正在上传跑步记录: \t");
        Call<uploadEntity> uploadRecord = apiService.uploadRecord(token, record);
        System.out.println(Objects.requireNonNull(uploadRecord.execute().body()).getData());
    }

}
