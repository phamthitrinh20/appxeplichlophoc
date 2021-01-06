package lichhoc1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import lichhoc.LichHoc;

public class XepLichLopHoc extends JFrame implements ActionListener {
	JPanel pnMain, pnRegister, pnList, pnListAll, pnListArrange;
	JTextField txtName, txtTimeStart, txtTimeEnd;
	JLabel  lbName, lbTimeStart, lbTimeEnd, lbList, lbListArrange;
	JButton btRegister, btArrange,btUpdate,btRemove, btAgain;
	ArrayList<LichHoc> arrLH = new ArrayList<>();
	ArrayList<LichHoc> arrLHSelect = new ArrayList<>();
	JTable table, tableArrange;
	DefaultTableModel tblModel,tbaModel;  // Điều khiển dữ liệu hiển thị trên table
	boolean ck = false;
	public XepLichLopHoc() {
		super("Sắp xếp lịch học ");
		setBounds(300,200,400,400);
		// tạo đối tượng
		JPanel panel = new JPanel();
		pnMain = new JPanel();
		pnRegister = new JPanel();
		pnList = new JPanel();
		pnListAll = new JPanel();
		pnListArrange = new JPanel();
		
		lbName = new JLabel("Tên lớp:");
		lbTimeStart = new JLabel("Thời điểm bắt đầu:");
		lbTimeEnd = new JLabel("Thời điểm kết thúc:");
		lbList = new JLabel();
		lbListArrange = new JLabel();
		
		txtName = new JTextField(8);
		txtTimeStart = new JTextField(8);
		txtTimeEnd = new JTextField(8);
		
		btRegister = new JButton("Đăng kí");
		btArrange = new JButton("Sắp xếp ");
		btUpdate = new JButton("Update ");
		btRemove = new JButton("Remove ");
		btAgain = new JButton("Đăng kí lại");
		
		table = new JTable();
		tableArrange = new JTable();
		
		// set layout
		pnMain.setLayout(new BorderLayout());
		pnListAll.setLayout(new BorderLayout());
		pnList.setLayout(new BorderLayout());
		pnListArrange.setLayout(new BorderLayout());
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		pnRegister.setLayout(gridBagLayout);
		pnRegister.setBackground(Color.getHSBColor(255, 51, 0));
		TitledBorder titledBorderRegister = new TitledBorder("Đăng kí lịch học");
		pnRegister.setBorder(titledBorderRegister);
		titledBorderRegister.setTitleColor(Color.YELLOW);
		
		// list danh sách đăng kí ra kiểu JTable, sau khi click button đăng kí
		// thì list ra
		lbList.setText("Danh sách lớp đăng kí phòng học ");
		pnList.add(lbList, BorderLayout.NORTH);
		pnList.setBackground(Color.LIGHT_GRAY);
		pnList.add(table, BorderLayout.CENTER);	
//		table.setBackground(Color.getHSBColor(255, 255, 51));
		table.setBackground(Color.white);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
		//list danh sách đã sắp xếp
		lbListArrange.setText("Danh sách lớp sau khi sắp xếp");
		pnListArrange.add(lbListArrange, BorderLayout.NORTH);
		pnListArrange.setBackground(Color.LIGHT_GRAY);
		tableArrange.setBackground(Color.pink);
		pnListArrange.add(tableArrange, BorderLayout.CENTER);
		tableArrange.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableArrangeMouseClicked(evt);
            }
        });
		btAgain.setMargin(new Insets(5, 5, 5, 5));
		pnListArrange.add(btAgain, BorderLayout.SOUTH);
		btAgain.setBackground(Color.red);
		btAgain.setVisible(false);
		// tạo form đăng kí
		lbName.setForeground(Color.RED); //đặt màu cho chữ
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new Insets(5, 10, 5, 10);
		pnRegister.add(lbName, gridBagConstraints);

		txtName.setMargin(new Insets(5, 5, 5, 5));
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		pnRegister.add(txtName, gridBagConstraints);
		txtName.setBackground(Color.blue);
	
		// thời gian bắt đầu
		lbTimeStart.setForeground(Color.RED);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		pnRegister.add(lbTimeStart, gridBagConstraints);

		txtTimeStart.setMargin(new Insets(5, 5, 5, 5));
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		pnRegister.add(txtTimeStart, gridBagConstraints);
		txtTimeStart.setBackground(Color.blue);
		// thời gian kết thúc
		lbTimeEnd.setForeground(Color.RED);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		pnRegister.add(lbTimeEnd, gridBagConstraints);

		txtTimeEnd.setMargin(new Insets(5, 5, 5, 5));
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		pnRegister.add(txtTimeEnd, gridBagConstraints);
		txtTimeEnd.setBackground(Color.blue);
		/*-----------đăng kí --------------*/
		btRegister.setMargin(new Insets(8, 15, 8, 15));
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.gridwidth = 2;
		pnRegister.add(btRegister, gridBagConstraints);
		btRegister.setBackground(Color.green);
		
		//btArrange : nút sắp xếp
		btArrange.setMargin(new Insets(8, 15, 8, 15));
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.gridwidth = 2;
		pnRegister.add(btArrange, gridBagConstraints);
		btArrange.setBackground(Color.green);
		
		//btUpdate : là nút update
		btUpdate.setMargin(new Insets(8, 15, 8, 15)); 
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.gridwidth = 2;
		pnRegister.add(btUpdate, gridBagConstraints);
		btUpdate.setBackground(Color.green);
		
