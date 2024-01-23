package view;

import java.awt.*; 
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.BanVeTaucontroller;
import model.Dbconection;
import model.User;
import model.UserManage;

import java.text.ParseException;

public class BanVeTauView extends JFrame{
	String[] ga = {"Hà Nội", "Vinh", "Huế", "Đà Nẵng", "Nha Trang", "Sài Gòn"};
	String[] loaighe = {"Ngồi-Mềm-Điều Hòa", "Giường-nằm"};
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	UserManage model = new UserManage();
	private JComboBox<String> boxdtuongMua;
	private JComboBox<String> boxLoaiGhe;
	private JCheckBox checkKhuHoi;
	private JTextField txtNgayDi;
	private JLabel lbNgayDi;
	private JTextField txtHoTen;
	private JLabel lbHoten;
	private JComboBox<String> boxNoidi;
	private JComboBox<String> boxNoiden;
	private JLabel lbdtuong;
	private CardLayout card;
	private JPanel contentpane;
	private CardLayout card3;
	private CardLayout card2;
	private JPanel content;
	private JPanel pnanh;
	private JPanel pnhoatdong;
	private Object [][]dataql= {};
	private Object [][]datand= {};
	private CardLayout cardxem;
	private JButton btan;
	private JButton btxem;
	private JPasswordField pwdangnhap;
	private JPanel pnxemcon;
	private CardLayout xempwdky;
	private JPanel pnxempwdky;
	private JPasswordField pwdky;
	private JPasswordField pwnhaplai;
	private JTextField CCCDdn;
	private JTextField CCCdk;
	private JTextField txtlienhe;
	private int giave;
	private DefaultTableModel dm;
	private DefaultTableModel dmql;
	private JTable tablengdung;
	private JTextField txtdt;
	private JTextField txtlv;
	private JTextField txtnhapten;
	private JTextField txtnhapngay;
	private DefaultTableModel dften;
	private DefaultTableModel dfngay;
	private JTable bangtkten;
	private JTable bangtkngay;
	private JButton btxn2;
	private JButton btxn;
	private DefaultTableModel sxgiave;
	private JTable bangsxgiave;
	private DefaultTableModel sxngay;
	private JTable bangsxngay;
	Dbconection db = new Dbconection();
	BanVeTaucontroller control = new BanVeTaucontroller(this);
	public BanVeTauView() throws ParseException {
		ketnoidtb();
		this.model = model;
		this.giaodiendangnhap();
	}
	//kết nối và lấy dữ liệu từ database
	public void ketnoidtb() throws ParseException {
		try {
			Connection con = Dbconection.getConnection();
			Statement stmt = con.createStatement();
			// Truy vấn lấy dữ liệu
			String sql = "SELECT * FROM dulieu";
			ResultSet rs = stmt.executeQuery(sql);
			// Đọc dữ liệu từ ResultSet và đưa vào ArrayList
			while (rs.next()) {
				  String hoten = rs.getString("hoten");
				  String CCCD = rs.getString("cccd");
				  String password = rs.getString("password");
				  String sdth = rs.getString("sdth");
				  String dtuong = rs.getString("doituongmua");
				  String ngay = rs.getString("ngaydi");
				  SimpleDateFormat sp = new SimpleDateFormat("yyyy-mm-dd");
				  Date d = sp.parse(ngay);
				  String sdate = sdf.format(d);
				  Date d2 = sdf.parse(sdate);
				  String loaighe = rs.getString("loaighe");
				  String khuhoi = rs.getString("khuhoi");
				  String noidi = rs.getString("noidi");
				  String noiden = rs.getString("noiden");
				  double giave = (double)rs.getInt("giave");
				  int masove = rs.getInt("masove");		  
				  User us = new User(hoten, CCCD, password, sdth, khuhoi, d2, dtuong, loaighe, noidi, noiden, giave, masove);
				  model.userList.add(us); 
			}
			// Đóng kết nối
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
//giao diện đăng nhập
	public void giaodiendangnhap() {
		this.setTitle("HỆ THỐNG BÁN VÉ TÀU TOÀN QUỐC");
		setSize(400, 250);
		dongvataidulieu();
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(BanVeTauView.class.getResource("/fileanh/train.png")));
		JPanel contentdn = new JPanel();
		contentdn.setLayout(new BorderLayout(10, 10));
		setContentPane(contentdn);
		
		JPanel pndangnhap = new JPanel();
		Border borderdn = BorderFactory.createEtchedBorder(1);
		TitledBorder title = new TitledBorder(borderdn,"Đăng nhập");
		pndangnhap.setBorder(title);
		pndangnhap.setLayout(new BorderLayout(10, 10));
		
		//panel nhập thông tin
		JPanel pnnhap = new JPanel();
		pnnhap.setLayout(new GridLayout(2, 2, 10, 10));
		JLabel lbCCCD = new JLabel(" Nhập căn cước công dân");
		CCCDdn = new JTextField(40);
		JLabel lbpass = new JLabel(" Nhập mật khẩu");
		pwdangnhap = new JPasswordField(40);
		pwdangnhap.setEchoChar('\u2022');
		pnnhap.add(lbCCCD);
		pnnhap.add(CCCDdn);
		pnnhap.add(lbpass);
		pnnhap.add(pwdangnhap);
		pndangnhap.add(pnnhap, BorderLayout.CENTER);
		
		//panel nút nhấn 
		JPanel pnnut = new JPanel();
		pnnut.setLayout(new BorderLayout(10, 10));
		
			//panel nút con
		JPanel pnxem = new JPanel();
		pnxem.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnxemcon = new JPanel();
		cardxem = new CardLayout();
		pnxemcon.setLayout(cardxem);
		btxem = new JButton("Hiện pw");
		btxem.addActionListener(control);
		btxem.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/view.png")));
		btan = new JButton("Ẩn pw");
		btan.addActionListener(control);
		btan.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/hide.png")));
		pnxemcon.add(btxem, "Xem");
		pnxemcon.add(btan, "Ẩn");
		pnxem.add(pnxemcon);
		pnnut.add(pnxem, BorderLayout.CENTER);
		
		JButton btdangnhap = new JButton("Đăng nhập");
		btdangnhap.addActionListener(control);
		btdangnhap.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/login.png")));
		pnnut.add(btdangnhap, BorderLayout.SOUTH);
		pndangnhap.add(pnnut, BorderLayout.SOUTH);
		contentdn.add(pndangnhap, BorderLayout.NORTH);
		
		//tạo panel chứa nút đăng ký 
		JPanel pndky = new JPanel();
		TitledBorder title2 = new TitledBorder(borderdn,"");
		pndky.setBorder(title2);
		pndky.setLayout(new GridLayout(1, 2, 10, 10));
		JLabel lbreport = new JLabel("            Bạn chưa có tài khoản?");
		JButton btdky = new JButton("Đăng ký ngay");
		btdky.addActionListener(control);
		btdky.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/signin.png")));
		pndky.add(lbreport);
		pndky.add(btdky);
		contentdn.add(pndky, BorderLayout.CENTER);
		
		this.setVisible(true);
		
	}

	
//giao diện đăng ký	
	public void giaodiendangky() {
		this.setTitle("HỆ THỐNG BÁN VÉ TÀU TOÀN QUỐC");
		setSize(400, 325);
		this.setMenuBar(null);
		dongvataidulieu();
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(BanVeTauView.class.getResource("/fileanh/train.png")));

		JPanel contentdk = new JPanel();
		contentdk.setLayout(new BorderLayout(10, 10));
		setContentPane(contentdk);
		
		JPanel pndangky = new JPanel();
		Border bddky = BorderFactory.createBevelBorder(1);
		TitledBorder title = new TitledBorder(bddky,"Đăng ký");
		pndangky.setBorder(title);
		pndangky.setLayout(new BorderLayout(10, 10));
		
		//panel nhập thông tin
		JPanel pnnhap = new JPanel();
		pnnhap.setLayout(new GridLayout(4, 2, 10, 10));
		JLabel lbCCCD = new JLabel(" Nhập căn cước công dân");
		CCCdk = new JTextField(40);
		JLabel lbpass = new JLabel(" Tạo mật khẩu");
		pwdky = new JPasswordField(40);
		JLabel lbpagain = new JLabel(" Nhập lại mật khẩu");
		pwnhaplai = new JPasswordField(40);
		pwdky.setEchoChar('\u2022');
		pnnhap.add(lbCCCD);
		pnnhap.add(CCCdk);
		pnnhap.add(lbpass);
		pnnhap.add(pwdky);
		pnnhap.add(lbpagain);
		pnnhap.add(pwnhaplai);
		pndangky.add(pnnhap, BorderLayout.CENTER);
		
		//panel nút nhấn 
		JPanel pnnut = new JPanel();
		pnnut.setLayout(new BorderLayout(10, 10));
		
			//panel nút con
		JPanel pnxem = new JPanel();
		pnxem.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnxempwdky = new JPanel();
		xempwdky = new CardLayout();
		pnxempwdky.setLayout(xempwdky);
		JButton btxem = new JButton("Hiện mk");
		btxem.addActionListener(control);
		btxem.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/view.png")));
		JButton btan = new JButton("Ẩn mk");
		btan.addActionListener(control);
		btan.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/hide.png")));
		pnxempwdky.add(btxem, "Xemdky");
		pnxempwdky.add(btan, "Ẩndky");
		pnxem.add(pnxempwdky);
		pnnut.add(pnxem, BorderLayout.CENTER);
		
		JButton btdky = new JButton("Đăng ký");
		btdky.addActionListener(control);
		btdky.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/signin.png")));
		pnnut.add(btdky, BorderLayout.SOUTH);
		pndangky.add(pnnut, BorderLayout.SOUTH);
		contentdk.add(pndangky, BorderLayout.NORTH);
		
		//tạo panel chứa nút quay lại
		JPanel pnback = new JPanel();
		pnback.setBorder(bddky);
		pnback.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JButton btback = new JButton("Back");
		btback.addActionListener(control);
		btback.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/back.png")));
		pnback.add(btback);
		contentdk.add(pnback, BorderLayout.CENTER);
		this.setVisible(true);
	}

	
//giao diện người dùng	
	public void GiaoDienNgDung() { 
		this.setTitle("HỆ THỐNG BÁN VÉ TÀU TOÀN QUỐC_website:vetautoanquoc.com");
		this.setMenuBar(null);
		this.setSize(700,725);
		dongvataidulieu();
		this.setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(BanVeTauView.class.getResource("/fileanh/train.png")));
		card = new CardLayout();
		contentpane = new JPanel();
		contentpane.setLayout(card);
		setContentPane(contentpane);

		
		//tạo thanh menu 
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnCaiDat = new JMenu("Cài đặt");
		mnCaiDat.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/setting.png")));
		menuBar.add(mnCaiDat);
		JMenuItem itemdangxuat = new JMenuItem("Đăng xuất");
		itemdangxuat.addActionListener(control);
		itemdangxuat.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/logout.png")));
		mnCaiDat.add(itemdangxuat);
		JMenuItem itemthoat = new JMenuItem("Thoát");
		itemthoat.addActionListener(control);
		itemthoat.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/exit.png")));
		mnCaiDat.add(itemthoat);
		JButton btTrangChu = new JButton("Trang chủ");
		menuBar.add(btTrangChu);
		btTrangChu.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/home.png")));
		btTrangChu.addActionListener(control);
		JButton btMua = new JButton("Mua vé");
		menuBar.add(btMua);
		btMua.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/cart.png")));
		btMua.addActionListener(control);
		
		
	//thiết kế panel trang chủ
		JPanel pnTrangChu = new JPanel();
		pnTrangChu.setLayout(new GridLayout(1, 2, 20, 20));	
		
