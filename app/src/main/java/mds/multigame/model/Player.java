package mds.multigame.model;

import android.widget.EditText;

import java.lang.reflect.Constructor;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Player extends RealmObject {

    @PrimaryKey

    private String name;
    private String firstname;
    private int age;
    private String localisation;
    private String picture;

    public Player(String name, String firstname, String localisation, String picture, int age) {
        setAge(age);
        setFirstname(firstname);
        setLocalisation(localisation);
        setName(name);
        setPicture(picture);
    }

    public Player()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
