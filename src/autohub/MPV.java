package autohub;

public class MPV extends Car implements DapatDites {
    private int kapasitasPenumpang;

    public MPV(String merek, String model, int tahun,
               double harga, String warna,
               int kapasitasPenumpang) {
        super(merek, model, tahun, harga, warna);
        setKapasitasPenumpang(kapasitasPenumpang);
    }

    public int getKapasitasPenumpang() {
        return kapasitasPenumpang;
    }

    public void setKapasitasPenumpang(int kapasitasPenumpang) {
        if (kapasitasPenumpang >= 2 && kapasitasPenumpang <= 15) {
            this.kapasitasPenumpang = kapasitasPenumpang;
        } else {
            this.kapasitasPenumpang = 7;
        }
    }

    @Override
    public void hitungPajak() {
        double pajak = getHarga() * 0.02;
        System.out.printf("Pajak MPV: Rp%,.0f%n", pajak);
    }

    @Override
    public void testDrive() {
        System.out.println(
            "Test drive MPV " + getMerek() + " "
            + getModel() + " siap dilakukan."
        );
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Jenis  : MPV");
        super.tampilkanInfo();
        System.out.println(
            "Kapasitas Penumpang: " + kapasitasPenumpang + " orang"
        );
    }
}