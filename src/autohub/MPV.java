package autohub;

public class MPV extends Car implements DapatDites {
    private int kapasitasPenumpang;

    public MPV(String merek, String model, int tahun, double harga, String warna, int kapasitasPenumpang) {
        super(merek, model, tahun, harga, warna);
        this.kapasitasPenumpang = kapasitasPenumpang;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Jenis  : MPV");
        super.tampilkanInfo();
        System.out.println("Kapasitas penumpang: " + kapasitasPenumpang);
    }

    @Override
    public void hitungPajak() {
        double pajak = getHarga() * 0.02;
        System.out.printf("Estimasi pajak MPV (2%%): Rp%,.0f%n", pajak);
    }

    @Override
    public void testDrive() {
        System.out.println("Test drive MPV dapat dijadwalkan.");
    }
}
