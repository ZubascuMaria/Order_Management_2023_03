package Model;

public class Orders {
    private Integer OID;
    private Integer cantitate;
    private Integer CID;
    private Integer PID;

    public Orders(Integer OID, Integer cantitate, Integer CID, Integer PID) {
        this.OID = OID;
        this.cantitate = cantitate;
        this.CID = CID;
        this.PID = PID;
    }

    public Orders(Integer cantitate, Integer CID, Integer PID) {
        this.cantitate = cantitate;
        this.CID = CID;
        this.PID = PID;
    }

    public Integer getOID() {
        return OID;
    }

    public void setOID(Integer OID) {
        this.OID = OID;
    }

    public Integer getCantitate() {
        return cantitate;
    }

    public void setCantitate(Integer cantitate) {
        this.cantitate = cantitate;
    }

    public Integer getCID() {
        return CID;
    }

    public void setCID(Integer CID) {
        this.CID = CID;
    }

    public Integer getPID() {
        return PID;
    }

    public void setPID(Integer PID) {
        this.PID = PID;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "OID=" + OID +
                ", cantitate=" + cantitate +
                ", CID=" + CID +
                ", PID=" + PID +
                '}';
    }
}
