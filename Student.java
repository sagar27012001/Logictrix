import java.sql.*;
import java.util.Scanner;

abstract class Db {
    abstract void insert(Connection con) throws SQLException;
    abstract void update(Connection con) throws SQLException;
    abstract void delete(Connection con) throws SQLException;
    abstract void display(Connection con) throws SQLException;
    abstract void search(Connection con) throws SQLException;
}

class Admin extends Db {
    String tname,query;
    String uname,pass;
    public Admin() {
        tname = "admin";
    }
    Scanner scr = new Scanner(System.in);
    @Override
    void insert(Connection con) throws SQLException {
        System.out.print("Enter user name : ");
        uname = scr.next();
        System.out.print("Enter Password : ");
        pass = scr.next();
        query = "INSERT INTO "+ tname +" VALUES (?,?)";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, uname);
        st.setString(2, pass);
        if(st.executeUpdate() != 0) {
            System.out.println("Data inserted");
        }
        else {
            System.out.println("Error while inserting data");
        }
        st.close();
    }
    @Override
    void update(Connection con) throws SQLException {
        System.out.print("Enter existing user name to update Record : ");
        uname = scr.next();
        System.out.print("Enter updated pass : ");
        pass = scr.next();
        query= "UPDATE " + tname +" SET pass='" + pass + "' WHERE uname='" + uname + "'";
        Statement st4 = con.createStatement();
        if(st4.executeUpdate(query) != 0) {
            System.out.println("Record updated successfully");
        }
        else {
            System.out.println("Error while updating");
        }
        st4.close();
    }
    @Override
    void delete(Connection con) throws SQLException {
        System.out.print("Enter user name to delete Record : ");
        uname = scr.next();
        query = "DELETE from "+ tname +" WHERE uname='" + uname + "'";
        Statement st5 = con.createStatement();
        if(st5.executeUpdate(query) != 0) {
            System.out.println("Record deleted succesfully");
        }
        else {
            System.out.println("Error while deleting Record");
        }
        st5.close();
    }
    @Override
    void display(Connection con) throws SQLException {
        query = "SELECT * FROM " + tname;
        Statement st3 = con.createStatement();
        ResultSet rs2 = st3.executeQuery(query);
        System.out.println("username");
        while(rs2.next()) {
            String name = rs2.getString(1);
            System.out.println(name);
        }
        st3.close();
    }
    @Override
    void search(Connection con) throws SQLException {
        int ch;
        System.out.println("1.Like statement/2.Simple search");
        System.out.print("Enter your choice : ");
        ch = scr.nextInt();
        switch(ch) {
            case 1:
                int ch2;
                System.out.println("1.Starting from/2.Containing/3.Ending with");
                System.out.print("Enter your choice : ");
                ch2 = scr.nextInt();
                switch(ch2) {
                    case 1:
                        System.out.print("Enter initial characters of user name to search record : ");
                        uname = scr.next();
                        query = "SELECT * FROM "+ tname + " where uname like'" + uname + "%'";
                        Statement st2 = con.createStatement();
                        ResultSet rs = st2.executeQuery(query);
                        System.out.println("Username -> Password");
                        while(rs.next()) {
                            String name = rs.getString(1) + " -> " + rs.getString(2);
                            System.out.println(name);
                        }
                        st2.close();
                        break;
                    case 2:
                        System.out.print("Enter middle characters of user name to search record : ");
                        uname = scr.next();
                        query = "SELECT * FROM "+ tname + " where uname like'%" + uname + "%'";
                        Statement st3 = con.createStatement();
                        ResultSet rs2 = st3.executeQuery(query);
                        System.out.println("Username -> Password");
                        while(rs2.next()) {
                            String name = rs2.getString(1) + " -> " + rs2.getString(2);
                            System.out.println(name);
                        }
                        st3.close();
                        break;
                    case 3:
                        System.out.print("Enter ending characters of user name to search record : ");
                        uname = scr.next();
                        query = "SELECT * FROM "+ tname + " where uname like'%" + uname + "'";
                        Statement st4 = con.createStatement();
                        ResultSet rs3 = st4.executeQuery(query);
                        System.out.println("Username -> Password");
                        while(rs3.next()) {
                            String name = rs3.getString(1) + " -> " + rs3.getString(2);
                            System.out.println(name);
                        }
                        st4.close();
                        break;
                    default:
                        System.out.println("Enter valid choice");
                }
                break;
            case 2:
                System.out.print("Enter user name to search record : ");
                uname = scr.next();
                query = "SELECT * FROM "+ tname + " where uname='" + uname + "'";
                Statement st2 = con.createStatement();
                ResultSet rs = st2.executeQuery(query);
                System.out.println("Username -> Password");
                while(rs.next()) {
                    String name = rs.getString(1) + " -> " + rs.getString(2);
                    System.out.println(name);
                }
                st2.close();
                break;
            default:
                System.out.println("Enter valid choice");
        }
    }
}
class School_info extends Db{
    String tname,dept,cour,add,query;
    int roll;
    public School_info() {
        tname = "school_info";
    }
    Scanner scr = new Scanner(System.in);
    @Override
    void insert(Connection con) throws SQLException {
        System.out.print("Enter roll no : ");
        roll = scr.nextInt();
        System.out.print("Enter Department : ");
        dept = scr.next();
        System.out.print("Enter Course : ");
        cour = scr.next();
        System.out.print("Enter Address : ");
        add = scr.next();
        query = "INSERT INTO "+ tname +" VALUES (?,?,?,?)";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, roll);
        st.setString(2, dept);
        st.setString(3, cour);
        st.setString(4, add);
        if(st.executeUpdate() != 0) {
            System.out.println("Data inserted");
        }
        else {
            System.out.println("Error while inserting data");
        }
        st.close();
    }
    @Override
    void update(Connection con) throws SQLException {
        System.out.print("Enter existing roll no to update Record : ");
        roll = scr.nextInt();
        System.out.print("Enter updated Department : ");
        dept = scr.next();
        System.out.print("Enter updated Course : ");
        cour = scr.next();
        System.out.print("Enter updated Address : ");
        add = scr.next();
        query= "UPDATE " + tname +" SET dept='" + dept +"',course='"+ cour +"',addr='"+ add + "' WHERE roll=" + roll;
        Statement st4 = con.createStatement();
        if(st4.executeUpdate(query) != 0) {
            System.out.println("Record updated successfully");
        }
        else {
            System.out.println("Error while updating");
        }
        st4.close();
    }
    @Override
    void delete(Connection con) throws SQLException {
        System.out.print("Enter roll no to delete Record : ");
        roll = scr.nextInt();
        query = "DELETE from "+ tname +" WHERE roll=" + roll;
        Statement st5 = con.createStatement();
        if(st5.executeUpdate(query) != 0) {
            System.out.println("Record deleted succesfully");
        }
        else {
            System.out.println("Error while deleting Record");
        }
        st5.close();
    }
    @Override
    void display(Connection con) throws SQLException {
        query = "SELECT * FROM " + tname;
        Statement st3 = con.createStatement();
        ResultSet rs2 = st3.executeQuery(query);
        System.out.println("Roll no -> Department -> Course -> Address");
        while(rs2.next()) {
            String name = rs2.getInt(1) + " -> " + rs2.getString(2) + " -> " + rs2.getString(3) +  " -> " + rs2.getString(4);
            System.out.println(name);
        }
        st3.close();
    }
    @Override
    void search(Connection con) throws SQLException {
        int ch;
        System.out.println("1.Like statement/2.Simple search");
        System.out.print("Enter your choice : ");
        ch = scr.nextInt();
        switch (ch) {
            case 1:
                int ch2;
                System.out.println("1.Starting from/2.Containing/3.Ending with");
                System.out.print("Enter your choice : ");
                ch2 = scr.nextInt();
                System.out.println("1.To search using department name/2.To search using address");
                System.out.print("Enter your choice : ");
                int ch3 = scr.nextInt();
                if(ch3 == 1) {
                    switch (ch2) {
                        case 1:
                            System.out.print("Enter initial characters of department name to search record : ");
                            dept = scr.next();
                            query = "SELECT * FROM " + tname + " where dept like'" + dept + "%'";
                            Statement st2 = con.createStatement();
                            ResultSet rs = st2.executeQuery(query);
                            System.out.println("Roll no -> Department -> Course -> Address");
                            while (rs.next()) {
                                String name = rs.getInt(1) + " -> " + rs.getString(2) + " -> " + rs.getString(3) + " -> " + rs.getString(4);
                                System.out.println(name);
                            }
                            st2.close();
                            break;
                        case 2:
                            System.out.print("Enter middle characters of department name to search record : ");
                            dept = scr.next();
                            query = "SELECT * FROM " + tname + " where dept like'%" + dept + "%'";
                            Statement st3 = con.createStatement();
                            ResultSet rs2 = st3.executeQuery(query);
                            System.out.println("Roll no -> Department -> Course -> Address");
                            while (rs2.next()) {
                                String name = rs2.getInt(1) + " -> " + rs2.getString(2) + " -> " + rs2.getString(3) + " -> " + rs2.getString(4);
                                System.out.println(name);
                            }
                            st3.close();
                            break;
                        case 3:
                            System.out.print("Enter ending characters of department name to search record : ");
                            dept = scr.next();
                            query = "SELECT * FROM " + tname + " where dept like'%" + dept + "'";
                            Statement st4 = con.createStatement();
                            ResultSet rs3 = st4.executeQuery(query);
                            System.out.println("Roll no -> Department -> Course -> Address");
                            while (rs3.next()) {
                                String name = rs3.getInt(1) + " -> " + rs3.getString(2) + " -> " + rs3.getString(3) + " -> " + rs3.getString(4);
                                System.out.println(name);
                            }
                            st4.close();
                            break;
                        default:
                            System.out.println("Enter valid choice");
                    }
                }
                else if(ch3 == 2) {
                    switch (ch2) {
                        case 1:
                            System.out.print("Enter initial characters of address to search record : ");
                            add = scr.next();
                            query = "SELECT * FROM " + tname + " where addr like'" + add + "%'";
                            Statement st2 = con.createStatement();
                            ResultSet rs = st2.executeQuery(query);
                            System.out.println("Roll no -> Department -> Course -> Address");
                            while (rs.next()) {
                                String name = rs.getInt(1) + " -> " + rs.getString(2) + " -> " + rs.getString(3) + " -> " + rs.getString(4);
                                System.out.println(name);
                            }
                            st2.close();
                            break;
                        case 2:
                            System.out.print("Enter middle characters of department name to search record : ");
                            add = scr.next();
                            query = "SELECT * FROM " + tname + " where addr like'%" + add + "%'";
                            Statement st3 = con.createStatement();
                            ResultSet rs2 = st3.executeQuery(query);
                            System.out.println("Roll no -> Department -> Course -> Address");
                            while (rs2.next()) {
                                String name = rs2.getInt(1) + " -> " + rs2.getString(2) + " -> " + rs2.getString(3) + " -> " + rs2.getString(4);
                                System.out.println(name);
                            }
                            st3.close();
                            break;
                        case 3:
                            System.out.print("Enter ending characters of department name to search record : ");
                            add = scr.next();
                            query = "SELECT * FROM " + tname + " where addr like'%" + add + "'";
                            Statement st4 = con.createStatement();
                            ResultSet rs3 = st4.executeQuery(query);
                            System.out.println("Roll no -> Department -> Course -> Address");
                            while (rs3.next()) {
                                String name = rs3.getInt(1) + " -> " + rs3.getString(2) + " -> " + rs3.getString(3) + " -> " + rs3.getString(4);
                                System.out.println(name);
                            }
                            st4.close();
                            break;
                        default:
                            System.out.println("Enter valid choice");
                    }
                }
                else {
                    System.out.println("Enter valid choice");
                }
                break;
            case 2:
                System.out.print("Enter roll no to search record : ");
                roll = scr.nextInt();
                query = "SELECT * FROM " + tname + " where roll=" + roll;
                Statement st2 = con.createStatement();
                ResultSet rs = st2.executeQuery(query);
                System.out.println("Roll no -> Department -> Course -> Address");
                while (rs.next()) {
                    String name = rs.getInt(1) + " -> " + rs.getString(2) + " -> " + rs.getString(3) + " -> " + rs.getString(4);
                    System.out.println(name);
                }
                st2.close();
                break;
        }
    }
}

