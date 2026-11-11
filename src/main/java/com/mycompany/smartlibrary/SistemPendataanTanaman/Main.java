package com.mycompany.smartlibrary.SistemPendataanTanaman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    // Aggregation
    static ArrayList<Tanaman> daftarTanaman = new ArrayList<>();
    static HashMap<String, Anggota> dataAnggota = new HashMap<>();

    static SistemTanaman sistem;
    static TransaksiTanaman transaksi = new TransaksiTanaman();

    public static void main(String[] args) {

        // Membuat objek pengelola terlebih dahulu.
        sistem = new SistemTanaman(daftarTanaman, dataAnggota);

        // Memuat data jika file sudah tersedia.
        boolean dataTersedia = sistem.muatDataDariFile();

        // Data awal hanya dibuat jika belum ada data tersimpan.
        if (!dataTersedia) {
            dataAwal();
        }

        boolean berjalan = true;

        while (berjalan) {
            try {
                System.out.println("\n==================================");
                System.out.println("     SISTEM PENDATAAN TANAMAN");
                System.out.println("==================================");
                System.out.println("1. Tambah Tanaman");
                System.out.println("2. Tampilkan Semua Tanaman");
                System.out.println("3. Cari Tanaman");
                System.out.println("4. Simulasi Perawatan Tanaman");
                System.out.println("5. Kemampuan Khusus Tanaman");
                System.out.println("6. Jumlah Tanaman");
                System.out.println("7. Kelola Data Anggota");
                System.out.println("8. Informasi Composition");
                System.out.println("9. Tampilkan Data Aggregation");
                System.out.println("10. Transaksi Association");
                System.out.println("11. Baca Log Transaksi");
                System.out.println("12. Simpan Data");
                System.out.println("13. Muat Data");
                System.out.println("14. Keluar");
                System.out.print("Pilih menu: ");

                int pilihan = input.nextInt();
                input.nextLine();

                switch (pilihan) {

                    case 1:
                        tambahTanaman();
                        break;

                    case 2:
                        sistem.tampilkanTanaman();
                        break;

                    case 3:
                        cariTanaman();
                        break;

                    case 4:
                        simulasiPerawatan();
                        break;

                    case 5:
                        kemampuanKhusus();
                        break;

                    case 6:
                        System.out.println(
                                "Jumlah tanaman: "
                                + daftarTanaman.size());

                        System.out.println(
                                "Jumlah objek tanaman yang pernah dibuat: "
                                + Tanaman.getTotalTanaman());
                        break;

                    case 7:
                        menuAnggota();
                        break;

                    case 8:
                        sistem.tampilkanRumahTanaman();
                        break;

                    case 9:
                        System.out.println(
                                "\n--- DATA AGGREGATION ---");

                        sistem.tampilkanTanaman();
                        sistem.tampilkanAnggota();
                        break;

                    case 10:
                        menuTransaksi();
                        break;

                    case 11:
                        sistem.bacaLogTransaksi();
                        break;

                    case 12:
                        sistem.simpanDataKeFile();
                        break;

                    case 13:
                        if (!sistem.muatDataDariFile()) {
                            System.out.println(
                                    "Data tersimpan belum tersedia.");
                        }
                        break;

                    case 14:
                        sistem.simpanDataKeFile();

                        berjalan = false;

                        System.out.println(
                                "Program selesai. Terima kasih.");
                        break;

                    default:
                        throw new TanamanException(
                                "Pilihan menu harus antara 1 sampai 14.");
                }

            } catch (InputMismatchException e) {

                System.out.println(
                        "Kesalahan: masukkan pilihan berupa angka.");

                input.nextLine();

            } catch (TanamanException e) {

                System.out.println(
                        "Pesan Exception: " + e.getMessage());

            } finally {

                System.out.println(
                        "Finally: proses menu telah diperiksa.");
            }
        }

        input.close();
    }

    static void dataAwal() {

        // Lima objek utama.
        daftarTanaman.add(new Bunga("Mawar", 2, 25000));
        daftarTanaman.add(new Bunga("Melati", 1, 15000));
        daftarTanaman.add(new Pohon("Mangga", 5, 150000));
        daftarTanaman.add(new Pohon("Jambu", 3, 100000));

        daftarTanaman.add(
                new TanamanHias("Lidah Mertua", 2, 45000));

        // Tiga objek entitas pendukung.
        dataAnggota.put(
                "2517051001",
                new Anggota("2517051001", "Melan Panesia Diwa"));

        dataAnggota.put(
                "2517051002",
                new Anggota("2517051002", "Ajeng"));

        dataAnggota.put(
                "2517051016",
                new Anggota("2517051016", "Nia Kurniawati"));
    }

    static void validasiTeks(
            String teks, String keterangan)
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
                    "Umur dan harga tidak boleh negatif.");
        }

        System.out.println("1. Bunga");
        System.out.println("2. Pohon");
        System.out.println("3. Tanaman Hias");

        System.out.print("Pilih jenis tanaman: ");

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

        sistem.tambahTanaman(tanamanBaru);
    }

    static void cariTanaman() throws TanamanException {

        System.out.print("Nama tanaman yang dicari: ");
        String nama = input.nextLine();

        validasiTeks(nama, "Nama pencarian");

        Tanaman tanaman = sistem.cariTanaman(nama);

        if (tanaman == null) {
            throw new TanamanException(
                    "Tanaman " + nama + " tidak ditemukan.");
        }

        System.out.println("Tanaman ditemukan:");
        tanaman.tampilkanInfo();
    }

    static void simulasiPerawatan() throws TanamanException {

        sistem.tampilkanTanaman();

        if (daftarTanaman.isEmpty()) {
            throw new TanamanException(
                    "Tidak ada tanaman untuk dirawat.");
        }

        System.out.print("Pilih nomor tanaman: ");
        int nomor = input.nextInt();
        input.nextLine();

        if (nomor < 1 || nomor > daftarTanaman.size()) {
            throw new TanamanException(
                    "Nomor tanaman tidak tersedia.");
        }

        Tanaman tanaman = daftarTanaman.get(nomor - 1);

        System.out.println(
                "\nPerawatan untuk " + tanaman.getNama());

        tanaman.perawatan();
    }

    static void kemampuanKhusus() throws TanamanException {

        sistem.tampilkanTanaman();

        if (daftarTanaman.isEmpty()) {
            throw new TanamanException(
                    "Tidak ada data tanaman.");
        }

        System.out.print("Pilih nomor tanaman: ");
        int nomor = input.nextInt();
        input.nextLine();

        if (nomor < 1 || nomor > daftarTanaman.size()) {
            throw new TanamanException(
                    "Nomor tanaman tidak tersedia.");
        }

        Tanaman tanaman = daftarTanaman.get(nomor - 1);

        System.out.println(
                "\nKemampuan khusus " + tanaman.getNama() + ":");

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
                System.out.println(
                        "\n--- Kelola Data Anggota ---");

                System.out.println("1. Tambah Anggota");
                System.out.println("2. Cari Anggota");
                System.out.println("3. Tampilkan Semua Anggota");
                System.out.println("4. Kembali");

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
                        sistem.tampilkanAnggota();
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
                        "Finally: data anggota diperiksa.");
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

        if (sistem.getDataAnggota().containsKey(npm)) {
            throw new TanamanException(
                    "NPM tersebut sudah terdaftar.");
        }

        sistem.tambahAnggota(new Anggota(npm, nama));
    }

    static void cariAnggota() throws TanamanException {

        System.out.print("Masukkan NPM yang dicari: ");
        String npm = input.nextLine();

        validasiTeks(npm, "NPM pencarian");

        Anggota anggota = sistem.cariAnggota(npm);

        if (anggota == null) {
            throw new TanamanException(
                    "Anggota dengan NPM tersebut tidak ditemukan.");
        }

        System.out.println("\nData anggota ditemukan:");
        anggota.tampilkanInfo();
    }

    static void menuTransaksi() throws TanamanException {

        System.out.println("\n--- Transaksi Association ---");

        System.out.print("Masukkan NPM anggota: ");
        String npm = input.nextLine();

        validasiTeks(npm, "NPM anggota");

        Anggota anggota = sistem.cariAnggota(npm);

        if (anggota == null) {
            throw new TanamanException(
                    "Anggota tidak ditemukan.");
        }

        sistem.tampilkanTanaman();

        System.out.print("Masukkan nama tanaman: ");
        String namaTanaman = input.nextLine();

        validasiTeks(namaTanaman, "Nama tanaman");

        Tanaman tanaman = sistem.cariTanaman(namaTanaman);

        if (tanaman == null) {
            throw new TanamanException(
                    "Tanaman tidak ditemukan.");
        }

        // Association
        transaksi.lakukanTransaksi(anggota, tanaman);
    }
}