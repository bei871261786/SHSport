package shlottery.gov.cn.lotterycenter.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-24-0024 10:33
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class PushBean implements Serializable {

    /**
     * ret : 100
     * msg : 错误
     * data : {"list":[{"id":"lottery","name":"开奖推送","items":[{"id":"dlt","name":"大乐透","push":true},{"id":"pl3","name":"排列三","push":false},{"id":"pl5","name":"排列五","push":false},{"id":"qxc","name":"七星彩","push":false},{"id":"sh115","name":"上海11选5","push":false},{"id":"sfc","name":"胜负彩","push":false},{"id":"bqc","name":"半全场","push":false},{"id":"jqc","name":"进球彩","push":false}]},{"id":"sysmsg","name":"系统消息","push":false},{"id":"bonus","name":"中奖提醒","push":false},{"id":"expert","name":"专家看彩","push":false}]}
     */

    private int ret;
    private String msg;
    private DataBean data;
    private ArrayList<Boolean> onOffList;//存储开关状态

    public ArrayList<Boolean> getOnOffList() {
        return onOffList;
    }

    public void setOnOffList(ArrayList<Boolean> onOffList) {
        this.onOffList = onOffList;
    }

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

    public static class DataBean implements Serializable{
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            /**
             * id : lottery
             * name : 开奖推送
             * items : [{"id":"dlt","name":"大乐透","push":true},{"id":"pl3","name":"排列三","push":false},{"id":"pl5","name":"排列五","push":false},{"id":"qxc","name":"七星彩","push":false},{"id":"sh115","name":"上海11选5","push":false},{"id":"sfc","name":"胜负彩","push":false},{"id":"bqc","name":"半全场","push":false},{"id":"jqc","name":"进球彩","push":false}]
             * push : false
             */

            private String id;
            private String name;
            private boolean push;
            private List<ItemsBean> items;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isPush() {
                return push;
            }

            public void setPush(boolean push) {
                this.push = push;
            }

            public List<ItemsBean> getItems() {
                return items;
            }

            public void setItems(List<ItemsBean> items) {
                this.items = items;
            }

            public static class ItemsBean implements Serializable{
                /**
                 * id : dlt
                 * name : 大乐透
                 * push : true
                 */

                private String id;
                private String name;
                private boolean push;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public boolean isPush() {
                    return push;
                }

                public void setPush(boolean push) {
                    this.push = push;
                }
            }
        }
    }
}
