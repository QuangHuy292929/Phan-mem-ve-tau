package model;

import java.util.Date;
import java.util.Objects;

public class User {
	private String HoTen;
	private String cccd;
	private String password;
	private String sdthlienhe;
	private String KhuHoi;
	private Date ngay;
	private String doituong;
	private String Loaighe;
	private String NoiDi;
	private String NoiDen;
	private double giaVe;
	private int masove;
	
	
	public User(String hoTen, String cccd, String password, String sdthlienhe, String khuHoi, Date ngay,
			String doituong, String loaighe, String noiDi, String noiDen, double giaVe, int masove) {
		HoTen = hoTen;
		this.cccd = cccd;
		this.password = password;
		this.sdthlienhe = sdthlienhe;
		KhuHoi = khuHoi;
		this.ngay = ngay;
		this.doituong = doituong;
		Loaighe = loaighe;
		NoiDi = noiDi;
		NoiDen = noiDen;
		this.giaVe = giaVe;
		this.masove = masove;
	}
	
	public int getMasove() {
		return masove;
	}

	
	public void setMasove(int masove) {
		this.masove = masove;
	}

	public User() {
	}
	public String getHoTen() {
		return HoTen;
	}
	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}
	public String getCccd() {
		return cccd;
	}
	public void setCccd(String cccd) {
		this.cccd = cccd;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSdthlienhe() {
		return sdthlienhe;
	}
	public void setSdthlienhe(String sdthlienhe) {
		this.sdthlienhe = sdthlienhe;
	}
	public String getLoaighe() {
		return Loaighe;
	}
	public void setLoaighe(String loaighe) {
		Loaighe = loaighe;
	}
	public String getKhuHoi() {
		return KhuHoi;
	}
	public void setKhuHoi(String khuHoi) {
		KhuHoi = khuHoi;
	}
	public Date getNgay() {
		return ngay;
	}
	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}
	public String getDoituong() {
		return doituong;
	}
	public void setDoituong(String doituong) {
		this.doituong = doituong;
	}
	public String getNoiDi() {
		return NoiDi;
	}
	public void setNoiDi(String noiDi) {
		NoiDi = noiDi;
	}
	public String getNoiDen() {
		return NoiDen;
	}
	public void setNoiDen(String noiDen) {
		NoiDen = noiDen;
	}
	public double getGiaVe() {
		return giaVe;
	}
	public void setGiaVe(double giaVe) {
		this.giaVe = giaVe;
	}
	@Override
	public int hashCode() {
		return Objects.hash(ngay);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(ngay, other.ngay);
	}

	
	
	
}