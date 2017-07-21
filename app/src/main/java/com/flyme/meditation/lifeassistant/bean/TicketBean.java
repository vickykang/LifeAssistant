package com.flyme.meditation.lifeassistant.bean;

import com.flyme.meditation.lifeassistant.database.ClazzBean;

import java.util.List;

/**
 * Created by kangweodai on 18/07/17.
 */

public class TicketBean extends BaseBean {

    private SiteBean startSite;
    private SiteBean endSite;
    private TimeBean startTime;
    private TimeBean endTime;
    private AirBean air;
    private int duration;
    private int price;
    private boolean isNonstop;
    private List<ClazzBean> clazzs;

    public SiteBean getStartSite() {
        return startSite;
    }

    public void setStartSite(SiteBean startSite) {
        this.startSite = startSite;
    }

    public SiteBean getEndSite() {
        return endSite;
    }

    public void setEndSite(SiteBean endSite) {
        this.endSite = endSite;
    }

    public TimeBean getStartTime() {
        return startTime;
    }

    public void setStartTime(TimeBean startTime) {
        this.startTime = startTime;
    }

    public TimeBean getEndTime() {
        return endTime;
    }

    public void setEndTime(TimeBean endTime) {
        this.endTime = endTime;
    }

    public AirBean getAir() {
        return air;
    }

    public void setAir(AirBean air) {
        this.air = air;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isNonstop() {
        return isNonstop;
    }

    public void setNonstop(boolean nonstop) {
        isNonstop = nonstop;
    }

    public List<ClazzBean> getClazzs() {
        return clazzs;
    }

    public void setClazzs(List<ClazzBean> clazzs) {
        this.clazzs = clazzs;
    }
}
