package khang.servlet;


public class SanPham {
 private String masp;
 private String tensp;
 private String nhacc;
 private double giadv;
 
 public SanPham(String masp, String tensp, String nhacc, double giadv) {
 this.masp = masp;
 this.tensp = tensp;
 this.nhacc = nhacc;
 this.giadv = giadv;
 }
 
public SanPham() {
 }
 
 public String getMasp() {
 return masp;
 }
 public void setMasp(String masp) {
 this.masp = masp;
 }
 public String getTensp() {
 return tensp;
 }
 public void setTensp(String tensp) {
 this.tensp = tensp;
 }
 public String getNhacc() {
 return nhacc;
 }
 public void setNhacc(String nhacc) {
 this.nhacc = nhacc;
 }
 public double getGiadv() {
 return giadv;
 }
 public void setGiadv(double giadv) {
 this.giadv = giadv;
 }
 @Override
 public int hashCode() {
 final int prime = 31;
 int result = 1;
 result = prime * result + ((masp == null) ? 0 : masp.hashCode());
 return result;
 }
 @Override
 public boolean equals(Object obj) {
 if (this == obj)
 return true;
 if (obj == null)
 return false;
 if (getClass() != obj.getClass())
 return false;
 SanPham other = (SanPham) obj;
 if (masp == null) {
 if (other.masp != null)
 return false;
 } else if (!masp.equals(other.masp))
 return false;
 return true;
 }
 
}