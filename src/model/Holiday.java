package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Holiday implements Serializable {
    private String name;
    private Date startDate;
    private Date endDate;
    private HolidayType type;
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM");

    public Holiday (String name) {
        this.name = name;
        this.startDate = new Date();
        this.endDate = null;
        this.type = HolidayType.OTHER;
    }

    public Holiday(String name, int typeNum) {
        this.name = name;
        this.startDate = new Date();
        this.endDate = null;
        this.type = HolidayType.values()[typeNum];
    }

    public Holiday(String name, Date start, int typeNum) {
        this.name = name;
        this.startDate = start;
        this.endDate = null;
        this.type = HolidayType.values()[typeNum];
    }

    public Holiday(String name, Date start,  Date end, int typeNum) {
        this.name = name;
        this.startDate = start;
        this.endDate = end;
        this.type = HolidayType.values()[typeNum];
    }

    public Holiday() {

    }

    public String getName() {
        return  this.name;
    }

    public String toString() {
        String s;
        if (endDate == null) s = dateFormat.format(startDate);//(!startDate.equals(endDate)) s = startDate.toString();
        else s = String.format("%s-%s",dateFormat.format(startDate),dateFormat.format(endDate));

        return String.format("%30s%15s%15s",name,s,type);
    }

    public String getStartDate() {
        return dateFormat.format(this.startDate);
    }

    public HolidayType getType() {
        return this.type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(HolidayType type) {
        this.type = type;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
