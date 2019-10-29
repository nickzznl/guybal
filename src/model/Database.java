package model;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Database {
    private List<Player> players;
    private Connection con;

    public Database()
    {
        players = new LinkedList<Player>();
    }

    public void connect() throws Exception {
        if(con != null) return;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new Exception("Driver not found");
        }

        String url = "jdbc:mysql://localhost:3306/testdatabal?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        con = DriverManager.getConnection(url, "root", "");
    }

    public void disconnect()
    {
        if (con != null)
        {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Can't close connection");
            }
        }
    }

    public void save() throws SQLException {

        String checkSql = "Select count(*) as count from players where id=?";
        PreparedStatement checkStmt = con.prepareStatement(checkSql);

        String insertSql = "insert into players (id, name, age, backNumber, knvbNumber, knvbMember, gender, position) values(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertStatement = con.prepareStatement(insertSql);

        String updateSql = "update players set name=?, age=?, backNumber=?, knvbNumber=?, knvbMember=?, gender=?, position=? where id=?";
        PreparedStatement updateStatement = con.prepareStatement(updateSql);

        for(Player player: players)
        {
            int id = player.getId();
            String name = player.getName();
            String position = player.getPosition();
            int backNumber = player.getBackNumber();
            AgeCategory ageCategory = player.getAgeCategory();
            int knvbNumber = player.getKnvbNumber();
            Boolean knvbMember = player.isKvnbMember();
            Gender gender = player.getGender();

            checkStmt.setInt(1, id);

            ResultSet checkResult = checkStmt.executeQuery();

            checkResult.next();

            int count = checkResult.getInt(1);

            if(count == 0)
            {
                System.out.println("Inserting person with ID " + id);

                int col = 1;
                insertStatement.setInt(col++, id);
                insertStatement.setString(col++, name);
                insertStatement.setString(col++, ageCategory.name());
                insertStatement.setInt(col++, backNumber);
                insertStatement.setInt(col++, knvbNumber);
                insertStatement.setBoolean(col++, knvbMember);
                insertStatement.setString(col++, gender.name());
                insertStatement.setString(col++, position);

                insertStatement.executeUpdate();
            }
            else
            {
                System.out.println("Updating person with ID "+ id);

                int col = 1;
                updateStatement.setString(col++, name);
                updateStatement.setString(col++, ageCategory.name());
                updateStatement.setInt(col++, backNumber);
                updateStatement.setInt(col++, knvbNumber);
                updateStatement.setBoolean(col++, knvbMember);
                updateStatement.setString(col++, gender.name());
                updateStatement.setString(col++, position);
                updateStatement.setInt(col++, id);

                updateStatement.executeUpdate();
            }
        }
        updateStatement.close();
        insertStatement.close();
        checkStmt.close();
    }

    public void load() throws SQLException {
        players.clear();

        String sql = "select id, name, age, backNumber, knvbNumber, knvbMember, gender, position from players order by id";
        Statement selectStatement = con.createStatement();

        ResultSet results = selectStatement.executeQuery(sql);

        while(results.next())
        {
            int id = results.getInt("id");
            String name = results.getString("name");
            String age = results.getString("age");
            int backNumber = results.getInt("backNumber");
            int knvbNumber = results.getInt("knvbNumber");
            boolean knvbMember = results.getBoolean("knvbMember");
            String gender = results.getString("gender");
            String position = results.getString("position");

            players.add(new Player(id, name, position, AgeCategory.valueOf(age), backNumber, knvbNumber, knvbMember, Gender.valueOf(gender)));
        }

        results.close();
        selectStatement.close();
    }

    public void addPlayer(Player player)
    {
        players.add(player);
    }

    public void removePlayer(int index)
    {
        players.remove(index);
    }

    public List<Player> getPlayers()
    {
        return Collections.unmodifiableList(players);
    }

    public void saveToFile(File file) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        Player[] Players = players.toArray(new Player[players.size()]);

        oos.writeObject(Players);

        oos.close();
    }

    public void loadFromFile(File file) throws IOException
    {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            Player[] Players = (Player[]) ois.readObject();

            players.clear();

            players.addAll(Arrays.asList(Players));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ois.close();
    }
}
