package dataBal.gui;

import java.util.EventObject;

public class FormEvent extends EventObject {

    private int id;
    private String name;
    private String position;
    private int ageCategory;
    private int backNumber;
    private int knvbNumber;
    private boolean knvbMember;
    private String gender;

    public FormEvent(Object source, String name, String position, int ageCat, int backNumber, int knvbNumber, boolean knvbMember, String gender)
    {
        super(source);

        this.name = name;
        this.position = position;
        this.ageCategory = ageCat;
        this.backNumber = backNumber;
        this.knvbNumber = knvbNumber;
        this.knvbMember = knvbMember;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getAgeCategory()
    {
        return ageCategory;
    }
    public int getBackNumber()
    {
        return backNumber;
    }
    public int getKnvbNumber() {
        return knvbNumber;
    }

    public boolean isKnvbMember() {
        return knvbMember;
    }

    public String getGender() {
        return gender;
    }
}
