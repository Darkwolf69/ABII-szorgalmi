package jdbcMethods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ProductsComplaintsClass {

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	public ProductsComplaintsClass() {
		super();
		this.conn = null;
		this.stmt = null;
		this.rs = null;
	}
	
	public void DriverReg() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Sikeres driver regisztrálás!\n");
		} catch	(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void Connect() {
		conn = null;
		String url = "jdbc:oracle:thin:@193.6.5.58:1521:XE";
		String user = "H21_FE019W";
		String pwd = "H21_FE019W";
		try {
			conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("Sikeres kapcsolódás\n");
		} catch (Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void Disconnect() {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("Sikeres lekapcsolódás\n");
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
	
	public void createTableProduct() {
		String sqlCommand = "create table product ( ProductID NUMBER GENERATED ALWAYS AS IDENTITY "
				+ "START WITH 1 INCREMENT BY 1 PRIMARY KEY, prodYear number(4), type char(15) )";
		if (conn != null) {
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(sqlCommand);
				System.out.println("Product table created succesfully!\n");
				stmt.close();
			} catch (Exception e){
				System.err.println(e.getMessage());
			}
		}	
	}
	
	public void createTableComplaint() {
		String sqlCommand = "create table complaint ( ComplaintID NUMBER GENERATED ALWAYS AS IDENTITY "
				+ "START WITH 1 INCREMENT BY 1 PRIMARY KEY, category char(20), complaintNumber number(15),"
				+ "CONSTRAINT complaint_fk foreign key(ProductID) references product(ProductID)";
		if (conn != null) {
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(sqlCommand);
				System.out.println("Complaint table created succesfully!\n");
				stmt.close();
			} catch (Exception e){
				System.err.println(e.getMessage());
			}
		}	
	}
	
	public void InsertIntoProduct(int ProductID, String type, int prodYear) {
		String sqlCommand = "insert into product values(" + ProductID + ", " + type + ", "
				+ prodYear + ")";
		try {
			stmt = conn.createStatement();
			stmt.execute(sqlCommand);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void InsertIntoComplaint(int ComplaintID, String category, int complaintNumber,
			int complaint_fk) {
		String sqlCommand = "insert into complaint values(" + ComplaintID + ", " + category + ", "
				+ complaintNumber + ", " + complaint_fk + ")";
		try {
			stmt = conn.createStatement();
			stmt.execute(sqlCommand);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void ProductDataQuery() {
		if(conn != null) {
			String sqlCommand = "select * from Product";
			System.out.println("ProductID   type   prodYear");
			System.out.println("----------------------------");
			try {
				stmt = conn.createStatement();
				stmt.executeQuery(sqlCommand);
				rs = stmt.getResultSet();
				while(rs.next()) {
					int ProductID = rs.getInt("ProductID");
					String type = rs.getString("type");
					int prodYear = rs.getInt("prodYear");
					System.out.println(ProductID+"\t"+type+"\t"+prodYear);
				}
				rs.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	public void deleteComplaints() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Hany db-tol kisebb darabszamu panaszt toroljem? ");
		String compNum = sc.next();
		String sqlCommand = "delete from complaint where complaintNumber < '" + compNum +"'";
		if (conn != null) {
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(sqlCommand);
				System.out.println("Records deleted that are less than " + compNum);
				stmt.close();
			} catch (Exception e){
				System.err.println(e.getMessage());
			}
		}
	}
}