class Student_tb extends Db {
    String name, query;
    int roll, mark;
    String tname;

    public Student_tb() {
        tname = "student";
    }

    Scanner scr = new Scanner(System.in);

    @Override
    void insert(Connection con) throws SQLException {
        System.out.print("Enter roll no : ");
        roll = scr.nextInt();
        System.out.print("Enter Name : ");
        name = scr.next();
        System.out.print("Enter Marks : ");
        mark = scr.nextInt();
        query = "INSERT INTO " + tname + " VALUES (?,?,?)";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, roll);
        st.setString(2, name);
        st.setInt(3, mark);
        if (st.executeUpdate() != 0) {
            System.out.println("Data inserted");
        } else {
            System.out.println("Error while inserting data");
        }
        st.close();
    }

    @Override
    void update(Connection con) throws SQLException {
        System.out.print("Enter existing roll no to update Record : ");
        roll = scr.nextInt();
        System.out.print("Enter updated Name : ");
        name = scr.next();
        System.out.print("Enter updated Marks : ");
        mark = scr.nextInt();
        query = "UPDATE " + tname + " SET name='" + name + "',marks=" + mark + " WHERE roll=" + roll;
        Statement st4 = con.createStatement();
        if (st4.executeUpdate(query) != 0) {
            System.out.println("Record updated successfully");
        } else {
            System.out.println("Error while updating");
        }
        st4.close();
    }

    @Override
    void delete(Connection con) throws SQLException {
        System.out.print("Enter roll no to delete Record : ");
        roll = scr.nextInt();
        query = "DELETE from " + tname + " WHERE roll=" + roll;
        Statement st5 = con.createStatement();
        if (st5.executeUpdate(query) != 0) {
            System.out.println("Record deleted succesfully");
        } else {
            System.out.println("Error while deleting Record");
        }
        st5.close();
    }

    @Override
    void display(Connection con) throws SQLException {
        query = "SELECT * FROM " + tname;
        Statement st3 = con.createStatement();
        ResultSet rs2 = st3.executeQuery(query);
        System.out.println("Roll no -> Name -> Marks");
        while (rs2.next()) {
            name = rs2.getInt(1) + " -> " + rs2.getString(2) + " -> " + rs2.getInt(3);
            System.out.println(name);
        }
        st3.close();
    }

    @Override
    void search(Connection con) throws SQLException {
        int ch;
        System.out.println("1.Like statement/2.Simple search");
        System.out.print("Enter your choice : ");
        ch = scr.nextInt();
        switch (ch) {
            case 1:
                int ch2;
                System.out.println("1.Starting from/2.Containing/3.Ending with");
                System.out.print("Enter your choice : ");
                ch2 = scr.nextInt();
                switch (ch2) {
                    case 1:
                        System.out.print("Enter initial characters of name to search record : ");
                        name = scr.next();
                        query = "SELECT * FROM " + tname + " where name like'" + name + "%'";
                        Statement st2 = con.createStatement();
                        ResultSet rs = st2.executeQuery(query);
                        System.out.println("Roll no -> Name -> Marks");
                        while (rs.next()) {
                            name = rs.getInt(1) + " -> " + rs.getString(2) + " -> " + rs.getInt(3);
                            System.out.println(name);
                        }
                        st2.close();
                        break;
                    case 2:
                        System.out.print("Enter middle characters of name to search record : ");
                        name = scr.next();
                        query = "SELECT * FROM " + tname + " where name like'%" + name + "%'";
                        Statement st3 = con.createStatement();
                        ResultSet rs2 = st3.executeQuery(query);
                        System.out.println("Roll no -> Name -> Marks");
                        while (rs2.next()) {
                            name = rs2.getInt(1) + " -> " + rs2.getString(2) + " -> " + rs2.getInt(3);
                            System.out.println(name);
                        }
                        st3.close();
                        break;
                    case 3:
                        System.out.print("Enter ending characters of name to search record : ");
                        name = scr.next();
                        query = "SELECT * FROM " + tname + " where name like'%" + name + "'";
                        Statement st4 = con.createStatement();
                        ResultSet rs3 = st4.executeQuery(query);
                        System.out.println("Roll no -> Name -> Marks");
                        while (rs3.next()) {
                            name = rs3.getInt(1) + " -> " + rs3.getString(2) + " -> " + rs3.getInt(3);
                            System.out.println(name);
                        }
                        st4.close();
                        break;
                    default:
                        System.out.println("Enter valid choice");
                }
                break;
            case 2:
                System.out.print("Enter roll no to search record : ");
                roll = scr.nextInt();
                query = "SELECT * FROM " + tname + " where roll=" + roll;
                Statement st2 = con.createStatement();
                ResultSet rs = st2.executeQuery(query);
                System.out.println("Roll no -> Name -> Marks");
                while (rs.next()) {
                    name = rs.getInt(1) + " -> " + rs.getString(2) + " -> " + rs.getInt(3);
                    System.out.println(name);
                }
                st2.close();
                break;
        }
    }
}

