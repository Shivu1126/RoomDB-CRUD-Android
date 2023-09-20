package com.androidwithshiv.crudoperation.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "PersonDetail")
public class Person implements Serializable {
    @ColumnInfo(name = "person_id")
    @PrimaryKey(autoGenerate = true)
    int id=0;
    @ColumnInfo(name = "person_name")
    String name;
    @ColumnInfo(name = "person_age")
    int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
