package model;

import java.io.Serializable;

public class Player implements Serializable {

    private static int count = 1;
    private int id;
    private String name;
    private String position;
    private AgeCategory ageCategory;
    private int backNumber;
    private int knvbNumber;
    private boolean kvnbMember;
    private Gender gender;

    public Player(String name, String position,
                  AgeCategory ageCategory, int backNumber,
                  int knvbNumber, boolean kvnbMember, Gender gender) {
        this.name = name;
        this.position = position;
        this.ageCategory = ageCategory;
        this.backNumber = backNumber;
        this.knvbNumber = knvbNumber;
        this.kvnbMember = kvnbMember;
        this.gender = gender;

        this.id = count;
        count++;
    }

    public Player(int id, String name, String position,
                  AgeCategory ageCategory, int backNumber,
                  int knvbNumber, boolean kvnbMember, Gender gender) {
        this(name, position, ageCategory, backNumber, knvbNumber, kvnbMember, gender);

        this.id = id;
    }


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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public AgeCategory getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(AgeCategory ageCategory) {
        this.ageCategory = ageCategory;
    }

    public int getBackNumber() {
        return backNumber;
    }

    public void setBackNumber(int backNumber) {
        this.backNumber = backNumber;
    }

    public int getKnvbNumber() {
        return knvbNumber;
    }

    public void setKnvbNumber(int knvbNumber) {
        this.knvbNumber = knvbNumber;
    }

    public boolean isKvnbMember() {
        return kvnbMember;
    }

    public void setKvnbMember(boolean kvnbMember) {
        this.kvnbMember = kvnbMember;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
