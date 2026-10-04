package autohub;

import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);

    // Array untuk menyimpan objek mobil
    private static Car[] daftarMobil = new Car[50];

    // Menyimpan jumlah data yang masuk ke array
    private static int jumlahData = 0;

    public static void main(String[] args) {

        // Membuat 4 objek awal
        isiDataAwal();

        int pilihan;

        // Looping utama program
        do {
            tampilkanMenu();

            pilihan = bacaInt("Pilih menu: ");

            // Switch-case untuk menu
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
                    System.out.println(
                        "\nTerima kasih telah menggunakan AutoHub."
                    );
                    break;

                default:
                    System.out.println(
                        "Pilihan tidak tersedia. Silakan pilih 1-4."
                    );
            }

        } while (pilihan != 4);

        input.close();
    }

    // =========================================================
    // MENU UTAMA
    // =========================================================

    public static void tampilkanMenu() {

        System.out.println("\n========================================");
        System.out.println("          AUTOHUB CAR DEALERSHIP");
        System.out.println("========================================");
        System.out.println("1. Tambah Mobil");
        System.out.println("2. Tampilkan Semua Mobil");
        System.out.println("3. Cari Mobil");
        System.out.println("4. Keluar");
        System.out.println("========================================");
    }

    // =========================================================
    // DATA AWAL
    // =========================================================

    public static void isiDataAwal() {

        daftarMobil[jumlahData++] =
            new Sedan(
                "Toyota",
                "Camry",
                2025,
                800000000,
                "Hitam",
                4
            );

        daftarMobil[jumlahData++] =
            new Sedan(
                "Honda",
                "Civic",
                2026,
                650000000,
                "Putih",
                4
            );

        daftarMobil[jumlahData++] =
            new SUV(
                "Toyota",
                "Fortuner",
                2025,
                620000000,
                "Silver",
                7
            );

        daftarMobil[jumlahData++] =
            new SUV(
                "Mitsubishi",
                "Pajero Sport",
                2026,
                750000000,
                "Merah",
                7
            );
    }

    // =========================================================
    // TAMBAH MOBIL
    // =========================================================

    public static void tambahMobil() {

        if (jumlahData >= daftarMobil.length) {
            System.out.println("Data mobil sudah penuh.");
            return;
        }

        System.out.println("\n--- TAMBAH MOBIL ---");
        System.out.println("1. Sedan");
        System.out.println("2. SUV");

        int jenis = bacaInt("Pilih jenis mobil: ");

        if (jenis != 1 && jenis != 2) {
            System.out.println("Jenis mobil tidak valid.");
            return;
        }

        String merek = bacaString("Merek: ");
        String model = bacaString("Model: ");
        int tahun = bacaInt("Tahun: ");
        double harga = bacaDouble("Harga: ");
        String warna = bacaString("Warna: ");

        if (jenis == 1) {

            int jumlahPintu =
                bacaInt("Jumlah pintu: ");

            daftarMobil[jumlahData] =
                new Sedan(
                    merek,
                    model,
                    tahun,
                    harga,
                    warna,
                    jumlahPintu
                );

        } else {

            int kapasitas =
                bacaInt("Kapasitas penumpang: ");

            daftarMobil[jumlahData] =
                new SUV(
                    merek,
                    model,
                    tahun,
                    harga,
                    warna,
                    kapasitas
                );
        }

        jumlahData++;

        System.out.println("Mobil berhasil ditambahkan.");
    }

    // =========================================================
    // TAMPILKAN SEMUA MOBIL
    // =========================================================

    public static void tampilkanSemuaMobil() {

        System.out.println("\n=========== DAFTAR MOBIL ===========");

        if (jumlahData == 0) {
            System.out.println("Belum ada data mobil.");
            return;
        }

        for (int i = 0; i < jumlahData; i++) {

            System.out.println("\nData ke-" + (i + 1));
            System.out.println("----------------------------------------");

            // Memanggil tampilkanInfo()
            daftarMobil[i].tampilkanInfo();
        }

        System.out.println("----------------------------------------");

        System.out.println(
            "Total objek mobil dibuat: "
            + Car.getJumlahMobil()
        );
    }

    // =========================================================
    // MENU PENCARIAN
    // =========================================================

    public static void menuPencarian() {

        System.out.println("\n=========== CARI MOBIL ===========");
        System.out.println("1. Cari berdasarkan merek");
        System.out.println("2. Cari berdasarkan harga maksimal");
        System.out.println(
            "3. Cari berdasarkan merek dan harga maksimal"
        );

        int pilihan =
            bacaInt("Pilih pencarian: ");

        if (pilihan == 1) {

            String merek =
                bacaString("Masukkan merek: ");

            cariMobil(merek);

        } else if (pilihan == 2) {

            double harga =
                bacaDouble("Masukkan harga maksimal: ");

            cariMobil(harga);

        } else if (pilihan == 3) {

            String merek =
                bacaString("Masukkan merek: ");

            double harga =
                bacaDouble("Masukkan harga maksimal: ");

            cariMobil(merek, harga);

        } else {

            System.out.println(
                "Pilihan pencarian tidak valid."
            );
        }
    }

    // =========================================================
    // METHOD OVERLOADING
    // =========================================================

    // Overload 1
    public static void cariMobil(String merek) {

        boolean ditemukan = false;

        System.out.println(
            "\nHasil pencarian merek: " + merek
        );

        for (int i = 0; i < jumlahData; i++) {

            if (daftarMobil[i]
                    .getMerek()
                    .equalsIgnoreCase(merek)) {

                daftarMobil[i].tampilkanInfo();

                System.out.println(
                    "----------------------------------------"
                );

                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println(
                "Mobil dengan merek tersebut tidak ditemukan."
            );
        }
    }

    // Overload 2
    public static void cariMobil(double hargaMaksimal) {

        boolean ditemukan = false;

        System.out.printf(
            "\nHasil pencarian harga <= Rp%,.0f%n",
            hargaMaksimal
        );

        for (int i = 0; i < jumlahData; i++) {

            if (daftarMobil[i].getHarga()
                    <= hargaMaksimal) {

                daftarMobil[i].tampilkanInfo();

                System.out.println(
                    "----------------------------------------"
                );

                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println(
                "Tidak ada mobil yang sesuai."
            );
        }
    }

    // Overload 3
    public static void cariMobil(
            String merek,
            double hargaMaksimal) {

        boolean ditemukan = false;

        System.out.printf(
            "\nHasil pencarian %s dengan harga <= Rp%,.0f%n",
            merek,
            hargaMaksimal
        );

        for (int i = 0; i < jumlahData; i++) {

            if (daftarMobil[i]
                    .getMerek()
                    .equalsIgnoreCase(merek)
                    && daftarMobil[i].getHarga()
                    <= hargaMaksimal) {

                daftarMobil[i].tampilkanInfo();

                System.out.println(
                    "----------------------------------------"
                );

                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println(
                "Tidak ada mobil yang sesuai."
            );
        }
    }

    // =========================================================
    // INPUT STRING
    // =========================================================

    public static String bacaString(String pesan) {

        System.out.print(pesan);

        return input.nextLine();
    }

    // =========================================================
    // INPUT INTEGER
    // =========================================================

    public static int bacaInt(String pesan) {

        while (true) {

            try {

                System.out.print(pesan);

                int nilai =
                    Integer.parseInt(input.nextLine());

                return nilai;

            } catch (NumberFormatException e) {

                System.out.println(
                    "Input harus berupa angka bulat."
                );
            }
        }
    }

    // =========================================================
    // INPUT DOUBLE
    // =========================================================

    public static double bacaDouble(String pesan) {

        while (true) {

            try {

                System.out.print(pesan);

                double nilai =
                    Double.parseDouble(input.nextLine());

                return nilai;

            } catch (NumberFormatException e) {

                System.out.println(
                    "Input harus berupa angka."
                );
            }
        }
    }
}