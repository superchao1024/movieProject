package com.example.a92828.movieproject.bean;

import java.util.List;

public class PiaoFangBean {


    private int error_code;
    private String reason;
    private List<DataBean> data;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Irank : 1
         * MovieName : 绿皮书
         * BoxOffice : 1004.21
         * sumBoxOffice : 16423.19
         * movieDay : 6
         * boxPer : 35.04
         * time : 2019-03-06 17:4:42
         */

        private String Irank;
        private String MovieName;
        private String BoxOffice;
        private String sumBoxOffice;
        private String movieDay;
        private String boxPer;
        private String time;

        public String getIrank() {
            return Irank;
        }

        public void setIrank(String Irank) {
            this.Irank = Irank;
        }

        public String getMovieName() {
            return MovieName;
        }

        public void setMovieName(String MovieName) {
            this.MovieName = MovieName;
        }

        public String getBoxOffice() {
            return BoxOffice;
        }

        public void setBoxOffice(String BoxOffice) {
            this.BoxOffice = BoxOffice;
        }

        public String getSumBoxOffice() {
            return sumBoxOffice;
        }

        public void setSumBoxOffice(String sumBoxOffice) {
            this.sumBoxOffice = sumBoxOffice;
        }

        public String getMovieDay() {
            return movieDay;
        }

        public void setMovieDay(String movieDay) {
            this.movieDay = movieDay;
        }

        public String getBoxPer() {
            return boxPer;
        }

        public void setBoxPer(String boxPer) {
            this.boxPer = boxPer;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
