package com.flyme.meditation.lifeassistant.bean;

import java.util.List;

/**
 * Created by kangweodai on 18/07/17.
 */

public class TicketBean extends BaseBean {

    private SiteBean startSite;
    private SiteBean endSite;
    private List<FlightBean> flights;

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

    public List<FlightBean> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightBean> flights) {
        this.flights = flights;
    }
}
