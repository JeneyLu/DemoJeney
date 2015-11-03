package com.jeney.demojeney.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * desc:
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/8/6
 */
public class ParcelableBean implements Parcelable {
    private int id;
    private String name;
    private ArrayList<String> features;
    //ParcelableModel必须也是Parcelable的，ParcelableBean才能当Parcelable使用
    private ArrayList<ParcelableModel> parcelableModels;

    public ParcelableBean(int id, String name, ArrayList<String> features) {
        this.id = id;
        this.name = name;
        this.features = features;
    }

    public ParcelableBean(int id, String name, ArrayList<String> features, ArrayList<ParcelableModel> parcelableModels) {
        this.id = id;
        this.name = name;
        this.features = features;
        this.parcelableModels = parcelableModels;
    }

    public ArrayList<ParcelableModel> getParcelableModels() {
        return parcelableModels;
    }

    public void setParcelableModels(ArrayList<ParcelableModel> parcelableModels) {
        this.parcelableModels = parcelableModels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<String> features) {
        this.features = features;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeStringList(this.features);
        dest.writeTypedList(parcelableModels);
    }

    protected ParcelableBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.features = in.createStringArrayList();
        this.parcelableModels = in.createTypedArrayList(ParcelableModel.CREATOR);
    }

    public static final Creator<ParcelableBean> CREATOR = new Creator<ParcelableBean>() {
        public ParcelableBean createFromParcel(Parcel source) {
            return new ParcelableBean(source);
        }

        public ParcelableBean[] newArray(int size) {
            return new ParcelableBean[size];
        }
    };

    public static class ParcelableModel implements Parcelable {
        private int id;
        private String name;

        public ParcelableModel(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.name);
        }

        protected ParcelableModel(Parcel in) {
            this.id = in.readInt();
            this.name = in.readString();
        }

        public static final Creator<ParcelableModel> CREATOR = new Creator<ParcelableModel>() {
            public ParcelableModel createFromParcel(Parcel source) {
                return new ParcelableModel(source);
            }

            public ParcelableModel[] newArray(int size) {
                return new ParcelableModel[size];
            }
        };
    }
}