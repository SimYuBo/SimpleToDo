package sg.edu.rp.c346.id21024095.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spnAddRemove;
    EditText etTask;
    Button btnAdd;
    Button btnRemove;
    Button btnClear;
    ListView lvTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnAddRemove = findViewById(R.id.spinner);
        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAddItem);
        btnRemove = findViewById(R.id.buttonRemoveItem);
        btnClear = findViewById(R.id.buttonClearAll);
        lvTasks = findViewById(R.id.ListViewTask);

        ArrayList<String> taskList = new ArrayList<>();

        ArrayAdapter aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        lvTasks.setAdapter(aaTask);

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etTask.setHint("Type in a new task here");
                        btnAdd.setTextColor(Color.BLACK);
                        btnRemove.setTextColor(Color.GRAY);
                        btnAdd.setEnabled(true);
                        btnRemove.setEnabled(false);
                        break;
                    case 1:
                        etTask.setHint("Type in the index of the task to be removed");
                        btnAdd.setTextColor(Color.GRAY);
                        btnRemove.setTextColor(Color.BLACK);
                        btnAdd.setEnabled(false);
                        btnRemove.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etTask.getText().toString();
                taskList.add(task);
                aaTask.notifyDataSetChanged();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (taskList.isEmpty()) {
                    Toast.makeText(MainActivity.this, "You don't have any tasks to remove", Toast.LENGTH_SHORT).show();
                } else if (etTask.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Invalid Index", Toast.LENGTH_SHORT).show();
                } else {
                    int index = Integer.parseInt(etTask.getText().toString());

                    if (taskList.size() < (index + 1) || taskList.size() > (index + 1)) {
                        Toast.makeText(MainActivity.this, "Invalid Index", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Not Null Test", Toast.LENGTH_SHORT).show();

                        taskList.remove(index);
                        aaTask.notifyDataSetChanged();
                    }

                }
            }

        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskList.clear();
                aaTask.notifyDataSetChanged();
            }
        });

    }
}