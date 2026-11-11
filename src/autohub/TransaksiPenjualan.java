package autohub;

// ASSOCIATION: class layanan yang hanya berinteraksi dengan objek Pelanggan dan Car
// lewat parameter method. Tidak ada yang saling memiliki.
public class TransaksiPenjualan {
    public static void prosesJual(Pelanggan pelanggan, Car mobil) {
        System.out.println("\n===== STRUK TRANSAKSI =====");
        System.out.println("Pembeli : " + pelanggan.getNama() + " (" + pelanggan.getId() + ")");
        System.out.println("Unit    : " + mobil.getMerek() + " " + mobil.getModel());
        System.out.printf("Harga   : Rp%,.0f%n", mobil.getHarga());

        mobil.hitungPajak();

        if (mobil instanceof DapatDijual) {
            ((DapatDijual) mobil).prosesPenjualan();
            System.out.println("Status  : Berhasil dicatat di sistem.");
        } else {
            System.out.println("Status  : Ditolak, unit ini belum dapat dijual.");
        }

        if (mobil instanceof DapatDites) {
            ((DapatDites) mobil).testDrive();
        }
        System.out.println("===========================");
    }
}