//		/btRemove: nút remove
		btRemove.setMargin(new Insets(8, 15, 8, 15));
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 9;
		gridBagConstraints.gridwidth = 2;
		pnRegister.add(btRemove, gridBagConstraints);
		btRemove.setBackground(Color.green);
		
		// add component vô container
		pnListAll.setLayout(new GridLayout(1,2,0,0));
		pnListAll.add(pnListArrange);
		pnListAll.add(pnList);
		pnMain.add(pnRegister, BorderLayout.WEST);
		pnMain.add(pnListAll, BorderLayout.CENTER);

		this.add(pnMain);
		
		btRegister.addActionListener(this);
		btArrange.addActionListener(this);
		btUpdate.addActionListener(this);
		btRemove.addActionListener(this);
		btAgain.addActionListener(this);
	}
	 // Cho phép kích vào các row trên table để hiển thị thông tin trong form nhập dữ liệu
	void tableMouseClicked (java.awt.event.MouseEvent evt) {
        int selected = table.getSelectedRow(); //trả về giá trị của hàng trong bảng
        if(selected>=0){
        	System.out.println(tblModel.getValueAt(selected, 0));
            String tenlop = (String) tblModel.getValueAt(selected, 0);

			int timeStart = Integer.parseInt(tblModel.getValueAt(selected, 1).toString());
			int timeEnd = Integer.parseInt(tblModel.getValueAt(selected, 2).toString());
            for(LichHoc lichHoc : arrLH){
                if(lichHoc.getNameClass().equals(tenlop)
                	 && lichHoc.getTimeStart() == timeStart
					&& lichHoc.getTimeEnd() == timeEnd
						){
                	txtName.setText(lichHoc.getNameClass());
                	txtTimeStart.setText(String.valueOf(lichHoc.getTimeStart()));
                	txtTimeEnd.setText(String.valueOf(lichHoc.getTimeEnd()));
                    return;
                }
            }
        }
	}
	 // Cho phép kích vào các row trên tableArange để hiển thị thông tin trong form nhập dữ liệu
	void tableArrangeMouseClicked (java.awt.event.MouseEvent evt) {
        int selected = tableArrange.getSelectedRow();
        if(selected>=0){
        	System.out.println();
            String tenlop = (String) tbaModel.getValueAt(selected, 0);
			int timeStart = Integer.parseInt(tbaModel.getValueAt(selected, 1).toString());
			int timeEnd = Integer.parseInt(tbaModel.getValueAt(selected, 2).toString());
			 // lặp qua các phần tử có trong danh sách dữ liệu 
            for(LichHoc lichHoc : arrLHSelect){
                if(lichHoc.getNameClass().equals(tenlop)
            		 && lichHoc.getTimeStart() == timeStart
					&& lichHoc.getTimeEnd() == timeEnd
					){
                	txtName.setText(lichHoc.getNameClass());
                	txtTimeStart.setText(String.valueOf(lichHoc.getTimeStart()));
                	txtTimeEnd.setText(String.valueOf(lichHoc.getTimeEnd()));
                    return;
                }
            }
        }
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	// Hiển thị dữ liệu từ danh sách lên table
	void filltb()
	{
		String[] headerTable = { "tên lớp học", "giờ bắt đầu", "giờ kết thúc" };
		if (arrLH.size() >= 0) {
			String[][] rowData = new String[arrLH.size()][3];
			for (int i = 0; i < arrLH.size(); i++) {
				rowData[i][0] = arrLH.get(i).getNameClass();
				rowData[i][1] = String.valueOf(arrLH.get(i).getTimeStart());
				rowData[i][2] = String.valueOf(arrLH.get(i).getTimeEnd());
			}
			tblModel = new DefaultTableModel(rowData, headerTable);
			table.setModel(new DefaultTableModel(rowData, headerTable));
		}
	}
	
	void sx()
	{
		arrLHSelect.clear();
		ArrayList<LichHoc> arr = new ArrayList<>();
		for(LichHoc lichHoc : arrLH){
			arr.add(lichHoc);
		}
		SelectionSort(arr);
		if(!arr.isEmpty())
		do {
			int min = arr.get(0).getTimeEnd();
			LichHoc lh = new LichHoc();
			lh.setNameClass(arr.get(0).getNameClass());
			lh.setTimeStart(arr.get(0).getTimeStart());
			lh.setTimeEnd(min);
			arrLHSelect.add(lh);
			int i = 0;
			do{
				if (arr.get(i).getTimeStart() < min) {
					arr.remove(i);
				}else{
					i++;
				}
			}while(i < arr.size());
			
		} while (!arr.isEmpty());	
	}
	// Hiển thị dữ liệu từ danh sách lên tableArrange
	void filltba()
	{
		sx();
		String[] headerTableSelect = { "tên lớp học", "giờ bắt đầu", "giờ kết thúc" };
		if (arrLHSelect.size() >= 0) {
			String[][] rowDataselect = new String[arrLHSelect.size()][3];
			for (int i = 0; i < arrLHSelect.size(); i++) {
				rowDataselect[i][0] = arrLHSelect.get(i).getNameClass();
				rowDataselect[i][1] = String.valueOf(arrLHSelect.get(i).getTimeStart());
				rowDataselect[i][2] = String.valueOf(arrLHSelect.get(i).getTimeEnd());
			}
			tbaModel = new DefaultTableModel(rowDataselect, headerTableSelect);
			tableArrange.setModel(new DefaultTableModel(rowDataselect, headerTableSelect));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btRegister) {
			String name = txtName.getText().toString();
			String timeStart = txtTimeStart.getText().toString();
			String timeEnd = txtTimeEnd.getText().toString();

			if ("".equals(name) || "".equals(timeStart) || "".equals(timeEnd)) {
				JOptionPane.showMessageDialog(null, "Nhập thiếu thông tin. Mời nhập lai");
			} else {
				try {
					if (Integer.parseInt(timeEnd) > Integer.parseInt(timeStart)) {
						LichHoc lh = new LichHoc();
							lh.setNameClass(name);
							lh.setTimeStart(Integer.parseInt(timeStart));
							lh.setTimeEnd(Integer.parseInt(timeEnd));
							arrLH.add(lh);
					} else {
						JOptionPane.showMessageDialog(null, "thời gian kết thúc phải lớn hơn thời gian bắt đầu");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Yêu cầu nhập đúng dữ liệu thời gian");
				}

			}

			
			 filltb();
			//xóa giá trị trong JTextfield
			txtName.setText("");
			txtTimeStart.setText("");
			txtTimeEnd.setText("");
		}
		//nếu nhấn nút sắp xếp
		if (e.getSource() == btArrange) {
			
			if (arrLH.size() != 0) {				
				ck = true;
				filltba();
				btRegister.setVisible(false);
				btArrange.setVisible(false);
				btAgain.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Chưa có lớp nào đăng kí");
			}
			
		}
		
		//nếu nhấn nút đăng kí lại
		if (e.getSource() == btAgain) {
			btRegister.setVisible(true);
			btArrange.setVisible(true);
			btAgain.setVisible(false);
			arrLH.clear();
			arrLHSelect.clear();
			filltb();
			filltba();
			ck = false;
			JOptionPane.showMessageDialog(null, "Các danh sách cũ đã bị xóa");
			
		}
		//nếu nhấn nút update
		if (e.getSource() == btUpdate) {
			String name = txtName.getText().toString();
			String timeStart = txtTimeStart.getText().toString();
			String timeEnd = txtTimeEnd.getText().toString();
			if(name.equals(""))
			{
				JOptionPane.showMessageDialog(null, "chưa có dòng nào được chọn");
			}
			else
			for(int i = 0;i < arrLH.size();i++)
			{
				if(arrLH.get(i).getNameClass().equals(name))
				{
					if ("".equals(name)  || "".equals(timeStart) || "".equals(timeEnd)) {
						JOptionPane.showMessageDialog(null, "Nhập thiếu thông tin. Mời nhập lai");
					} else {
						try {
							if (Integer.parseInt(timeEnd) > Integer.parseInt(timeStart)) {
									arrLH.get(i).setNameClass(name);
									arrLH.get(i).setTimeStart(Integer.parseInt(timeStart));
									arrLH.get(i).setTimeEnd(Integer.parseInt(timeEnd));
									filltb();
									if(ck)filltba();
									txtName.setText("");
									txtTimeStart.setText("");
									txtTimeEnd.setText("");
									JOptionPane.showMessageDialog(null, "Đối tượng đã được cập nhật");
									break;
							} else {
								JOptionPane.showMessageDialog(null, "thời gian kết thúc phải lớn hơn thời gian bắt đầu");
							}
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Yêu cầu nhập đúng dữ liệu thời gian");
						}

					}
				}
			}	
		}
		// nếu nhấn nút remove
		if (e.getSource() == btRemove) {
			String name = txtName.getText().toString();
			int timeStart = 0;
			if(!txtTimeStart.getText().toString().equals(""))
			timeStart = Integer.parseInt(txtTimeStart.getText().toString());
			int timeEnd = 0;
			if(!txtTimeEnd.getText().toString().equals(""))
			timeEnd = Integer.parseInt(txtTimeEnd.getText().toString());
			if(name.equals(""))
			{
				JOptionPane.showMessageDialog(null, "chưa có dòng nào được chọn");
			}
			else
			for(int i = 0;i < arrLH.size();i++)
			{
				if(arrLH.get(i).getNameClass().equals(name) 
						 && arrLH.get(i).getTimeStart() == timeStart
						&& arrLH.get(i).getTimeEnd() == timeEnd)
				{
					arrLH.remove(i);
					txtName.setText("");
					txtTimeStart.setText("");
					txtTimeEnd.setText("");
					filltb();
					if(ck)filltba();
					JOptionPane.showMessageDialog(null, "Xóa đói tượng thành công");
					break;
				}
			}
		}
		
	}

	//dùng thuật toán "phương pháp chọn" để sắp xếp mảng tăng dần
	public static void SelectionSort(ArrayList<LichHoc> arrLH){
		 int soLuong = arrLH.size();
		 for (int i = 0; i < soLuong - 1; i++) {
			for (int j = i + 1; j < soLuong; j++) {
				if (arrLH.get(i).getTimeEnd() > arrLH.get(j).getTimeEnd()) {
					LichHoc classSwap = arrLH.get(i);
					arrLH.set(i, arrLH.get(j));
					arrLH.set(j, classSwap);				}
			}
		}
	 }
}
