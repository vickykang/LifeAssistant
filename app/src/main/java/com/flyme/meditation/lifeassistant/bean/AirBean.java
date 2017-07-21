package com.flyme.meditation.lifeassistant.bean;

import android.text.TextUtils;

/**
 * Created by kangweodai on 18/07/17.
 */

public class AirBean extends BaseBean {

    private String company;
    private String name;
    private String model;
    private String size;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(company)
                .append(" ")
                .append(name);
        if (!TextUtils.isEmpty(model)) {
            builder.append(" | ")
                    .append(model);
            if (!TextUtils.isEmpty(size)) {
                builder.append("(")
                        .append(size)
                        .append(")");
            }
        }
        return builder.toString();
    }
}
