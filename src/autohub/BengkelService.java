package autohub;

// Komponen internal Dealer (Composition): bengkel servis resmi.
public class BengkelService {
    private String namaBengkel;
    private int jumlahStall;

    public BengkelService(String namaBengkel, int jumlahStall) {
        this.namaBengkel = namaBengkel;
        this.jumlahStall = jumlahStall;
    }

    public String getNamaBengkel() { return namaBengkel; }
    public int getJumlahStall() { return jumlahStall; }

    public void bukaBengkel() {
        System.out.println("-> [" + namaBengkel + "] dibuka. Jumlah stall servis: " + jumlahStall + ".");
    }
}
