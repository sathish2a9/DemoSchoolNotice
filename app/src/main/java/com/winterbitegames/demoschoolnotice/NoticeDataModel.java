package com.winterbitegames.demoschoolnotice;

import android.os.Parcel;
import android.os.Parcelable;

public class NoticeDataModel implements Parcelable {
    private String title, description, date, remarks;
    private int id;

    public NoticeDataModel() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.date);
        dest.writeString(this.remarks);
        dest.writeInt(this.id);
    }

    protected NoticeDataModel(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.date = in.readString();
        this.remarks = in.readString();
        this.id = in.readInt();
    }

    public static final Creator<NoticeDataModel> CREATOR = new Creator<NoticeDataModel>() {
        @Override
        public NoticeDataModel createFromParcel(Parcel source) {
            return new NoticeDataModel(source);
        }

        @Override
        public NoticeDataModel[] newArray(int size) {
            return new NoticeDataModel[size];
        }
    };
}
