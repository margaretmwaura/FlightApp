package com.android.flightapp.Model;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.flightapp.Presenter.OnItemClickListener;
import com.android.flightapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AirportAdapter extends RecyclerView.Adapter<AirportAdapter.AirportViewHolder>
{
    private List<Airport> airportList;
    private Context mContext;
    private OnItemClickListener cLickListener;
    @NonNull
    @Override
    public AirportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(R.layout.airport_dapter,parent,shouldAttachToParentImmediately);
        AirportViewHolder studentViewHolder = new AirportViewHolder(view);
        return studentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AirportViewHolder holder, int position)
    {
        Airport airport = airportList.get(position);
        holder.airportCode.setText(airport.getAirportCode());
        holder.cityCode.setText(airport.getCityCode());
        holder.countryCode.setText(airport.getCountryCode());
    }

    @Override
    public int getItemCount() {
        return airportList.size();
    }

    public void setClickListener(OnItemClickListener itemClickListener)
    {
        this.cLickListener = itemClickListener;
    }

    public void setAirportList(List<Airport> airportList)
    {
        this.airportList = airportList;
        notifyDataSetChanged();
    }

    class AirportViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView airportCode,cityCode,countryCode;
        public AirportViewHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            airportCode = itemView.findViewById(R.id.airport_code);
            cityCode = itemView.findViewById(R.id.citycode);
            countryCode = itemView.findViewById(R.id.countrycode);

        }

        @Override
        public void onClick(View v)
        {
            cLickListener.onClick(v,getAdapterPosition());
            Log.d("Clicking","On clicking has been called ");
        }
    }
}
