package Servlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectDB {
 
	 Connection connect = null;
	 Statement stmt = null;
	 ResultSet rs = null;
 

	 protected void hasDriver() throws Exception{
		 try {
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 } catch (ClassNotFoundException ex) {
		 throw new Exception ("Invalid Driver!!Please check this drver....");
		 }
	 }
 
	 protected Connection openConnect() throws Exception{
		 if(connect == null){
		 hasDriver();
		 String url = "jdbc:mysql://localhost:3306/toritori";
		 // url_sql = "....";
		 try {
		 this.connect = DriverManager.getConnection(url,"root","");
		 } catch (SQLException e) {
			 throw new Exception(e.getMessage() + "Connect failed to database .... ");
		 }
		 }
		 return connect;
	 }
	
	 protected Statement getStatement() throws SQLException, Exception{
		 if(stmt == null){
		 stmt = openConnect().createStatement();
		 }
		 return stmt;
	 }
	
//	 public ArrayList<SanPham> getAllProducts() throws Exception{
//		 ArrayList<SanPham> lst = new ArrayList<SanPham>();
//		 String strSQL = "select * from SanPham";
//		 try {
//		 rs = getStatement().executeQuery(strSQL);
//		 while(rs.next()){
//		 String ms = rs.getString("masp");
//		 String ten = rs.getString("tensp");
//		 String ncc = rs.getString("nhacc");
//		 double gia = Double.parseDouble(rs.getString("giadv"));
//		 SanPham sp = new SanPham(ms, ten, ncc, gia);
//		 lst.add(sp);
//		 }
//		 } catch (Exception e) {
//		 throw new Exception(e.getMessage() +" Error at : " + strSQL);
//		 }
//		 closeConnet();
//		 return lst;
//	 }
	 
	 public boolean insertNew(Shisutemu sp) throws Exception{
		 String sql = "insert into shisutemu values(?,?)";
		 PreparedStatement pst = openConnect().prepareStatement(sql);
		 pst.setString(2, sp.getKotoba());
		 pst.setInt(3, sp.getSutetasu());
		 
		 return pst.executeUpdate() > 0;
	 
	 }
	 
	 public boolean deleteProduct() throws Exception{
	 String sql = "UPDATE shisutemu SET sutetasu = 0 ";
	 PreparedStatement pst = openConnect().prepareStatement(sql);
	 return pst.executeUpdate() > 0;
	 }
	 

	 public int executeUpdate(String strSQL) throws Exception{
		 int result = 0;
		 try {
		 result = getStatement().executeUpdate(strSQL);
		 } catch (Exception ex) {
		 throw new Exception(ex.getMessage() + " Error at: " + strSQL);
		 }finally{
		 this.closeConnet();
		 }
		 return result;
	 }
	 
	 public Shisutemu getProductByID(String kotoba)throws Exception{
		 Shisutemu sp = null;
		 if(kotoba != null || kotoba != "") {
			 String sql = "select * from shisutemu where right(kotoba,1) like left(?,1) and sutetasu = '"+ 0 +"'";
			 UpdateProduct(kotoba);
			 //String sql = "select * from shisutemu where right(kotoba,1) like left('"+kotoba+"',1) and sutetatu = '"+ 0 +"'";
			 PreparedStatement pst = openConnect().prepareStatement(sql);
			 pst.setString(1, kotoba);
			 ResultSet rs = pst.executeQuery();
			 if(rs.next()){
				 String kotoba1 = rs.getString("kotoba");
				 int sutetasu = rs.getInt("sutetasu");
				 sp = new Shisutemu(kotoba1, sutetasu);
				 UpdateProduct(kotoba1);
			 }
		 }
		return sp; 
	 }
	 
	public Shisutemu Kiemtratontaiendgame(String kotoba)throws Exception{
		 
		 String sql = "select * from shisutemu where kotoba like ? and sutetasu = '"+1+"'";
		 PreparedStatement pst = openConnect().prepareStatement(sql);
		 pst.setString(1, kotoba);
		 ResultSet rs = pst.executeQuery();
		 Shisutemu sp = null;
		 if(rs.next()){
			 String kotoba1 = rs.getString("kotoba");
			 int sutetasu = rs.getInt("sutetasu");
			 sp = new Shisutemu(kotoba1, sutetasu);
		 }
		 return sp;
	
	 
	 }
	
	public Shisutemu Kiemtratontai(String kotoba)throws Exception{
		 
		 String sql = "select * from shisutemu where kotoba like '"+kotoba+"'";
		 PreparedStatement pst = openConnect().prepareStatement(sql);
		 pst.setString(1, kotoba);
		 ResultSet rs = pst.executeQuery();
		 Shisutemu sp = null;
		 if(rs.next()){
		 String kotoba1 = rs.getString("kotoba");
		 int sutetasu = rs.getInt("sutetasu");
		 sp = new Shisutemu( kotoba1, sutetasu);
		 }
		 return sp;
	
	
	}
	 
	 public boolean UpdateProduct(String kotoba) throws Exception{
		 String sql = "update shisutemu set sutetasu = '"+ 1 +"' where kotoba like ? ";
		 PreparedStatement pst = openConnect().prepareStatement(sql);
		 pst.setString(1, kotoba);
		 return pst.executeUpdate()>0;
	 }
	
	 public void closeConnet() throws SQLException{
		 if(rs != null && !rs.isClosed())
		 rs.close();
		 if(stmt != null)
		 stmt.close();
		 if(connect != null)
		 connect.close();
	 }
	 
	 public static void main(String[] args) throws Exception {
		 new ConnectDB().deleteProduct();
		 System.out.println(new ConnectDB().getProductByID("ichi"));
		 
	 }
 
}