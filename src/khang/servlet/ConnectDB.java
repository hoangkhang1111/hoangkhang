package khang.servlet;
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
 
/**
 * Check Driver connect to MySQL
 */
 protected void hasDriver() throws Exception{
 try {
 Class.forName("com.mysql.jdbc.Driver");
 } catch (ClassNotFoundException ex) {
 throw new Exception ("Invalid Driver!!Please check this drver....");
 }
 }
 
/**
 * Function used to get the connection to the Database
 * Step 1 - I check my connection or not!!
 * Step 2 - If not, it will be null and initialization.
 * Step 3 - Then it return
 * @return Connection
 */
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
 /**
 * Make a Statement to execute the SQL statement
 * @return Statement
 */
 protected Statement getStatement() throws SQLException, Exception{
 if(stmt == null){
 stmt = openConnect().createStatement();
 }
 return stmt;
 }
 /**
 * Used to execute the Select statement
 * @param strSQL Query VD: Select * from Employee
 * @return ResultSet
 */
 public ArrayList<SanPham> getAllProducts() throws Exception{
 ArrayList<SanPham> lst = new ArrayList<SanPham>();
 String strSQL = "select * from SanPham";
 try {
 rs = getStatement().executeQuery(strSQL);
 while(rs.next()){
 String ms = rs.getString("masp");
 String ten = rs.getString("tensp");
 String ncc = rs.getString("nhacc");
 double gia = Double.parseDouble(rs.getString("giadv"));
 SanPham sp = new SanPham(ms, ten, ncc, gia);
 lst.add(sp);
 }
 } catch (Exception e) {
 throw new Exception(e.getMessage() +" Error at : " + strSQL);
 }
 closeConnet();
 return lst;
 }
 
 public boolean insertNew(SanPham sp) throws Exception{
 String sql = "insert into SanPham values(?,?,?,?)";
 PreparedStatement pst = openConnect().prepareStatement(sql);
 pst.setString(1, sp.getMasp());
 pst.setString(2, sp.getTensp());
 pst.setString(3, sp.getNhacc());
 pst.setDouble(4, sp.getGiadv());
 
 return pst.executeUpdate() > 0;
 
 }
 
 public boolean deleteProduct(String masp) throws Exception{
 String sql = "delete from SanPham where masp=?";
 PreparedStatement pst = openConnect().prepareStatement(sql);
 pst.setString(1, masp);
 return pst.executeUpdate() > 0;
 }
 
 /**
 * Used to execute the Insert, Update, Delete statement
 * @param strSQL Query VD: Insert into TableName values ('??','??')
 * @return The number of lines affected by the command
 */
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
 
 public SanPham getProductByID(String masp)throws Exception{
 String sql = "select * from SanPham where masp=?";
 PreparedStatement pst = openConnect().prepareStatement(sql);
 pst.setString(1, masp);
 ResultSet rs = pst.executeQuery();
 SanPham sp = null;
 if(rs.next()){
 String ms = rs.getString("masp");
 String ten = rs.getString("tensp");
 String ncc = rs.getString("nhacc");
 double gia = Double.parseDouble(rs.getString("giadv"));
 sp = new SanPham(ms, ten, ncc, gia);
 }
 return sp;
 }
 
 public boolean UpdateProduct(String masp,SanPham newsp) throws Exception{
 String sql = "update SanPham set tensp=?, nhacc=?, giadv=? where masp=?";
 PreparedStatement pst = openConnect().prepareStatement(sql);
 pst.setString(1, newsp.getTensp());
 pst.setString(2, newsp.getNhacc());
 pst.setDouble(3, newsp.getGiadv());
 pst.setString(4, newsp.getMasp());
 return pst.executeUpdate()>0;
 }
 /**
 * A method to close the connection.
 * @throws SQLException
 */
 public void closeConnet() throws SQLException{
 if(rs != null && !rs.isClosed())
 rs.close();
 if(stmt != null)
 stmt.close();
 if(connect != null)
 connect.close();
 }
 
 public static void main(String[] args) throws Exception {
 new ConnectDB().UpdateProduct("sp01", new SanPham("sp01", "AAAA", "BBBB", 1000));
 System.out.println(new ConnectDB().getProductByID("sp01").getTensp());
 
 }
 
}