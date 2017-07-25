package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Clouds implements Parcelable {
    public static final Parcelable.Creator<Clouds> CREATOR = new Parcelable.Creator<Clouds>() {
        @Override
        public Clouds createFromParcel(Parcel source) {
            return new Clouds(source);
        }

        @Override
        public Clouds[] newArray(int size) {
            return new Clouds[size];
        }
    };
    @SerializedName("all")
    private double all;

    public Clouds() {
    }

    protected Clouds(Parcel in) {
        this.all = in.readDouble();
    }

    public double getAll() {
        return all;
    }

    public void setAll(double all) {
        this.all = all;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.all);
    }
}
