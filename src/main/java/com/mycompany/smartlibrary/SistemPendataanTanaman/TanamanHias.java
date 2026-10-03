
package com.mycompany.smartlibrary.SistemPendataanTanaman;


public class TanamanHias extends Tanaman {

    private String lokasiPenempatan;

    public TanamanHias(String nama, int umur, double harga, String lokasiPenempatan) {
        super(nama, umur, harga);
        this.setLokasiPenempatan(lokasiPenempatan);
    }

    public String getLokasiPenempatan() {
        return lokasiPenempatan;
    }

    public void setLokasiPenempatan(String lokasiPenempatan) {
        if (lokasiPenempatan != null && !lokasiPenempatan.isEmpty()) {
            this.lokasiPenempatan = lokasiPenempatan;
        } else {
            this.lokasiPenempatan = "Tidak diketahui";
        }
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Lokasi       : " + lokasiPenempatan);
        System.out.println("Jenis        : Tanaman Hias");
    }

    @Override
    public void perawatan() {
        System.out.println("Tanaman hias perlu dirawat dan diletakkan di tempat yang sesuai.");
    }
}