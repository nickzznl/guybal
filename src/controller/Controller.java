package controller;

import dataBal.gui.FormEvent;
import model.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Controller {

    Database db = new Database();

    public List<Player> getPlayers() {
        return db.getPlayers();
    }

    public void removePlayer(int index)
    {
        db.removePlayer(index);
    }

    public void save() throws SQLException
    {
        db.save();
    }

    public void load() throws SQLException {
        db.load();
    }

    public void disconnect()
    {
        db.disconnect();
    }

    public void connect() throws Exception {
        db.connect();
    }


    public void addPlayer(FormEvent ev)
    {
        int id = ev.getId();
        String name = ev.getName();
        String position = ev.getPosition();
        int ageCatId = ev.getAgeCategory();
        int backNumber = ev.getBackNumber();
        boolean knvbMember = ev.isKnvbMember();
        int knvbNumber = ev.getKnvbNumber();
        String gender = ev.getGender();

        AgeCategory ageCategory = null;

        switch (ageCatId)
        {
            case 0:
                ageCategory = AgeCategory.child;
                break;
            case 1:
                ageCategory = AgeCategory.adult;
                break;
            case 2:
                ageCategory = AgeCategory.senior;
                break;
        }


        Gender gendercat;

        if(gender.equals("male"))
        {
            gendercat = Gender.male;
        }
        else
        {
            gendercat = Gender.female;
        }

        Player player = new Player(name, position, ageCategory, backNumber
        , knvbNumber, knvbMember, gendercat);

        db.addPlayer(player);
    }
    public void saveToFile(File file) throws IOException
    {
        db.saveToFile(file);
    }
    public void loadFromFile(File file) throws IOException
    {
        db.loadFromFile(file);
    }

}
