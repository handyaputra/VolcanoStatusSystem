package Main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class Home {

	private JFrame frame;
	private JTextField textSkalaGempa;
	private JTextField textKetinggianPlume;
	private JTextField textSuhuKawah;
	private JTextField textDefuzzifikasi;
	private Double[] a = new Double[28];
	private Integer[] r = new Integer[28];
	private Double[] z = new Double[28];
	private JTextField textStatusGempa;
	private JTextField textStatusPlume;
	private JTextField textStatusKawah;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSistemPendukungKeputusan = new JLabel("Sistem Pendukung Keputusan");
		lblSistemPendukungKeputusan.setBounds(62, 21, 176, 14);
		frame.getContentPane().add(lblSistemPendukungKeputusan);
		
		JLabel lblStatusGunungBerapi = new JLabel("Status Gunung Berapi");
		lblStatusGunungBerapi.setBounds(80, 36, 136, 14);
		frame.getContentPane().add(lblStatusGunungBerapi);
		
		textSkalaGempa = new JTextField();
		textSkalaGempa.setBounds(55, 82, 183, 20);
		frame.getContentPane().add(textSkalaGempa);
		textSkalaGempa.setColumns(10);
		
		JLabel lblSkalaGempa = new JLabel("Skala Gempa (0.0 - 10.0 SR)");
		lblSkalaGempa.setBounds(55, 62, 183, 14);
		frame.getContentPane().add(lblSkalaGempa);
		
		textKetinggianPlume = new JTextField();
		textKetinggianPlume.setBounds(55, 149, 183, 20);
		frame.getContentPane().add(textKetinggianPlume);
		textKetinggianPlume.setColumns(10);
		
		JLabel lblKetinggianPlume = new JLabel("Ketinggian Plume (0 - 8000 m)");
		lblKetinggianPlume.setBounds(55, 131, 183, 14);
		frame.getContentPane().add(lblKetinggianPlume);
		
		textSuhuKawah = new JTextField();
		textSuhuKawah.setBounds(55, 218, 183, 20);
		frame.getContentPane().add(textSuhuKawah);
		textSuhuKawah.setColumns(10);
		
		JLabel lblSuhuKawah = new JLabel("Suhu Kawah (0 - 2500 oC)");
		lblSuhuKawah.setBounds(55, 198, 183, 14);
		frame.getContentPane().add(lblSuhuKawah);
		
		JTextPane textStatus = new JTextPane();
		textStatus.setBounds(55, 342, 183, 46);
		frame.getContentPane().add(textStatus);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Double gempa = Double.parseDouble(textSkalaGempa.getText());
					Double rGempa, sGempa, tGempa; //menentukan nilai keanggotaan dari inputan gempa
					//rendah
					if (gempa >= 0 && gempa <= 4) {
						rGempa = 1.0;
					} else if (gempa > 5 || gempa < 0) {
						rGempa = 0.0;
					} else {
						rGempa = (5.0 - gempa) / 1.0;
					}
					//sedang
					if (gempa >= 5 && gempa <= 5.5) {
						sGempa = 1.0;
					} else if (gempa > 6 || gempa < 4) {
						sGempa = 0.0;
					} else if (gempa >= 4 && gempa <= 5) {
						sGempa = (gempa - 4.0) / 1.0;
					} else {
						sGempa = (6.0 - gempa) / 0.5;
					}
					//tinggi
					if (gempa >= 6 && gempa <= 10) {
						tGempa = 1.0;
					} else if (gempa > 10 || gempa < 5.5) {
						tGempa = 0.0;
					} else {
						tGempa = (gempa - 5.5) / 0.5;
					}
					
					Double plume = Double.parseDouble(textKetinggianPlume.getText());
					plume = plume / 1000.0;
					Double rPlume, sPlume, tPlume; //menentukan nilai keanggotaan dari inputan plume
					//rendah
					if (plume >= 0 && plume <= 2) {
						rPlume = 1.0;
					} else if (plume > 4 || plume < 0) {
						rPlume = 0.0;
					} else {
						rPlume = (4.0 - plume) / 2.0;
					}
					//sedang
					if (plume >= 4 && plume <= 5) {
						sPlume = 1.0;
					} else if (plume > 6 || plume < 2) {
						sPlume = 0.0;
					} else if (plume >= 2 && plume <= 4) {
						sPlume = (plume - 2.0) / 2.0;
					} else {
						sPlume = (6.0 - plume) / 1.0;
					}
					//tinggi
					if (plume >= 5 && plume <= 8) {
						tPlume = 1.0;
					} else if (plume > 8 || plume < 5) {
						tPlume = 0.0;
					} else {
						tPlume = (plume - 5) / 1.0;
					}		
					
					Double kawah = Double.parseDouble(textSuhuKawah.getText());
					kawah = kawah / 100.0;
					Double bKawah, pKawah, sKawah; //menentukan nilai keanggotaan dari inputan kawah
					//biasa
					if (kawah >= 0 && kawah <= 7.5) {
						bKawah = 1.0;
					} else if (kawah > 8.5 || kawah < 0) {
						bKawah = 0.0;
					} else {
						bKawah = (8.5 - kawah) / 1.0;
					}
					//panas
					if (kawah >= 8.5 && kawah <= 14) {
						pKawah = 1.0;
					} else if (kawah > 16 || kawah < 7.5) {
						pKawah = 0.0;
					} else if (kawah >= 7.5 && kawah <= 8.5) {
						pKawah = (kawah - 7.5) / 1.0;
					} else {
						pKawah = (16.0 - kawah) / 2.0;
					}
					//sangat panas
					if (kawah >= 16 && kawah <= 25) {
						sKawah = 1.0;
					} else if (kawah > 25 || kawah < 14) {
						sKawah = 0.0;
					} else {
						sKawah = (kawah - 14) / 2.0;
					}		
					
					//rule tiap kemungkinan
					a[1] = Math.min(rGempa, Math.min(rPlume, bKawah)); r[1] = 1;
					a[2] = Math.min(rGempa, Math.min(rPlume, pKawah)); r[2] = 2;
					a[3] = Math.min(rGempa, Math.min(rPlume, sKawah)); r[3] = 2;
					a[4] = Math.min(rGempa, Math.min(sPlume, bKawah)); r[4] = 2;
					a[5] = Math.min(rGempa, Math.min(sPlume, pKawah)); r[5] = 2;
					a[6] = Math.min(rGempa, Math.min(sPlume, sKawah)); r[6] = 2;
					a[7] = Math.min(rGempa, Math.min(tPlume, bKawah)); r[7] = 2;
					a[8] = Math.min(rGempa, Math.min(tPlume, pKawah)); r[8] = 2;
					a[9] = Math.min(rGempa, Math.min(tPlume, sKawah)); r[9] = 2;
					a[10] = Math.min(sGempa, Math.min(rPlume, bKawah)); r[10] = 2;
					a[11] = Math.min(sGempa, Math.min(rPlume, pKawah)); r[11] = 2;
					a[12] = Math.min(sGempa, Math.min(rPlume, sKawah)); r[12] = 3;
					a[13] = Math.min(sGempa, Math.min(sPlume, bKawah)); r[13] = 2;
					a[14] = Math.min(sGempa, Math.min(sPlume, pKawah)); r[14] = 3;
					a[15] = Math.min(sGempa, Math.min(sPlume, sKawah)); r[15] = 3;
					a[16] = Math.min(sGempa, Math.min(tPlume, bKawah)); r[16] = 2;
					a[17] = Math.min(sGempa, Math.min(tPlume, pKawah)); r[17] = 3;
					a[18] = Math.min(sGempa, Math.min(tPlume, sKawah)); r[18] = 3;
					a[19] = Math.min(tGempa, Math.min(rPlume, bKawah)); r[19] = 2;
					a[20] = Math.min(tGempa, Math.min(rPlume, pKawah)); r[20] = 3;
					a[21] = Math.min(tGempa, Math.min(rPlume, sKawah)); r[21] = 3;
					a[22] = Math.min(tGempa, Math.min(sPlume, bKawah)); r[22] = 2;
					a[23] = Math.min(tGempa, Math.min(sPlume, pKawah)); r[23] = 3;
					a[24] = Math.min(tGempa, Math.min(sPlume, sKawah)); r[24] = 4;
					a[25] = Math.min(tGempa, Math.min(tPlume, bKawah)); r[25] = 3;
					a[26] = Math.min(tGempa, Math.min(tPlume, pKawah)); r[26] = 4;
					a[27] = Math.min(tGempa, Math.min(tPlume, sKawah)); r[27] = 4;
					
					//penentuan nilai z ke i
					for (int i=1;i<=27;i++) {
						if(r[i] == 1) z[i] = 0.1 - (a[i] * 0.05);
						else if(r[i] == 2) z[i] = (0.6 - (a[i] * 0.05)) + ((a[i] * 0.05) + 0.05);
						else if(r[i] == 3) z[i] = (0.8 - (a[i] * 0.05)) + ((a[i] * 0.05) + 0.55);
						else if(r[i] == 4) z[i] = (a[i] * 0.05) + 0.75;
					}
					
					Double sigmaAZ = 0.0;
					Double sigmaA = 0.0;
					//defuzzifikasi
					for(int i=1;i<=27;i++) {
						sigmaAZ += (a[i] * z[i]);
						sigmaA += a[i];
					}
					
					Double Z = sigmaAZ / sigmaA;
					
					textDefuzzifikasi.setText(Z + "");
					
					Double nilaiGempa, nilaiPlume, nilaiKawah;
					Integer result = 0;
					//find max //mencari nilai keanggotaan terbesar tiap masukan
					nilaiGempa = Math.max(rGempa, Math.max(sGempa, tGempa));
					if (nilaiGempa == (rGempa/1.0)) {
						textStatusGempa.setText("Rendah");
						result += 1;
					} else if (nilaiGempa == (sGempa/1.0)) {
						textStatusGempa.setText("Sedang");
						result += 2;
					} else {
						textStatusGempa.setText("Tinggi");
						result += 3;
					}
					
					nilaiPlume = Math.max(rPlume, Math.max(sPlume, tPlume));
					if (nilaiPlume == (rPlume/1.0)) {
						textStatusPlume.setText("Rendah");
						result += 1;
					} else if (nilaiPlume == (sPlume/1.0)) {
						textStatusPlume.setText("Sedang");
						result += 2;
					} else {
						textStatusPlume.setText("Tinggi");
						result += 3;
					}
					
					nilaiKawah = Math.max(bKawah, Math.max(pKawah, sKawah));
					if (nilaiKawah == (bKawah/1.0)) {
						textStatusKawah.setText("Biasa");
						result += 1;
					} else if (nilaiKawah == (pKawah/1.0)) {
						textStatusKawah.setText("Panas");
						result += 2;
					} else {
						textStatusKawah.setText("Sangat Panas");
						result += 3;
					}
					
					if(result == 3) {
						textStatus.setText(" -- LEVEL 1 -- \n -- NORMAL -- ");
					} else if(result <= 5) {
						textStatus.setText(" -- LEVEL 2 -- \n -- WASPADA -- ");
					} else if(result <= 7) {
						textStatus.setText(" -- LEVEL 3 -- \n -- SIAGA -- ");
					} else {
						textStatus.setText(" -- LEVEL 4 -- \n -- AWAS -- ");
					}
					
				} catch(Exception evt) {
					System.out.println(evt.getMessage());
				}
			}
		});
		btnCalculate.setBounds(106, 270, 89, 23);
		frame.getContentPane().add(btnCalculate);
		
		textDefuzzifikasi = new JTextField();
		textDefuzzifikasi.setBounds(55, 320, 183, 20);
		frame.getContentPane().add(textDefuzzifikasi);
		textDefuzzifikasi.setColumns(10);
		
		JLabel lblDefuzzifikasi = new JLabel("Defuzzifikasi");
		lblDefuzzifikasi.setBounds(55, 304, 89, 14);
		frame.getContentPane().add(lblDefuzzifikasi);
		
		textStatusGempa = new JTextField();
		textStatusGempa.setColumns(10);
		textStatusGempa.setBounds(55, 100, 183, 20);
		frame.getContentPane().add(textStatusGempa);
		
		textStatusPlume = new JTextField();
		textStatusPlume.setColumns(10);
		textStatusPlume.setBounds(55, 167, 183, 20);
		frame.getContentPane().add(textStatusPlume);
		
		textStatusKawah = new JTextField();
		textStatusKawah.setColumns(10);
		textStatusKawah.setBounds(55, 236, 183, 20);
		frame.getContentPane().add(textStatusKawah);
		
	}
}
