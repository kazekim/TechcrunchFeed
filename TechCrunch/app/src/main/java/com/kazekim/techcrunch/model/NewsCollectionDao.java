package com.kazekim.techcrunch.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Jirawat Harnsiriwatanakit (Kim) on 4/7/2017 AD.
 * @contact jirawat.h@kazekim.com
 */

public class NewsCollectionDao implements Parcelable {

    @SerializedName("articles")     private List<NewsDao> dataList;

    public NewsCollectionDao()
    {

    }

    protected NewsCollectionDao(Parcel in) {
        dataList = in.createTypedArrayList(NewsDao.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(dataList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<NewsCollectionDao> CREATOR = new Parcelable.Creator<NewsCollectionDao>() {
        @Override
        public NewsCollectionDao createFromParcel(Parcel in) {
            return new NewsCollectionDao(in);
        }

        @Override
        public NewsCollectionDao[] newArray(int size) {
            return new NewsCollectionDao[size];
        }
    };

    public List<NewsDao> getDataList() {
        return dataList;
    }

    public void setDataList(List<NewsDao> dataList) {
        this.dataList = dataList;
    }
}
