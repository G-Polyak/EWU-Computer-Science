/*****************************
Query the University Database
*****************************/
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.util.Date;
import java.lang.String;

public class MyQuery {

    private Connection conn = null;
	 private Statement statement = null;
	 private ResultSet resultSet = null;
    
    public MyQuery(Connection c)throws SQLException
    {
        conn = c;
        // Statements allow to issue SQL queries to the database
        statement = conn.createStatement();
    }
    
    public void findFall2009Students() throws SQLException
    {
        String query  = "select distinct name from student natural join takes where semester = \'Fall\' and year = 2009;";

        resultSet = statement.executeQuery(query);
    }
    
    public void printFall2009Students() throws IOException, SQLException
    {
	      System.out.println("******** Query 0 ********");
         System.out.println("name");
         while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number which starts at 1
			String name = resultSet.getString(1);
         System.out.println(name);
   		}        
    }

    public void findGPAInfo() throws SQLException
    {

    	String makeTempTable = "create temporary table withGradeAsNum (" +
			    "  `ID` varchar(5) NOT NULL DEFAULT ''," +
			    "  `course_id` varchar(8) NOT NULL DEFAULT ''," +
			    "  `sec_id` varchar(8) NOT NULL DEFAULT ''," +
			    "  `semester` varchar(6) NOT NULL DEFAULT ''," +
			    "  `year` decimal(4,0) NOT NULL DEFAULT '0'," +
			    "  `grade` varchar(4) DEFAULT NULL);";
    	String insertValues = "insert into withGradeAsNum (select * from takes);";
    	String changeToNum1 = "update withGradeAsNum set grade=case when grade='A' then '4.0'\n" +
			    " when grade='A-' then '3.67' when grade='B+' then '3.33' when grade='B' then '3.0'" +
			    " when grade='B-' then '2.67' when grade='C+' then '2.33' when grade='C' then '2.0'" +
			    " when grade='C-' then '1.67' when grade='D+' then '1.33' when grade='D' then '1.0'" +
			    " when grade='D-' then '0.67' when grade='F' then '0.0' else null end;";
    	String changeToNum2 = "alter table withGradeAsNum modify column grade double;";
    	String query = "select ID, name, round(sum(grade * credits)/sum(credits), 6) GPA from course natural join withGradeAsNum"
			    + " natural join student group by ID";
    	statement.execute(makeTempTable);
    	statement.execute(insertValues);
    	statement.executeUpdate(changeToNum1);
    	statement.execute(changeToNum2);
    	resultSet = statement.executeQuery(query);

    }
    
    public void printGPAInfo() throws IOException, SQLException
    {
		   System.out.println("******** Query 1 ********");
		   System.out.println("id     name      GPA");
		   while (resultSet.next()) {
		   	String id = resultSet.getString(1);
		   	String name = resultSet.getString(2);
		   	String gpa = resultSet.getString(3);
		   	System.out.println(id + " " + name + " " + gpa);
		   }

    }

    public void findMorningCourses() throws SQLException
    {

    	String getOutput = "select course_ID, title, section_ID, semester, year, name, from "

    }

    public void printMorningCourses() throws IOException, SQLException
    {
	   	System.out.println("******** Query 2 ********");
    }

    public void findBusyInstructor() throws SQLException
    {
 
    }

    public void printBusyInstructor() throws IOException, SQLException
    {
		   System.out.println("******** Query 3 ********");
    }

    public void findPrereq() throws SQLException
    {

    }

    public void printPrereq() throws IOException, SQLException
    {
		   System.out.println("******** Query 4 ********");
    }

    public void updateTable() throws SQLException
    {

    }

    public void printUpdatedTable() throws IOException, SQLException
    {
		   System.out.println("******** Query 5 ********");
    }

    public void findFirstLastSemester() throws SQLException
    {
 
    }

    public void printFirstLastSemester() throws IOException, SQLException
    {
        System.out.println("******** Query 6 ********");
    }
	
	public void findHeadCounts() throws SQLException
	{
		   System.out.println("******** Query 7 ********");	
	}
}
