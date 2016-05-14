package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

/**
 * Created by carlos2 on 12/05/2016.
 */
public class Establecimiento {
    private String idEstablec;
    private String nombreEstablec;
    private String direccion;
    private int idMunicipio;
    private String encargadoNit;
    private String idTipoEstablec;
    private String telefono;

    public String getIdEstablec() {
        return idEstablec;
    }
    public void setIdEstablec(String idEstablec) {
        this.idEstablec = idEstablec;
    }

    public String getNombreEstablec() {
        return nombreEstablec;
    }

    public void setNombreEstablec(String nombreEstablec) {
        this.nombreEstablec = nombreEstablec;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getEncargadoNit() {
        return encargadoNit;
    }

    public void setEncargadoNit(String encargadoNit) {
        this.encargadoNit = encargadoNit;
    }

    public String getIdTipoEstablec() {
        return idTipoEstablec;
    }

    public void setIdTipoEstablec(String idTipoEstablec) {
        this.idTipoEstablec = idTipoEstablec;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
