package main;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Menu {
	Scanner scan = new Scanner(System.in);
	SecureRandom secureRandom = new SecureRandom();
	Random random = new Random();
	private ArrayList<karyawan> karyawanList = new ArrayList<>();
	
	public void displayStartMenu() {
		Integer choice;
		while(true ) {
			System.out.println("Pick your choice: ");
			System.out.println("1. Insert data karyawan");
			System.out.println("2. View data karyawan");
			System.out.println("3. Update data karyawan");
			System.out.println("4. Delete data karyawan");
			System.out.println("Your choice: ");
			choice = scan.nextInt();
			choiceHandler(choice);
		}
		
	}
	
	public void choiceHandler(Integer choice) {
		String nama, gender, jabatan, kode;
		double gaji = 0;
		switch (choice) {
			case 1:
				while(true) {
					kode = generateCode();
				    boolean exists = false;
				    
				    for(karyawan k : karyawanList) {
				        if(k.getKode().equals(kode)) {
				            exists = true;
				            break;
				        }
				    }
				    if(!exists) break;
				}
				
				while(true) {
					System.out.println("Input nama karyawa [>=3]: ");
					nama = scan.nextLine();
					if(nama.length() >= 3) break;
				}
				
				while(true) {
					System.out.println("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
					gender = scan.nextLine();
					if(gender.equals("Laki-laki") || gender.equals("Perempuan")) break;
				}
				
				while (true) {
		            System.out.print("Jabatan [Manager/Supervisor/Admin]: ");
		            jabatan = scan.nextLine();
		            if (jabatan.equals("Manager") || jabatan.equals("Supervisor") || jabatan.equals("Admin")) break;
		        }
		        
				switch(jabatan) {
	            	case "Manager":
	            		gaji = 8000000;
	            		break;
	            	case "Supervisor":
	            		gaji = 6000000;
	                	break;
	            	case "Admin":
	                	gaji = 4000000;
	                	break;
				}
		        
				karyawan newKaryawan = new karyawan(kode, nama, gender, jabatan, gaji);
		        karyawanList.add(newKaryawan);

		        applyBonus(jabatan);

		        System.out.println("Berhasil menambahkan karyawan dengan ID: " + kode);
		        System.out.println("Gaji: Rp " + gaji);
				
		        break;
		        	
			 case 2:
				 viewKaryawan();
				
				 break;
				
			 case 3:
				 viewKaryawan();
				 updateKaryawan();
				 
				 break;
			 case 4:
				 deleteKaryawan();
				 
				 break;
		}
	}
	
	private String generateCode () {
		char letter1 = (char) ('A' + random.nextInt(26));
        char letter2 = (char) ('A' + random.nextInt(26));

        int randomNumber = random.nextInt(9000) + 1000;

        return "" + letter1 + letter2 + "-" + randomNumber;
	}
	
	private void applyBonus(String jabatan) {
        int count = 0;
        double bonusPercentage = 0;

        for (karyawan k : karyawanList) {
            if (k.getJabatan().equals(jabatan)) {
                count++;
            }
        }

        switch (jabatan) {
            case "Manager":
                bonusPercentage = 0.10;
                break;
            case "Supervisor":
                bonusPercentage = 0.075;
                break;
            case "Admin":
                bonusPercentage = 0.05;
                break;
        }

        if (count >= 4) {
            int bonusRecipients = ((count - 1) / 3) * 3;

            int given = 0;
            for (karyawan k : karyawanList) {
                if (k.getJabatan().equals(jabatan) && given < bonusRecipients) {
                    double newSalary = k.getGaji() + (k.getGaji() * bonusPercentage);
                    k.setGaji(newSalary);
                    given++;
                }
            }

            System.out.println("Bonus diberikan kepada " + bonusRecipients + " karyawan dengan jabatan " + jabatan + "!");
        }
    }
	
	private void viewKaryawan() {
		int i=0;
		System.out.println("|-----|--------------------|-----------------------------|---------------|--------------|---------------|");
		 System.out.printf("| %-3s | %-18s | %-27s | %-13s | %-12s | %13s\n",
				 "No", "Kode Karyawan", "Nama Karyawan", "Jenis Kelamin", "Jabatan", "Gaji Karyawan |");
		 System.out.println("|-----|--------------------|-----------------------------|---------------|--------------|---------------|");
		 for (karyawan k: karyawanList) {
		 	i++;
		 	System.out.printf("| %-3d | %-18s | %-27s | %-13s | %-12s | %13.2f |", 
		 			i, k.getKode(), k.getNama(), k.getGender(), k.getJabatan(), k.getGaji());
		 	System.out.println();
		 }
		 System.out.println("|-----|--------------------|-----------------------------|---------------|--------------|---------------|");
	}
	
	private void updateKaryawan() {
		System.out.println("input nomor urutan karyawan yang ingin diupdate: ");
		 int index = scan.nextInt() - 1;
	        scan.nextLine();

	        if (index < 0 || index >= karyawanList.size()) {
	            System.out.println("Nomor tidak valid!");
	            return;
	        }

	        karyawan k = karyawanList.get(index);

	        while(true) {
				System.out.println("Input nama karyawa [>=3]: ");
				String nama = scan.nextLine();
				if(nama.length() >= 3) {
					k.setNama(nama);
					break;
				}
			}
			
			while(true) {
				System.out.println("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
				String gender = scan.nextLine();
				if(gender.equals("Laki-laki") || gender.equals("Perempuan")) {
					k.setGender(gender);
					break;
				}
			}
			
			while (true) {
	            System.out.print("Jabatan [Manager/Supervisor/Admin]: ");
	            String jabatan = scan.nextLine();
	            if (jabatan.equals("Manager") || jabatan.equals("Supervisor") || jabatan.equals("Admin")) {
	            	k.setJabatan(jabatan);
	            	break;
	            }
	        }

	        System.out.println("Berhasil mengupdate karyawan dengan id " + k.getKode());
	        System.out.println("ENTER to return");
	}
	
	private void deleteKaryawan() {
        viewKaryawan();

        if (karyawanList.isEmpty()) return;

        System.out.print("Input nomor urutan karyawan yang ingin dihapus: ");
        int index = scan.nextInt() - 1;
        scan.nextLine();

        if (index < 0 || index >= karyawanList.size()) {
            System.out.println("Nomor tidak valid!");
            return;
        }

        System.out.println("Karyawan dengan kode " + karyawanList.get(index).getKode() + " berhasil dihapus");
        karyawanList.remove(index);
        System.out.println("ENTER to return");
    }
}