		JLabel lbanhchinh = new JLabel("");
		lbanhchinh.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/tauhoa1.jpg")));
		lbanhchinh.setHorizontalAlignment(lbanhchinh.CENTER);
		pnTrangChu.add(lbanhchinh);
		
		//panel chứa các sự kiện
		JPanel pnevent = new JPanel();
		pnevent.setLayout(new GridLayout(2, 1, 20, 20));
		Border border3 = BorderFactory.createSoftBevelBorder(1, Color.white, Color.black);
		TitledBorder bdtitle3 = BorderFactory.createTitledBorder(border3, "Các ưu đãi đang diễn ra");
		pnevent.setBorder(bdtitle3);
		
			//panel sự kiện 1
		JPanel event1 = new JPanel();
		event1.setLayout(new BorderLayout(10, 10));
		JLabel anhevent = new JLabel();
		
		anhevent.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/uudai.jpg")));
		anhevent.setHorizontalAlignment(anhevent.CENTER);
		JButton mua1 = new JButton("Mua vé ngay");
		mua1.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/cart.png")));
		mua1.addActionListener(control);
		event1.add(anhevent, BorderLayout.CENTER);
		event1.add(mua1, BorderLayout.SOUTH);
		pnevent.add(event1);
		
			//panel sự kiện 2
		JPanel event2 = new JPanel();
		event2.setLayout(new BorderLayout(10, 10));		
		JLabel anhevent2 = new JLabel();
		anhevent2.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/uudai2.png")));
		anhevent2.setHorizontalAlignment(anhevent2.CENTER);
		JButton mua2 = new JButton("Mua vé ngay");
		mua2.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/cart.png")));
		mua2.addActionListener(control);
		event2.add(anhevent2, BorderLayout.CENTER);
		event2.add(mua2, BorderLayout.SOUTH);
		pnevent.add(event2);
		
		pnTrangChu.add(pnevent);

	//thiết kế panel mua vé
		JPanel pnMuaVe = new JPanel();
		pnMuaVe.setLayout(new BorderLayout(10, 10));
		
		//thiết kế giao diện panel nhập thông tin
		
		JPanel pnnhapthongtin = new JPanel();
		pnnhapthongtin.setLayout(new GridLayout(9, 4, 10, 10));
		Border border1 = BorderFactory.createLineBorder(Color.red);
		TitledBorder bdtitle1 = BorderFactory.createTitledBorder(border1, "Nhập thông tin mua vé");
		pnnhapthongtin.setBorder(bdtitle1);
		JLabel emty3 = new JLabel();
		JLabel emty4 = new JLabel();
		JLabel emty5 = new JLabel();
		JLabel emty6 = new JLabel();
		JLabel emty7 = new JLabel();
		JLabel emty8 = new JLabel();
		JLabel emty9 = new JLabel();
		JLabel emty10 = new JLabel();
		JLabel emty11 = new JLabel();
		JLabel emty12 = new JLabel();
		JLabel emty13 = new JLabel();
		JLabel emty14 = new JLabel();
		JLabel emty15 = new JLabel();
		JLabel emty16 = new JLabel();
		JLabel emty17 = new JLabel();
		JLabel emty18 = new JLabel();
		JLabel emty19 = new JLabel();

