package com.example.w23comp1008lhw9;

import java.sql.DriverManager;
import java.util.ArrayList;

public class DBUtility {

    private static string user="student";
    private static string password="student";

    //jdbc:mysql-this is telling the system which SQL driver to use
    //172.31.22.43:3306- tells Java the MYSQL server address is 172.31.22.43 and port is 3306
    //zabiullah1205298-this is the database name
    private static string connectUrl = "jdbc:mysql://172.31.22.43:3306/Zabiullah1205298";

    //
    //1. to connect with a MYSQL server, we uploaded the dependency in the POM.XML file
    //2. We need to update the module.info file with using java.sql;
    //3. we need the connection detauls defind above

    /**
     * This method will query the DB and return a list of all the courses
     */
    public static ArrayList<Course> getCoursesFromDB()
    {
        ArrayList<Course> courses = new ArrayList<>();

        //connect to the database
        //try () with round brackets indicates that it is a "try with resources", that
        //means anything inside the () will automatically be closed when the method
        //completes
        try (
                Connection conn = DriverManager.getConnection(connectUrl,user,password);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM courses");
        )
        {
            //loop over the resultSet and create Course objects
            while (resultSet.next())
            {
                int crn = resultSet.getInt("crn");
                String courseCode = resultSet.getString("courseCode");
                String courseName = resultSet.getString("courseName");

                courses.add(new Course(crn,courseCode,courseName));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return courses;
    }

}






