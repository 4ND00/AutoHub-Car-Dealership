package autohub;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final Dealer dealer = new Dealer("AutoHub");

    public static void main(String[] args) {
        dealer.bukaDealer();
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
                        menuTransaksi();
                        break;
                    case 7:
                        dealer.bukaDealer();
                        break;
                    case 8:
                        System.out.println("Terima kasih telah menggunakan AutoHub.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Pilih menu 1 sampai 8.");
                }
            } while (pilihan != 8);
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
        System.out.println("6. Transaksi Penjualan (Demo Association)");
        System.out.println("7. Info Dealer & Komponen (Demo Composition)");
        System.out.println("8. Keluar");
        System.out.println("================================");
    }

    private static void isiDataAwal() {
        dealer.tambahMobil(new Sedan("Toyota", "Camry", 2024, 650000000, "Hitam", 4));
        dealer.tambahMobil(new Sedan("Honda", "Civic", 2023, 550000000, "Putih", 4));
        dealer.tambahMobil(new SUV("Toyota", "Fortuner", 2024, 600000000, "Hitam", 7));
        dealer.tambahMobil(new SUV("Mitsubishi", "Pajero Sport", 2023, 580000000, "Putih", 7));
        dealer.tambahMobil(new MPV("Toyota", "Innova Zenix", 2024, 450000000, "Silver", 7));
    }

    private static void isiPelangganAwal() {
        dealer.tambahPelanggan(new Pelanggan("PLG001", "Andi Saputra"));
        dealer.tambahPelanggan(new Pelanggan("PLG002", "Budi Santoso"));
        dealer.tambahPelanggan(new Pelanggan("PLG003", "Citra Lestari"));
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
            dealer.tambahMobil(new Sedan(merek, model, tahun, harga, warna, pintu));
        } else if (jenis == 2) {
            int kapasitas = bacaInt("Kapasitas penumpang: ");
            dealer.tambahMobil(new SUV(merek, model, tahun, harga, warna, kapasitas));
        } else {
            int kapasitas = bacaInt("Kapasitas penumpang: ");
            dealer.tambahMobil(new MPV(merek, model, tahun, harga, warna, kapasitas));
        }
        System.out.println("Mobil berhasil ditambahkan.");
    }

    private static void tampilkanSemuaMobil() {
        dealer.lihatSemuaMobil();
    }

    private static void menuPencarian() {
        System.out.println("\n--- Pencarian Mobil ---");
        System.out.println("1. Cari berdasarkan merek");
        System.out.println("2. Cari berdasarkan harga maksimal");
        System.out.println("3. Cari berdasarkan merek dan harga maksimal");
        int pilihan = bacaInt("Pilih pencarian: ");

        try {
            if (pilihan == 1) {
                dealer.cariMobil(bacaString("Masukkan merek: "));
            } else if (pilihan == 2) {
                dealer.cariMobil(bacaDouble("Harga maksimal: "));
            } else if (pilihan == 3) {
                String merek = bacaString("Masukkan merek: ");
                double harga = bacaDouble("Harga maksimal: ");
                dealer.cariMobil(merek, harga);
            } else {
                System.out.println("Pilihan pencarian tidak valid.");
            }
        } catch (MobilTidakDitemukanException e) {
            System.out.println("Error pencarian: " + e.getMessage());
        } finally {
            System.out.println("Pemeriksaan pencarian selesai.");
        }
    }

    private static void menuProsesMobil() {
        if (dealer.getJumlahMobil() == 0) {
            System.out.println("Belum ada mobil yang dapat diproses.");
            return;
        }
        System.out.println("\n--- Pilih Mobil untuk Diproses ---");
        for (int i = 0; i < dealer.getJumlahMobil(); i++) {
            Car mobil = dealer.getMobil(i);
            System.out.println((i + 1) + ". " + mobil.getMerek() + " " + mobil.getModel());
        }
        int pilihan = bacaInt("Pilih nomor mobil: ");
        if (pilihan < 1 || pilihan > dealer.getJumlahMobil()) {
            System.out.println("Nomor mobil tidak valid.");
            return;
        }
        prosesMobil(dealer.getMobil(pilihan - 1));
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

    // ASSOCIATION: objek Pelanggan dan Car diteruskan ke TransaksiPenjualan lewat parameter.
    private static void menuTransaksi() {
        System.out.println("\n-- Transaksi Penjualan (Asosiasi) --");
        String idPelanggan = bacaString("Masukkan ID Pelanggan: ");

        Pelanggan pembeli = dealer.getPelanggan(idPelanggan);

        if (pembeli == null) {
            System.out.println("Gagal: Pelanggan belum terdaftar!");
        } else if (dealer.getJumlahMobil() == 0) {
            System.out.println("Gagal: Belum ada mobil yang bisa dijual.");
        } else {
            dealer.lihatSemuaMobil();
            int noMobil = bacaInt("Pilih nomor mobil yang ingin dibeli: ") - 1;

            if (noMobil >= 0 && noMobil < dealer.getJumlahMobil()) {
                Car itemJual = dealer.getMobil(noMobil);

                TransaksiPenjualan.prosesJual(pembeli, itemJual);
            } else {
                System.out.println("Nomor mobil tidak valid!");
            }
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
            dealer.tambahPelanggan(new Pelanggan(id, nama));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Proses penambahan pelanggan selesai.");
        }
    }

    private static void cariPelanggan() {
        dealer.cariPelanggan(bacaString("Masukkan ID pelanggan: "));
    }

    private static void tampilkanSemuaPelanggan() {
        dealer.lihatSemuaPelanggan();
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
