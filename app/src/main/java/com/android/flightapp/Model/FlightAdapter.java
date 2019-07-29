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

public class FlightAdapter extends  RecyclerView.Adapter<FlightAdapter.FlightViewHolder>
{

    private List<Flight> flightsList;
    private Context mContext;
    private OnItemClickListener cLickListener;
    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(R.layout.fligh_adapter,parent,shouldAttachToParentImmediately);
        FlightViewHolder flightViewHolder = new FlightViewHolder(view);
        return flightViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position)
    {
       Flight flight = flightsList.get(position);
       holder.airplaneCode.setText(String.valueOf(position));
       holder.airplaneId.setText(flight.getMarketCourier().getAirplaneId());
       holder.flightNumber.setText(String.valueOf(flight.getMarketCourier().getFlightNumber()));

    }

    @Override
    public int getItemCount() {
        return  flightsList.size();
    }

    public void setClickListener(OnItemClickListener itemClickListener)
    {
        this.cLickListener = itemClickListener;
    }

    public void setFlightList(List<Flight> flightList)
    {
        this.flightsList = flightList;
        notifyDataSetChanged();
    }

    class FlightViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView airplaneCode , airplaneId , flightNumber ;
        public FlightViewHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            airplaneCode = itemView.findViewById(R.id.airplancecode);
            airplaneId = itemView.findViewById(R.id.airplaneid);
            flightNumber = itemView.findViewById(R.id.flightnumber);

        }

        @Override
        public void onClick(View v)
        {
            cLickListener.onClick(v,getAdapterPosition());
            Log.d("Clicking","On clicking has been called ");
        }
    }
}
