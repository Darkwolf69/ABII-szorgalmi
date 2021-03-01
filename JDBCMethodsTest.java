package jdbcMethods;

public class JDBCMethodsTest {

	public static void main(String[] args) {
		
		ProductsComplaintsClass DBHandler = new ProductsComplaintsClass();
		
		DBHandler.DriverReg();
		DBHandler.Connect();
		
		DBHandler.createTableProduct();
		DBHandler.createTableComplaint();
		
		DBHandler.InsertIntoProduct(1, "jatek", 2019);
		DBHandler.InsertIntoProduct(2, "ruha", 2016);
		DBHandler.InsertIntoProduct(3, "elektronika", 2008);
		DBHandler.InsertIntoProduct(4, "jatek", 2020);
		DBHandler.InsertIntoProduct(5, "elektronika", 2021);
		DBHandler.InsertIntoProduct(6, "hobbi", 2018);
		
		DBHandler.InsertIntoComplaint(1, "minosegi", 6, 1);
		DBHandler.InsertIntoComplaint(2, "design", 8, 1);
		DBHandler.InsertIntoComplaint(3, "minosegi", 2, 3);
		DBHandler.InsertIntoComplaint(4, "design", 1, 5);
		DBHandler.InsertIntoComplaint(5, "minosegi", 1, 5);
		DBHandler.InsertIntoComplaint(6, "minosegi", 3, 6);
		DBHandler.InsertIntoComplaint(7, "meret", 1, 2);
		DBHandler.InsertIntoComplaint(8, "minosegi", 4, 4);
		
		DBHandler.ProductDataQuery();
		
		DBHandler.deleteComplaints();
		
		DBHandler.Disconnect();
	}

}
