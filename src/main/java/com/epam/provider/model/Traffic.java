package com.epam.provider.model;

import java.sql.Date;
import java.util.Objects;

/**
 * Created by HP on 29.03.2018.
 */
public class Traffic extends Entity {

    private Integer idTraffic;
    private Integer recieved;
    private Integer transfered;
    private Date date;
    private Integer idProfiles;

    public Integer getIdTraffic() {
        return idTraffic;
    }

    public void setIdTraffic(Integer idTraffic) {
        this.idTraffic = idTraffic;
    }

    public Integer getRecieved() {
        return recieved;
    }

    public void setRecieved(Integer recieved) {
        this.recieved = recieved;
    }

    public Integer getTransfered() {
        return transfered;
    }

    public void setTransfered(Integer transfered) {
        this.transfered = transfered;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getIdProfiles() {
        return idProfiles;
    }

    public void setIdProfiles(Integer idProfiles) {
        this.idProfiles = idProfiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Traffic traffic = (Traffic) o;
        return Objects.equals(idTraffic, traffic.idTraffic) &&
                Objects.equals(recieved, traffic.recieved) &&
                Objects.equals(transfered, traffic.transfered) &&
                Objects.equals(date, traffic.date) &&
                Objects.equals(idProfiles, traffic.idProfiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTraffic, recieved, transfered, date, idProfiles);
    }
}
