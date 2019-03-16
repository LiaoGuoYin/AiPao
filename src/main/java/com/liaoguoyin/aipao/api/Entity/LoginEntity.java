package com.liaoguoyin.aipao.api.Entity;

import java.util.HashMap;
import java.util.Map;

public class LoginEntity {
    /**
     * Success : true
     * Data : {"Token":"8ab97c64cab149adb773c821c0cbce8f","UserId":396970,"IMEICode":"63166186f4804dceb6b860330ae2f576","AndroidVer":2.1,"AppleVer":1.24,"WinVer":1}
     */

    private boolean Success;
    private DataBean Data;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    /**
     * Todo 似乎这里也可以用反射
     *
     * @return 返回一些有价值的信息
     */
    @Override
    public String toString() {
        Map<String, Object> map = new HashMap<>();
        map.put("Token", this.getData().Token);
        map.put("UserId", this.getData().UserId);
        map.put("Login状态", this.Success);
        return map.toString();
    }

    public static class DataBean {
        /**
         * Token : 8ab97c64cab149adb773c821c0cbce8f
         * UserId : 396971
         * IMEICode : 63166186f4804dceb6b860330ae2f576
         * AndroidVer : 2.1
         * AppleVer : 1.24
         * WinVer : 1
         */

        private String Token;
        private int UserId;
        private String IMEICode;
        private double AndroidVer;
        private double AppleVer;
        private int WinVer;

        public String getToken() {
            return Token;
        }

        public void setToken(String Token) {
            this.Token = Token;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public String getIMEICode() {
            return IMEICode;
        }

        public void setIMEICode(String IMEICode) {
            this.IMEICode = IMEICode;
        }

        public double getAndroidVer() {
            return AndroidVer;
        }

        public void setAndroidVer(double AndroidVer) {
            this.AndroidVer = AndroidVer;
        }

        public double getAppleVer() {
            return AppleVer;
        }

        public void setAppleVer(double AppleVer) {
            this.AppleVer = AppleVer;
        }

        public int getWinVer() {
            return WinVer;
        }

        public void setWinVer(int WinVer) {
            this.WinVer = WinVer;
        }
    }
}
