package shlottery.gov.cn.lotterycenter.bean;

import java.util.List;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-12-20-0020 13:45
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class HomeBean {

    /**
     * ret : 100
     * data : {"focus":[{"picUrl":"http://vpn.gooooal.com/shtcapi/pic/1001/466143.jpg","title":"头奖分落三地 大乐透奖池38.06亿","linkType":2,"linkUrl":"","linkItem":"139"},{"picUrl":"http://vpn.gooooal.com/shtcapi/pic/1001/640147.jpg","title":"\u201c体彩之星\u201d第三届体彩销售员技能大赛精彩纷呈","linkType":2,"linkUrl":"","linkItem":"143"}],"notice":[{"id":44,"title":"关于足彩12月9日奖期计奖有关事宜的通知"},{"id":45,"title":"关于足彩12月16-18日奖期竞猜场次的通知"},{"id":4,"title":"2016年上海市体育彩票销售网点征召启事"}],"bonus":[{"lotId":"dlt","lotName":"大乐透","issueNo":"2016149","bonusTime":1482148800,"bonusCode":"01,02,03,12,33#04,09"},{"lotId":"pl3","lotName":"排列三","issueNo":"2016347","bonusTime":1482148800,"bonusCode":"5,8,9"},{"lotId":"pl5","lotName":"排列五","issueNo":"2016347","bonusTime":1482148800,"bonusCode":"5,8,9,0,9"},{"lotId":"sh115","lotName":"上海11选5","issueNo":"2016122029","bonusTime":1482212395,"bonusCode":"08,10,03,05,06"}],"famous":[{"id":1,"name":"黄宇龙","logo":"http://vpn.gooooal.com/shtcapi/pic/1001/336104.jpg","intro":"现供职于新闻晨报体育部，对个体化比赛的分析独到和捕捉准确。","winRate":83}],"recom":[{"id":3,"lotId":"jzxspf","lotName":"竞彩足球 让球胜平负","issueNo":"20161212","playType":"3串1","amount":1,"multiple":1,"totalMoney":2}],"news":[{"id":69,"title":"[青浦]区体彩\u201c11选5\u201d自主促销活动","picUrl":"http://vpn.gooooal.com/shtcapi/pic/1001/992144.jpg","tags":"上海11选5,青浦区"},{"id":138,"title":"\u201c11+2\u201d复式票擒奖 杨浦彩民收获大乐透1086万","picUrl":"http://vpn.gooooal.com/shtcapi/pic/1001/448142.jpg","tags":"大乐透,中奖"}]}
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
        private List<FocusBean> focus;
        private List<NoticeBean> notice;
        private List<BonusBean> bonus;
        private List<FamousBean> famous;
        private List<RecomBean> recom;
        private List<NewsBean> news;

        public List<FocusBean> getFocus() {
            return focus;
        }

        public void setFocus(List<FocusBean> focus) {
            this.focus = focus;
        }

        public List<NoticeBean> getNotice() {
            return notice;
        }

        public void setNotice(List<NoticeBean> notice) {
            this.notice = notice;
        }

        public List<BonusBean> getBonus() {
            return bonus;
        }

        public void setBonus(List<BonusBean> bonus) {
            this.bonus = bonus;
        }

        public List<FamousBean> getFamous() {
            return famous;
        }

        public void setFamous(List<FamousBean> famous) {
            this.famous = famous;
        }

        public List<RecomBean> getRecom() {
            return recom;
        }

        public void setRecom(List<RecomBean> recom) {
            this.recom = recom;
        }

        public List<NewsBean> getNews() {
            return news;
        }

        public void setNews(List<NewsBean> news) {
            this.news = news;
        }

        public static class FocusBean {
            /**
             * picUrl : http://vpn.gooooal.com/shtcapi/pic/1001/466143.jpg
             * title : 头奖分落三地 大乐透奖池38.06亿
             * linkType : 2
             * linkUrl :
             * linkItem : 139
             */

            private String picUrl;
            private String title;
            private int linkType;
            private String linkUrl;
            private int linkItem;

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getLinkType() {
                return linkType;
            }

            public void setLinkType(int linkType) {
                this.linkType = linkType;
            }

            public String getLinkUrl() {
                return linkUrl;
            }

            public void setLinkUrl(String linkUrl) {
                this.linkUrl = linkUrl;
            }

            public int getLinkItem() {
                return linkItem;
            }

            public void setLinkItem(int linkItem) {
                this.linkItem = linkItem;
            }
        }

        public static class NoticeBean {
            /**
             * id : 44
             * title : 关于足彩12月9日奖期计奖有关事宜的通知
             */

            private int id;
            private String title;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class BonusBean {
            /**
             * lotId : dlt
             * lotName : 大乐透
             * issueNo : 2016149
             * bonusTime : 1482148800
             * bonusCode : 01,02,03,12,33#04,09
             * plusAward : 嘉奖资讯id
             */

            private String lotId;
            private String lotName;
            private String issueNo;
            private int bonusTime;
            private String bonusCode;
            private int plusAward=-1;

            public int getPlusAward() {
                return plusAward;
            }

            public void setPlusAward(int plusAward) {
                this.plusAward = plusAward;
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

            public int getBonusTime() {
                return bonusTime;
            }

            public void setBonusTime(int bonusTime) {
                this.bonusTime = bonusTime;
            }

            public String getBonusCode() {
                return bonusCode;
            }

            public void setBonusCode(String bonusCode) {
                this.bonusCode = bonusCode;
            }
        }

        public static class FamousBean {
            /**
             * id : 1
             * name : 黄宇龙
             * logo : http://vpn.gooooal.com/shtcapi/pic/1001/336104.jpg
             * intro : 现供职于新闻晨报体育部，对个体化比赛的分析独到和捕捉准确。
             * winRate : 83
             */

            private int id;
            private String name;
            private String logo;
            private String intro;
            private int winRate;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public int getWinRate() {
                return winRate;
            }

            public void setWinRate(int winRate) {
                this.winRate = winRate;
            }
        }

        public static class RecomBean {
            /**
             * id : 3
             * lotId : jzxspf
             * lotName : 竞彩足球 让球胜平负
             * issueNo : 20161212
             * playType : 3串1
             * amount : 1
             * multiple : 1
             * totalMoney : 2
             */

            private int id;
            private String lotId;
            private String lotName;
            private String issueNo;
            private String playType;
            private int amount;
            private int multiple;
            private String totalMoney;

            public String getBonusEstimate() {
                return bonusEstimate;
            }

            public void setBonusEstimate(String bonusEstimate) {
                this.bonusEstimate = bonusEstimate;
            }

            private String bonusEstimate;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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
        }

        public static class NewsBean {
            /**
             * id : 69
             * title : [青浦]区体彩“11选5”自主促销活动
             * picUrl : http://vpn.gooooal.com/shtcapi/pic/1001/992144.jpg
             * tags : 上海11选5,青浦区
             */

            private int id;
            private String title;
            private String picUrl;
            private String tags;
            private String newsTime;

            public String getNewsTime() {
                return newsTime;
            }

            public void setNewsTime(String newsTime) {
                this.newsTime = newsTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
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
