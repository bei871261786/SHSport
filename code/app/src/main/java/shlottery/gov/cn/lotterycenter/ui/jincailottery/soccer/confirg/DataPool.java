package shlottery.gov.cn.lotterycenter.ui.jincailottery.soccer.confirg;


import java.util.HashMap;

import shlottery.gov.cn.lotterycenter.bean.Jincai.DingDanSelectBean;


/**
 * Created by yongchao.bei on 2016/7/13.
 */
public class DataPool {
    private static HashMap<Integer, DingDanSelectBean> dingDanSelectBeanMap;
    private static int multiple;
    private static int price;

    public static int getMultiple() {
        return multiple;
    }

    public static void setMultiple(int multiple) {
        DataPool.multiple = multiple;
    }

    public static int getPrice() {
        return price;
    }

    public static void setPrice(int price) {
        DataPool.price = price;
    }

    public static DingDanSelectBean getDingDanSelectBean(int type) {
        if (dingDanSelectBeanMap == null || !dingDanSelectBeanMap.containsKey(type)) {
            dingDanSelectBeanMap = new HashMap<>();
            dingDanSelectBeanMap.put(type, new DingDanSelectBean());
        }
        return dingDanSelectBeanMap.get(type);

    }

    public static void setDingDanSelectBeanMap(DingDanSelectBean dingDanSelectBean, int type) {
        if (dingDanSelectBeanMap == null || !dingDanSelectBeanMap.containsKey(type)) {
            dingDanSelectBeanMap = new HashMap<>();
        }
        dingDanSelectBeanMap.put(type, dingDanSelectBean);
    }
}
