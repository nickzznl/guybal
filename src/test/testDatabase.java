package test;

import model.*;

import java.sql.SQLException;

public class testDatabase {

    public static void main(String[] args)
    {
        System.out.println("Running database test");

        Database db = new Database();
        try {
            db.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.addPlayer(new Player("Harry", "spits", AgeCategory.adult, 12, 1, true, Gender.male));
        db.addPlayer(new Player("Karel", "keeper", AgeCategory.adult, 1, 2, true, Gender.male));



        try {
            db.save();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            db.load();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.disconnect();
    }

}
