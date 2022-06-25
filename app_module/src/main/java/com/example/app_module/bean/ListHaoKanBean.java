package com.example.app_module.bean;

import java.util.List;

public class ListHaoKanBean {
    public String total;
    public List<HaoKanBean> list;

    public class HaoKanBean {
        public String coverUrl;
        public String duration;
        public String id;
        public String playUrl;
        public String title;
        public String userName;
        public String userPic;


        public HaoKanBean(String coverUrl, String duration, String id, String playUrl, String title, String userName, String userPic) {
            this.coverUrl = coverUrl;
            this.duration = duration;
            this.id = id;
            this.playUrl = playUrl;
            this.title = title;
            this.userName = userName;
            this.userPic = userPic;
        }


        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPlayUrl() {
            return playUrl;
        }

        public void setPlayUrl(String playUrl) {
            this.playUrl = playUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPic() {
            return userPic;
        }

        public void setUserPic(String userPic) {
            this.userPic = userPic;
        }


        @Override
        public String toString() {
            return "KaoKanBean{" +
                    "coverUrl='" + coverUrl + '\'' +
                    ", duration='" + duration + '\'' +
                    ", id='" + id + '\'' +
                    ", playUrl='" + playUrl + '\'' +
                    ", title='" + title + '\'' +
                    ", userName='" + userName + '\'' +
                    ", userPic='" + userPic + '\'' +
                    '}';
        }

    }


}
