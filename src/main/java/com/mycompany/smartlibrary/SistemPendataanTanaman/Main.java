package com.mycompany.smartlibrary.SistemPendataanTanaman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    static ArrayList<Tanaman> daftarTanaman = new ArrayList<>();

    static HashMap<String, String> dataAnggota = new HashMap<>();

    public static void main(String[] args) {

        dataAwal();

        boolean berjalan = true;

        while (berjalan) {
            try {
                System.out.println("\n================================");
                System.out.println(" SISTEM PENDATAAN TANAMAN");
                System.out.println("================================");
                System.out.println("1. Tambah Tanaman");
                System.out.println("2. Tampilkan Semua Tanaman");
                System.out.println("3. Cari Tanaman");
                System.out.println("4. Simulasi Perawatan Tanaman");
                System.out.println("5. Kemampuan Khusus Tanaman");
                System.out.println("6. Jumlah Tanaman");
                System.out.println("7. Kelola Data Anggota");
                System.out.println("8. Keluar");
                System.out.print("Pilih menu: ");

                int pilihan = input.nextInt();
                input.nextLine();

                switch (pilihan) {
                    case 1:
                        tambahTanaman();
                        break;

                    case 2:
                        tampilkanSemua();
                        break;

                    case 3:
                        cariMenu();
                        break;

                    case 4:
                        simulasiPerawatan();
                        break;

                    case 5:
                        kemampuanKhusus();
                        break;

                    case 6:
                        System.out.println("Jumlah tanaman: "
                                + daftarTanaman.size());
                        System.out.println("Jumlah objek tanaman yang "
                                + "pernah dibuat: "
                                + Tanaman.getTotalTanaman());
                        break;

                    case 7:
                        menuAnggota();
                        break;

                    case 8:
                        berjalan = false;
                        System.out.println("Program selesai. Terima kasih.");
                        break;

                    default:
                        throw new TanamanException(
                                "Pilihan menu harus antara 1 sampai 8.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Kesalahan: masukkan pilihan berupa angka.");
                input.nextLine();

            } catch (TanamanException e) {
                System.out.println("Pesan Exception: " + e.getMessage());

            } finally {
                System.out.println(
                        "Finally: proses menu telah diperiksa.");
            }
        }

        input.close();
    }

    static void dataAwal() {
        daftarTanaman.add(new Bunga("Mawar", 2, 25000));
        daftarTanaman.add(new Bunga("Melati", 1, 15000));
        daftarTanaman.add(new Pohon("Mangga", 5, 150000));
        daftarTanaman.add(new Pohon("Jambu", 3, 100000));
        daftarTanaman.add(new TanamanHias("Lidah Mertua", 2, 45000));

        dataAnggota.put("2517051001", "Melan Panesia Diwa");
        dataAnggota.put("2517051002", "Ajeng");
        dataAnggota.put("2517051016", "Nia Kurniawati");
    }

    static void validasiTeks(String teks, String keterangan)
            throws TanamanException {

        if (teks == null || teks.trim().isEmpty()) {
            throw new TanamanException(
                    keterangan + " tidak boleh kosong.");
        }
    }

    static void tambahTanaman() throws TanamanException {
        System.out.println("\n--- Tambah Tanaman ---");

        System.out.print("Nama tanaman: ");
        String nama = input.nextLine();
        validasiTeks(nama, "Nama tanaman");

        System.out.print("Umur tanaman (tahun): ");
        int umur = input.nextInt();

        System.out.print("Harga tanaman: ");
        double harga = input.nextDouble();
        input.nextLine();

        if (umur < 0 || harga < 0) {
            throw new TanamanException(
                    "Umur dan harga tidak boleh bernilai negatif.");
        }

        System.out.println("Pilih jenis tanaman:");
        System.out.println("1. Bunga");
        System.out.println("2. Pohon");
        System.out.println("3. Tanaman Hias");
        System.out.print("Pilihan: ");

        int jenis = input.nextInt();
        input.nextLine();

        Tanaman tanamanBaru;

        switch (jenis) {
            case 1:
                tanamanBaru = new Bunga(nama, umur, harga);
                break;

            case 2:
                tanamanBaru = new Pohon(nama, umur, harga);
                break;

            case 3:
                tanamanBaru = new TanamanHias(nama, umur, harga);
                break;

            default:
                throw new TanamanException(
                        "Jenis tanaman tidak tersedia.");
        }

        daftarTanaman.add(tanamanBaru);
        System.out.println("Tanaman berhasil ditambahkan.");
    }

    static void tampilkanSemua() throws TanamanException {
        System.out.println("\n--- Daftar Tanaman ---");

        if (daftarTanaman.isEmpty()) {
            throw new TanamanException(
                    "Daftar tanaman masih kosong.");
        }

        for (int i = 0; i < daftarTanaman.size(); i++) {
            System.out.println("\nNomor: " + (i + 1));
            daftarTanaman.get(i).tampilkanInfo();
        }

        System.out.println("\nTotal data tanaman: "
                + daftarTanaman.size());
    }

    static void cariMenu() throws TanamanException {
        System.out.print("Masukkan nama tanaman yang dicari: ");
        String nama = input.nextLine();

        validasiTeks(nama, "Nama pencarian");

        boolean ditemukan = false;

        for (Tanaman tanaman : daftarTanaman) {
            if (tanaman.getNama().equalsIgnoreCase(nama)) {
                System.out.println("Tanaman ditemukan:");
                tanaman.tampilkanInfo();
                ditemukan = true;
                break;
            }
        }

        if (!ditemukan) {
            throw new TanamanException(
                    "Tanaman dengan nama '" + nama
                    + "' tidak ditemukan.");
        }
    }

    static void simulasiPerawatan() throws TanamanException {
        if (daftarTanaman.isEmpty()) {
            throw new TanamanException(
                    "Tidak ada tanaman untuk dirawat.");
        }

        tampilkanSemua();

        System.out.print("\nPilih nomor tanaman yang akan dirawat: ");
        int nomor = input.nextInt();
        input.nextLine();

        if (nomor < 1 || nomor > daftarTanaman.size()) {
            throw new TanamanException(
                    "Nomor tanaman tidak tersedia.");
        }

        Tanaman tanaman = daftarTanaman.get(nomor - 1);

        System.out.println("\nPerawatan untuk "
                + tanaman.getNama() + ":");

        tanaman.perawatan();
    }

    static void kemampuanKhusus() throws TanamanException {
        if (daftarTanaman.isEmpty()) {
            throw new TanamanException(
                    "Tidak ada data tanaman.");
        }

        tampilkanSemua();

        System.out.print("\nPilih nomor tanaman: ");
        int nomor = input.nextInt();
        input.nextLine();

        if (nomor < 1 || nomor > daftarTanaman.size()) {
            throw new TanamanException(
                    "Nomor tanaman tidak tersedia.");
        }

        Tanaman tanaman = daftarTanaman.get(nomor - 1);

        System.out.println("\nKemampuan khusus "
                + tanaman.getNama() + ":");

        if (tanaman instanceof DapatDisiram) {
            DapatDisiram objek = (DapatDisiram) tanaman;
            objek.jadwalPenyiraman();
        }

        if (tanaman instanceof DapatDipupuk) {
            DapatDipupuk objek = (DapatDipupuk) tanaman;
            objek.pemberianPupuk();
        }

        if (!(tanaman instanceof DapatDisiram)
                && !(tanaman instanceof DapatDipupuk)) {
            System.out.println(
                    "Tidak ada kemampuan khusus yang terdaftar.");
        }
    }

    static void menuAnggota() throws TanamanException {
        boolean kembali = false;

        while (!kembali) {
            try {
                System.out.println("\n--- Kelola Data Anggota ---");
                System.out.println("1. Tambah Anggota");
                System.out.println("2. Cari Anggota");
                System.out.println("3. Tampilkan Semua Anggota");
                System.out.println("4. Kembali ke Menu Utama");
                System.out.print("Pilih menu anggota: ");

                int pilihan = input.nextInt();
                input.nextLine();

                switch (pilihan) {
                    case 1:
                        tambahAnggota();
                        break;

                    case 2:
                        cariAnggota();
                        break;

                    case 3:
                        tampilkanAnggota();
                        break;

                    case 4:
                        kembali = true;
                        break;

                    default:
                        throw new TanamanException(
                                "Pilihan menu anggota tidak valid.");
                }

            } catch (InputMismatchException e) {
                System.out.println(
                        "Kesalahan: pilihan harus berupa angka.");
                input.nextLine();

            } catch (TanamanException e) {
                System.out.println(
                        "Pesan Exception: " + e.getMessage());

            } finally {
                System.out.println(
                        "Finally: proses data anggota selesai diperiksa.");
            }
        }
    }

    static void tambahAnggota() throws TanamanException {
        System.out.print("Masukkan NPM: ");
        String npm = input.nextLine();
        validasiTeks(npm, "NPM");

        System.out.print("Masukkan nama anggota: ");
        String nama = input.nextLine();
        validasiTeks(nama, "Nama anggota");

        if (dataAnggota.containsKey(npm)) {
            throw new TanamanException(
                    "NPM tersebut sudah terdaftar.");
        }

        dataAnggota.put(npm, nama);
        System.out.println("Anggota berhasil ditambahkan.");
    }

    static void cariAnggota() throws TanamanException {
        System.out.print("Masukkan NPM yang dicari: ");
        String npm = input.nextLine();
        validasiTeks(npm, "NPM pencarian");

        if (!dataAnggota.containsKey(npm)) {
            throw new TanamanException(
                    "Anggota dengan NPM tersebut tidak ditemukan.");
        }

        System.out.println("Data anggota ditemukan:");
        System.out.println("NPM  : " + npm);
        System.out.println("Nama : " + dataAnggota.get(npm));
    }

    static void tampilkanAnggota() {
        System.out.println("\n--- Daftar Anggota ---");
        System.out.println("Jumlah anggota: " + dataAnggota.size());

        for (Map.Entry<String, String> anggota
                : dataAnggota.entrySet()) {
            System.out.println("NPM: " + anggota.getKey()
                    + " | Nama: " + anggota.getValue());
        }
    }
}
