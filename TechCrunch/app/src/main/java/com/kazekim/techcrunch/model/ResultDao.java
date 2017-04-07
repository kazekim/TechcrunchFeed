package com.kazekim.techcrunch.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Jirawat Harnsiriwatanakit (Kim) on 4/7/2017 AD.
 * @contact jirawat.h@kazekim.com
 */

public class ResultDao<T> {

    public static final String RESULTDAOKEY_STATUS = "status";
    public static final String RESULTDAOKEY_SOURCE = "source";
    public static final String RESULTDAOKEY_SORTBY = "sortBy";
    public static final String RESULTDAOKEY_DATA = "articles";

    @SerializedName(RESULTDAOKEY_STATUS) private String status;
    @SerializedName(RESULTDAOKEY_SOURCE)  private String source;
    @SerializedName(RESULTDAOKEY_SORTBY)  private String sortBy;
    @SerializedName(RESULTDAOKEY_DATA)  private List<T> dataList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

}
