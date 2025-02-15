package main;

public class karyawan implements method {
	private String kode;
	private String nama;
	private String gender;
	private String jabatan;
	private double gaji;
	public karyawan(String kode, String nama, String gender, String jabatan, double gaji) {
		super();
		this.kode = kode;
		this.nama = nama;
		this.gender = gender;
		this.jabatan = jabatan;
		this.gaji = gaji;
	}
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getJabatan() {
		return jabatan;
	}
	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}
	public double getGaji() {
		return gaji;
	}
	public void setGaji(double gaji) {
		this.gaji = gaji;
	}
	@Override
	public double naikGaji() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
