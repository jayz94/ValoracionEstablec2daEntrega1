package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

/**
 * Created by carlos2 on 27/04/2016.
 */
public class Departamento {

    private int IdDepartamento;
    private String NombreDepartamento;
    private String Zona;

    public Departamento(){/*tengo que hacer tambien el constructor vacio sino da problemas al ingresarle los datos en ControlDB..*/
    }

    public Departamento(int idDepartamento, String nombreDepartamento, String zona) {
        this.IdDepartamento = idDepartamento;
        this.NombreDepartamento = nombreDepartamento;
        this.Zona = zona;
    }

    public int getIdDepartamento() {
        return IdDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.IdDepartamento = idDepartamento;
    }

    public String getNombreDepartamento() {
        return NombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.NombreDepartamento = nombreDepartamento;
    }

    public String getZona() {
        return Zona;
    }

    public void setZona(String zona) {
        this.Zona = zona;
    }
}
