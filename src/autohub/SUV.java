package autohub;

public class SUV extends Car implements DapatDijual, DapatDites {
    private int kapasitasPenumpang;

    public SUV(String merek, String model, int tahun, double harga, String warna, int kapasitasPenumpang) {
        super(merek, model, tahun, harga, warna);
        this.kapasitasPenumpang = kapasitasPenumpang;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Jenis  : SUV");
        super.tampilkanInfo();
        System.out.println("Kapasitas penumpang: " + kapasitasPenumpang);
    }

    @Override
    public void hitungPajak() {
        double pajak = getHarga() * 0.025;
        System.out.printf("Estimasi pajak SUV (2,5%%): Rp%,.0f%n", pajak);
    }

    @Override
    public void prosesPenjualan() {
        System.out.println("SUV memenuhi syarat untuk diproses penjualannya.");
    }

    @Override
    public void testDrive() {
        System.out.println("Test drive SUV dapat dijadwalkan.");
    }
}
