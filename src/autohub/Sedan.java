package autohub;

public class Sedan extends Car implements DapatDijual {
    private int jumlahPintu;

    public Sedan(String merek, String model, int tahun, double harga, String warna, int jumlahPintu) {
        super(merek, model, tahun, harga, warna);
        this.jumlahPintu = jumlahPintu;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Jenis  : Sedan");
        super.tampilkanInfo();
        System.out.println("Pintu  : " + jumlahPintu);
    }

    @Override
    public void hitungPajak() {
        double pajak = getHarga() * 0.02;
        System.out.printf("Estimasi pajak Sedan (2%%): Rp%,.0f%n", pajak);
    }

    @Override
    public void prosesPenjualan() {
        System.out.println("Sedan memenuhi syarat untuk diproses penjualannya.");
    }
}
