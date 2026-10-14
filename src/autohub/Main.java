package autohub;

import java.util.Scanner;

public class Main {
    private static Scanner input = new Scanner(System.in);
    private static Car[] daftarMobil = new Car[50];
    private static int jumlahData = 0;

    public static void main(String[] args) {
        isiDataAwal();

        int pilihan;

        do {
            System.out.println("\n================================");
            System.out.println("       AUTOHUB DEALER MOBIL");
            System.out.println("================================");
            System.out.println("1. Tambah Mobil");
            System.out.println("2. Tampilkan Semua Mobil");
            System.out.println("3. Cari Mobil");
            System.out.println("4. Proses Mobil");
            System.out.println("5. Keluar");
            System.out.println("================================");

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
                    System.out.println("Terima kasih telah menggunakan AutoHub.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 5);

        input.close();
    }

    private static void isiDataAwal() {
        daftarMobil[jumlahData++] =
            new Sedan("Toyota", "Camry", 2024, 650000000, "Hitam", 4);

        daftarMobil[jumlahData++] =
            new Sedan("Honda", "Civic", 2023, 550000000, "Putih", 4);

        daftarMobil[jumlahData++] =
            new SUV("Toyota", "Fortuner", 2024, 600000000, "Hitam", 7);

        daftarMobil[jumlahData++] =
            new SUV("Mitsubishi", "Pajero Sport", 2023,
                    580000000, "Putih", 7);

        daftarMobil[jumlahData++] =
            new MPV("Toyota", "Innova Zenix", 2024,
                    450000000, "Silver", 7);
    }

    private static void tambahMobil() {
        if (jumlahData >= daftarMobil.length) {
            System.out.println("Penyimpanan mobil sudah penuh.");
            return;
        }

        System.out.println("\n--- Tambah Mobil ---");
        System.out.println("1. Sedan");
        System.out.println("2. SUV");
        System.out.println("3. MPV");

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
            daftarMobil[jumlahData++] =
                new Sedan(merek, model, tahun, harga, warna, pintu);
        } else if (jenis == 2) {
            int kapasitas = bacaInt("Kapasitas penumpang: ");
            daftarMobil[jumlahData++] =
                new SUV(merek, model, tahun, harga, warna, kapasitas);
        } else {
            int kapasitas = bacaInt("Kapasitas penumpang: ");
            daftarMobil[jumlahData++] =
                new MPV(merek, model, tahun, harga, warna, kapasitas);
        }

        System.out.println("Mobil berhasil ditambahkan.");
    }

    private static void tampilkanSemuaMobil() {
        if (jumlahData == 0) {
            System.out.println("Belum ada data mobil.");
            return;
        }

        System.out.println("\n--- Daftar Mobil AutoHub ---");

        for (int i = 0; i < jumlahData; i++) {
            System.out.println("\nMobil ke-" + (i + 1));

            // Runtime polymorphism:
            // Java memanggil tampilkanInfo() sesuai jenis objeknya.
            daftarMobil[i].tampilkanInfo();

            System.out.println("----------------------------");
        }

        System.out.println("Jumlah mobil: " + Car.getJumlahMobil());
    }

    private static void menuPencarian() {
        System.out.println("\n--- Pencarian Mobil ---");
        System.out.println("1. Cari berdasarkan merek");
        System.out.println("2. Cari berdasarkan harga maksimal");
        System.out.println("3. Cari berdasarkan merek dan harga maksimal");

        int pilihan = bacaInt("Pilih pencarian: ");

        if (pilihan == 1) {
            String merek = bacaString("Masukkan merek: ");
            cariMobil(merek);
        } else if (pilihan == 2) {
            double harga = bacaDouble("Harga maksimal: ");
            cariMobil(harga);
        } else if (pilihan == 3) {
            String merek = bacaString("Masukkan merek: ");
            double harga = bacaDouble("Harga maksimal: ");
            cariMobil(merek, harga);
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    // Method overloading 1
    private static void cariMobil(String merek) {
        boolean ditemukan = false;

        for (int i = 0; i < jumlahData; i++) {
            if (daftarMobil[i].getMerek().equalsIgnoreCase(merek)) {
                daftarMobil[i].tampilkanInfo();
                System.out.println("----------------------------");
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Mobil dengan merek tersebut tidak ditemukan.");
        }
    }

    // Method overloading 2
    private static void cariMobil(double hargaMaksimal) {
        boolean ditemukan = false;

        for (int i = 0; i < jumlahData; i++) {
            if (daftarMobil[i].getHarga() <= hargaMaksimal) {
                daftarMobil[i].tampilkanInfo();
                System.out.println("----------------------------");
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Tidak ada mobil dengan harga tersebut.");
        }
    }

    // Method overloading 3
    private static void cariMobil(String merek, double hargaMaksimal) {
        boolean ditemukan = false;

        for (int i = 0; i < jumlahData; i++) {
            if (daftarMobil[i].getMerek().equalsIgnoreCase(merek)
                    && daftarMobil[i].getHarga() <= hargaMaksimal) {
                daftarMobil[i].tampilkanInfo();
                System.out.println("----------------------------");
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Mobil yang sesuai tidak ditemukan.");
        }
    }

    private static void menuProsesMobil() {
        if (jumlahData == 0) {
            System.out.println("Belum ada mobil yang dapat diproses.");
            return;
        }

        System.out.println("\n--- Pilih Mobil untuk Diproses ---");

        for (int i = 0; i < jumlahData; i++) {
            System.out.println((i + 1) + ". "
                    + daftarMobil[i].getMerek() + " "
                    + daftarMobil[i].getModel());
        }

        int pilihan = bacaInt("Pilih nomor mobil: ");

        if (pilihan < 1 || pilihan > jumlahData) {
            System.out.println("Nomor mobil tidak valid.");
            return;
        }

        // Array menyimpan referensi Car, tetapi objek sebenarnya
        // dapat berupa Sedan, SUV, atau MPV.
        prosesMobil(daftarMobil[pilihan - 1]);
    }

    // Parameter bertipe superclass Car.
    private static void prosesMobil(Car mobil) {
        System.out.println("\n--- Proses Mobil ---");

        mobil.tampilkanInfo();
        mobil.hitungPajak();

        // instanceof memastikan objek mendukung interface
        // sebelum method interface dipanggil.
        if (mobil instanceof DapatDijual) {
            DapatDijual penjualan = (DapatDijual) mobil;
            penjualan.prosesPenjualan();
        }

        if (mobil instanceof DapatDites) {
            DapatDites testDrive = (DapatDites) mobil;
            testDrive.testDrive();
        }
    }

    private static String bacaString(String pesan) {
        System.out.print(pesan);
        return input.nextLine();
    }

    private static int bacaInt(String pesan) {
        while (true) {
            try {
                System.out.print(pesan);
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Masukkan bilangan bulat yang valid.");
            }
        }
    }

    private static double bacaDouble(String pesan) {
        while (true) {
            try {
                System.out.print(pesan);
                return Double.parseDouble(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid.");
            }
        }
    }
}