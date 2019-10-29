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

	public void SEMESTER() throws SQLException {
		this.query.executeUpdate("CREATE TABLE IF NOT EXISTS semester ("
			+ "id SERIAL PRIMARY KEY NOT NULL,"
			+ "year INTEGER NOT NULL,"
			+ "season TEXT NOT NULL);");
	}


	public void DEPARTMENT() throws SQLException {
		this.query.executeUpdate("CREATE TABLE IF NOT EXISTS department ("
			+ "id SERIAL PRIMARY KEY NOT NULL, "
			+ "name TEXT NOT NULL, "
			+ "building TEXT NOT NULL);");
	}

	public void MAJOR() throws SQLException {
		this.query.executeUpdate("CREATE TABLE IF NOT EXISTS major ("
			+ "id SERIAL PRIMARY KEY NOT NULL,"
			+ "department INTEGER NOT NULL,"
			+ "name TEXT NOT NULL,"
			+ "FOREIGN KEY(department) REFERENCES department(id));");
	}


	public void LOCATION() throws SQLException {
		this.query.executeUpdate("CREATE TABLE IF NOT EXISTS location ("
			+ "id SERIAL PRIMARY KEY NOT NULL,"
			+ "building TEXT NOT NULL,"
			+ "room TEXT NOT NULL,"
			+" purpose TEXT NOT NULL);");
	}


	public void FACULTY() throws SQLException {
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
	}


	public void STUDENT() throws SQLException {
		this.query.executeUpdate("CREATE TABLE IF NOT EXISTS student ("
			+ "id SERIAL PRIMARY KEY NOT NULL, "
			+ "name TEXT NOT NULL, "
			+ "graduationDate INTEGER NOT NULL, "
			+ "major INTEGER, "
			+ "adviser INTEGER NOT NULL, "
			+ "FOREIGN KEY(graduationDate) REFERENCES semester(id), "
			+ "FOREIGN KEY(major) REFERENCES major(id), "
			+ "FOREIGN KEY(adviser) REFERENCES faculty(id));");
	}

	public void COURSE() throws SQLException {
		this.query.executeUpdate("CREATE TABLE IF NOT EXISTS course("
			+ "id SERIAL PRIMARY KEY NOT NULL, "
			+ "department INTEGER NOT NULL, "
			+ "abbreviation TEXT NOT NULL, "
			+ "number INTEGER NOT NULL, "
			+ "title TEXT NOT NULL, "
			+ "credits INTEGER NOT NULL, "
			+ "FOREIGN KEY(department) REFERENCES department(id));");
	}

	public void SECTION() throws SQLException {
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
	}

	public void ENROLLMENT() throws SQLException {
		this.query.executeUpdate("CREATE TABLE IF NOT EXISTS enrollment ("
			+ "id SERIAL PRIMARY NOT NULL,"
			+ "student INTEGER NOT NULL,"
			+ "section INTEGER NOT NULL,"
			+ "grade TEXT,"
			+ "FOREIGN KEY(student) REFERENCES student(id),"
			+ "FOREIGN KEY(section) REFERENCES section(id));");
	}
}
