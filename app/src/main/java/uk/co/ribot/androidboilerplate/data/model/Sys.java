package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Sys implements Parcelable {
    public static final Parcelable.Creator<Sys> CREATOR = new Parcelable.Creator<Sys>() {
        @Override
        public Sys createFromParcel(Parcel source) {
            return new Sys(source);
        }

        @Override
        public Sys[] newArray(int size) {
            return new Sys[size];
        }
    };
    @SerializedName("type")
    private double type;
    @SerializedName("id")
    private long id;
    @SerializedName("message")
    private double message;
    @SerializedName("country")
    private String country;
    @SerializedName("sunrise")
    private long sunrise;
    @SerializedName("sunset")
    private long sunset;

    public Sys() {
    }

    protected Sys(Parcel in) {
        this.type = in.readDouble();
        this.id = in.readLong();
        this.message = in.readDouble();
        this.country = in.readString();
        this.sunrise = in.readLong();
        this.sunset = in.readLong();
    }

    public double getType() {
        return type;
    }

    public void setType(double type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.type);
        dest.writeLong(this.id);
        dest.writeDouble(this.message);
        dest.writeString(this.country);
        dest.writeLong(this.sunrise);
        dest.writeLong(this.sunset);
    }
}
