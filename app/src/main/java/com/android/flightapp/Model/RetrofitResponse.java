package com.android.flightapp.Model;

import com.google.gson.annotations.SerializedName;

public class RetrofitResponse
{
    @SerializedName("access_token")
    String accessToken;
    @SerializedName("token_type")
    String tokenType;

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
}
