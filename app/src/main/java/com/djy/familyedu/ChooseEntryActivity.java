package com.djy.familyedu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by djy-ubuntu16 on 9/23/17.
 */

public class ChooseEntryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_entry);

        final Button teacherButton = findViewById(R.id.button_teacher);
        teacherButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_choose_entry_teacher);
                final Button SignUpButton = findViewById(R.id.button_teacher_sign_up);
                final Button LogInButton = findViewById(R.id.button_teacher_login);
                SignUpButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(ChooseEntryActivity.this, TeacherSignUpActivity.class));
                    }
                });
                LogInButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(ChooseEntryActivity.this, TeacherLogInActivity.class));
                    }
                });
            }
        });


        final Button parentButton = findViewById(R.id.button_parent);
        parentButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                startActivity(new Intent(ChooseEntryActivity.this, ParentLogInActivity.class));
                setContentView(R.layout.activity_choose_entry_parent);
                final Button SignUpButton = findViewById(R.id.button_parent_sign_up);
                final Button LogInButton = findViewById(R.id.button_parent_login);
                SignUpButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(ChooseEntryActivity.this, ParentSignUpActivity.class));
                    }
                });
                LogInButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(ChooseEntryActivity.this, ParentLogInActivity.class));
                    }
                });
            }
        });

        final Button skipButton = findViewById(R.id.button_skip);
        skipButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ChooseEntryActivity.this, SucceededLoginActivity.class));
            }
        });
    }
}
