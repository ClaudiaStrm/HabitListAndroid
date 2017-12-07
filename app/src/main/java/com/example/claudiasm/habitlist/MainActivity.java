package com.example.claudiasm.habitlist;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_add_task:
                Log.d(TAG, "Adicionar nova atividade");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    final EditText taskEditText = new EditText(this);
    AlertDialog dialog = new AlertDialog.Builder(this)
            .setTitle("Adicionar novo hábito")
            .setMessage("Qual hábito você deseja criar?")
            .setView(taskEditText)
            .setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String task = String.valueOf(taskEditText.getText());
                    Log.d(TAG, "Adicionar hábito: " + task);
                }
            })
            .setNegativeButton("Cancelar", null)
            .create();
            dialog.show(); //show();
}
