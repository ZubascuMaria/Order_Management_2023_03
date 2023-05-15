package Model;

public class Product {
    private Integer PID;
    private String numeProdus;
    private Integer pret;
    private Integer stoc;

    public Product(Integer PID, String numeProdus, Integer pret, Integer stoc) {
        this.PID = PID;
        this.numeProdus = numeProdus;
        this.pret = pret;
        this.stoc = stoc;
    }

    public Product(String numeProdus, Integer pret, Integer stoc) {
        this.numeProdus = numeProdus;
        this.pret = pret;
        this.stoc = stoc;
    }

    public String getNumeProdus() {
        return numeProdus;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public Integer getPID() {
        return PID;
    }

    public Integer getPret() {
        return pret;
    }

    public void setPret(Integer pret) {
        this.pret = pret;
    }

    public Integer getStoc() {
        return stoc;
    }

    public void setStoc(Integer stoc) {
        this.stoc = stoc;
    }

    @Override
    public String toString() {
        return "Product{" +
                "PID=" + PID +
                ", numeProdus=" + numeProdus + '\n' +
                ", pret=" + pret +
                ", stoc=" + stoc +
                '}'+'\n';
    }
}
