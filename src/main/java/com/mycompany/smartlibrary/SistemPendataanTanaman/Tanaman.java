
package com.mycompany.smartlibrary.SistemPendataanTanaman;


public class Tanaman {

    private String nama;
    private int umur;
    private double harga;

    private static int totalTanaman = 0;

    public Tanaman(String nama, int umur, double harga) {
        this.setNama(nama);
        this.setUmur(umur);
        this.setHarga(harga);
        totalTanaman++;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (nama != null && !nama.isEmpty()) {
            this.nama = nama;
        } else {
            this.nama = "Tanpa Nama";
        }
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        if (umur >= 0) {
            this.umur = umur;
        } else {
            this.umur = 0;
        }
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        if (harga > 0) {
            this.harga = harga;
        } else {
            this.harga = 0;
        }
    }

    public void tampilkanInfo() {
        System.out.println("Nama Tanaman : " + nama);
        System.out.println("Umur         : " + umur + " tahun");
        System.out.println("Harga        : Rp" + harga);
        System.out.println("Jenis        : Tanaman");
    }

    public void perawatan() {
        System.out.println("Tanaman perlu dirawat dan disiram secara rutin.");
    }

    public static int getTotalTanaman() {
        return totalTanaman;
    }
}