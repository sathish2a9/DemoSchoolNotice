package com.winterbitegames.demoschoolnotice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class NoticeDetails extends AppCompatActivity {
    private NoticeDataModel noticeData;
    private TextView title, description, remarks, date,tv_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_details);
        noticeData = getIntent().getParcelableExtra("notice");
        init();
    }

    public void init(){
        title = findViewById(R.id.tv_title);
        description = findViewById(R.id.tv_description);
        remarks = findViewById(R.id.tv_remarks);
        date = findViewById(R.id.tv_date);
        tv_id= findViewById(R.id.tv_id);
        if (noticeData != null){
            setData();
        }
    }

    public void setData(){
        title.setText(noticeData.getTitle());
        description.setText(getResources().getString(R.string.stu_description,noticeData.getDescription()));
        remarks.setText(getResources().getString(R.string.stu_remarks,noticeData.getRemarks()));
        tv_id.setText(getResources().getString(R.string.stu_id,String.valueOf(noticeData.getId())));
        date.setText(noticeData.getDate());
    }

    @Override
    protected void onPause() {
        super.onPause();
        setPreferenceData();
    }

    public void setPreferenceData(){
        SharedPreferences sh1 = getSharedPreferences("DemoApp", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sh1.edit();
        myEdit.putInt("page", 2);
        myEdit.putInt("id", noticeData.getId() );
        myEdit.apply();
    }
}