package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

/**
 * Created by carlos2 on 27/04/2016.
 */
public class Municipio {

    private int IdMunicipio;
    private int IdDepartamento;
    private String NombreMunicipio;

    public Municipio(){ /*tengo que hacer tambien el constructor vacio sino da problemas al ingresarle los datos en ControlDB..*/
    }

    public Municipio(int idMunicipio, int idDepartamento, String nombreMunicipio) {
        this.IdMunicipio = idMunicipio;
        this.IdDepartamento = idDepartamento;
        this.NombreMunicipio = nombreMunicipio;
    }

    public int getIdMunicipio() {
        return IdMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        IdMunicipio = idMunicipio;
    }

    public int getIdDepartamento() {
        return IdDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        IdDepartamento = idDepartamento;
    }

    public String getNombreMunicipio() {
        return NombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        NombreMunicipio = nombreMunicipio;
    }
}
