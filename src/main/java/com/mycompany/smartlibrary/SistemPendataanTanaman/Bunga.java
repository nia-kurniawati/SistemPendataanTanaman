
package com.mycompany.smartlibrary.SistemPendataanTanaman;


public class Bunga extends Tanaman {

    private String warnaBunga;

    public Bunga(String nama, int umur, double harga, String warnaBunga) {
        super(nama, umur, harga);
        this.setWarnaBunga(warnaBunga);
    }

    public String getWarnaBunga() {
        return warnaBunga;
    }

    public void setWarnaBunga(String warnaBunga) {
        if (warnaBunga != null && !warnaBunga.isEmpty()) {
            this.warnaBunga = warnaBunga;
        } else {
            this.warnaBunga = "Tidak diketahui";
        }
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Warna Bunga  : " + warnaBunga);
        System.out.println("Jenis        : Bunga");
    }

    @Override
    public void perawatan() {
        System.out.println("Bunga perlu disiram dan mendapat cukup sinar matahari.");
    }
}