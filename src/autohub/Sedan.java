package autohub;

public class Sedan extends Car {
    private int jumlahPintu;

    public Sedan(String merek, String model, int tahun,
                 double harga, String warna, int jumlahPintu) {

        super(merek, model, tahun, harga, warna);
        setJumlahPintu(jumlahPintu);
    }

    public int getJumlahPintu() {
        return jumlahPintu;
    }

    public void setJumlahPintu(int jumlahPintu) {
        if (jumlahPintu >= 2 && jumlahPintu <= 6) {
            this.jumlahPintu = jumlahPintu;
        } else {
            this.jumlahPintu = 4;
        }
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Jenis  : Sedan");
        super.tampilkanInfo();
        System.out.println("Pintu  : " + jumlahPintu);
    }
}