package com.liaoguoyin.aipao.api.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class runningEntity {

    private boolean Success;
    private DataBean Data;

    /**
     * Success : true
     * Data : {"StartTime":"2019-03-15 10:25:59","RunId":"ecab195139c146ecb4506d5840441cdc","FUserId":0,"FieldId":289,"Routes":"A0A3A1A2A0","LifeValue":0,"Powers":0,"LenValue":0,"Points":[{"PointNo":"A0","Lat":40.628369,"Lng":120.791004,"Minor":1},{"PointNo":"A3","Lat":40.627007,"Lng":120.791651,"Minor":4},{"PointNo":"A1","Lat":40.628355,"Lng":120.791669,"Minor":2},{"PointNo":"A2","Lat":40.627028,"Lng":120.790986,"Minor":3},{"PointNo":"A0","Lat":40.628369,"Lng":120.791004,"Minor":1}],"FiledName":"","Area":"","SenseType":"0","ImgUrl":"","Major":0}
     */

    @Override
    public String toString() {
        Map<String, Object> map = new HashMap<>();
        map.put("本次开始跑步时间", this.Data.StartTime);
        map.put("RunId", this.Data.RunId);
        List<DataBean.PointsBean> points = this.getData().getPoints();
        for (int i = 0; i < points.size(); i++) {
            map.put("跑步点" + i, "(" + points.get(i).Lng + "," + points.get(i).Lat + ")");
        }
        return map.toString();
    }

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

    public static class DataBean {
        /**
         * StartTime : 2019-03-15 10:25:59
         * RunId : ecab195139c146ecb4506d5840441cdc
         * FUserId : 0
         * FieldId : 289
         * Routes : A0A3A1A2A0
         * LifeValue : 0
         * Powers : 0
         * LenValue : 0
         * Points : [{"PointNo":"A0","Lat":40.628369,"Lng":120.791004,"Minor":1},{"PointNo":"A3","Lat":40.627007,"Lng":120.791651,"Minor":4},{"PointNo":"A1","Lat":40.628355,"Lng":120.791669,"Minor":2},{"PointNo":"A2","Lat":40.627028,"Lng":120.790986,"Minor":3},{"PointNo":"A0","Lat":40.628369,"Lng":120.791004,"Minor":1}]
         * FiledName :
         * Area :
         * SenseType : 0
         * ImgUrl :
         * Major : 0
         */

        private String StartTime;
        private String RunId;
        private int FieldId;
        private String Routes;
        private String SenseType;
        private int Major;
        private List<PointsBean> Points;

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String StartTime) {
            this.StartTime = StartTime;
        }

        public String getRunId() {
            return RunId;
        }

        public void setRunId(String RunId) {
            this.RunId = RunId;
        }

        public int getFieldId() {
            return FieldId;
        }

        public void setFieldId(int FieldId) {
            this.FieldId = FieldId;
        }

        public String getRoutes() {
            return Routes;
        }

        public void setRoutes(String Routes) {
            this.Routes = Routes;
        }

        public String getSenseType() {
            return SenseType;
        }

        public void setSenseType(String SenseType) {
            this.SenseType = SenseType;
        }

        public int getMajor() {
            return Major;
        }

        public void setMajor(int Major) {
            this.Major = Major;
        }

        List<PointsBean> getPoints() {
            return Points;
        }

        public void setPoints(List<PointsBean> Points) {
            this.Points = Points;
        }

        public static class PointsBean {
            /**
             * PointNo : A0
             * Lat : 40.628369
             * Lng : 120.791004
             * Minor : 1
             */

            private String PointNo;
            private double Lat;
            private double Lng;
            private int Minor;

            public String getPointNo() {
                return PointNo;
            }

            public void setPointNo(String PointNo) {
                this.PointNo = PointNo;
            }

            public double getLat() {
                return Lat;
            }

            public void setLat(double Lat) {
                this.Lat = Lat;
            }

            public double getLng() {
                return Lng;
            }

            public void setLng(double Lng) {
                this.Lng = Lng;
            }

            public int getMinor() {
                return Minor;
            }

            public void setMinor(int Minor) {
                this.Minor = Minor;
            }
        }
    }
}
