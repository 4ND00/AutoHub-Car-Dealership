package autohub;

public class Car {
    private String merek;
    private String model;
    private int tahun;
    private double harga;
    private String warna;

    private static int jumlahMobil = 0;

    public Car(String merek, String model, int tahun,
               double harga, String warna) {

        setMerek(merek);
        setModel(model);
        setTahun(tahun);
        setHarga(harga);
        setWarna(warna);

        jumlahMobil++;
    }

    // Getter dan Setter
    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        if (merek != null && !merek.trim().isEmpty()) {
            this.merek = merek;
        } else {
            this.merek = "Tidak diketahui";
        }
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model != null && !model.trim().isEmpty()) {
            this.model = model;
        } else {
            this.model = "Tidak diketahui";
        }
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        if (tahun >= 2000 && tahun <= 2100) {
            this.tahun = tahun;
        } else {
            this.tahun = 2026;
        }
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        if (harga > 0) {
            this.harga = harga;
        } else {
            this.harga = 1;
        }
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        if (warna != null && !warna.trim().isEmpty()) {
            this.warna = warna;
        } else {
            this.warna = "Tidak diketahui";
        }
    }

    // Static
    public static int getJumlahMobil() {
        return jumlahMobil;
    }

    // Method yang akan dioverride oleh subclass
    public void tampilkanInfo() {
        System.out.println("Merek  : " + merek);
        System.out.println("Model  : " + model);
        System.out.println("Tahun  : " + tahun);
        System.out.printf("Harga  : Rp%,.0f%n", harga);
        System.out.println("Warna  : " + warna);
    }
}