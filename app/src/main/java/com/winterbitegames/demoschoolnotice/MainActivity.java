package com.winterbitegames.demoschoolnotice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    private RecyclerView recyclerView;
    private CustomAdapter adaptor;
    private LinearLayoutManager layoutManager;
    private ArrayList<NoticeDataModel> dataModelList;
    private Context mContext;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        init();
    }

    public void init() {
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progress_circular);
        progressBar.setVisibility(View.VISIBLE);

        //Adding data to JSON manually for now.
        JSONArray array = new JSONArray();
        for (int i = 1; i < 20; i++) {
            JSONObject notice = new JSONObject();
            try {
                notice.put("id", i);
                notice.put("title", "Summer Holidays Announcement");
                notice.put("description", "This is to inform the parents that the ABC school management has decided to have the summer vacations fot the students from class 6 to 10th from 25 May 2020 till 30 July 2020.");
                notice.put("remarks", "Parents should make sure the students return to school the 31 July 2020 at any cost.");
                notice.put("date", "10:30 AM 20 May,2020");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            array.put(notice); // Assuming this JSON Array to be the server response for now.
        }


        //fetching data out of JSON response
        dataModelList = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            NoticeDataModel data = new NoticeDataModel();
            try {
                JSONObject object = array.getJSONObject(i);
                data.setId(object.getInt("id"));
                data.setTitle(object.getString("title"));
                data.setDescription(object.getString("description"));
                data.setRemarks(object.getString("remarks"));
                data.setDate(object.getString("date"));

                dataModelList.add(data);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        setAdaptor(dataModelList);

    }

    private void setAdaptor(ArrayList<NoticeDataModel> modelList) {
        progressBar.setVisibility(View.GONE);
        SharedPreferences sd = getSharedPreferences("DemoApp", MODE_PRIVATE);
        int page = sd.getInt("page", 0);
        if (page != 2) {
            layoutManager = new LinearLayoutManager(mContext);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            adaptor = new CustomAdapter(mContext, modelList, this);
            recyclerView.setAdapter(adaptor);
        } else {
            int id = sd.getInt("id", 0);
            if (id != 0) {
                for (int i = 1; i < dataModelList.size(); i++) {
                    if (id == dataModelList.get(i).getId()) {
                        Intent intent = new Intent(this, NoticeDetails.class);
                        intent.putExtra("notice", dataModelList.get(i));
                        startActivity(intent);
                    }
                }
            }
        }
    }

    @Override
    public void clickOnItem(int section, int position) {
        if (section == 0) {
            Intent intent = new Intent(this, NoticeDetails.class);
            intent.putExtra("notice", dataModelList.get(position));
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dataModelList != null){
            setPreferenceData();
            setAdaptor(dataModelList);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        setPreferenceData();
    }

    public void setPreferenceData(){
        SharedPreferences sh1 = getSharedPreferences("DemoApp", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sh1.edit();
        myEdit.putInt("page", 1);
        myEdit.apply();
    }
}