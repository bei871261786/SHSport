package shlottery.gov.cn.lotterycenter.bean;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/11/18 14:36
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class UserBean {

    /**
     * ret : 100
     * data : {"mobile":"15701656560","nickName":"都好都好","logoUrl":"http://vpn.gooooal.com/shtcapi/pic/user.jpg","userType":1,"realName":"","integral":2,"signIn":true}
     */

    private int ret;
    /**
     * mobile : 15701656560
     * nickName : 都好都好
     * logoUrl : http://vpn.gooooal.com/shtcapi/pic/user.jpg
     * userType : 1
     * realName :
     * integral : 2
     * signIn : true
     */

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
        private String mobile;
        private String nickName;
        private String logoUrl;
        private int userType;
        private String realName;
        private String idNumber;
        private int integral;
        private boolean signIn;
        private String subscribeNum;
        private String siteRegUrl;


        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public String getSiteRegUrl() {
            return siteRegUrl;
        }

        public void setSiteRegUrl(String siteRegUrl) {
            this.siteRegUrl = siteRegUrl;
        }

        public String getSubscribeNum() {
            return subscribeNum;
        }

        public void setSubscribeNum(String subscribeNum) {
            this.subscribeNum = subscribeNum;
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

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public boolean isSignIn() {
            return signIn;
        }

        public void setSignIn(boolean signIn) {
            this.signIn = signIn;
        }
    }
}
