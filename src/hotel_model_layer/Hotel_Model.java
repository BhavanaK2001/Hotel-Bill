package hotel_model_layer;

import java.io.ObjectInputFilter.Status;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Item {

	int newsubTotal;
	int newprice;
	int newqua;
	String newitemName;

	public Item(String newitemName, int newprice, int newqua, int newsubTotal) {
		this.newitemName = newitemName;
		this.newprice = newprice;
		this.newqua = newqua;
		this.newsubTotal = newsubTotal;
	}

	@Override
	public String toString() {
		if (newitemName.equalsIgnoreCase("water bottle")) {

			return newitemName + "  " + newprice + "       " + newqua + "         " + newsubTotal;

		} else {

			return newitemName + "          " + newprice + "       " + newqua + "         " + newsubTotal;
		}
	}
}

public class Hotel_Model {
	static LinkedList<Item> list = new LinkedList<Item>();

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "bhavana28");


			Scanner sc = new Scanner(System.in);

			System.out.println("Hotel Menu");

			System.out.print("slno  ");
			System.out.print("item     ");
			System.out.println("price");

			System.out.print(1 + " ");
			System.out.print("idly        ");
			System.out.println(20 + "       ");

			System.out.print(2 + " ");
			System.out.print("dosa        ");
			System.out.println(50 + "       ");

			System.out.print(3 + " ");
			System.out.print("coffee      ");
			System.out.println(10 + "     ");

			System.out.print(4 + " ");
			System.out.print("tea         ");
			System.out.println(8 + " ");

			System.out.print(5 + " ");
			System.out.print("water bottle");
			System.out.println(20 + " ");

			System.out.print(6 + " ");
			System.out.print("vada        ");
			System.out.println(35 + " ");

			String st = "Yes";
			int newsubTotal = 0;
			int newprice = 0;
			int newqua = 0;
			String newitemName = null;
			System.out.println("can you please tell your name?");
			String name = sc.nextLine();
			Pattern p = Pattern.compile("[A-Z,a-z]");
			Matcher m = p.matcher(name);
			String validname= "";
			while(m.find()){
				validname = validname+m.group();
			}
			if(validname.isEmpty()|| validname.length()<3) {
				System.out.println("Invalid Name please enter your name again");
				validname = sc.nextLine();
			} else {
				if (st.equalsIgnoreCase("Yes")) {
					while (st.equalsIgnoreCase("Yes")) {
						System.out.println("what to you want to buy?");
						int newquant = sc.nextInt();

						switch (newquant) {
						case 1:
							System.out.println("idly");
							System.out.println("how much quantity?(Numbers only)");
							newitemName = "Idly";
							newprice = 20;
							newqua = sc.nextInt();
							newsubTotal = newprice * newqua;
							break;

						case 2:
							System.out.println("Dosa");
							System.out.println("how much quantity?");
							newitemName = "Dosa";
							newprice = 50;
							newqua = sc.nextInt();
							newsubTotal = newprice * newqua;
							break;

						case 3:
							System.out.println("Coffee");
							System.out.println("how much quantity?");
							newitemName = "Coffee";
							newprice = 10;
							newqua = sc.nextInt();
							newsubTotal = newprice * newqua;
							break;

						case 4:
							System.out.println("Tea");
							System.out.println("how much quantity?");
							newitemName = "Tea";
							newprice = 8;
							newqua = sc.nextInt();
							newsubTotal = newprice * newqua;
							break;

						case 5:
							System.out.println("Water Bottle");
							System.out.println("how much quantity?");
							newitemName = "Water Bottle";
							newprice = 20;
							newqua = sc.nextInt();
							newsubTotal = newprice * newqua;
							break;

						case 6:
							System.out.println("Vada");
							System.out.println("how much quantity?");
							newitemName = "Vada";
							newprice = 35;
							newqua = sc.nextInt();
							newsubTotal = newprice * newqua;
							break;

						default:
							System.out.println("invalid input  or item not inculeded in Menu");

						}

						System.out.println("do you want to buy something more?");
						st = sc.next();

						list.add(new Item(newitemName, newprice, newqua, newsubTotal));

					}
					System.out.println("Bill Amount");
					System.out.println("Item       " + "Price    " + "Quantity    " + "SubTotal    ");
					int i = 0;
					for (Item item : list) {
						i = i + item.newsubTotal;
						System.out.println(item);

					}
					String xyz = "insert into bill(customer_name, bill_amount) values(?,?)";
					PreparedStatement pstmt = con.prepareStatement(xyz);
					pstmt.setString (1,validname);
					pstmt.setInt(2, i);
					pstmt.executeUpdate();
					System.out.println("Total                            " + i);
					con.close();
					System.out.println("do you like to give tip as per your perference?");
					int rs = 0;
					//if(st.equalsIgnoreCase("Yes")) {
						st = sc.next();
						if(st.equalsIgnoreCase("Yes")) {
							System.out.println("what is your preference tip?");
							int tips = sc.nextInt();
							rs=tips+i;
							System.out.println("Bill Amount  "+i+" Tips is  "+tips+" Total Amount  "+rs);
						}else {
							rs =(i*10)/100;
							rs= rs+i;
							System.out.println("Bill Amount  "+i+" Tip is 10%"+ " Total Amount  "+rs);
						}

					} 

				}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
