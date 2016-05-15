package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

/**
 * Created by c on 05/05/2016.
 */
public class Comprobante {

    private int numComprobante;
    private int idComprobante;
    private String fechaComprobante;
    private int monto;
    private String vendedor;
    private int idTipoComprobante;

    public Comprobante() {
    }

    public Comprobante(int numComprobante, int idComprobante, String fechaComprobante, int monto, String vendedor, int idtipo) {
        this.numComprobante = numComprobante;
        this.idComprobante = idComprobante;
        this.fechaComprobante = fechaComprobante;
        this.monto = monto;
        this.vendedor = vendedor;
        idTipoComprobante = idtipo;
    }

    public int getNumComprobante() {
        return numComprobante;
    }

    public void setNumComprobante(int numComprobante) {
        this.numComprobante = numComprobante;
    }

    public int getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getFechaComprobante() {
        return fechaComprobante;
    }

    public void setFechaComprobante(String fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public int getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(int idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }
}