		lbHoten = new JLabel("Họ Tên");
		txtHoTen = new JTextField(20);
		lbNgayDi = new JLabel("Ngày đi (dd/mm/yyyy)");
		txtNgayDi = new JTextField(20);
		JLabel lblienhe = new JLabel("Số điện thoại liên hệ");
		txtlienhe = new JTextField(20);
		checkKhuHoi = new JCheckBox("Khứ hồi");
		JLabel lbLoaiGhe = new JLabel("Loại ghế");
		boxLoaiGhe = new JComboBox(loaighe);
		boxLoaiGhe.setSelectedItem(null);
		String dtuong[] = new String[] {
				"Người lớn(11-59 tuổi)",
				"Trẻ em(6-10 tuổi)",
				"Sinh Viên(sv có thẻ sv)",
				"Người cao tuổi(trên 60 tuổi)",
		};
		lbdtuong = new JLabel("Đối tượng");
		boxdtuongMua = new JComboBox(dtuong);
		boxdtuongMua.setSelectedItem(null);
		JLabel lbNoiDi = new JLabel("Nơi đi");
		boxNoidi = new JComboBox(ga);
		boxNoidi.setSelectedItem(null);
		JLabel lbNoiDen = new JLabel("Nơi đến");
		boxNoiden = new JComboBox(ga);
		boxNoiden.setSelectedItem(null);
		pnnhapthongtin.add(lbHoten);
		pnnhapthongtin.add(txtHoTen);
		pnnhapthongtin.add(lbdtuong);
		pnnhapthongtin.add(boxdtuongMua);
		pnnhapthongtin.add(lbNgayDi);
		pnnhapthongtin.add(txtNgayDi);
		pnnhapthongtin.add(lblienhe);
		pnnhapthongtin.add(txtlienhe);
		pnnhapthongtin.add(lbLoaiGhe);
		pnnhapthongtin.add(boxLoaiGhe);
		pnnhapthongtin.add(emty3);
		pnnhapthongtin.add(emty4);
		pnnhapthongtin.add(checkKhuHoi);
		pnnhapthongtin.add(emty5);
		pnnhapthongtin.add(emty6);
		pnnhapthongtin.add(emty7);
		pnnhapthongtin.add(lbNoiDi);
		pnnhapthongtin.add(boxNoidi);
		pnnhapthongtin.add(lbNoiDen);
		pnnhapthongtin.add(boxNoiden);
		pnnhapthongtin.add(emty8);
		pnnhapthongtin.add(emty9);
		pnnhapthongtin.add(emty10);
		pnnhapthongtin.add(emty11);
		pnnhapthongtin.add(emty12);
		pnnhapthongtin.add(emty13);
		pnnhapthongtin.add(emty14);
		pnnhapthongtin.add(emty15);
		pnnhapthongtin.add(emty16);
		pnnhapthongtin.add(emty17);
		pnnhapthongtin.add(emty18);
		pnnhapthongtin.add(emty19);
		
		//thiết kế giao diện panel nut nhấn
		JPanel pnnutnhan = new JPanel();
		Border border2 = BorderFactory.createSoftBevelBorder(1);
		pnnutnhan.setBorder(border2);
		FlowLayout flow =  new FlowLayout(FlowLayout.CENTER);
		pnnutnhan.setLayout(flow);
		flow.setHgap(10);
		flow.setVgap(10);
		
		JButton nutDatVe = new JButton("Đặt vé");
		nutDatVe.addActionListener(control);
		nutDatVe.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/order.png")));
		JButton nutcapnhat = new JButton("Cập nhật");
		nutcapnhat.addActionListener(control);
		nutcapnhat.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/browser.png")));
		JButton nutluu = new JButton("Lưu cập nhật");
		nutluu.addActionListener(control);
		nutluu.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/diskette.png")));
		JButton nutxoa = new JButton("Xóa vé");
		nutxoa.addActionListener(control);
		nutxoa.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/trash.png")));
		JButton nuthuy = new JButton("Hủy bỏ mẫu");
		nuthuy.addActionListener(control);
		nuthuy.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/paper.png")));
		pnnutnhan.add(nutDatVe); 
		pnnutnhan.add(nutcapnhat);
		pnnutnhan.add(nutluu);
		pnnutnhan.add(nutxoa);
		pnnutnhan.add(nuthuy);
		
	//thiết kế giao diện panel lịch sử giao dịch
		JPanel pnlichsu = new JPanel();
		pnlichsu.setLayout(new BorderLayout());
		Border border = BorderFactory.createLineBorder(Color.RED);
		TitledBorder borderTitle = BorderFactory.createTitledBorder(border, "Lịch sử giao dịch của bạn");
		pnlichsu.setBorder(borderTitle);
		String[] columnNames = { "Tên", "CCCD", "Số điện thoại", "Đối tượng mua", "Ngày đi", "Loại ghế", "Khứ hồi",
				"Nơi đi", "Nơi đến", "Giá vé", "Mã số vé" };
		dm = new DefaultTableModel(datand, columnNames);
		tablengdung = new JTable(dm);
		tablengdung.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablengdung.getColumnModel().getColumn(0).setPreferredWidth(110);
		tablengdung.getColumnModel().getColumn(3).setPreferredWidth(130);
		tablengdung.getColumnModel().getColumn(5).setPreferredWidth(100);
		JScrollPane scrollPane = new JScrollPane(tablengdung);
		scrollPane.setPreferredSize(new Dimension(740, 250));

		pnlichsu.add(scrollPane, BorderLayout.CENTER);
		
		//add các panel phụ vào panel mua vé
		pnMuaVe.add(pnnhapthongtin, BorderLayout.NORTH);
		pnMuaVe.add(pnnutnhan, BorderLayout.CENTER);
		pnMuaVe.add(pnlichsu, BorderLayout.SOUTH);
		
		contentpane.add(pnTrangChu,"Trang chủ");
		contentpane.add(pnMuaVe,"Mua vé");
		this.setVisible(true);
		
	}

	
