package com.android.flightapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class RetrofitResponse implements Parcelable
{
    @SerializedName("access_token")
    String accessToken;
    @SerializedName("token_type")
    String tokenType;

    protected RetrofitResponse(Parcel in) {
        accessToken = in.readString();
        tokenType = in.readString();
    }

    public static final Creator<RetrofitResponse> CREATOR = new Creator<RetrofitResponse>() {
        @Override
        public RetrofitResponse createFromParcel(Parcel in) {
            return new RetrofitResponse(in);
        }

        @Override
        public RetrofitResponse[] newArray(int size) {
            return new RetrofitResponse[size];
        }
    };

    public String getAccessToken()
    {
        return this.accessToken;
    }
    public String getTokenType()
    {
        return this.tokenType;
    }
    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }
    public void setTokenType(String tokenType)
    {
        this.tokenType = tokenType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(accessToken);
        dest.writeString(tokenType);
    }
}
