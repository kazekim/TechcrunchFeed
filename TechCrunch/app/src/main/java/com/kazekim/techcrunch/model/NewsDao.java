package com.kazekim.techcrunch.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jirawat Kim Harnsiriwatanakit on 4/7/2017 AD.
 * Contact : jirawat.h@kazekim.com
 */

public class NewsDao  implements Parcelable {


    @SerializedName("author") private String author;
    @SerializedName("title") private String title;
    @SerializedName("description") private String description;
    @SerializedName("url") private String url;
    @SerializedName("urlToImage") private String urlToImage;
    @SerializedName("publishedAt") private String publishedAt;

    public NewsDao()
    {

    }

    protected NewsDao(Parcel in) {
        author = in.readString();
        title = in.readString();
        description = in.readString();
        url = in.readString();
        urlToImage = in.readString();
        publishedAt = in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeString(urlToImage);
        dest.writeString(publishedAt);
    }

    public static final Creator<NewsDao> CREATOR = new Creator<NewsDao>() {
        @Override
        public NewsDao createFromParcel(Parcel in) {
            return new NewsDao(in);
        }

        @Override
        public NewsDao[] newArray(int size) {
            return new NewsDao[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
