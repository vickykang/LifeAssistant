package com.flyme.meditation.lifeassistant.bean;

import com.flyme.meditation.lifeassistant.database.ClazzBean;

import java.util.List;

/**
 * Created by kangweodai on 21/07/17.
 */

public class SourceBean extends BaseBean {

    private String name;
    private String url;
    private List<ClazzBean> clazzs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ClazzBean> getClazzs() {
        return clazzs;
    }

    public void setClazzs(List<ClazzBean> clazzs) {
        this.clazzs = clazzs;
    }
}