//giao diện quản lý	
	public void giaodienquanly() {
		this.setTitle("QUẢN LÝ BÁN VÉ TÀU_website:vetautoanquoc.com");
		dongvataidulieu();
		this.setSize(700,700);
		setLocationRelativeTo(null);

		Dimension dsvanban = new Dimension(30, 20);
		setIconImage(Toolkit.getDefaultToolkit().getImage(BanVeTauView.class.getResource("/fileanh/train.png")));
		//tạo thanh menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnCaiDat = new JMenu("Cài đặt");
		mnCaiDat.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/setting.png")));
		menuBar.add(mnCaiDat);
		JMenuItem itemdangxuat = new JMenuItem("Đăng xuất");
		itemdangxuat.addActionListener(control);
		itemdangxuat.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/logout.png")));
		mnCaiDat.add(itemdangxuat);
		JMenuItem itemthoat = new JMenuItem("Thoát");
		itemthoat.addActionListener(control);
		itemthoat.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/exit.png")));
		mnCaiDat.add(itemthoat);
		
			//tạo menu thống kê
		JMenu mnthongke  = new JMenu("Thống kê");
		mnthongke.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/statistics.png")));
		JMenuItem doanhthu = new JMenuItem("Doanh thu");
		doanhthu.addActionListener(control);
		doanhthu.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/profit.png")));	
		mnthongke.add(doanhthu);
		JMenuItem soluong = new JMenuItem("Số lượng đơn");
		soluong.addActionListener(control);
		soluong.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/clipboard.png")));
		mnthongke.add(soluong);
		menuBar.add(mnthongke);
		
			//tạo menu tìm kiếm
		JMenu mntimkiem = new JMenu("Tìm kiếm");
		mntimkiem.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/search.png")));
		JMenuItem tenngdung = new JMenuItem("Theo tên người dùng");
		tenngdung.addActionListener(control);
		tenngdung.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/name.png")));
		mntimkiem.add(tenngdung);
		JMenuItem ngaydi = new JMenuItem("Theo ngày đi");
		ngaydi.addActionListener(control);
		ngaydi.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/calendar.png")));
		mntimkiem.add(ngaydi);
		menuBar.add(mntimkiem);
		
			//tạo menu sắp xếp
		JMenu mnsapxep = new JMenu("Sắp xếp dữ liệu");
		mnsapxep.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/filter.png")));
		JMenuItem ngay = new JMenuItem("Theo thứ tự ngày");
		ngay.addActionListener(control);
		ngay.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/day.png")));
		mnsapxep.add(ngay);
		JMenuItem quangduong = new JMenuItem("Theo giá vé");
		quangduong.addActionListener(control);
		quangduong.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/distance.png")));
		mnsapxep.add(quangduong);
		menuBar.add(mnsapxep);
		
		//tạo panel chính
		content = new JPanel();
		setContentPane(content);
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		
			//panel chứa bảng
		JPanel pnbang = new JPanel();
		pnbang.setLayout(new BorderLayout(10, 10));
		Border border = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
		TitledBorder title = BorderFactory.createTitledBorder(border, "Giao dịch mua vé");
		pnbang.setBorder(title);
		String[] columnNames = { "Tên", "CCCD","Password", "Số điện thoại", "Đối tượng mua", "Ngày đi", "Loại ghế", "Khứ hồi", "Nơi đi", "Nơi đến",  "Giá vé",  "Mã số vé" };
		dmql = new DefaultTableModel(dataql, columnNames);
		JTable tablequanly = new JTable(dmql);
		tablequanly.getColumnModel().getColumn(0).setPreferredWidth(110);
		tablequanly.getColumnModel().getColumn(4).setPreferredWidth(130);
		tablequanly.getColumnModel().getColumn(6).setPreferredWidth(100);
		JScrollPane scrollPane = new JScrollPane(tablequanly);
		scrollPane.setPreferredSize(new Dimension(700, 250));
		tablequanly.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		pnbang.add(scrollPane, BorderLayout.CENTER);
		content.add(pnbang);
		
			//panel chứa các hoạt động
		pnhoatdong = new JPanel();
		card2 = new CardLayout();
		pnhoatdong.setLayout(card2);
		Border border1 = BorderFactory.createBevelBorder(1);
		TitledBorder title1 = BorderFactory.createTitledBorder(border1, "Hoạt động");
		pnhoatdong.setBorder(title1);
		
				//panel rỗng
		JPanel pnemty = new JPanel();
		JLabel lbemty = new JLabel("");
		lbemty.setPreferredSize(new Dimension(680, 200));
		pnemty.add(lbemty);
		
				//pn thống kê doanh thu
		JPanel pndoanhthu = new JPanel();
		pndoanhthu.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JLabel tongdt = new JLabel("Tổng doanh thu");
		pndoanhthu.add(tongdt);
		txtdt = new JTextField(20);
		txtdt.setEditable(false);
		pndoanhthu.add(txtdt);
		btxn = new JButton("Tính doanh thu");
		btxn.addActionListener(control);
		pndoanhthu.add(btxn);
		
				//pn thống kê số lượng vs đã bán
		JPanel pnluongve = new JPanel();
		pnluongve.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JLabel tonglv = new JLabel("Số lượng vé đã bán");
		pnluongve.add(tonglv);
		txtlv = new JTextField(20);
		txtlv.setEditable(false);
		pnluongve.add(txtlv);
		btxn2 = new JButton("Tính số vé");
		btxn2.addActionListener(control);
		pnluongve.add(btxn2);
		
				//panel tìm kiếm từ tên người
		JPanel pntimtenng = new JPanel();
		pntimtenng.setLayout(new BorderLayout(10, 10));
		JPanel pnhead = new JPanel();
		pnhead.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JLabel vanban = new JLabel("Nhập tên người muốn tra cứu");
		vanban.setMinimumSize(dsvanban);
		txtnhapten = new JTextField(30);
		JButton tracuu = new JButton("Tra cứu tên");
		tracuu.addActionListener(control);
		pnhead.add(vanban);
		pnhead.add(txtnhapten);
		pnhead.add(tracuu);
		pntimtenng.add(pnhead, BorderLayout.NORTH);
		String[][] datatkten = {};
		dften = new DefaultTableModel(datatkten, columnNames);
		bangtkten = new JTable(dften);
		bangtkten.getColumnModel().getColumn(0).setPreferredWidth(110);
		bangtkten.getColumnModel().getColumn(4).setPreferredWidth(130);
		bangtkten.getColumnModel().getColumn(6).setPreferredWidth(100);
		bangtkten.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane truot = new JScrollPane(bangtkten);
		truot.setPreferredSize(new Dimension(680, 200));
		pntimtenng.add(truot, BorderLayout.CENTER);
		
				//panel tìm kiếm từ ngày
		JPanel pntimngay = new JPanel();
		pntimngay.setLayout(new BorderLayout(10, 10));
		JPanel pnhead2 = new JPanel();
		pnhead2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JLabel vanban2 = new JLabel("Nhập ngày muốn tra cứu");
		vanban2.setMinimumSize(dsvanban);
		txtnhapngay = new JTextField(30);
		JButton tracuu2 = new JButton("Tra cứu ngày");
		tracuu2.addActionListener(control);
		pnhead2.add(vanban2);
		pnhead2.add(txtnhapngay);
		pnhead2.add(tracuu2);
		pntimngay.add(pnhead2, BorderLayout.NORTH);
		Object[][] datatkngay = {};
		dfngay = new DefaultTableModel(datatkngay, columnNames);
		bangtkngay = new JTable(dfngay);
		bangtkngay.getColumnModel().getColumn(0).setPreferredWidth(110);
		bangtkngay.getColumnModel().getColumn(4).setPreferredWidth(130);
		bangtkngay.getColumnModel().getColumn(6).setPreferredWidth(100);
		bangtkngay.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane2 = new JScrollPane(bangtkngay);
		scrollPane2.setPreferredSize(new Dimension(680, 200));
		pntimngay.add(scrollPane2, BorderLayout.CENTER);
		
				//panel bảng sắp xếp theo ngày đi
		JPanel sapxepngay = new JPanel();
		sapxepngay.setLayout(new BorderLayout(10, 10));
		Object[][] datasxngay = {};
		sxngay = new DefaultTableModel(datasxngay, columnNames);
		bangsxngay = new JTable(sxngay);
		bangsxngay.getColumnModel().getColumn(0).setPreferredWidth(110);
		bangsxngay.getColumnModel().getColumn(4).setPreferredWidth(130);
		bangsxngay.getColumnModel().getColumn(6).setPreferredWidth(100);
		JScrollPane scrollPane3 = new JScrollPane(bangsxngay);
		bangsxngay.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane3.setPreferredSize(new Dimension(680,190));
		sapxepngay.add(scrollPane3, BorderLayout.CENTER);
		
				//panel bảng sắp xếp theo giá vé
		JPanel sapxepgiave = new JPanel();
		sapxepgiave.setLayout(new BorderLayout(10, 10));
		Object[][] datasxgiave = {};
		sxgiave = new DefaultTableModel(datasxgiave, columnNames);
		bangsxgiave = new JTable(sxgiave);
		bangsxgiave.getColumnModel().getColumn(0).setPreferredWidth(110);
		bangsxgiave.getColumnModel().getColumn(4).setPreferredWidth(130);
		bangsxgiave.getColumnModel().getColumn(6).setPreferredWidth(100);
		JScrollPane scrollPane4 = new JScrollPane(bangsxgiave);
		bangsxgiave.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane4.setPreferredSize(new Dimension(680,190));
		sapxepgiave.add(scrollPane4, BorderLayout.CENTER);
		
		
		pnhoatdong.add(pnemty, "Emty");
		pnhoatdong.add(pndoanhthu, "Doanh thu");
		pnhoatdong.add(pnluongve, "Lượng vé");
		pnhoatdong.add(pntimtenng, "Tìm tên ng dùng");
		pnhoatdong.add(pntimngay, "Tìm ngày");
		pnhoatdong.add(sapxepngay, "Sắp xếp ngày");
		pnhoatdong.add(sapxepgiave, "Sắp xếp theo giá vé");
		content.add(pnhoatdong);
		
		//tạo panel ảnh 
		pnanh = new JPanel();
		card3 = new CardLayout();
		pnanh.setLayout(card3);
		Border bdanh = BorderFactory.createSoftBevelBorder(1);
		pnanh.setBorder(bdanh);
		
		//panel rỗng
		JPanel pnemty2 = new JPanel();
		JLabel emty = new JLabel("");
		emty.setPreferredSize(new Dimension(680, 200));
		//panel anh doanh thu
		JPanel anhdt = new JPanel();
		JLabel anh1 = new JLabel("");
		anh1.setPreferredSize(new Dimension(680, 200));
		anh1.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/doanhthu.png")));
		anhdt.add(anh1);
		//pnael anhe số lượng
		JPanel anhsl = new JPanel();
		JLabel anh2 = new JLabel("");
		anh2.setPreferredSize(new Dimension(680, 200));
		anh2.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/amount.png")));
		anhsl.add(anh2);
		//panale ảnh tra cứu tên người dùng
		JPanel anhngdung = new JPanel();
		JLabel anh3 = new JLabel("");
		anh3.setPreferredSize(new Dimension(680, 200));
		anh3.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/ngdung.png")));
		anhngdung.add(anh3);
		//panel ảnh tra cứu ngày đi 
		JPanel anhngaydi = new JPanel();
		JLabel anh4 = new JLabel("");
		anh4.setPreferredSize(new Dimension(680, 200));
		anh4.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/ngay.png")));
		anhngaydi.add(anh4);
		//panel sắp xếp theo ngày đi 
		JPanel anhsx = new JPanel();
		JLabel anh5 = new JLabel("");
		anh5.setPreferredSize(new Dimension(680, 200));
		anh5.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/sapxep.png")));
		anhsx.add(anh5);
		//panel ảnh sắp xếp theo giá vé
		JPanel anhgv = new JPanel();
		JLabel anh6 = new JLabel("");
		anh6.setPreferredSize(new Dimension(680, 200));
		anh6.setIcon(new ImageIcon(BanVeTauView.class.getResource("/fileanh/quangduong.png")));
		anhgv.add(anh6);
		
		pnanh.add(pnemty2, "no");
		pnanh.add(anhdt, "ảnh 1");
		pnanh.add(anhsl, "ảnh 2");
		pnanh.add(anhngdung, "ảnh 3");
		pnanh.add(anhngaydi, "ảnh 4");
		pnanh.add(anhsx, "ảnh 5");
		pnanh.add(anhgv, "ảnh 6");
		content.add(pnanh);
		
		setVisible(true);		
		
	}
	
	
    // xử lý các nút	
	public void lytrangchu() {
		this.card.show(contentpane,"Trang chủ");
	}
	public void lymuave() {
		this.card.show(contentpane,"Mua vé");
	}
	public void lydoanhthu() {
		this.card2.show(pnhoatdong, "Doanh thu");
		this.card3.show(pnanh, "ảnh 1");
	}
	public void lysoluong() {
		this.card2.show(pnhoatdong, "Lượng vé");
		this.card3.show(pnanh, "ảnh 2");
	}
	public void lytenng() {
		this.card2.show(pnhoatdong, "Tìm tên ng dùng");
		this.card3.show(pnanh, "ảnh 3");
	}
	public void lyngay() {
		this.card2.show(pnhoatdong, "Tìm ngày");
		this.card3.show(pnanh, "ảnh 4");
	}
	public void lysxngay() {
		this.card2.show(pnhoatdong, "Sắp xếp ngày");
		this.card3.show(pnanh, "ảnh 5");
	}
	public void lysxgiave() {
		this.card2.show(pnhoatdong, "Sắp xếp theo giá vé");
		this.card3.show(pnanh, "ảnh 6");
	}
	public void bthiendn() {
		this.cardxem.show(pnxemcon, "Ẩn");
		pwdangnhap.setEchoChar((char)0);
	}
	public void btandn() {
		this.cardxem.show(pnxemcon, "Xem");
		pwdangnhap.setEchoChar('\u2022');
	}
	public void bthiendky() {
		this.xempwdky.show(pnxempwdky, "Ẩndky");
		pwdky.setEchoChar((char)0);
		pwnhaplai.setEchoChar((char)0);
	}
	public void btandky() {
		this.xempwdky.show(pnxempwdky, "Xemdky");
		pwdky.setEchoChar('\u2022');
		pwnhaplai.setEchoChar('\u2022');
	}
	public void btdky() {
		tatgiaodienhientai();
		giaodiendangky();
	}
	public void tatgiaodienhientai() {
		this.setVisible(false);
	}
	public void goigddn() {
		tatgiaodienhientai();
		giaodiendangnhap();
	}
	public void donggiaodien() {
		dispose();
		this.setJMenuBar(null);
		System.gc();
	}
	public void dangxuat() {
		donggiaodien();
		this.giaodiendangnhap();
	}
	//phương thức định dạng số cccd
	public static boolean dinhdang(String cccd) {
	        String regex = "0\\d{11}";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(cccd);
	        return matcher.matches();
	}
	//pt check đăng nhập
	public void checkdangnhap() {
		try {
			char[] pass = this.pwdangnhap.getPassword();
			String stpass = new String(pass);
			if (dinhdang(CCCDdn.getText()) == true) {
				if (stpass.equals("quanghuyhehe") && CCCDdn.getText().equals("049205013081")) {
					JOptionPane.showMessageDialog(this, "Xin chào ông chủ");
					this.setVisible(false);
					giaodienquanly();
					for (User u : this.model.userList) {
						Date date = u.getNgay();
						String ngay = sdf.format(date);
						dmql.addRow(new Object[] { u.getHoTen() + "", u.getCccd() + "", u.getPassword(),
								u.getSdthlienhe() + "", u.getDoituong() + "", ngay, u.getLoaighe() + "",
								u.getKhuHoi() + "", u.getNoiDi() + "", u.getNoiDen() + "", u.getGiaVe(),
								u.getMasove() });
					}
				} else {
					int dem = 0;
					for (User us : model.userList) {
						if (stpass.equals(us.getPassword()) && CCCDdn.getText().equals(us.getCccd())) {
							dem++;
						}
					}
					if (dem >= 1) {
						JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
						this.setVisible(false);
						giaodienquanly();
						this.setVisible(false);
						GiaoDienNgDung();
						for (User u : model.userList) {
							if (CCCDdn.getText().equals(u.getCccd())) {
								Date date = u.getNgay();
								String ngay = sdf.format(date);
								dm.addRow(new Object[] { u.getHoTen() + "", u.getCccd() + "", u.getSdthlienhe() + "",
										u.getDoituong() + "", ngay, u.getLoaighe() + "", u.getKhuHoi() + "",
										u.getNoiDi() + "", u.getNoiDen() + "", u.getGiaVe(), u.getMasove() });
							}
						}
					} else {
						int luachon = JOptionPane.showConfirmDialog(this,
								"Nhập sai cccd hoặc mật khẩu\n\n Bạn có muốn tạo tài khoản", "Lỗi",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
						if (luachon == JOptionPane.OK_OPTION) {
							this.dispose();
							giaodienquanly();
							this.setVisible(false);
							this.setJMenuBar(null);
							System.gc();
							giaodiendangky();
						} else if (luachon == JOptionPane.CANCEL_OPTION) {
							JOptionPane.getRootFrame().dispose();
						}
					}
				}
			} else
				JOptionPane.showMessageDialog(this, "Nhập sai định dạng cccd");
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());

		}
	}
	//pt check đăng ký
	public void checkdangky() {
		if(dinhdang(CCCdk.getText())==true) {
			for (User us : this.model.getUserList()) {
				if(CCCdk.getText().equals(us.getCccd())) {
					JOptionPane.showMessageDialog(this, "Số căn cước công dân này\n"+
														"đã được sử dụng");
				} else if(Arrays.equals(pwdky.getPassword(), pwnhaplai.getPassword())) {
					char [] passdky = this.pwdky.getPassword();
					String stpassdky = new String(passdky);
					pwdangnhap.setText(stpassdky);
					CCCDdn.setText(CCCdk.getText());
					JOptionPane.showMessageDialog(this, "Đăng ký thành công");
					this.setVisible(false);
					giaodienquanly();
					this.setVisible(false);
					GiaoDienNgDung();
				} else {
					JOptionPane.showMessageDialog(this, "Mật khẩu nhập lại không khớp");
				}
				break;
			}
		}
	}
	//pt xóa mẫu đang tạo
	public void xoamau() {
		txtHoTen.setText("");
		txtNgayDi.setText("");
		txtlienhe.setText("");
		checkKhuHoi.setSelected(false);
		boxdtuongMua.setSelectedIndex(-1);
		boxNoidi.setSelectedIndex(-1);
		boxNoiden.setSelectedIndex(-1);
		boxLoaiGhe.setSelectedIndex(-1);
	}
	//mảng 2 chiều để lấy giá vé
	int[][] prices = {
		    {0      , 470000 , 820000 , 1020000, 1760000, 2200000},
		    {470000 , 0      , 350000 , 550000 , 1290000, 1730000},
		    {820000 , 350000 , 0      , 200000 , 940000 , 1380000},
		    {1020000, 550000 , 200000 , 0      , 740000 , 1180000},
		    {1760000, 1290000, 940000 , 740000 , 0      , 440000 },
		    {2200000, 1730000, 1380000, 1180000, 440000 , 0      }};
	//pt lấy nơi đi từ boxnoidi
	public int laynoidi() {
		int vitridi = boxNoidi.getSelectedIndex();
		return vitridi;
	}
	//pt lấy nơi đến từ boxnoiden
	public int laynoiden() {
		int vitriden = boxNoiden.getSelectedIndex();
		return vitriden;
	}
	//phương thức tính giá vé đầy đủ 
	public int laygiave(int noidi, int noiden) {
		int gvelamtron=1;
		try {
			double giasaukhitinh;
			double giamotchang = (prices[noidi][noiden])*0.95;
			giasaukhitinh = 1;
			if(prices[noidi][noiden]==2200000&&checkKhuHoi.isSelected()) {
				giamotchang*=0.85;
			}
			if(checkKhuHoi.isSelected()) {
				giasaukhitinh = giamotchang*2*0.95;
			} else giasaukhitinh = giamotchang;
			int dtuong = boxdtuongMua.getSelectedIndex();
			if(dtuong==0) {
			} 
			else if(dtuong==1) giasaukhitinh*=0.95;
			else if(dtuong==2) giasaukhitinh*=0.97;
			else if(dtuong==3) giasaukhitinh*=0.95;
			int ghe = boxLoaiGhe.getSelectedIndex();
			if(ghe==0) giasaukhitinh*=1.01;
			else if(ghe==1) giasaukhitinh*=1.2;
			gvelamtron = (int)giasaukhitinh;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gvelamtron;
	}
	//phương thức check định dạng ngày
	public boolean checkngay(String ngay) { 
		try {
			Date date = sdf.parse(ngay);
			if(date==null) {
				  return false;
			  } 
		} catch (ParseException e) {
		}
		return true;
	}
	public static boolean dinhdangsdth(String sdth) {
        String regex = "0\\d{9}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sdth);
        return matcher.matches();
	}
	//phuonwng thức tạo mã số vé bằng random
	private int taomasove() { 
        Random random = new Random();
        int randomNumber = random.nextInt(89998) + 10001;
        return randomNumber;
    }
	//phương thức đưa thông tin lên bảng ng dùng 
	public void dualenbang(User us) {
		Date date = us.getNgay();
		String ngay = sdf.format(date);
		dm.addRow(new Object[] { us.getHoTen() + "", us.getCccd() + "", us.getSdthlienhe() + "", us.getDoituong() + "",
				ngay, us.getLoaighe() + "", us.getKhuHoi() + "", us.getNoiDi() + "", us.getNoiDen() + "", us.getGiaVe(),
				us.getMasove() });
		dm.fireTableDataChanged();
		dmql.fireTableDataChanged();
	}
	//phương thức đặt mua vé
		public void datve(){ 
			boolean check = true;
			String Hoten = this.txtHoTen.getText();
			String CCCD = CCCDdn.getText();
			char[] pass = this.pwdangnhap.getPassword();
			String stpass = new String(pass);
			String lienhe = this.txtlienhe.getText();
			Date date = null;
			String stringdate = this.txtNgayDi.getText();
			try {
				  date = sdf.parse(stringdate);
				} catch (ParseException e) {
			}
			String doituong = (String)boxdtuongMua.getSelectedItem();
			String loaighe = (String)boxLoaiGhe.getSelectedItem();
			String khuhoi = null;
			if(checkKhuHoi.isSelected()) {
				khuhoi = "Có";
			} else khuhoi = "Không";
			String Noidi = (String)boxNoidi.getSelectedItem();
			String Noiden = (String)boxNoiden.getSelectedItem();
			if(Hoten==null || lienhe==null || checkngay(stringdate)==false || doituong==null || loaighe==null || Noidi==null || Noiden==null || dinhdangsdth(lienhe)==false) {
				check = false;
			}
			ImageIcon QRicon = new ImageIcon("/fileanh/QR.jpg");
			if(check==true) {
				giave = 0;
				giave = laygiave(laynoidi(),laynoiden());
				if(giave!=0) { 
					int luachon = JOptionPane.showConfirmDialog(this,
							"Họ Tên: "+Hoten+"\n"+
							"Số điện thoại liên hệ: "+lienhe+"\n"+
							"Đối tượng mua: "+doituong+"\n"+
							"Ngày đi: "+stringdate+"\n"+
							"Loại ghế: "+loaighe+"\n"+
							"Khứ hồi: "+khuhoi+"\n"+
							"Nơi đi: "+Noidi+"\n"+
							"Nơi đến: "+Noiden+"\n"+
							"Giá vé: "+ giave+"\n"
							, "Xem trước bill", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,QRicon);
					if(luachon==JOptionPane.OK_OPTION) {
						int masove = taomasove();
						JOptionPane.getRootFrame().dispose();
						JOptionPane.showMessageDialog(this,
								"Họ Tên: "+Hoten+"\n"+
								"CCCD: "+ CCCD +"\n"+
								"Số điện thoại liên hệ: "+lienhe+"\n"+
								"Đối tượng mua: "+doituong+"\n"+
								"Ngày đi: "+stringdate+"\n"+
								"Loại ghế: "+loaighe+"\n"+
								"Khứ hồi: "+khuhoi+"\n"+
								"Nơi đi: "+Noidi+"\n"+
								"Nơi đến: "+Noiden+"\n"+
								"Giá vé: "+giave+"\n"+
								"Mã số vé của bạn: "+masove
								,"Hóa đơn bán hàng",JOptionPane.INFORMATION_MESSAGE);
						User us = new User(Hoten, CCCD, stpass, lienhe, khuhoi, date, doituong, loaighe, Noidi, Noiden, giave, masove);
						this.model.chen(us);
						this.dualenbang(us);
						xoamau();
						
					}
					else if(luachon==JOptionPane.CANCEL_OPTION){
						JOptionPane.getRootFrame().dispose();
					}
				} else if(giave==0) {
					JOptionPane.showMessageDialog(this, "Vui lòng chọn lại nơi đi và nơi đến", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Nhập sai định dạng!\n"
												   +"Vui lòng nhập lại", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	//phương thức lưu vé cần cập nhật
	public void luuve() throws ParseException { 
			boolean check = true;
			String Hoten = this.txtHoTen.getText();
			String CCCD = CCCDdn.getText();
			char[] pass = this.pwdangnhap.getPassword();
			String stpass = new String(pass);
			String lienhe = this.txtlienhe.getText();
			String stringdate = this.txtNgayDi.getText();
			Date date = null;
			try {
				date = sdf.parse(stringdate);
			} catch (ParseException e) {
			}
			String doituong = (String)boxdtuongMua.getSelectedItem();
			String loaighe = (String)boxLoaiGhe.getSelectedItem();
			String khuhoi = null;
			if(checkKhuHoi.isSelected()) {
				khuhoi = "Có";
			} else khuhoi = "Không";
			String Noidi = (String)boxNoidi.getSelectedItem();
			String Noiden = (String)boxNoiden.getSelectedItem();
			if (Hoten == null || lienhe == null || checkngay(stringdate) == false || doituong == null || loaighe == null
					|| Noidi == null || Noiden == null || dinhdangsdth(lienhe) == false) {
				check = false;
			}
			ImageIcon QRicon = new ImageIcon("/fileanh/QR.jpg");
			if(check==true) {
				giave = 0;
				giave = laygiave(laynoidi(),laynoiden());
				if(giave!=0) { 
					User u = getthongtin();
					double tien = giave - u.getGiaVe();
					double tienhoantra, tienthanhtoan;
					if(tien>0) {
						tienthanhtoan = tien;
						tienhoantra = 0;
					} else if(tien<0) {
						tienthanhtoan = 0;
						tienhoantra = Math.abs(tien);
					} else {
						tienthanhtoan = 0;
						tienhoantra = 0;
					}
					
					int luachon = JOptionPane.showConfirmDialog(this,
							"Họ Tên: "+Hoten+"\n"+
							"Số điện thoại liên hệ: "+lienhe+"\n"+
							"Đối tượng mua: "+doituong+"\n"+
							"Ngày đi: "+stringdate+"\n"+
							"Loại ghế: "+loaighe+"\n"+
							"Khứ hồi: "+khuhoi+"\n"+
							"Nơi đi: "+Noidi+"\n"+
							"Nơi đến: "+Noiden+"\n"+
							"Giá vé: "+ giave+"\n"+
							"Tiền cần thanh toán: "+tienthanhtoan+"\n"+
							"Tiền hoàn trả: "+tienhoantra
							, "Xem trước bill", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,QRicon);
					if(luachon==JOptionPane.OK_OPTION) {
						int masove = taomasove();
						JOptionPane.getRootFrame().dispose();
						JOptionPane.showMessageDialog(this,
								"Họ Tên: "+Hoten+"\n"+
								"CCCD: "+ CCCD +"\n"+
								"Số điện thoại liên hệ: "+lienhe+"\n"+
								"Đối tượng mua: "+doituong+"\n"+
								"Ngày đi: "+stringdate+"\n"+
								"Loại ghế: "+loaighe+"\n"+
								"Khứ hồi: "+khuhoi+"\n"+
								"Nơi đi: "+Noidi+"\n"+
								"Nơi đến: "+Noiden+"\n"+
								"Giá vé: "+giave+"\n"+
								"Mã số vé của bạn: "+masove
								,"Hóa đơn bán hàng",JOptionPane.INFORMATION_MESSAGE);
						User us = new User(Hoten, CCCD, stpass, lienhe, khuhoi, date, doituong, loaighe, Noidi, Noiden, giave, masove);
						for (User ur : model.userList) {
							if(u.getMasove() == ur.getMasove()) {
								this.model.sua(ur, us);
								break;
								}

						}
						int soLuongDong = tablengdung.getRowCount();
						for (int i = 0; i < soLuongDong; i++) {
							int maso = (int)tablengdung.getValueAt(i, 10);
							if(maso == u.getMasove()) {
								tablengdung.setValueAt(us.getHoTen()+"", i, 0);
								tablengdung.setValueAt(us.getCccd()+"", i, 1);
								tablengdung.setValueAt(us.getSdthlienhe()+"", i, 2);
								tablengdung.setValueAt(us.getDoituong()+"", i, 3);
								Date d = us.getNgay();
								String s_ngay = sdf.format(d);
								tablengdung.setValueAt(s_ngay, i, 4);
								tablengdung.setValueAt(us.getLoaighe()+"", i, 5);
								tablengdung.setValueAt(us.getKhuHoi()+"", i, 6);
								tablengdung.setValueAt(us.getNoiDi()+"", i, 7);
								tablengdung.setValueAt(us.getNoiDen()+"", i, 8);
								tablengdung.setValueAt(us.getGiaVe(), i, 9);
								tablengdung.setValueAt(us.getMasove(), i, 10);
								dm.fireTableDataChanged();
								break;
							}
						}
						xoamau();
						
					}
					else if(luachon==JOptionPane.CANCEL_OPTION){
						JOptionPane.getRootFrame().dispose();
					}
				} else if(giave==0) {
					JOptionPane.showMessageDialog(this, "Vui lòng chọn lại nơi đi và nơi đến", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Cập nhật sai hoặc thiếu thông tin!\n"
												   +"Vui lòng xem lại", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
			
	}
	
	//phương thức lấy thông tin từ bảng ra và trả về một đối tượng của user
	public User getthongtin() throws ParseException {
		int rowselect = tablengdung.getSelectedRow();
			String ohoten = tablengdung.getValueAt(rowselect, 0)+"";
			String occcd = tablengdung.getValueAt(rowselect, 1)+"";
			String olienhe = tablengdung.getValueAt(rowselect, 2)+"";
			String okhuhoi = tablengdung.getValueAt(rowselect, 6).toString();
			String odate = tablengdung.getValueAt(rowselect, 4).toString();
			Date Ngay = sdf.parse(odate);
			String odoituong = tablengdung.getValueAt(rowselect, 3)+"";
			String oloaighe = tablengdung.getValueAt(rowselect, 5)+"";
			String onoidi = tablengdung.getValueAt(rowselect, 7)+"";
			String onoiden = tablengdung.getValueAt(rowselect, 8)+"";
			double ogiave = (double) tablengdung.getValueAt(rowselect, 9);
			int omasove = (int)tablengdung.getValueAt(rowselect, 10);
			char[] pass = this.pwdangnhap.getPassword();
			String stpass = new String(pass);
			
			User u = new User(ohoten, occcd, stpass, olienhe, okhuhoi, Ngay, odoituong, oloaighe, onoidi, onoiden, ogiave, omasove);
		return u;
	}
	//phương thức set thông tin từ getthongtin đưa lên các component
	public void setthongtin() throws ParseException {
		int chonhang = -1;
		chonhang = tablengdung.getSelectedRow();
		if (chonhang != -1) {
			User u = getthongtin();
			txtHoTen.setText(u.getHoTen());
			Date date = u.getNgay();
			String s_ngay = sdf.format(date);
			txtNgayDi.setText(s_ngay);
			boxdtuongMua.setSelectedItem(u.getDoituong());
			txtlienhe.setText(u.getSdthlienhe());
			boxLoaiGhe.setSelectedItem(u.getLoaighe());
			if (u.getKhuHoi().equals("Có")) {
				checkKhuHoi.setSelected(true);
			} else if (u.getKhuHoi().equals("Không")) {
				checkKhuHoi.setSelected(false);
			}
			boxNoidi.setSelectedItem(u.getNoiDi());
			boxNoiden.setSelectedItem(u.getNoiDen());
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng muốn cập nhật");
		}
	}
		
	//phương thức xóa vé
	public void xoave() throws ParseException {
		int rdelete = -1;
		rdelete = tablengdung.getSelectedRow();
		if (rdelete != -1) {
			int luachon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa vé này hay không", "Waring",
					JOptionPane.YES_NO_OPTION);
			if (luachon == JOptionPane.YES_OPTION) {
				User us = getthongtin();
				this.model.xoa(us);
				dm.removeRow(rdelete);
			}
		} else
			JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng muốn xóa");
	}
	//phương thức tính doanh thu
	public void tinhdoanhthu() {
		double doanhthudb = 0;
		for(User us: model.userList) {
			doanhthudb += us.getGiaVe(); 
		}
		int doanhthu =  (int)doanhthudb;
		int dong = 0, ty = 0, trieu = 0, nghin = 0, copy = doanhthu;
		if(copy>0) {
			dong = copy%1000;
			copy/=1000;
			if(copy>0) {
				nghin = copy%1000;
				copy/=1000;
				if(copy>0) {
					trieu = copy%1000;
					copy/=1000;
					if(copy>0) ty = copy;
				}
			}
		}
		txtdt.setEditable(true);
		txtdt.setText(ty+" Tỷ "+trieu+" Triệu "+nghin+" Nghìn "+dong+" Đồng.");
		txtdt.setEditable(false);

	}
	//phương thức tính tổng số vé đã bán
	public void tongsove() {
		int totalTickets = model.userList.size();
		txtdt.setEditable(true);
		txtlv.setText(String.valueOf(totalTickets)+"");
		txtdt.setEditable(false);
	}
	//phương thức tìm dữ liệu thông qua tên
	public List<User> timten(String name) {
		  List<User> result = new ArrayList<>();
		  for (User user : model.userList) {
			  String ten = user.getHoTen().toLowerCase();
			  String tennhap = name.toLowerCase();
			  if (ten.contains(tennhap)) {
		    	result.add(user); 
			  }
		  }
		  return result;
	}
	public void hienthilenbangten() {
		List<User> users = timten(txtnhapten.getText());	
		dften.setRowCount(0);
		for (User user : users) {
			Date date = user.getNgay();
			String ngay = sdf.format(date);
			dften.addRow(new Object[] { user.getHoTen() + "", user.getCccd() + "", user.getPassword(),
					user.getSdthlienhe() + "", user.getDoituong() + "", ngay, user.getLoaighe() + "",
					user.getKhuHoi() + "", user.getNoiDi() + "", user.getNoiDen() + "", user.getGiaVe(),
					user.getMasove() });
		}
	}
	//phương thức tìm dữ liệu thông qua ngày
	public List<User> timngay(Date date){
		List<User> ketqua = new ArrayList<>();
		for (User user : model.userList) {
			if(user.getNgay().compareTo(date)==0) {
				ketqua.add(user);
			}
		}
		return ketqua;
	}
	public void hienthilenbangngay() {
		try {
			Date ngay = sdf.parse(txtnhapngay.getText());
			List<User> us = timngay(ngay);
			dfngay.setRowCount(0);
			for (User user : us) {
				Date date = user.getNgay();
				String ngay2 = sdf.format(date);
				dfngay.addRow(new Object[] { user.getHoTen() + "", user.getCccd() + "", user.getPassword(),
						user.getSdthlienhe() + "", user.getDoituong() + "", ngay2, user.getLoaighe() + "",
						user.getKhuHoi() + "", user.getNoiDi() + "", user.getNoiDen() + "", user.getGiaVe(),
						user.getMasove() });
			}
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng");
		}
	}
	//phương thức sắp xếp arraylist theo thứ tự ngày đi
	public List<User> sxtheongay(List<User> userList) { 
        List<User> sapxep = new ArrayList<>(userList);
        Collections.sort(sapxep, new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return user1.getNgay().compareTo(user2.getNgay());
            }
        });

        return sapxep;
    }
	public void hienthisxtheongay() { 
		List<User> us = sxtheongay(model.userList);
		sxngay.setRowCount(0);
		for (User user : us) {
			Date date = user.getNgay();
			String ngay = sdf.format(date);
			sxngay.addRow(new Object[] { user.getHoTen() + "", user.getCccd() + "", user.getPassword(),
					user.getSdthlienhe() + "", user.getDoituong() + "", ngay, user.getLoaighe() + "",
					user.getKhuHoi() + "", user.getNoiDi() + "", user.getNoiDen() + "", user.getGiaVe(),
					user.getMasove() });
		}
	}
	//phương thức sắp xếp arraylist theo giá vé giảm dần
	public List<User> sxtheogiave(){
		 List<User> sxgiave = new ArrayList<>(model.userList);
	        Collections.sort(sxgiave, new Comparator<User>() {
	            @Override
	            public int compare(User user1, User user2) {
	                return Double.compare(user2.getGiaVe(), user1.getGiaVe());
	            }
	        });

	        return sxgiave;
	}
	public void hienthisxtheogiave() { 
		List<User> us = sxtheogiave();
		sxgiave.setRowCount(0);
		for (User user : us) {
			Date date = user.getNgay();
			String ngay = sdf.format(date);
			sxgiave.addRow(new Object[] { user.getHoTen() + "", user.getCccd() + "", user.getPassword(),
					user.getSdthlienhe() + "", user.getDoituong() + "", ngay, user.getLoaighe() + "",
					user.getKhuHoi() + "", user.getNoiDi() + "", user.getNoiDen() + "", user.getGiaVe(),
					user.getMasove() });
		}
	}
	//phương thức đưa dữ liệu từ arraylist sang database
	public void mangtodtb() {
		Connection con = null;
		try {
			con =  db.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeUpdate("TRUNCATE TABLE dulieu"); 
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO dulieu (hoten, cccd, password, sdth, doituongmua, ngaydi, loaighe, khuhoi, noidi, noiden, giave, masove) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			for (User us : model.userList) {
				ps.setString(1, us.getHoTen());
				ps.setString(2, us.getCccd());
				ps.setString(3, us.getPassword());
				ps.setString(4, us.getSdthlienhe());
				ps.setString(5, us.getDoituong());
				ps.setDate(6, new java.sql.Date(us.getNgay().getTime()));
				ps.setString(7, us.getLoaighe());
				ps.setString(8, us.getKhuHoi());
				ps.setString(9, us.getNoiDi());
				ps.setString(10, us.getNoiDen());
				ps.setInt(11, (int)us.getGiaVe());
				ps.setInt(12, us.getMasove());
			    ps.addBatch(); 
			}
			ps.executeBatch();
		    con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//phương thức đóng màn hình và đưa dữ liệu lên database
	public void dongvataidulieu() {
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					mangtodtb();
					System.exit(0);
				}
			});
		
	}
	
}
