package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

/**
 * Created by cgcomputadoras on 27/4/2016.
 */
public class Cliente {
    private String dui;
    private String Nombres;
    private String Apellidos;
    private String Sexo;
    private Integer Edad;
    private String Correo;

    public Cliente(){}

    public Cliente(String nombres, String apellidos, Integer edad, String sexo, String correo) {
        Nombres = nombres;
        Apellidos = apellidos;
        Edad = edad;
        Sexo = sexo;
        Correo = correo;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public Integer getEdad() {
        return Edad;
    }

    public void setEdad(Integer edad) {
        Edad = edad;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }
}