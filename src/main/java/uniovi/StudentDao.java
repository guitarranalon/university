package uniovi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
	public static List<Student> resultSetToList(ResultSet rsStudents) {
		List<Student> dataList = new ArrayList<Student>();
		
		try {
	        while (rsStudents.next()) {
	            Student student = new Student();
	            student.setId(rsStudents.getInt("id"));
	            student.setName(rsStudents.getString("name"));
	            student.setEmail(rsStudents.getString("email"));
	
	            dataList.add(student);
	        }
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
        return dataList;
	}
}
