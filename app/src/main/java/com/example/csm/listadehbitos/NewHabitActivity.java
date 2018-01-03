package com.example.csm.listadehbitos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewHabitActivity extends AppCompatActivity {

    private Button mAddHabit;
    private  DatabaseReference mDatabase;
    private  EditText mNewHabit;
    private int mStreak = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit);

            mDatabase = FirebaseDatabase.getInstance().getReference().child("Habit");
            mAddHabit = (Button) findViewById(R.id.addHabit);
            mNewHabit = (EditText) findViewById(R.id.newHabit);

            mAddHabit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Habit h = new Habit();

                    h.setHabit(mNewHabit.getText().toString());
                    h.setStreak(0);

                    //Insere Novo Hábito
                    mDatabase.push().setValue(h).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(NewHabitActivity.this, "Hábito adicionado com sucesso", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(NewHabitActivity.this, "Erro ao adicionar.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    Intent myIntent = new Intent(NewHabitActivity.this, MainActivity.class);
                    NewHabitActivity.this.startActivity(myIntent);
                }
            });
    }
}
