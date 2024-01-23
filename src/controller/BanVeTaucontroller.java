package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import view.BanVeTauView;

public class BanVeTaucontroller implements ActionListener{
	private BanVeTauView view;
		
	public BanVeTaucontroller(BanVeTauView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
			if(button.equals("Trang chủ")) {
				this.view.lytrangchu();
			} else if(button.equals("Mua vé")||button.equals("Mua vé ngay")) {
				this.view.lymuave();
			} else if(button.equals("Thoát")) {
				this.view.mangtodtb();
				System.exit(0);
			} else if(button.equals("Doanh thu")) {
				this.view.lydoanhthu();
			} else if(button.equals("Số lượng đơn")) {
				this.view.lysoluong();
			} else if(button.equals("Theo tên người dùng")) {
				this.view.lytenng();
			} else if(button.equals("Theo ngày đi")) {
				this.view.lyngay();
			} else if(button.equals("Theo thứ tự ngày")) {
				this.view.lysxngay();
				this.view.hienthisxtheongay();
			} else if(button.equals("Theo giá vé")) {
				this.view.lysxgiave();
				this.view.hienthisxtheogiave();
			} else if(button.equals("Hiện pw")) {
				this.view.bthiendn();
			} else if(button.equals("Ẩn pw")) {
				this.view.btandn();
			} else if(button.equals("Hiện mk")) {
				this.view.bthiendky();
			} else if(button.equals("Ẩn mk")) {
				this.view.btandky();
			} else if(button.equals("Đăng ký ngay")) {
				this.view.btdky();
			} else if(button.equals("Back")) {
				this.view.goigddn();
			} else if(button.equals("Đăng xuất")) {
				this.view.dangxuat();
			} else if(button.equals("Đặt vé")) {
				this.view.datve();
			} else if(button.equals("Hủy bỏ mẫu")) {
				this.view.xoamau();
			} else if(button.equals("Đăng nhập")) {
				this.view.checkdangnhap();
			} else if(button.equals("Đăng ký")) {
				this.view.checkdangky();
			} else if(button.equals("Cập nhật")) {
				try {
					this.view.setthongtin();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			} else if(button.equals("Lưu cập nhật")) {
				try {
					this.view.luuve();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			} else if(button.equals("Xóa vé")) {
				try {
					this.view.xoave();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if(button.equals("Tra cứu tên")) {
				this.view.hienthilenbangten();
			} else if(button.equals("Tính doanh thu")) {
				this.view.tinhdoanhthu();
			} else if(button.equals("Tính số vé")) {
				this.view.tongsove();
			} else if(button.equals("Tra cứu ngày")) {
				this.view.hienthilenbangngay();
			}
	}
	
}
