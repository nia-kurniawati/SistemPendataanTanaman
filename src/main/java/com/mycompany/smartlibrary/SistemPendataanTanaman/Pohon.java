
package com.mycompany.smartlibrary.SistemPendataanTanaman;


public class Pohon extends Tanaman {

    private double tinggiPohon;

    public Pohon(String nama, int umur, double harga, double tinggiPohon) {
        super(nama, umur, harga);
        this.setTinggiPohon(tinggiPohon);
    }

    public double getTinggiPohon() {
        return tinggiPohon;
    }

    public void setTinggiPohon(double tinggiPohon) {
        if (tinggiPohon > 0) {
            this.tinggiPohon = tinggiPohon;
        } else {
            this.tinggiPohon = 1;
        }
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Tinggi Pohon : " + tinggiPohon + " meter");
        System.out.println("Jenis        : Pohon");
    }

    @Override
    public void perawatan() {
        System.out.println("Pohon perlu disiram dan diberi pupuk secara rutin.");
    }
}