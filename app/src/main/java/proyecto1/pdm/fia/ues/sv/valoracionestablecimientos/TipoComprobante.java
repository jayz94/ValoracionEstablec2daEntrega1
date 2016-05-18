package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

/**
 * Created by c on 05/05/2016.
 */
public class TipoComprobante {

    private int idTipoComprobante;
    private String tipoComprobante;

    public int getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(int idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public TipoComprobante() {
    }

    public TipoComprobante(int idTipoComprobante, String tipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
        this.tipoComprobante = tipoComprobante;
    }
}
