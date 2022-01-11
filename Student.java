import java.sql.*;
import java.util.Scanner;

public class Student {
    public static void main(String[] args) throws Exception {
        int ch;
        Scanner scr = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/Logictrix";
        String uname = "root";
        String pass = "";
        int roll,mark;
        String query;
        String name;
        Connection con = DriverManager.getConnection(url, uname, pass);

        int choice;
        System.out.println("1.Work on existing table/2.Create new table");
        System.out.print("Enter your choice : ");
        choice = scr.nextInt();

        if(choice == 2) {
            String tname = "";
            System.out.print("Enter table name : ");
            tname = scr.next();
            query = "CREATE TABLE " + tname + " (" + "\n" +
                    "roll INT PRIMARY KEY," + "\n" +
                    "name VARCHAR(30)," + "\n" +
                    "marks INT" + "\n" +
                    ");";
            System.out.println(query);
            Statement statement = con.createStatement();
            if(statement.executeUpdate(query) == 0) {
                System.out.println("Table Created Succesfully");
            }
            else {
                System.out.println("Error while creating table");
            }
        }

        do{
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
                    System.out.print("Enter roll no : ");
                    roll = scr.nextInt();
                    System.out.print("Enter Name : ");
                    name = scr.next();
                    System.out.print("Enter Marks : ");
                    mark = scr.nextInt();
                    query = "INSERT INTO student VALUES (?,?,?)";
                    PreparedStatement st = con.prepareStatement(query);
                    st.setInt(1, roll);
                    st.setString(2, name);
                    st.setInt(3, mark);
                    if(st.executeUpdate() != 0) {
                        System.out.println("Data inserted");
                    }
                    else {
                        System.out.println("Error while inserting data");
                    }
                    st.close();
                    break;

                case 2:
                    System.out.print("Enter roll no to delete Record : ");
                    roll = scr.nextInt();
                    query = "DELETE from student WHERE roll=" + roll;
                    Statement st5 = con.createStatement();
                    if(st5.executeUpdate(query) != 0) {
                        System.out.println("Record deleted succesfully");
                    }
                    else {
                        System.out.println("Error while deleting Record");
                    }
                    st5.close();
                    break;

                case 3:
                    System.out.print("Enter existing roll no to update Record : ");
                    roll = scr.nextInt();
                    System.out.print("Enter updated Name : ");
                    name = scr.next();
                    System.out.print("Enter updated Marks : ");
                    mark = scr.nextInt();
                    query= "UPDATE student SET name='" + name +"',marks="+ mark + " WHERE roll=" + roll;
                    Statement st4 = con.createStatement();
                    if(st4.executeUpdate(query) != 0) {
                        System.out.println("Record updated successfully");
                    }
                    else {
                        System.out.println("Error while updating");
                    }
                    st4.close();
                    break;

                case 4:
                    query = "SELECT * FROM student";
                    Statement st3 = con.createStatement();
                    ResultSet rs2 = st3.executeQuery(query);
                    System.out.println("Roll no -> Name -> Marks");
                    while(rs2.next()) {
                        name = rs2.getInt(1) + " -> " + rs2.getString(2) + " -> " + rs2.getInt(3);
                        System.out.println(name);
                    }
                    st3.close();
                    break;

                case 5:
                    System.out.print("Enter roll no to search record : ");
                    roll = scr.nextInt();
                    query = "SELECT * FROM student where roll=" + roll;
                    Statement st2 = con.createStatement();
                    ResultSet rs = st2.executeQuery(query);
                    System.out.println("Roll no -> Name -> Marks");
                    while(rs.next()) {
                        name = rs.getInt(1) + " -> " + rs.getString(2) + " -> " + rs.getInt(3);
                        System.out.println(name);
                    }
                    st2.close();
                    break;

                case 6:
                    System.out.print("Enter name of table you want to delete : ");
                    String tname = scr.next();
                    query = "DROP TABLE " + tname;
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
