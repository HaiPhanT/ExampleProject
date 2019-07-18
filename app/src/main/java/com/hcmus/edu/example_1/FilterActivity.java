package com.hcmus.edu.example_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView ivDone;
    TextView tvGenderMen, tvGenderWomen, tvGenderBoth;
    String genderChosen = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        ivDone = findViewById(R.id.ivDone);
        tvGenderMen = findViewById(R.id.tvGenderMen);
        tvGenderWomen = findViewById(R.id.tvGenderWomen);
        tvGenderBoth = findViewById(R.id.tvGenderBoth);
        ivDone.setOnClickListener(this);
        tvGenderMen.setOnClickListener(this);
        tvGenderWomen.setOnClickListener(this);
        tvGenderBoth.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.ivDone) {
            onDone();
        } else if (id == R.id.tvGenderMen) {
            genderChosen = "Men";
        } else if (id == R.id.tvGenderWomen) {
            genderChosen = "Women";
        } else if (id == R.id.tvGenderBoth) {
            genderChosen = "Both";
        }
    }

    public void onBackPressed() {
        onDone();
    }

    private void onDone() {
        Gender gender = new Gender(genderChosen);
        Intent data = new Intent(FilterActivity.this, MainActivity.class);
        data.putExtra("GENDER", gender);
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}
