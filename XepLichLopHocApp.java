package lichhoc1;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class XepLichLopHocApp {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//để giao diện thay đổi theo platform (giao diện của ubuntu, win, mac,...)
		try {	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			Logger.getLogger(XepLichLopHoc.class.getName()).log(Level.SEVERE, null, e);
		}catch (InstantiationException e) {
			Logger.getLogger(XepLichLopHoc.class.getName()).log(Level.SEVERE, null, e);
		}catch (IllegalAccessException e) {
			Logger.getLogger(XepLichLopHoc.class.getName()).log(Level.SEVERE, null, e);
		}catch (UnsupportedLookAndFeelException e) {
			Logger.getLogger(XepLichLopHoc.class.getName()).log(Level.SEVERE, null, e);
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				XepLichLopHoc giaodien = new XepLichLopHoc();
				giaodien.setSize(680, 380);
				giaodien.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				giaodien.setVisible(true); //hiển thị cửa sổ //thiết lập tính nhìn thấy của thành phần
			}
		});

	}
}
