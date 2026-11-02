package autohub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final ArrayList<Car> daftarMobil = new ArrayList<>();
    private static final HashMap<String, String> dataPelanggan = new HashMap<>();

    public static void main(String[] args) {
        isiDataAwal();
        isiPelangganAwal();

        int pilihan = 0;
        try {
            do {
                tampilkanMenuUtama();
                pilihan = bacaInt("Pilih menu: ");

                switch (pilihan) {
                    case 1:
                        tambahMobil();
                        break;
                    case 2:
                        tampilkanSemuaMobil();
                        break;
                    case 3:
                        menuPencarian();
                        break;
                    case 4:
                        menuProsesMobil();
                        break;
                    case 5:
                        menuPelanggan();
                        break;
                    case 6:
                        System.out.println("Terima kasih telah menggunakan AutoHub.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Pilih menu 1 sampai 6.");
                }
            } while (pilihan != 6);
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan pada aplikasi: " + e.getMessage());
        } finally {
            input.close();
            System.out.println("Blok finally dijalankan: aplikasi AutoHub selesai dan input ditutup.");
        }
    }

    private static void tampilkanMenuUtama() {
        System.out.println("\n================================");
        System.out.println("       AUTOHUB DEALER MOBIL");
        System.out.println("================================");
        System.out.println("1. Tambah Mobil");
        System.out.println("2. Tampilkan Semua Mobil");
        System.out.println("3. Cari Mobil");
        System.out.println("4. Proses Mobil");
        System.out.println("5. Kelola Data Pelanggan");
        System.out.println("6. Keluar");
        System.out.println("================================");
    }

    private static void isiDataAwal() {
        daftarMobil.add(new Sedan("Toyota", "Camry", 2024, 650000000, "Hitam", 4));
        daftarMobil.add(new Sedan("Honda", "Civic", 2023, 550000000, "Putih", 4));
        daftarMobil.add(new SUV("Toyota", "Fortuner", 2024, 600000000, "Hitam", 7));
        daftarMobil.add(new SUV("Mitsubishi", "Pajero Sport", 2023, 580000000, "Putih", 7));
        daftarMobil.add(new MPV("Toyota", "Innova Zenix", 2024, 450000000, "Silver", 7));
    }

    private static void isiPelangganAwal() {
        dataPelanggan.put("PLG001", "Andi Saputra");
        dataPelanggan.put("PLG002", "Budi Santoso");
        dataPelanggan.put("PLG003", "Citra Lestari");
    }

    private static void tambahMobil() {
        System.out.println("\n--- Tambah Mobil ---");
        System.out.println("1. Sedan\n2. SUV\n3. MPV");
        int jenis = bacaInt("Pilih jenis mobil: ");
        if (jenis < 1 || jenis > 3) {
            System.out.println("Jenis mobil tidak valid.");
            return;
        }

        String merek = bacaString("Merek: ");
        String model = bacaString("Model: ");
        int tahun = bacaInt("Tahun: ");
        double harga = bacaDouble("Harga: ");
        String warna = bacaString("Warna: ");

        if (jenis == 1) {
            int pintu = bacaInt("Jumlah pintu: ");
            daftarMobil.add(new Sedan(merek, model, tahun, harga, warna, pintu));
        } else if (jenis == 2) {
            int kapasitas = bacaInt("Kapasitas penumpang: ");
            daftarMobil.add(new SUV(merek, model, tahun, harga, warna, kapasitas));
        } else {
            int kapasitas = bacaInt("Kapasitas penumpang: ");
            daftarMobil.add(new MPV(merek, model, tahun, harga, warna, kapasitas));
        }
        System.out.println("Mobil berhasil ditambahkan.");
    }

    private static void tampilkanSemuaMobil() {
        if (daftarMobil.isEmpty()) {
            System.out.println("Belum ada data mobil.");
            return;
        }
        System.out.println("\n--- Daftar Mobil AutoHub ---");
        for (int i = 0; i < daftarMobil.size(); i++) {
            System.out.println("\nMobil ke-" + (i + 1));
            daftarMobil.get(i).tampilkanInfo();
            System.out.println("----------------------------");
        }
        System.out.println("Jumlah mobil dalam daftar: " + daftarMobil.size());
    }

    private static void menuPencarian() {
        System.out.println("\n--- Pencarian Mobil ---");
        System.out.println("1. Cari berdasarkan merek");
        System.out.println("2. Cari berdasarkan harga maksimal");
        System.out.println("3. Cari berdasarkan merek dan harga maksimal");
        int pilihan = bacaInt("Pilih pencarian: ");

        try {
            if (pilihan == 1) {
                cariMobil(bacaString("Masukkan merek: "));
            } else if (pilihan == 2) {
                cariMobil(bacaDouble("Harga maksimal: "));
            } else if (pilihan == 3) {
                String merek = bacaString("Masukkan merek: ");
                double harga = bacaDouble("Harga maksimal: ");
                cariMobil(merek, harga);
            } else {
                System.out.println("Pilihan pencarian tidak valid.");
            }
        } catch (MobilTidakDitemukanException e) {
            System.out.println("Error pencarian: " + e.getMessage());
        } finally {
            System.out.println("Pemeriksaan pencarian selesai.");
        }
    }

    // Overloading 1. throws mendeklarasikan kemungkinan exception.
    private static void cariMobil(String merek) throws MobilTidakDitemukanException {
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

    // Overloading 2.
    private static void cariMobil(double hargaMaksimal) throws MobilTidakDitemukanException {
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

    // Overloading 3.
    private static void cariMobil(String merek, double hargaMaksimal)
            throws MobilTidakDitemukanException {
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

    private static void menuProsesMobil() {
        if (daftarMobil.isEmpty()) {
            System.out.println("Belum ada mobil yang dapat diproses.");
            return;
        }
        System.out.println("\n--- Pilih Mobil untuk Diproses ---");
        for (int i = 0; i < daftarMobil.size(); i++) {
            Car mobil = daftarMobil.get(i);
            System.out.println((i + 1) + ". " + mobil.getMerek() + " " + mobil.getModel());
        }
        int pilihan = bacaInt("Pilih nomor mobil: ");
        if (pilihan < 1 || pilihan > daftarMobil.size()) {
            System.out.println("Nomor mobil tidak valid.");
            return;
        }
        prosesMobil(daftarMobil.get(pilihan - 1));
    }

    private static void prosesMobil(Car mobil) {
        System.out.println("\n--- Proses Mobil ---");
        mobil.tampilkanInfo();
        mobil.hitungPajak();

        if (mobil instanceof DapatDijual) {
            DapatDijual penjualan = (DapatDijual) mobil;
            penjualan.prosesPenjualan();
        }
        if (mobil instanceof DapatDites) {
            DapatDites testDrive = (DapatDites) mobil;
            testDrive.testDrive();
        }
    }

    private static void menuPelanggan() {
        int pilihan;
        do {
            System.out.println("\n--- MANAJEMEN DATA PELANGGAN ---");
            System.out.println("1. Tambah Pelanggan");
            System.out.println("2. Cari Pelanggan berdasarkan ID");
            System.out.println("3. Tampilkan Semua Pelanggan");
            System.out.println("4. Kembali ke Menu Utama");
            pilihan = bacaInt("Pilih menu pelanggan: ");

            switch (pilihan) {
                case 1:
                    tambahPelanggan();
                    break;
                case 2:
                    cariPelanggan();
                    break;
                case 3:
                    tampilkanSemuaPelanggan();
                    break;
                case 4:
                    System.out.println("Kembali ke menu utama.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 4);
    }

    private static void tambahPelanggan() {
        String id = bacaString("ID pelanggan: ").trim();
        String nama = bacaString("Nama pelanggan: ").trim();
        try {
            if (id.isEmpty() || nama.isEmpty()) {
                throw new IllegalArgumentException("ID dan nama pelanggan tidak boleh kosong.");
            }
            if (dataPelanggan.containsKey(id)) {
                throw new IllegalArgumentException("ID pelanggan sudah digunakan.");
            }
            dataPelanggan.put(id, nama);
            System.out.println("Pelanggan berhasil ditambahkan.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Proses penambahan pelanggan selesai.");
        }
    }

    private static void cariPelanggan() {
        String id = bacaString("Masukkan ID pelanggan: ").trim();
        if (dataPelanggan.containsKey(id)) {
            System.out.println("Pelanggan ditemukan.");
            System.out.println("ID   : " + id);
            System.out.println("Nama : " + dataPelanggan.get(id));
        } else {
            System.out.println("Pelanggan dengan ID " + id + " tidak ditemukan.");
        }
    }

    private static void tampilkanSemuaPelanggan() {
        if (dataPelanggan.isEmpty()) {
            System.out.println("Belum ada data pelanggan.");
            return;
        }
        System.out.println("\n--- Daftar Pelanggan AutoHub ---");
        for (Map.Entry<String, String> data : dataPelanggan.entrySet()) {
            System.out.println("ID: " + data.getKey() + " | Nama: " + data.getValue());
        }
        System.out.println("Jumlah pelanggan: " + dataPelanggan.size());
    }

    private static String bacaString(String pesan) {
        System.out.print(pesan);
        return input.nextLine();
    }

    // InputMismatchException ditangani agar input yang salah tidak menghentikan program.
    private static int bacaInt(String pesan) {
        while (true) {
            try {
                System.out.print(pesan);
                int nilai = input.nextInt();
                input.nextLine();
                return nilai;
            } catch (InputMismatchException e) {
                System.out.println("Masukkan bilangan bulat yang valid.");
                input.nextLine(); // membuang input yang salah
            }
        }
    }

    private static double bacaDouble(String pesan) {
        while (true) {
            try {
                System.out.print(pesan);
                double nilai = input.nextDouble();
                input.nextLine();
                return nilai;
            } catch (InputMismatchException e) {
                System.out.println("Masukkan angka yang valid.");
                input.nextLine(); // membuang input yang salah
            }
        }
    }
}
