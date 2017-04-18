package shlottery.gov.cn.lotterycenter.bean;

import java.util.List;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-26-0026 14:19
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class DingYueBean {

    /**
     * ret : 100
     * data : {"count":3,"pageNo":1,"pageCount":1,"list":[{"id":4,"type":0,"title":"欢迎使用上海体彩手机APP","sendTime":"2016-12-25","isRead":false},{"id":5,"type":2,"title":"竞彩足球 3串1 推荐","sendTime":"2016-12-24","isRead":false,"recomId":34,"name":"阿德","logo":"http://vpn.gooooal.com/shtcapi/pic/1001/112105.png","lotId":"jzxspf","lotName":"竞彩足球 让球胜平负","issueNo":"20161220","playType":"4串1","amount":4,"multiple":1,"totalMoney":8,"bonusEstimate":96.51,"validTime":1482249000},{"id":6,"type":1,"title":"2016年上海市体育彩票销售网点征召启事","sendTime":"2016-12-23","isRead":false,"newsId":4,"tags":"公告"}]}
     */

    private int ret;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

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
        /**
         * count : 3
         * pageNo : 1
         * pageCount : 1
         * list : [{"id":4,"type":0,"title":"欢迎使用上海体彩手机APP","sendTime":"2016-12-25","isRead":false},{"id":5,"type":2,"title":"竞彩足球 3串1 推荐","sendTime":"2016-12-24","isRead":false,"recomId":34,"name":"阿德","logo":"http://vpn.gooooal.com/shtcapi/pic/1001/112105.png","lotId":"jzxspf","lotName":"竞彩足球 让球胜平负","issueNo":"20161220","playType":"4串1","amount":4,"multiple":1,"totalMoney":8,"bonusEstimate":96.51,"validTime":1482249000},{"id":6,"type":1,"title":"2016年上海市体育彩票销售网点征召启事","sendTime":"2016-12-23","isRead":false,"newsId":4,"tags":"公告"}]
         */

        private int count;
        private int pageNo;
        private int pageCount;
        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 4
             * type : 0
             * title : 欢迎使用上海体彩手机APP
             * sendTime : 2016-12-25
             * isRead : false
             * recomId : 34
             * name : 阿德
             * logo : http://vpn.gooooal.com/shtcapi/pic/1001/112105.png
             * lotId : jzxspf
             * lotName : 竞彩足球 让球胜平负
             * issueNo : 20161220
             * playType : 4串1
             * amount : 4
             * multiple : 1
             * totalMoney : 8
             * bonusEstimate : 96.51
             * validTime : 1482249000
             * newsId : 4
             * tags : 公告
             */

            private int id;
            private int type;
            private String title;
            private String sendTime;
            private boolean isRead;
            private int recomId;
            private String name;
            private String logo;
            private String lotId;
            private String lotName;
            private String issueNo;
            private String playType;
            private int amount;
            private int multiple;
            private String totalMoney;
            private String bonusEstimate;
            private int validTime;
            private int newsId;
            private String tags;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSendTime() {
                return sendTime;
            }

            public void setSendTime(String sendTime) {
                this.sendTime = sendTime;
            }

            public boolean isIsRead() {
                return isRead;
            }

            public void setIsRead(boolean isRead) {
                this.isRead = isRead;
            }

            public int getRecomId() {
                return recomId;
            }

            public void setRecomId(int recomId) {
                this.recomId = recomId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getLotId() {
                return lotId;
            }

            public void setLotId(String lotId) {
                this.lotId = lotId;
            }

            public String getLotName() {
                return lotName;
            }

            public void setLotName(String lotName) {
                this.lotName = lotName;
            }

            public String getIssueNo() {
                return issueNo;
            }

            public void setIssueNo(String issueNo) {
                this.issueNo = issueNo;
            }

            public String getPlayType() {
                return playType;
            }

            public void setPlayType(String playType) {
                this.playType = playType;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public int getMultiple() {
                return multiple;
            }

            public void setMultiple(int multiple) {
                this.multiple = multiple;
            }

            public String getTotalMoney() {
                return totalMoney;
            }

            public void setTotalMoney(String totalMoney) {
                this.totalMoney = totalMoney;
            }

            public String getBonusEstimate() {
                return bonusEstimate;
            }

            public void setBonusEstimate(String bonusEstimate) {
                this.bonusEstimate = bonusEstimate;
            }

            public int getValidTime() {
                return validTime;
            }

            public void setValidTime(int validTime) {
                this.validTime = validTime;
            }

            public int getNewsId() {
                return newsId;
            }

            public void setNewsId(int newsId) {
                this.newsId = newsId;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
            }
        }
    }
}
