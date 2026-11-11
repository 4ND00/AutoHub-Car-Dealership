package autohub;

// Komponen internal Dealer (Composition): showroom tempat mobil dipamerkan.
public class RuangPamer {
    private String namaRuangan;
    private int kapasitasUnit;

    public RuangPamer(String namaRuangan, int kapasitasUnit) {
        this.namaRuangan = namaRuangan;
        this.kapasitasUnit = kapasitasUnit;
    }

    public String getNamaRuangan() { return namaRuangan; }
    public int getKapasitasUnit() { return kapasitasUnit; }

    public void bukaRuangan() {
        System.out.println("-> [" + namaRuangan + "] dibuka. Kapasitas: " + kapasitasUnit + " unit mobil.");
    }
}
