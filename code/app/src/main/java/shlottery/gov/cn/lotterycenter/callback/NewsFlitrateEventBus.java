package shlottery.gov.cn.lotterycenter.callback;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2016/12/9 10:30
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class NewsFlitrateEventBus implements Serializable{
    private ArrayList<String> lottery = new ArrayList<>();
    private ArrayList<String> category = new ArrayList<>();
    private ArrayList<String> famous = new ArrayList<>();
    private ArrayList<String> district = new ArrayList<>();

    public void clear()
    {
        lottery.clear();
        category.clear();
        famous.clear();
        district.clear();
    }

    public ArrayList<String> getLottery() {
        return lottery;
    }

    public void addLottery(String name) {
        lottery.add(name);
    }

    public void removeLottery(String name) {
        if (lottery.contains(name)) {
            lottery.remove(name);
        }

    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public void addCategory(String name) {
        category.add(name);
    }

    public void removeCategory(String name) {
        if (category.contains(name)) {
            category.remove(name);
        }

    }

    public ArrayList<String> getFamous() {
        return famous;
    }

    public void addFamous(String name) {
        famous.add(name);
    }

    public void removeFamous(String name) {
        if (famous.contains(name)) {
            famous.remove(name);
        }

    }

    public ArrayList<String> getDistrict() {
        return district;
    }

    public void addDistrict(String name) {
        district.add(name);
    }

    public void removeDistrict(String name) {
        if (district.contains(name)) {
            district.remove(name);
        }

    }
}
