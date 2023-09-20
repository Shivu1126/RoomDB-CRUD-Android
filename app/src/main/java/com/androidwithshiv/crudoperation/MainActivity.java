package com.androidwithshiv.crudoperation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidwithshiv.crudoperation.database.RoomDB;
import com.androidwithshiv.crudoperation.models.Person;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private EditText personNameEt, personAgeEt, personIdEt;
    private Button addBt, updateBt, deleteBt, showBt;
    private List<Person> personList;
    RoomDB database;
    private void init(){
        context = MainActivity.this;
        personNameEt = findViewById(R.id.name);
        personAgeEt = findViewById(R.id.age);
        personIdEt = findViewById(R.id.person_id);

        addBt = findViewById(R.id.add);
        updateBt = findViewById(R.id.update);
        deleteBt = findViewById(R.id.delete);
        showBt = findViewById(R.id.show);

        database = RoomDB.getInstance(context);
        personList = database.mainDAO().getAll();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        getWindow().setStatusBarColor(ContextCompat.getColor(context, R.color.white));

        addBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(personNameEt.getText().length()==0 || personAgeEt.getText().length()==0){
                    showToast("fill name and age...");
                }
                else{
                    Person newPerson = new Person();
                    newPerson.setName(personNameEt.getText().toString());
                    newPerson.setAge(Integer.parseInt(personAgeEt.getText().toString()));
                    database.mainDAO().insert(newPerson);
                    showToast("added successfully...");
                    listUpdate();
                }
            }
        });

        deleteBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(personIdEt.getText().length()==0){
                    showToast("fill id...");
                }
                else {
                    int deletedId = Integer.parseInt( personIdEt.getText().toString());
                    Person deletedPerson = null;
                    for (Person p : personList){
                        if(p.getId()==deletedId){
                            deletedPerson = p;
                            break;
                        }
                    }
                    if(deletedPerson==null){
                        showToast("Person not there...");
                    }
                    else{
                        database.mainDAO().delete(deletedPerson);
                        showToast("deleted successfully...");
                        listUpdate();
                    }
                }
            }
        });

        updateBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(personNameEt.getText().length()==0 || personAgeEt.getText().length()==0 || personIdEt.getText().length()==0){
                    showToast("fill name,age and id...");
                }
                else{
                    int updateId = Integer.parseInt( personIdEt.getText().toString());
                    boolean isThere = false;
                    for (Person p : personList){
                        if(p.getId()==updateId){
                            isThere = true;
                            break;
                        }
                    }
                    if(isThere){
                        String updateName = personNameEt.getText().toString();
                        String updateAge = personAgeEt.getText().toString();
                        database.mainDAO().update(updateId, updateName, Integer.parseInt(updateAge));
                        showToast("updated successfully....");
                        listUpdate();
                    }else {
                        showToast("Id not in...");
                    }
                }
            }
        });

        showBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder stringBuilder = new StringBuilder();
                for(Person person : personList){
                    stringBuilder.append(person.getName()+"  "+person.getAge()+"  "+person.getId() );
                    stringBuilder.append("\n");
                }
                String allData = stringBuilder.toString();
                showToast(allData);
            }
        });
    }
    private void listUpdate(){
        personList.clear();
        personList.addAll(database.mainDAO().getAll());
    }
    private void showToast(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}