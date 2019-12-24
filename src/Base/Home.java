package Base;

import java.util.*;

public class Home {
	
	public static void main(String[] args) {
		
		Double gempa, plume, kawah, result = 0.0;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("== IDENTIFIKASI STATUS GUNUNG BERAPI ==");
		System.out.print(" Skala Gempa (0.0 SR - 10.0 SR) = ");
		gempa = scan.nextDouble();
		System.out.print(" Ketinggian Plume (0 m - 8000 m) = ");
		plume = scan.nextDouble();
		System.out.print(" Suhu Kawah (0 C - 2500 C) = ");
		kawah = scan.nextDouble();
		
		//skala gempa
		Double rGempa, sGempa, tGempa;
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
		
		//ketinggian plume
		plume = plume / 1000.0;
		Double rPlume, sPlume, tPlume;
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
		
		//suhu kawah
		kawah = kawah / 100.0;
		Double bKawah, pKawah, sKawah;
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
		System.out.println();
		
		Double nilaiGempa, nilaiPlume, nilaiKawah;
		//find max
		nilaiGempa = Math.max(rGempa, Math.max(sGempa, tGempa));
		System.out.println(nilaiGempa + " " + rGempa + " " + sGempa + " " + tGempa);
		if (nilaiGempa == (rGempa/1.0)) {
			System.out.println("Nilai Gempa = Rendah");
			result += 1;
		} else if (nilaiGempa == (sGempa/1.0)) {
			System.out.println("Nilai Gempa = Sedang");
			result += 2;
		} else {
			System.out.println("Nilai Gempa = Tinggi");
			result += 3;
		}
		
		nilaiPlume = Math.max(rPlume, Math.max(sPlume, tPlume));
		System.out.println(nilaiPlume + " " + rPlume + " " + sPlume + " " + tPlume);
		if (nilaiPlume == (rPlume/1.0)) {
			System.out.println("Nilai Plume = Rendah");
			result += 1;
		} else if (nilaiPlume == (sPlume/1.0)) {
			System.out.println("Nilai Plume = Sedang");
			result += 2;
		} else {
			System.out.println("Nilai Plume = Tinggi");
			result += 3;
		}
		
		nilaiKawah = Math.max(bKawah, Math.max(pKawah, sKawah));
		System.out.println(nilaiKawah + " " + bKawah + " " + pKawah + " " + sKawah);
		if (nilaiKawah == (bKawah/1.0)) {
			System.out.println("Nilai Kawah = Biasa");
			result += 1;
		} else if (nilaiKawah == (pKawah/1.0)) {
			System.out.println("Nilai Kawah = Panas");
			result += 2;
		} else {
			System.out.println("Nilai Kawah = Sangat Panas");
			result += 3;
		}
		
		if (result == 3) {
			System.out.println("\n==LEVEL 1==\n==NORMAL==");
		} else if (result <= 5) {
			System.out.println("\n==LEVEL 2==\n==WASPADA==");
		} else if (result <= 7) {
			System.out.println("\n==LEVEL 3==\n==SIAGA==");
		} else {
			System.out.println("\n==LEVEL 4==\n==AWAS==");
		}
		
		scan.close();

	}

}
