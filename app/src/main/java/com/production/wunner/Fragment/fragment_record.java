package com.production.wunner.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.production.wunner.Api.GetWunnerDataService;
import com.production.wunner.Api.RetrofitInstance;
import com.production.wunner.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_record extends Fragment {
    private static EditText edit_Score;
    Context context;
    private static EditText edit_teamID;
    private EditText edit_StationID;


    public fragment_record(Context context) {
        this.context = context;
    }

    public static fragment_record newInstance(Context context)
    {
        return new fragment_record(context);
    }

    public void submit(String station_id, String team_id) {
        String point = String.valueOf(edit_Score.getText());
        GetWunnerDataService service = RetrofitInstance.getRetrofitInstance().create(GetWunnerDataService.class);
        Call<String> call =service.SubmitPoint(team_id,station_id,team_id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(context,"Succesfull",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_submit_record,container,false);
        edit_Score=view.findViewById(R.id.edit_Score);
        edit_StationID=view.findViewById(R.id.edit_StationID);
        edit_teamID=view.findViewById(R.id.edit_teamID);

        return view;
    }
}
