package shlottery.gov.cn.lotterycenter.bean;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-15-0015 16:16
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class VcodeBean {
    /**
     * ret : 100
     * msg : 手机号已注册
     * data : {"mobile":"18772557682","validTime":1479461683,"logoUrl":"http://vpn.gooooal.com/shtcapi/pic/1001/196005.jpg"}
     */

    private int ret;
    private String msg;
    private DataBean data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * mobile : 18772557682
         * validTime : 1479461683
         * logoUrl : http://vpn.gooooal.com/shtcapi/pic/1001/196005.jpg
         */

        private String mobile;
        private int validTime;
        private String logoUrl;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getValidTime() {
            return validTime;
        }

        public void setValidTime(int validTime) {
            this.validTime = validTime;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }
    }


  /*  *//**
     * ret : 100
     * data : {"mobile":"18772557682","validTime":1479461442,"logoUrl":"http://vpn.gooooal.com/shtcapi/pic/1001/196005.jpg","msg":"手机号已注册"}
     *//*

    private int ret;
    private DataBean data;

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
      *//*  *//**//**
         * mobile : 18772557682
         * validTime : 1479461442
         * logoUrl : http://vpn.gooooal.com/shtcapi/pic/1001/196005.jpg
         * msg : 手机号已注册
         *//**//*

        private String mobile;
        private int validTime;
        private String logoUrl;
        private String msg;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getValidTime() {
            return validTime;
        }

        public void setValidTime(int validTime) {
            this.validTime = validTime;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }*/
}
