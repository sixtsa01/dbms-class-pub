import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ProjectDatabase {
	Class.forName("com.mysql.jdbc.Driver");
	connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root")
	statement = connect.createStatement();

  public void semesterTable() throws SQLException {
    this.query.executeUpdate("CREATE TABLE IF NOT EXISTS semester ("
        + "id SERIAL PRIMARY KEY NOT NULL,"
        + "year INTEGER NOT NULL,"
        + "season TEXT NOT NULL);");
    int count = 0;
    ResultSet count_semester = this.query.executeQuery("SELECT count(*) FROM semester");
    if (count_semester.next()) {
      count = count_semester.getInt(1);
    }
  }


  public void departmentTable() throws SQLException {
    this.query.executeUpdate("CREATE TABLE IF NOT EXISTS department ("
        + "id SERIAL PRIMARY KEY NOT NULL, "
        + "name TEXT NOT NULL, "
        + "building TEXT NOT NULL);");
    int count = 0;
    ResultSet count_department = this.query.executeQuery("SELECT count(*) FROM department");
    if (count_department.next()) {
      count = count_department.getInt(1);
    }
  }

  public void majorTable() throws SQLException {
    this.query.executeUpdate("CREATE TABLE IF NOT EXISTS major ("
        + "id SERIAL PRIMARY KEY NOT NULL,"
        + "department INTEGER NOT NULL,"
        + "name TEXT NOT NULL,"
      + "FOREIGN KEY(department) REFERENCES department(id));");
    int count = 0;
    ResultSet count_major = this.query.executeQuery("SELECT count(*) FROM major");
    if (count_major.next()) {
      count = count_major.getInt(1);
    }
  }


  public void locationTable() throws SQLException {
    this.query.executeUpdate("CREATE TABLE IF NOT EXISTS location ("
        + "id SERIAL PRIMARY KEY NOT NULL,"
        + "building TEXT NOT NULL,"
        + "room TEXT NOT NULL,"
        +" purpose TEXT NOT NULL);");
    int count = 0;
    ResultSet count_location = this.query.executeQuery("SELECT count(*) FROM location");
    if (count_location.next()) {
      count = count_location.getInt(1);
    }
  }


  public void facultyTable() throws SQLException {
    this.query.executeUpdate("CREATE TABLE IF NOT EXISTS faculty ("
        + "id SERIAL PRIMARY KEY NOT NULL, "
        + "name TEXT NOT NULL, "
        + "department INTEGER NOT NULL, "
        + "startDate INTEGER NOT NULL, "
        + "endDate INTEGER, "
        + "office INTEGER NOT NULL, "
      + "FOREIGN KEY(startDate) REFERENCES semester(id), "
      + "FOREIGN KEY(endDate) REFERENCES semester(id), "
      + "FOREIGN KEY(office) REFERENCES location(id));");
    int count = 0;
    ResultSet count_faculty= this.query.executeQuery("SELECT count(*) FROM faculty");
    if (count_faculty.next()) {
      count = count_faculty.getInt(1);
    }
  }


  public void studentTable() throws SQLException {
    this.query.executeUpdate("CREATE TABLE IF NOT EXISTS student ("
        + "id SERIAL PRIMARY KEY NOT NULL, "
        + "name TEXT NOT NULL, "
        + "graduationDate INTEGER NOT NULL, "
        + "major INTEGER, "
        + "adviser INTEGER NOT NULL, "
      + "FOREIGN KEY(graduationDate) REFERENCES semester(id), "
      + "FOREIGN KEY(major) REFERENCES major(id), "
      + "FOREIGN KEY(adviser) REFERENCES faculty(id));");
    int count = 0;
    ResultSet count_student = this.query.executeQuery("SELECT count(*) FROM student");
    if (count_student.next()) {
      count = count_student.getInt(1);
    }
  }

  public void courseTable() throws SQLException {
    this.query.executeUpdate("CREATE TABLE IF NOT EXISTS course("
        + "id SERIAL PRIMARY KEY NOT NULL, "
        + "department INTEGER NOT NULL, "
        + "abbreviation TEXT NOT NULL, "
        + "number INTEGER NOT NULL, "
        + "title TEXT NOT NULL, "
        + "credits INTEGER NOT NULL, "
      + "FOREIGN KEY(department) REFERENCES department(id));");
    int count = 0;
    ResultSet count_course = this.query.executeQuery("SELECT count(*) FROM course");
    if (count_course.next()) {
      count = count_course.getInt(1);
    }
  }

  public void sectionTable() throws SQLException {
    this.query.executeUpdate("CREATE TABLE IF NOT EXISTS section ("
        + "id SERIAL PRIMARY KEY NOT NULL,"
        + "course INTEGER NOT NULL,"
        + "instructor INTEGER NOT NULL,"
        + "offered INTEGER NOT NULL,"
        + "location INTEGER NOT NULL,"
        + "startHour TIME NOT NULL,"
      + "FOREIGN KEY(course) REFERENCES course(id),"
      + "FOREIGN KEY(instructor) REFERENCES faculty(id),"
      + "FOREIGN KEY(offered) REFERENCES semester(id),"
      + "FOREIGN KEY(location) REFERENCES location(id));");
    int count = 0;
    ResultSet count_section = this.query.executeQuery("SELECT count(*) FROM section");
    if (count_section.next()) {
      count = count_section.getInt(1);
    }
  }

  public void enrollmentTable() throws SQLException {
    this.query.executeUpdate("CREATE TABLE IF NOT EXISTS enrollment ("
        + "id SERIAL PRIMARY NOT NULL,"
        + "student INTEGER NOT NULL,"
        + "section INTEGER NOT NULL,"
        + "grade TEXT,"
      + "FOREIGN KEY(student) REFERENCES student(id),"
      + "FOREIGN KEY(section) REFERENCES section(id));");
    int count = 0;
    ResultSet count_enrollment = this.query.executeQuery("SELECT count(*) FROM enrollment");
    if (count_enrollment.next()) {
      count = count_enrollment.getInt(1);
    }
  }
}
