
package com.mycompany.smartlibrary.SistemPendataanTanaman;

import java.util.Scanner;

public class Main {

    static Tanaman[] daftarTanaman = new Tanaman[20];
    static int jumlahTanaman = 0;

    public static void cariTanaman(String nama) {
        boolean ditemukan = false;

        for (int i = 0; i < jumlahTanaman; i++) {
            if (daftarTanaman[i].getNama().equalsIgnoreCase(nama)) {
                System.out.println("\nTanaman ditemukan:");
                daftarTanaman[i].tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Tanaman tidak ditemukan.");
        }
    }

    public static void cariTanaman(int umur) {
        boolean ditemukan = false;

        for (int i = 0; i < jumlahTanaman; i++) {
            if (daftarTanaman[i].getUmur() == umur) {
                System.out.println("\nTanaman dengan umur " + umur + " tahun:");
                daftarTanaman[i].tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Tidak ada tanaman dengan umur tersebut.");
        }
    }

    public static void prosesPerawatan(Tanaman tanaman) {
        System.out.println("\nProses perawatan " + tanaman.getNama() + ":");
        tanaman.perawatan();
    }

    public static void tampilkanKemampuan(Tanaman tanaman) {
        System.out.println("\nKemampuan khusus " + tanaman.getNama() + ":");

        if (tanaman instanceof DapatDisiram) {
            DapatDisiram penyiram = (DapatDisiram) tanaman;
            penyiram.jadwalPenyiraman();
        }

        if (tanaman instanceof DapatDipupuk) {
            DapatDipupuk pemupuk = (DapatDipupuk) tanaman;
            pemupuk.pemberianPupuk();
        }
    }

    public static void dataAwal() {
        daftarTanaman[jumlahTanaman++] =
                new Bunga("Mawar", 2, 25000, "Merah");

        daftarTanaman[jumlahTanaman++] =
                new Bunga("Melati", 1, 20000, "Putih");

        daftarTanaman[jumlahTanaman++] =
                new Pohon("Mangga", 5, 75000, 4.5);

        daftarTanaman[jumlahTanaman++] =
                new Pohon("Jambu", 3, 60000, 3.0);

        daftarTanaman[jumlahTanaman++] =
                new TanamanHias("Lidah Mertua", 2, 35000, "Ruang Tamu");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean berjalan = true;

        dataAwal();

        System.out.println("======================================");
        System.out.println("      SISTEM PENDATAAN TANAMAN");
        System.out.println("======================================");

        while (berjalan) {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Data Tanaman");
            System.out.println("2. Tampilkan Seluruh Data");
            System.out.println("3. Cari Tanaman");
            System.out.println("4. Simulasi Perawatan");
            System.out.println("5. Kemampuan Khusus Tanaman");
            System.out.println("6. Tampilkan Total Tanaman");
            System.out.println("7. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    if (jumlahTanaman >= daftarTanaman.length) {
                        System.out.println("Data tanaman sudah penuh.");
                        break;
                    }

                    System.out.println("\nJenis Tanaman:");
                    System.out.println("1. Bunga");
                    System.out.println("2. Pohon");
                    System.out.println("3. Tanaman Hias");
                    System.out.print("Pilih jenis: ");

                    int jenis = scanner.nextInt();
                    scanner.nextLine();

                    if (jenis < 1 || jenis > 3) {
                        System.out.println("Jenis tanaman tidak tersedia.");
                        break;
                    }

                    System.out.print("Nama tanaman: ");
                    String nama = scanner.nextLine();

                    System.out.print("Umur tanaman: ");
                    int umur = scanner.nextInt();

                    System.out.print("Harga tanaman: ");
                    double harga = scanner.nextDouble();
                    scanner.nextLine();

                    if (jenis == 1) {
                        System.out.print("Warna bunga: ");
                        String warna = scanner.nextLine();

                        daftarTanaman[jumlahTanaman++] =
                                new Bunga(nama, umur, harga, warna);

                    } else if (jenis == 2) {
                        System.out.print("Tinggi pohon: ");
                        double tinggi = scanner.nextDouble();
                        scanner.nextLine();

                        daftarTanaman[jumlahTanaman++] =
                                new Pohon(nama, umur, harga, tinggi);

                    } else {
                        System.out.print("Lokasi penempatan: ");
                        String lokasi = scanner.nextLine();

                        daftarTanaman[jumlahTanaman++] =
                                new TanamanHias(nama, umur, harga, lokasi);
                    }

                    System.out.println("Data berhasil ditambahkan.");
                    break;

                case 2:
                    System.out.println("\n===== DAFTAR SELURUH TANAMAN =====");

                    for (int i = 0; i < jumlahTanaman; i++) {
                        System.out.println("\nData ke-" + (i + 1));
                        daftarTanaman[i].tampilkanInfo();
                        System.out.println("--------------------------------------");
                    }
                    break;

                case 3:
                    System.out.println("\nCari berdasarkan:");
                    System.out.println("1. Nama");
                    System.out.println("2. Umur");
                    System.out.print("Pilih: ");

                    int pilihanCari = scanner.nextInt();
                    scanner.nextLine();

                    if (pilihanCari == 1) {
                        System.out.print("Masukkan nama tanaman: ");
                        cariTanaman(scanner.nextLine());
                    } else if (pilihanCari == 2) {
                        System.out.print("Masukkan umur tanaman: ");
                        cariTanaman(scanner.nextInt());
                        scanner.nextLine();
                    } else {
                        System.out.println("Pilihan tidak tersedia.");
                    }
                    break;

                case 4:
                    for (int i = 0; i < jumlahTanaman; i++) {
                        System.out.println((i + 1) + ". "
                                + daftarTanaman[i].getNama());
                    }

                    System.out.print("Pilih nomor tanaman: ");
                    int nomorRawat = scanner.nextInt();

                    if (nomorRawat >= 1 && nomorRawat <= jumlahTanaman) {
                        prosesPerawatan(daftarTanaman[nomorRawat - 1]);
                    } else {
                        System.out.println("Nomor tidak tersedia.");
                    }
                    break;

                case 5:
                    for (int i = 0; i < jumlahTanaman; i++) {
                        System.out.println((i + 1) + ". "
                                + daftarTanaman[i].getNama());
                    }

                    System.out.print("Pilih nomor tanaman: ");
                    int nomorKemampuan = scanner.nextInt();

                    if (nomorKemampuan >= 1
                            && nomorKemampuan <= jumlahTanaman) {
                        tampilkanKemampuan(
                                daftarTanaman[nomorKemampuan - 1]);
                    } else {
                        System.out.println("Nomor tidak tersedia.");
                    }
                    break;

                case 6:
                    System.out.println("Total tanaman: "
                            + Tanaman.getTotalTanaman());
                    break;

                case 7:
                    berjalan = false;
                    System.out.println("Program selesai. Terima kasih.");
                    break;

                default:
                    System.out.println("Pilihan menu tidak tersedia.");
            }
        }

        scanner.close();
    }
}
