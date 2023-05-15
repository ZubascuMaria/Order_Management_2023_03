package Model;

public class Client {
    private Integer CID;
    private String nume;
    private String adresa;
    private String contact;

    public Client(Integer CID, String nume, String adresa, String contact) {
        this.CID = CID;
        this.nume = nume;
        this.adresa = adresa;
        this.contact = contact;
    }

    public Client(String nume, String adresa, String contact) {
        this.nume = nume;
        this.adresa = adresa;
        this.contact = contact;
    }

    public Integer getCID() {
        return CID;
    }

    public void setCID(Integer CID) {
        this.CID = CID;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Client{" +
                "CID=" + CID +
                ", nume= " + nume + '\n' +
                ", adresa= " + adresa + '\n' +
                ", contact= " + contact +'}'+ '\n';
    }
}
