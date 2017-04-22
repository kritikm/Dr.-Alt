package com.example.kritik.dralt.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kritik.dralt.Interfaces.RecyclerItemClickListener;
import com.example.kritik.dralt.R;

/**
 * Created by Kritik on 09-Apr-17.
 */

public class AdverseResponseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    View riskIndicator;
    TextView reactions, seriousness, suspectedDrug, additionalType, additionalDrug;
    LinearLayout additionalLayout, itemLayout;
    RecyclerItemClickListener listener;

    AdverseResponseViewHolder(View itemView, RecyclerItemClickListener listener)
    {
        super(itemView);

        this.listener = listener;
        itemView.setOnClickListener(this);
        riskIndicator = itemView.findViewById(R.id.v_risk_indicator);
        reactions = (TextView)itemView.findViewById(R.id.tv_reactions);
        seriousness = (TextView)itemView.findViewById(R.id.tv_serious);
        suspectedDrug = (TextView)itemView.findViewById(R.id.tv_suspected_drug);
        additionalType = (TextView)itemView.findViewById(R.id.tv_additional_type);
        additionalDrug = (TextView)itemView.findViewById(R.id.tv_additional_drug);
        additionalLayout = (LinearLayout)itemView.findViewById(R.id.ll_additional_drug);
        itemLayout = (LinearLayout)itemView.findViewById(R.id.ll_item_layout);
    }

    @Override
    public void onClick(View view)
    {
        listener.onItemClick(itemView, getAdapterPosition(), 0);
    }
}
