package com.androidwithshiv.crudoperation.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

import com.androidwithshiv.crudoperation.models.Person;

import java.util.List;

@Dao
public interface MainDAO {
    //DAO - Data Access Object
    @Insert(onConflict = REPLACE)
    void insert(Person person);

    @Query("UPDATE PersonDetail SET person_name = :name, person_age = :age WHERE person_id = :id")
    void update(int id, String name, int age);

    @Delete
    void delete(Person person);

    @Query("SELECT * FROM PersonDetail ORDER BY person_id DESC")
    List<Person> getAll();
}
