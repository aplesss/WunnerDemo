package com.production.wunner.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.production.wunner.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class fragment_sumary extends Fragment {
    Context context;
    private TextView txt_teamID,txt_StationID,txt_Timer;
    private ArrayList<String> list=new ArrayList();

    public fragment_sumary(Context context) {
        this.context = context;
        this.list= (ArrayList<String>) list;
    }

    public static fragment_sumary newInstance(Context context )
    {
        return new fragment_sumary(context );
    }

    public void UpdateData(List<String> data)
    {
        list= (ArrayList<String>) data;
        txt_teamID.setText(list.get(0));
        txt_StationID.setText(list.get(1));
        int timer= Integer.parseInt(list.get(2));
        txt_Timer.setText((new SimpleDateFormat("mm:ss").format(timer)));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_submit_sumary,container,false);
        txt_StationID=view.findViewById(R.id.txt_stationID);
        txt_teamID=view.findViewById(R.id.txt_teamID);
        txt_Timer=view.findViewById(R.id.txt_timeSubmit);

        return view;
    }
}
