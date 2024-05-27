package hotel_model_layer;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Admin_Module {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your email");
		String email = sc.nextLine();
		System.out.println("Enter your password");
		String password = sc.nextLine();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "bhavana28");
			Statement stmt = con.createStatement();
			ResultSet status = stmt.executeQuery("select * from login where email = '"+email+"' and password = '"+password+"'");


			if(status.next()==true)
			{
				ResultSet result = stmt.executeQuery("select * from bill ");
				System.out.println("sales transaction");
				while(result.next()) {
					System.out.println(result.getInt(1)+"     "+result.getString(2)+"    "+result.getString(3));


				}		

			}
			else {
			System.out.println("invaild credentail");	
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
