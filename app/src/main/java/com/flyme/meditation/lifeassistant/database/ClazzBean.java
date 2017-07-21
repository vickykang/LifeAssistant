package com.flyme.meditation.lifeassistant.database;

import com.flyme.meditation.lifeassistant.bean.BaseBean;

/**
 * Created by kangweodai on 21/07/17.
 */

public class ClazzBean extends BaseBean {

    private int price;
    private String clazz;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
