package com.mycompany.smartlibrary.SistemPendataanTanaman;

public abstract class Tanaman {
    private String nama;
    private int umur;
    private double harga;

    private static int totalTanaman = 0;

    public Tanaman(String nama, int umur, double harga) {
        this.nama = nama;
        this.umur = umur;
        this.harga = harga;
        totalTanaman++;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void tampilkanInfo() {
        System.out.println("Nama  : " + nama);
        System.out.println("Umur  : " + umur + " tahun");
        System.out.println("Harga : Rp" + harga);
    }

    public abstract void perawatan();

    public static int getTotalTanaman() {
        return totalTanaman;
    }
}
