package autohub;

// Entitas pendukung yang mandiri (Aggregation): dibuat di luar Dealer.
public class Pelanggan {
    private String id;
    private String nama;

    public Pelanggan(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
}
