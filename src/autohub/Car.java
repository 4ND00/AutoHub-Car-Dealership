package autohub;

public abstract class Car {
    private String merek;
    private String model;
    private int tahun;
    private double harga;
    private String warna;
    private static int jumlahMobil = 0;

    public Car(String merek, String model, int tahun, double harga, String warna) {
        this.merek = merek;
        this.model = model;
        this.tahun = tahun;
        this.harga = harga;
        this.warna = warna;
        jumlahMobil++;
    }

    public String getMerek() { return merek; }
    public String getModel() { return model; }
    public int getTahun() { return tahun; }
    public double getHarga() { return harga; }
    public String getWarna() { return warna; }

    public void setMerek(String merek) { this.merek = merek; }
    public void setModel(String model) { this.model = model; }
    public void setTahun(int tahun) { this.tahun = tahun; }
    public void setHarga(double harga) { this.harga = harga; }
    public void setWarna(String warna) { this.warna = warna; }

    public static int getJumlahMobil() { return jumlahMobil; }

    public void tampilkanInfo() {
        System.out.println("Merek  : " + merek);
        System.out.println("Model  : " + model);
        System.out.println("Tahun  : " + tahun);
        System.out.printf("Harga  : Rp%,.0f%n", harga);
        System.out.println("Warna  : " + warna);
    }

    // Setiap jenis mobil wajib menyediakan implementasi perhitungan pajak.
    public abstract void hitungPajak();
}
