package shlottery.gov.cn.lotterycenter.bean;

import java.io.Serializable;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-03-0003 14:29
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class ClientBean implements Serializable{

    /**
     * ret : 100
     * data : {"client":"i1ioocxfbt05","newVersion":false,"userLogin":false}
     */

    private int ret;
    private DataBean data;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * client : i1ioocxfbt05
         * newVersion : false
         * userLogin : false
         */

        private String client;
        private boolean newVersion;
        private boolean userLogin;
        private boolean forceUpdate;
        private int userType;
        private String downUrl;
        private String mobile;
        private String nickName;
        private String logoUrl;
        private String secret;
        private String realName;
        private String verName;


        public boolean isForceUpdate() {
            return forceUpdate;
        }

        public void setForceUpdate(boolean forceUpdate) {
            this.forceUpdate = forceUpdate;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getDownUrl() {
            return downUrl;
        }

        public void setDownUrl(String downUrl) {
            this.downUrl = downUrl;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getVerName() {
            return verName;
        }

        public void setVerName(String verName) {
            this.verName = verName;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public String getClient() {
            return client;
        }

        public void setClient(String client) {
            this.client = client;
        }

        public boolean isNewVersion() {
            return newVersion;
        }

        public void setNewVersion(boolean newVersion) {
            this.newVersion = newVersion;
        }

        public boolean isUserLogin() {
            return userLogin;
        }

        public void setUserLogin(boolean userLogin) {
            this.userLogin = userLogin;
        }
    }
}
