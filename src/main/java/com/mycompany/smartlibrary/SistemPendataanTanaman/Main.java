/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    public static void dataAwal() {

        daftarTanaman[jumlahTanaman] =
                new Bunga("Mawar", 2, 25000, "Merah");
        jumlahTanaman++;

        daftarTanaman[jumlahTanaman] =
                new Bunga("Melati", 1, 20000, "Putih");
        jumlahTanaman++;

        daftarTanaman[jumlahTanaman] =
                new Pohon("Mangga", 5, 75000, 4.5);
        jumlahTanaman++;

        daftarTanaman[jumlahTanaman] =
                new Pohon("Jambu", 3, 60000, 3.0);
        jumlahTanaman++;

        daftarTanaman[jumlahTanaman] =
                new Bunga("Anggrek", 2, 40000, "Ungu");
        jumlahTanaman++;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;

        dataAwal();

        System.out.println("======================================");
        System.out.println("      SISTEM PENDATAAN TANAMAN");
        System.out.println("======================================");

        while (isRunning) {

            System.out.println("\nMenu:");
            System.out.println("1. Tambah Data Tanaman");
            System.out.println("2. Tampilkan Seluruh Data");
            System.out.println("3. Cari Tanaman");
            System.out.println("4. Tampilkan Total Tanaman");
            System.out.println("5. Keluar");
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
                    System.out.print("Pilih jenis: ");

                    int jenis = scanner.nextInt();
                    scanner.nextLine();

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

                        daftarTanaman[jumlahTanaman] =
                                new Bunga(nama, umur, harga, warna);

                        jumlahTanaman++;

                        System.out.println("Data bunga berhasil ditambahkan.");

                    } else if (jenis == 2) {

                        System.out.print("Tinggi pohon: ");
                        double tinggi = scanner.nextDouble();
                        scanner.nextLine();

                        daftarTanaman[jumlahTanaman] =
                                new Pohon(nama, umur, harga, tinggi);

                        jumlahTanaman++;

                        System.out.println("Data pohon berhasil ditambahkan.");

                    } else {
                        System.out.println("Jenis tanaman tidak tersedia.");
                    }

                    break;

                case 2:

                    System.out.println("\n======================================");
                    System.out.println("       DAFTAR SELURUH TANAMAN");
                    System.out.println("======================================");

                    for (int i = 0; i < jumlahTanaman; i++) {

                        System.out.println("\nData ke-" + (i + 1));
                        System.out.println("--------------------------------------");

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
                        String cariNama = scanner.nextLine();

                        cariTanaman(cariNama);

                    } else if (pilihanCari == 2) {

                        System.out.print("Masukkan umur tanaman: ");
                        int cariUmur = scanner.nextInt();
                        scanner.nextLine();

                        cariTanaman(cariUmur);

                    } else {
                        System.out.println("Pilihan tidak tersedia.");
                    }

                    break;

                case 4:

                    System.out.println("\nTotal tanaman: "
                            + Tanaman.getTotalTanaman());

                    break;

                case 5:

                    isRunning = false;

                    System.out.println("\nProgram selesai. Terima kasih.");

                    break;

                default:

                    System.out.println("Pilihan menu tidak tersedia.");
            }
        }

        scanner.close();
    }
}