public class Student {
    public static void main(String[] args) throws Exception {
        int ch;
        Scanner scr = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/Logictrix";
        String uname = "root";
        String pass = "";
        String tname;
        int roll,mark;
        String query;
        String name;
        Connection con = DriverManager.getConnection(url, uname, pass);
        Db std = new Student_tb();
        Db ad = new Admin();
        Db si = new School_info();
        int choice = 1;
//        System.out.println("1.Work on existing table/2.Create new table");
//        System.out.print("Enter your choice : ");
//        choice = scr.nextInt();
//
//        if(choice == 2) {
//            System.out.print("Enter table name : ");
//            tname = scr.next();
//            query = "CREATE TABLE " + tname + " (" + "\n" +
//                    "roll INT PRIMARY KEY," + "\n" +
//                    "name VARCHAR(30)," + "\n" +
//                    "marks INT" + "\n" +
//                    ");";
//            Statement statement = con.createStatement();
//            if (statement.executeUpdate(query) == 0) {
//                System.out.println("Table Created Succesfully");
//            } else {
//                System.out.println("Error while creating table");
//            }
//        }

        do{
            System.out.println("1.Admin/2.Student/3.Student info/0.Exit");
            System.out.print("Enter your choice : ");
            int ch2 = scr.nextInt();
            if(ch2 == 0) {
                break;
            }
            System.out.print("1.Insert/");
            System.out.print("2.Delete/");
            System.out.print("3.Update/");
            System.out.print("4.Display/");
            System.out.print("5.Search/");
            System.out.print("6.Delete Table/");
            System.out.println("0.Exit");
            System.out.print("Enter your choice : ");
            ch = scr.nextInt();
            switch (ch) {
                case 1:
                    switch(ch2) {
                        case 1:
                            ad.insert(con);
                            break;
                        case 2:
                            std.insert(con);
                            break;
                        case 3:
                            si.insert(con);
                            break;
                        default:
                            System.out.println("Enter valid choice");
                    }
                    break;

                case 2:
                    switch(ch2) {
                        case 1:
                            ad.delete(con);
                            break;
                        case 2:
                            std.delete(con);
                            break;
                        case 3:
                            si.delete(con);
                            break;
                        default:
                            System.out.println("Enter valid choice");
                    }
                    break;

                case 3:
                    switch(ch2) {
                        case 1:
                            ad.update(con);
                            break;
                        case 2:
                            std.update(con);
                            break;
                        case 3:
                            si.update(con);
                            break;
                        default:
                            System.out.println("Enter valid choice");
                    }
                    break;

                case 4:
                    switch(ch2) {
                        case 1:
                            ad.display(con);
                            break;
                        case 2:
                            std.display(con);
                            break;
                        case 3:
                            si.display(con);
                            break;
                        default:
                            System.out.println("Enter valid choice");
                    }
                    break;

                case 5:
                    switch(ch2) {
                        case 1:
                            ad.search(con);
                            break;
                        case 2:
                            std.search(con);
                            break;
                        case 3:
                            si.search(con);
                            break;
                        default:
                            System.out.println("Enter valid choice");
                    }
                    break;

                case 6:
                    System.out.print("Enter name of table you want to delete : ");
                    String tname2 = scr.next();
                    query = "DROP TABLE " + tname2;
                    Statement st6 = con.createStatement();
                    if(st6.executeUpdate(query) == 0) {
                        System.out.println("Table deleted successfully");
                    }
                    else {
                        System.out.println("Error while deleting table");
                    }
                    st6.close();
                    break;

                default:
                    System.out.println("Enter valid choice");
            }
        }while(ch != 0);

        con.close();
    }
}
