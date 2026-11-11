package autohub;

import java.util.ArrayList;
import java.util.HashMap;

// Class induk pengelola sistem.
// - Composition: RuangPamer dan BengkelService dibuat (new) di dalam constructor.
// - Aggregation: Car dan Pelanggan dibuat di luar, lalu dimasukkan lewat method.
public class Dealer {
    private String namaDealer;

    // COMPOSITION: komponen internal, hidupnya bergantung pada Dealer.
    private RuangPamer ruangPamer;
    private BengkelService bengkel;

    // AGGREGATION: koleksi objek yang dibuat di luar Dealer.
    private ArrayList<Car> daftarMobil;
    private HashMap<String, Pelanggan> dataPelanggan;

    public Dealer(String namaDealer) {
        this.namaDealer = namaDealer;

        // Objek komponen diciptakan secara INTERNAL di sini.
        this.ruangPamer = new RuangPamer("Showroom Utama", 20);
        this.bengkel = new BengkelService("Bengkel Servis AutoHub", 4);

        this.daftarMobil = new ArrayList<>();
        this.dataPelanggan = new HashMap<>();
    }

    public void bukaDealer() {
        System.out.println("\n*** Menginisialisasi sistem " + namaDealer + " ***");
        ruangPamer.bukaRuangan();
        bengkel.bukaBengkel();
    }

    // ---------- Data mobil ----------
    public void tambahMobil(Car mobil) {
        daftarMobil.add(mobil);
    }

    public void lihatSemuaMobil() {
        if (daftarMobil.isEmpty()) {
            System.out.println("Belum ada data mobil.");
            return;
        }
        System.out.println("\n--- Daftar Mobil " + namaDealer + " ---");
        for (int i = 0; i < daftarMobil.size(); i++) {
            System.out.println("\nMobil ke-" + (i + 1));
            daftarMobil.get(i).tampilkanInfo();
            System.out.println("----------------------------");
        }
        System.out.println("Jumlah mobil dalam daftar: " + daftarMobil.size());
    }

    // Pencarian (overloading), sebelumnya ada di Main.
    public void cariMobil(String merek) throws MobilTidakDitemukanException {
        if (merek.trim().isEmpty()) {
            throw new MobilTidakDitemukanException("Merek yang dicari tidak boleh kosong.");
        }
        boolean ditemukan = false;
        for (Car mobil : daftarMobil) {
            if (mobil.getMerek().equalsIgnoreCase(merek.trim())) {
                mobil.tampilkanInfo();
                System.out.println("----------------------------");
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            throw new MobilTidakDitemukanException("Mobil merek '" + merek + "' tidak ditemukan.");
        }
    }

    public void cariMobil(double hargaMaksimal) throws MobilTidakDitemukanException {
        boolean ditemukan = false;
        for (Car mobil : daftarMobil) {
            if (mobil.getHarga() <= hargaMaksimal) {
                mobil.tampilkanInfo();
                System.out.println("----------------------------");
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            throw new MobilTidakDitemukanException("Tidak ada mobil dengan harga maksimal tersebut.");
        }
    }

    public void cariMobil(String merek, double hargaMaksimal) throws MobilTidakDitemukanException {
        boolean ditemukan = false;
        for (Car mobil : daftarMobil) {
            if (mobil.getMerek().equalsIgnoreCase(merek.trim())
                    && mobil.getHarga() <= hargaMaksimal) {
                mobil.tampilkanInfo();
                System.out.println("----------------------------");
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            throw new MobilTidakDitemukanException(
                    "Mobil merek '" + merek + "' dengan batas harga tersebut tidak ditemukan.");
        }
    }

    // ---------- Data pelanggan ----------
    public void tambahPelanggan(Pelanggan pelanggan) {
        String id = pelanggan.getId().trim();
        if (id.isEmpty() || pelanggan.getNama().trim().isEmpty()) {
            throw new IllegalArgumentException("ID dan nama pelanggan tidak boleh kosong.");
        }
        if (dataPelanggan.containsKey(id)) {
            throw new IllegalArgumentException("ID pelanggan sudah digunakan.");
        }
        dataPelanggan.put(id, pelanggan);
        System.out.println("Pelanggan berhasil ditambahkan.");
    }

    public void cariPelanggan(String id) {
        Pelanggan p = dataPelanggan.get(id.trim());
        if (p != null) {
            System.out.println("Pelanggan ditemukan.");
            System.out.println("ID   : " + p.getId());
            System.out.println("Nama : " + p.getNama());
        } else {
            System.out.println("Pelanggan dengan ID " + id + " tidak ditemukan.");
        }
    }

    public void lihatSemuaPelanggan() {
        if (dataPelanggan.isEmpty()) {
            System.out.println("Belum ada data pelanggan.");
            return;
        }
        System.out.println("\n--- Daftar Pelanggan " + namaDealer + " ---");
        for (Pelanggan p : dataPelanggan.values()) {
            System.out.println("ID: " + p.getId() + " | Nama: " + p.getNama());
        }
        System.out.println("Jumlah pelanggan: " + dataPelanggan.size());
    }

    // ---------- Getter untuk dipakai class lain ----------
    public Pelanggan getPelanggan(String id) { return dataPelanggan.get(id.trim()); }
    public Car getMobil(int index) { return daftarMobil.get(index); }
    public int getJumlahMobil() { return daftarMobil.size(); }
}
