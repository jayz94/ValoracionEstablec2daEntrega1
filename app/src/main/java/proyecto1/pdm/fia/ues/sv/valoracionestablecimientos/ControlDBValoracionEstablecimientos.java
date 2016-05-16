package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ControlDBValoracionEstablecimientos {

    private static final String[]camposDepartamento = new String [] {"IdDepartamento","NombreDepartamento","Zona"};
    private static final String[]camposMunicipio = new String [] {"IdMunicipio","IdDepartamento","NombreMunicipio"};
    /*variables katya --------------------------------------------------------------------------------------------*/
    String [] camposCliente={"dui","nombre","apellido","sexo","edad","correo"};
    String[] camposEncargado={"nit","nombre","apellido","sexo","edad","cargo"};
    /*variables juan carlos---------------------------------------------------------------------------------------*/
    private static final String[] camposTiEstablec = new String[]{"idTipoEstablecimiento ", "tipoEstablecimiento"};
    private static final String[] camposEstablec = new String[]{"idEstablecimiento", "nombreEstablecimiento", "direccion", "telefono", "encargadoNit", "idMunicipio", "idTipoEstablecimiento "};
    /*variables Elias --------------------------------------------------------------------------------------------*/
    private static final String[] camposComprobante= new String []{"numComprobante", "idComprobante","fechaComprobante","monto","vendedor","idTipoComprobante"};
    private static final String[] camposTipoComprobante = new String[]{"idTipoComprobante","TipoComprobante"};

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlDBValoracionEstablecimientos(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "base01.s3db";
        private static final int VERSION = 1;
        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL("CREATE TABLE Departamento(IdDepartamento INTEGER NOT NULL PRIMARY KEY,NombreDepartamento VARCHAR(30),Zona VARCHAR(30));");
                db.execSQL("CREATE TABLE Municipio(IdMunicipio INTERGER NOT NULL PRIMARY KEY,IdDepartamento INTERGER,NombreMunicipio VARCHAR(30));");
                /*Consultas juan --------------------------------------------------------------------------------------------------*/
                db.execSQL("CREATE TABLE TipoEstablecimiento (idTipoEstablecimiento char(6) primary key NOT NULL,tipoEstablecimiento varchar(30));");
                db.execSQL("CREATE TABLE Establecimiento (idEstablecimiento varchar(6) primary key NOT NULL,nombreEstablecimiento varchar(20)," +
                        "direccion varchar(100), telefono char(8),encargadoNit char(17),idMunicipio integer, idTipoEstablecimiento char(6));");
                db.execSQL("CREATE TRIGGER delete_TipoEs BEFORE DELETE ON TipoEstablecimiento BEGIN  SELECT CASE   WHEN((SELECT idTipoEstablecimiento FROM Establecimiento WHERE idTipoEstablecimiento=OLD.idTipoEstablecimiento)IS NOT NULL) THEN RAISE(ABORT, 'Tipo establecimiento ya fue usado')     END;     END;");
                /*consultas katya -----------------------------------------------------------------------------------------------*/
                db.execSQL("CREATE TABLE cliente (dui VARCHAR(10)NOT NULL PRIMARY KEY, nombre VARCHAR(50),apellido VARCHAR(50),sexo VARCHAR(1), edad INTEGER, correo VARCHAR(50));");
                db.execSQL("CREATE TABLE encargado(nit VARCHAR(17) NOT NULL PRIMARY KEY,nombre VARCHAR(50), apellido VARCHAR(50),sexo VARCHAR(1),edad INTEGER, cargo VARCHAR(50));");
                /*consultas Elias -----------------------------------------------------------------------------------------------*/
                db.execSQL("CREATE TABLE comprobante(numComprobante INTEGER ,idComprobante INTEGER NOT NULL PRIMARY KEY,fechaComprobante VARCHAR(10),monto INTEGER,vendedor VARCHAR(30),idTipoComprobante INTEGER);");
                db.execSQL("CREATE TABLE tipoComprobante(idTipoComprobante VARCHAR(6) NOT NULL PRIMARY KEY,tipoComprobante VARCHAR(30));");
               /* db.execSQL("CREATE TABLE establecimiento(numeroEstablecimiento VARCHAR(10) NOT NULL PRIMARY KEY,direccion VARCHAR(50),encargadoNit VARCHAR(17));");*/
                db.execSQL("CREATE TRIGGER fk_establecimiento_encargado BEFORE DELETE ON encargado"
                        +" "+"FOR EACH ROW BEGIN SELECT CASE"
                        +" "+ "WHEN ((SELECT encargadoNit FROM establecimiento WHERE encargadoNit=OLD.nit) IS NOT NULL)"
                        +" "+ "THEN RAISE (ABORT,'Existen establecimientos asociados')"
                        +" "+ "END; " + " END;");

                /*SQL para la administracion de los usuarios*/
                db.execSQL("CREATE TABLE Usuario(IdUsuario CHAR(2) NOT NULL PRIMARY KEY,NombreUsuario VARCHAR(30),Clave CHAR(25));");

                db.execSQL("CREATE TABLE OpcionCrud(IdOpcion CHAR(3) NOT NULL PRIMARY KEY,DesOpcion VARCHAR(30),Activities VARCHAR(30),NumCrud INTEGER);");

                db.execSQL("CREATE TABLE AccesoUsuario(IdOpcion CHAR(3) NOT NULL,IdUsuario CHAR(2) NOT NULL, PRIMARY KEY(IdOpcion,IdUsuario));");

            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
              // TODO Auto-generated method stub
        }
    }

    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return;
    }

    public void cerrar(){
        DBHelper.close();
    }

    /*metodos juan carlos----------------------------------------------------------------------------------------*/
    public String insertar(Establecimiento establec) {
        String regInsert = "Registro Insertado Numero=";
        long cont = 0;
        ContentValues establecLocal = new ContentValues();
        if (establec.getIdEstablec().equals(""))
            return "Error, Ingrese un Id";
        else {
            establecLocal.put("idEstablecimiento", establec.getIdEstablec());
            establecLocal.put("nombreEstablecimiento", establec.getNombreEstablec());
            establecLocal.put("direccion", establec.getDireccion());
            establecLocal.put("telefono", establec.getTelefono());
            establecLocal.put("encargadoNit", establec.getEncargadoNit());
            establecLocal.put("idMunicipio", establec.getIdMunicipio());
            establecLocal.put("idTipoEstablecimiento", establec.getIdTipoEstablec());
            cont = db.insert("Establecimiento", null, establecLocal);

            if (cont == -1 || cont == 0) {
                regInsert = "Error al insertar, Registro Duplicado :(";
            } else {
                regInsert = regInsert + cont;
            }
            return regInsert;
        }
    }

    /*------------------------------------------------------metodos para CRUD de establecimiento-------------------------*/
    public Establecimiento consultarEstablecimiento(String id) {
        String[] idEsta = {id};
        /*"idEstablecimiento","nombreEstablecimiento","direccion","telefono","encargadoNit","idMunicipio","idTipoEstablecimiento "*/
        Cursor cursor = db.query("Establecimiento", camposEstablec, "idEstablecimiento=?", idEsta, null, null, null);
        if (cursor.moveToFirst()) {
            Establecimiento establec = new Establecimiento();
            establec.setIdEstablec(cursor.getString(0));
            establec.setNombreEstablec(cursor.getString(1));
            establec.setDireccion(cursor.getString(2));
            establec.setTelefono(cursor.getString(3));
            establec.setEncargadoNit(cursor.getString(4));
            establec.setIdMunicipio(cursor.getInt(5));
            establec.setIdTipoEstablec(cursor.getString(6));
            return establec;
        } else {
            return null;
        }
    }

    public String actualizar(Establecimiento establec) {
        String[] id = {establec.getIdEstablec()};
        ContentValues coVa = new ContentValues();
        coVa.put("nombreEstablecimiento", establec.getNombreEstablec());
        coVa.put("direccion", establec.getDireccion());
        coVa.put("telefono", establec.getTelefono());
        coVa.put("encargadoNit", establec.getEncargadoNit());
        coVa.put("idMunicipio", establec.getIdMunicipio());
        coVa.put("idTipoEstablecimiento", establec.getIdEstablec());
        int cont = db.update("Establecimiento", coVa, "idEstablecimiento=?", id);
        if (cont == -1 || cont == 0) return "EL registro no se encontro";
        else {
            return "Registro Actualizado Correctamente";
        }
    }/*falta la verificacion de integridad*/


    public String eliminar(Establecimiento establec) {
        String afectados = " eliminado con exito";
        /*falta la verificacion de integridad*/
        int cont = db.delete("Establecimiento", "idEstablecimiento='" + establec.getIdEstablec() + "'", null);
        if (cont == 0 || cont == -1)
            return "Error, el Registro no existe";
        else
            return establec.getIdEstablec() + afectados;
    }

    /*------------------------------------------------------metodos para CRUD de Tipo establecimiento-------------------------*/
    public String insertar(TipoEstablecimiento tiEst) {
        String regInsert = "Registro Insertado con exito";
        long cont = 0;
        ContentValues tiEstLocal = new ContentValues();
        tiEstLocal.put("idTipoEstablecimiento", tiEst.getIdTiestablec());
        tiEstLocal.put("tipoEstablecimiento", tiEst.getTipoEstablec());
        cont = db.insert("TipoEstablecimiento", null, tiEstLocal);

        if (cont == -1 || cont == 0) {
            regInsert = "Error al insertar, Registro Duplicado :(,cambie el identificador";
        } else {
            regInsert = regInsert + cont;
        }
        return regInsert;
    }

    public String actualizarTipoEstablecimiento(TipoEstablecimiento tiEstablec) {
        String[] id = {tiEstablec.getIdTiestablec()};
        ContentValues coVa = new ContentValues();
        coVa.put("tipoEstablecimiento", tiEstablec.getTipoEstablec());
        int cont = db.update("TipoEstablecimiento", coVa, "idTipoEstablecimiento=?", id);
        if (cont == -1 || cont == 0) {
            return "Error al actualizar, Registro no existe";
        } else
            return "Registro Actualizado Correctamente";
    }/*falta la verificacion de integridad*/

    public String eliminar(TipoEstablecimiento tiEstablec) {
        String afectados = "Registro elminado con exito ";
        int cont = 0;
        try{cont += db.delete("TipoEstablecimiento", "idTipoEstablecimiento='" + tiEstablec.getIdTiestablec() + "'", null);}
        catch(SQLException e){
            return "Eror,Tipo Establecimiento ya fue usado en establecimiento";
        }

        afectados += cont;
        if (cont == 0 || cont == -1)
            return "Error al actualizar, Registro no existe";
        else
            return afectados;
    }

    public TipoEstablecimiento consultarTipoEstablecimiento(String id) {
        String[] idTiEsta = {id};
        Cursor cursor = db.query("TipoEstablecimiento", camposTiEstablec, "idTipoEstablecimiento=?", idTiEsta, null, null, null);
        if (cursor.moveToFirst()) {
            TipoEstablecimiento tipoEstablec = new TipoEstablecimiento();
            tipoEstablec.setIdTiestablec(cursor.getString(0));
            tipoEstablec.setTipoEstablec(cursor.getString(1));
            return tipoEstablec;
        } else {
            return null;
        }
    }

    /* metodos katya ---------------------------------------------------------------------------------------*/
    public String insertar(Cliente cliente){
        String regInsertador="Registro Insertado N°= ";
        long contador=0;

        ContentValues cli = new ContentValues();

        cli.put("dui",cliente.getDui());
        cli.put("nombre",cliente.getNombres());
        cli.put("apellido",cliente.getApellidos());
        cli.put("sexo",cliente.getSexo());
        cli.put("edad",cliente.getEdad());
        cli.put("correo",cliente.getCorreo());

        contador=db.insert("cliente", null,cli);

        if(contador==-1 || contador==0){
            regInsertador="Error al Insertar registro, RegistroDuplicado. Verificar Inserccion";
        }else {
            regInsertador=regInsertador+contador;
        }

        return regInsertador;
    }

    public String actualizarCliente(Cliente cliente){

        String [] id={cliente.getDui()};
        ContentValues cc= new ContentValues();

        cc.put("nombre",cliente.getNombres());
        cc.put("apellido",cliente.getApellidos());
        cc.put("sexo",cliente.getSexo());
        cc.put("edad",cliente.getEdad());
        cc.put("correo",cliente.getCorreo());
        db.update("cliente",cc,"dui=?",id);
        return "Registro con dui "+ cliente.getDui()+" actualizado correctamente";


    }

    public String eliminarCliente(Cliente cliente){

        String regAfectados="filas afectadas= ";
        int contador=0;

        contador+=db.delete("cliente","dui='"+cliente.getDui()+"'",null);
        regAfectados+=contador;
        return regAfectados;
    }

    public Cliente consultarCliente(String dui) {

        String[] id = {dui};
        Cursor cursor = db.query("cliente", camposCliente, "dui=?", id, null, null, null);

        if (cursor.moveToFirst()) {
            Cliente cliente = new Cliente();

            cliente.setDui(cursor.getString(0));
            cliente.setNombres(cursor.getString(1));
            cliente.setApellidos(cursor.getString(2));
            cliente.setSexo(cursor.getString(3));
            cliente.setEdad(cursor.getInt(4));
            cliente.setCorreo(cursor.getString(5));
            return cliente;
        } else {
            return null;
        }
    }
    public String insertarEncargado(Encargado encargado){

        String regInsertado="Registro Insertado N°= ";
        long contador=0;

        ContentValues ce = new ContentValues();

        ce.put("nit",encargado.getNit());
        ce.put("nombre",encargado.getNombre());
        ce.put("apellido",encargado.getApellido());
        ce.put("sexo",encargado.getSexo());
        ce.put("edad",encargado.getEdad());
        ce.put("cargo",encargado.getCargo());

        contador=db.insert("encargado",null,ce);
        if(contador==-1 || contador==0)
        {
            regInsertado="Error al insertar registros de encargado";
        }else {

            regInsertado=regInsertado+contador;
        }

        return regInsertado;

    }

    /*public String insertarEstablecimiento(Establecimiento establecimiento){

        String regInsertado="Registro Insertado N°= ";
        long contador=0;

        ContentValues ce = new ContentValues();

        ce.put("numeroEstablecimiento",establecimiento.getNumeroEstablecimiento());
        ce.put("direccion",establecimiento.getDireccion());
        ce.put("encargadoNit",establecimiento.getEncargadoNit());

        contador=db.insert("establecimiento",null,ce);
        if(contador==-1 || contador==0)
        {
            regInsertado="Error al insertar registros de establecimiento";
        }else {

            regInsertado=regInsertado+contador;
        }

        return regInsertado;

    }*/  /*este metodo lo quite para hacerlo con el de juan carlos*/

    public String eliminarEncargado(Encargado encargado){
        String llaveFonarea="No se puede eliminar Encargado, tiene Establecimientos asociados";
        String registrosAfectados="Filas Afectadas= ";
        long contador=0;
        if (verificarIntegridad(encargado, 4)){/*cambie de 1 a 4 xq ahora ese es el case que le corresponde*/
            String [] nit={encargado.getNit()};
            try{
                contador=db.delete("encargado", "nit=?", nit);
                registrosAfectados=registrosAfectados+contador;
            }catch (SQLException e){
                registrosAfectados=llaveFonarea;
            }

        }else{
            registrosAfectados="No existe el Encargado";
        }

        return registrosAfectados;
    }


    public Encargado consultarEncargado(String nit){

        String[] nitConsultar={nit};

        Cursor cursor=db.query("encargado",camposEncargado,"nit=?",nitConsultar,null,null,null);

        if (cursor.moveToFirst()){
            Encargado encargado=new Encargado();
            encargado.setNit(cursor.getString(0));
            encargado.setNombre(cursor.getString(1));
            encargado.setApellido(cursor.getString(2));
            encargado.setSexo(cursor.getString(3));
            encargado.setEdad(cursor.getInt(4));
            encargado.setCargo(cursor.getString(5));


            return encargado;
        }else {
            return null;
        }

    }

    public String actualizarEncargado(Encargado encargado){

        String regInsertado="Registro Actualizado N°= ";
        long contador=0;

        ContentValues ce = new ContentValues();

        ce.put("nit",encargado.getNit());
        ce.put("nombre",encargado.getNombre());
        ce.put("apellido",encargado.getApellido());
        ce.put("sexo",encargado.getSexo());
        ce.put("edad",encargado.getEdad());
        ce.put("cargo",encargado.getCargo());


        String []nit={encargado.getNit()};
        contador = db.update("encargado", ce, "nit=?", nit);
        if(contador==-1 || contador==0)
        {
            regInsertado="Error al actualizar registros de encargado";
        }else {

            regInsertado=regInsertado+contador;
        }

        return regInsertado;

    }

    /*metodos carlos -----------------------------------------------------------------*/

    public String insertar(Departamento departamento){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues depart = new ContentValues();
        depart.put("IdDepartamento", departamento.getIdDepartamento());
        depart.put("NombreDepartamento", departamento.getNombreDepartamento());
        depart.put("Zona", departamento.getZona());

        contador=db.insert("Departamento", null, depart);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }
    public String insertar(Municipio municipio){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        if(verificarIntegridad(municipio,1))
        {
            ContentValues muni = new ContentValues();
            muni.put("IdMunicipio", municipio.getIdMunicipio());
            muni.put("IdDepartamento", municipio.getIdDepartamento());
            muni.put("NombreMunicipio", municipio.getNombreMunicipio());

            contador=db.insert("Municipio", null, muni);
        }
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public String actualizar(Departamento departamento){
        if(verificarIntegridad(departamento, 3)){     /* el numero es el numero del case*/
            String[] id = {String.valueOf(departamento.getIdDepartamento())};/*tuve que convertirlo a una variable de tipo string */
            ContentValues cv = new ContentValues();
            cv.put("NombreDepartamento", departamento.getNombreDepartamento());
            cv.put("Zona", departamento.getZona());

            db.update("Departamento", cv, "IdDepartamento = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con IdDepartamento " + departamento.getIdDepartamento() + " no existe";
        }
    }
    public String actualizar(Municipio municipio){

        if(verificarIntegridad(municipio, 2)){
            String[] id = {String.valueOf(municipio.getIdMunicipio()), };/*tuve que hacer un parse*/
            ContentValues cv = new ContentValues();
            cv.put("IdDepartamento", municipio.getIdDepartamento());
            cv.put("NombreMunicipio", municipio.getNombreMunicipio());
            db.update("Municipio", cv, "IdMunicipio = ? ", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro no Existe";
        }
    }

    public String eliminar(Departamento departamento){

        String regAfectados="filas afectadas= ";
        String msj1 = "";
       /* String msj2 = "";*/
        int contador=0;
        if (verificarIntegridad(departamento,3)){
            Cursor aux1 = db.rawQuery("select count(*) from departamento", null);
            aux1.moveToFirst();
            int aux2 = aux1.getInt(0);
            try{
                db.execSQL("DELETE FROM Departamento where IdDepartamento = " + departamento.getIdDepartamento());
                Cursor aux3 = db.rawQuery("select count(*) from departamento",null);
                aux3.moveToFirst();
                int aux4 = aux3.getInt(0);
                if(aux2 != aux4){
                    contador = aux2 - aux4;
                    regAfectados += contador;
                }
            }catch (SQLException e){
               /* e.printStackTrace();*/
               msj1 = "No se puede eliminar.Departamento tiene asociados municipios";
                regAfectados += msj1;
            }
        }

        return regAfectados;
    }
    public String eliminar(Municipio municipio){

        String regAfectados="filas afectadas= ";
        int contador=0;
        String where="IdMunicipio='"+municipio.getIdMunicipio() + "'";
        /*where=where+" AND codmateria='"+municipio.getCodmateria()+"'";
        where=where+" AND ciclo="+municipio.getCiclo();*/
        contador+=db.delete("Municipio", where, null);
        regAfectados+=contador;
        return regAfectados;
    }

    public Departamento consultarDepartamento(String IdDepartamento){/*ocupe String xq este dato viene de la vista,y esos datos son string*/
       /* String[] id = {String.valueOf(IdDepartamento)};  /*mando como string un entero pero la base lo soporta(verificar)*/
        String[] id = {IdDepartamento};

        Cursor cursor = db.query("Departamento", camposDepartamento, "IdDepartamento = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Departamento departamento = new Departamento();
            departamento.setIdDepartamento(Integer.parseInt(cursor.getString(0))); /*tuve que hacer un parse por el tipo de dato de IdDepartamento*/
            departamento.setNombreDepartamento(cursor.getString(1));
            departamento.setZona(cursor.getString(2));

            return departamento;
        }else{
            return null;
        }
    }
    public Municipio consultarMunicipio(int IdMunicipio){

        String[] id = {String.valueOf(IdMunicipio)};/* tuve que hacer un parse por el tipo de dato*/
        Cursor cursor = db.query("Municipio", camposMunicipio, "IdMunicipio = ? ", id, null, null, null);
        if(cursor.moveToFirst()){
            Municipio municipio = new Municipio();
            municipio.setIdMunicipio(Integer.parseInt(cursor.getString(0)));/*tuve que hacer un parse por el tipo de dato de IdMunicipio*/
            municipio.setIdDepartamento(Integer.parseInt(cursor.getString(1)));/*tuve que hacer un parse por el tipo de dato de IdDepartamento*/
            municipio.setNombreMunicipio(cursor.getString(2));

            return municipio;
        }else{
            return null;
        }
    }

    /*metodos Elias -----------------------------------------------------------------*/

    public String insertar(Comprobante comprobante){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        ContentValues comp= new ContentValues();
        comp.put("numComprobante",comprobante.getNumComprobante());
        comp.put("idComprobante",comprobante.getIdComprobante());
        comp.put("fechaComprobante",comprobante.getFechaComprobante());
        comp.put("monto",comprobante.getMonto());
        comp.put("vendedor",comprobante.getVendedor());
        comp.put("idTipoComprobante",comprobante.getIdTipoComprobante());
        contador=db.insert("comprobante",null,comp);

        if (contador==-1||contador==0){
            regInsertados="Error al insertar registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }
    public String insertar(TipoComprobante tipoComprobante) {
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        ContentValues tipoComp= new ContentValues();
        tipoComp.put("idTipoComprobante", tipoComprobante.getIdTipoComprobante());
        tipoComp.put("tipoComprobante", tipoComprobante.getTipoComprobante());
        contador=db.insert("tipocomprobante",null,tipoComp);

        if (contador==-1||contador==0){
            regInsertados="Error al insertar registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public String actualizar(Comprobante comprobante){
        String[] id = {""+comprobante.getIdComprobante()};
        ContentValues cv = new ContentValues();
        cv.put("numComprobante", comprobante.getNumComprobante());
        cv.put("fechaComprobante", comprobante.getFechaComprobante());
        cv.put("monto", comprobante.getMonto());
        cv.put("vendedor", comprobante.getVendedor());
        cv.put("idTipoComprobante",comprobante.getIdTipoComprobante());
        db.update("comprobante", cv, "idComprobante = ?", id);
        return "Registro Actualizado Correctamente";
    }

    public String actualizar(TipoComprobante tipoComprobante){
        String[] id = {""+tipoComprobante.getIdTipoComprobante()};
        ContentValues cv = new ContentValues();
        cv.put("idTipoComprobante", tipoComprobante.getIdTipoComprobante());
        cv.put("tipoComprobante", tipoComprobante.getTipoComprobante());
        db.update("tipoComprobante", cv, "idTipoComprobante = ?", id);
        return "Registro Actualizado Correctamente";
    }

    public String eliminar(Comprobante comprobante){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("comprobante", "idComprobante='"+comprobante.getIdComprobante()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    public String eliminar(TipoComprobante tipoComprobante){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("tipoComprobante", "idTipoComprobante='"+tipoComprobante.getIdTipoComprobante()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    public Comprobante consultarComprobante(String dato, int tipo){
        if (tipo==1){//significa que el usuario buscará por id
            String[] idcomp = {dato};
            Cursor cursor = db.query("comprobante", camposComprobante, "idComprobante = ?", idcomp, null, null, null);
            if(cursor.moveToFirst()){
                Comprobante comprobante = new Comprobante();
                comprobante.setNumComprobante(cursor.getInt(0));
                comprobante.setIdComprobante(cursor.getInt(1));
                comprobante.setFechaComprobante(cursor.getString(2));
                comprobante.setMonto(cursor.getInt(3));
                comprobante.setVendedor(cursor.getString(4));
                comprobante.setIdTipoComprobante(cursor.getInt(5));
                return comprobante;
            }else{
                return null;
            }
        }else{//significa que el usuario buscará por monto
            String[] montocomp = {dato};
            Cursor cursor = db.query("comprobante", camposComprobante, "monto = ?", montocomp, null, null, null);
            if(cursor.moveToFirst()){
                Comprobante comprobante = new Comprobante();
                comprobante.setNumComprobante(cursor.getInt(0));
                comprobante.setIdComprobante(cursor.getInt(1));
                comprobante.setFechaComprobante(cursor.getString(2));
                comprobante.setMonto(cursor.getInt(3));
                comprobante.setVendedor(cursor.getString(4));
                return comprobante;
            }else{
                return null;
            }
        }
    }
    public TipoComprobante consultarTipoComprobante(String idTipoComprobante){
        String[] idcomp = {idTipoComprobante};
        Cursor cursor = db.query("tipoComprobante", camposTipoComprobante, "idTipoComprobante = ?", idcomp, null, null, null);
        if(cursor.moveToFirst()){
            TipoComprobante tipoComprobante = new TipoComprobante();
            tipoComprobante.setIdTipoComprobante(cursor.getInt(0));
            tipoComprobante.setTipoComprobante(cursor.getString(1));
            return tipoComprobante;
        }else{
            return null;
        }
    }

     /*---------------------------------------------------------------------------------------------------------*/
    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion) {

            case 1: {
                //verificar que al insertar municipio exista IdDepartamento del departamento
                Municipio municipio = (Municipio) dato;
                String[] id1 = {String.valueOf(municipio.getIdDepartamento())}; /*le agregue el String.ValueOf xq devolvia un entero y no podia guardarse en string*/
                abrir();
                Cursor cursor1 = db.query("Departamento", null, "IdDepartamento = ?", id1, null, null, null);

                if (cursor1.moveToFirst()) {/* otra opcion cursor ¡= null*/
                    //Se encontraron datos
                    return true;
                }
                return false;
            }
            case 2: {
                //verificar que al modificar municipio exista IdDepartamento del Departamento
                Municipio municipio1 = (Municipio) dato;
                String[] ids = {String.valueOf(municipio1.getIdDepartamento())}; /*le agregue el String.ValueOf xq devolvia un entero y no podia guardarse en string*/
                abrir();
                Cursor c = db.query("Municipio", null, "IdDepartamento = ?", ids, null, null, null);/*se diferencia del metodo anterior*/
                                                                       /*en que aqui se ocupa la tabla municipio no departamento*/
                if (c.moveToFirst()) {
                    //Se encontraron datos
                    return true;
                }
                return false;
            }
            /*verificar primero para que es el case 3*/
           /* case 3:
            {
                Alumno alumno = (Alumno)dato;
                Cursor c=db.query(true, "nota", new String[] {
                        "carnet" }, "carnet='"+alumno.getCarnet()+"'",null, null, null, null, null);
                if(c.moveToFirst())
                    return true;
                else
                    return false;
            }
            case 4:
            {
                Materia materia = (Materia)dato;
                Cursor cmat=db.query(true, "nota", new String[] {
                        "codmateria" }, "codmateria='"+materia.getCodmateria()+"'",null, null, null, null, null);
                if(cmat.moveToFirst())
                    return true;
                else
                    return false;
            }*/
            case 3: {
                //verificar que exista Departamento
                Departamento departamento2 = (Departamento) dato;
                String[] id = {String.valueOf(departamento2.getIdDepartamento())};
                abrir();
                Cursor c2 = db.query("Departamento", null, "IdDepartamento = ?", id, null, null, null);
                if (c2.moveToFirst()) {
                    //Se encontro Departamento
                    return true;
                }
                return false;
            }
            case 4: {
                Encargado encargado = (Encargado) dato;

                String[] nit = {encargado.getNit()};

                Cursor cursor = db.query("encargado", null, "nit=?", nit, null, null, null);

                if (cursor.moveToFirst()) {
                    return true;
                }else{
                    return false;
                }

            }
            case 5: { /*para juan carlos*/
               /* Cursor c;
                c = db.rawQuery("SELECT idTipoEstablecimiento FROM TipoEstablecimiento where idTipoEstablecimiento=?", new String[]{idTipoEstablec + ""});
                c.moveToFirst();
                if (c.getCount()<1)
                    return "El tipo de Establecimiento no existe";
                return null;*/ /* por el momento da error*/
            }

            default:
                return false;
        }
    }

    /* metodos agregados por juan carlos ---------------------------------------------------------*/
    public String ultimoRegistro() {
        Cursor c; //= db.rawQuery("SELECT idTipoEstablecimiento FROM TipoEstablecimiento ", null);
        try{
            c = db.rawQuery("SELECT idTipoEstablecimiento FROM TipoEstablecimiento ", null);
            c.moveToLast();
            return c.getString(0);
        }
        catch(SQLException e){return "Error, Base de datos vacia";}
    }
    public List listaIdTiEstablec() {
        List<String> lista = new ArrayList<String>();
        Cursor c;
        c = db.rawQuery("SELECT idTipoEstablecimiento FROM TipoEstablecimiento ", null);
        c.moveToFirst();
        int cont = c.getCount();
        for (int i = 0; i < cont; i++) {
            lista.add(c.getString(0));
            c.moveToNext();
        }
        return lista;

    }

    public String ultimoRegistroE() {
        Cursor c;// = db.rawQuery("SELECT idEstablecimiento FROM Establecimiento ", null);
        try{
            c = db.rawQuery("SELECT idEstablecimiento FROM Establecimiento ", null);
            c.moveToLast();
            return c.getString(0);
        }
        catch(SQLException e){
            return "Error, Base de datos vacia";
        }


    } /* creo que los utilizaba para llenar la base pero ahora no haremos de manera diferente*/
    public String verificarIntegridad(String encargadoNit, String idTipoEstablec, int idMun) {
        Cursor c = db.rawQuery("SELECT idMunicipio FROM Municipio where idMunicipio=?", new String[]{idMun + ""});
        c.moveToFirst();
        if (c.getCount()<1)
            return "El municipio no existe";
        c = db.rawQuery("SELECT nit FROM Encargado where nit=?", new String[]{encargadoNit});
        c.moveToFirst();
        if (c.getCount()<1)
            return "El Encargado no existe";
        c = null;
        c = db.rawQuery("SELECT idTipoEstablecimiento FROM TipoEstablecimiento where idTipoEstablecimiento=?", new String[]{idTipoEstablec + ""});
        c.moveToFirst();
        if (c.getCount()<1)
            return "El tipo de Establecimiento no existe";
        return null;

    }
    /*By Juan Carlos Cruz*/
    public String logear(String usuario, String pass){
        Cursor c = db.rawQuery("SELECT IdUsuario FROM Usuario ", null);
        c.moveToFirst();
        if (c.getCount()<1)
            llenarBaseUsuarios();/*al momento de llenar la base con los datos del usuario*/

        Cursor c2 = db.rawQuery("SELECT IdUsuario FROM Usuario where NombreUsuario=? AND Clave=?", new String[]{usuario,pass});
        if (c2.moveToFirst()) {
            //Se encontro usuario
            return c2.getString(0);
        }
        return "";

    }
/*metodo para obtener las opciones del CRUD que le corresponde a cada usuario*/
    public String[] obtenerMenu(String id){
        Cursor cursorOpciones = db.rawQuery("SELECT IdOpcion FROM AccesoUsuario where IdUsuario=?", new String[]{id});
        cursorOpciones.moveToFirst();
        int cont=cursorOpciones.getCount();
        String[] menu=new String[cont];
        for(int i=0;i<cont;i++){
            Cursor cursorDes= db.rawQuery("SELECT DesOpcion FROM OpcionCrud where IdOpcion=?", new String[]{cursorOpciones.getString(0)});
            cursorDes.moveToFirst();
            menu[i]=cursorDes.getString(0);
            cursorOpciones.moveToNext();
        }
        return menu;
    }
    /*metodo para obtener las opciones del CRUD que le corresponde a cada usuario*/
    public String[] obtenerActivites(String id){
        Cursor cursorOpciones = db.rawQuery("SELECT IdOpcion FROM AccesoUsuario where IdUsuario=?", new String[]{id});
        cursorOpciones.moveToFirst();
        int cont=cursorOpciones.getCount();
        String[] menuActiviti=new String[cont];
        for(int i=0;i<cont;i++){
            Cursor cursorDes= db.rawQuery("SELECT Activities FROM OpcionCrud where IdOpcion=?", new String[]{cursorOpciones.getString(0)});
            cursorDes.moveToFirst();
            menuActiviti[i]=cursorDes.getString(0);
            cursorOpciones.moveToNext();
        }
        return menuActiviti;
    }
    public void llenarBaseUsuarios(){
        /*IdUsuario,NombreUsuario Clave */
        long cont=0;
        ContentValues usu = new ContentValues();
        String[] id_Usuario = new String [] {"C1",      "K1",     "S1",        "J1",           "L1","A1"};
        String[] nomUsuario = new String [] {"Carlos",  "Katy",   "Samuel",    "Juan Carlos",  "Leo","Admin"};
        String[] clave = new String [] {     "carlos",  "katy",   "samuel",    "juan carlos",  "leo","admin"};
       for (int i=0;i<id_Usuario.length;i++) {
            usu.put("IdUsuario",id_Usuario[i]);
            usu.put("NombreUsuario",nomUsuario[i]);
            usu.put("Clave", clave[i]);
            cont=db.insert("Usuario", null, usu);
        }
/* OpcionCrud(IdOpcion CHAR(3) NOT NULL PRIMARY KEY,DesOpcion VARCHAR(30),NumCrud*/
        String[] idOpcion = new String [] {"100","101","102","103","104","105","106","107","108","109",/*modulos de menu*/
                "001","002","003","004","005","006","007","008",/*modulos Carlos*/
                "009","010","011","012","013","014","015","016",/*modulos de Katy*/
                "017","018","019","020","021","022","023","024",/*modulos de Samuel*/
                "025","026","027","028","029","030","031","032",/*modulos de Juan Carlos*/
                "033","034","035","036","037","038","039","040"};/*modulos de Leo*/
        String[] activities= new String []{"DepartamentoMenuActivity","MunicipioMenuActivity",
                                          "ClienteMenuActivity","EncargadoMenuActivity",
                                         "ComprobanteMenuActivity","TipoComprobanteMenuActivity",
                                         "EstablecMenuActivity","TiEsMenuActivity",
                                         "ValoracionMenuActivity","TipoValoracionMenuActivity",/*son los de leo*/

                "DepartamentoInsertarActivity","DepartamentoEliminarActivity","DepartamentoConsultarActivity", "DepartamentoActualizarActivity",
                "MunicipioInsertarActivity","MunicipioEliminarActivity","MunicipioConsultarActivity", "MunicipioActualizarActivity",/*carlos*/
                "ClienteInsertarActivity","ClienteConsultarActivity","ClienteActualizarActivity","ClienteEliminarActivity",
                "EncargadoInsertarActivity","EncargadoConsultarActivity","EncargadoActualizarActivity","EncargadoEliminarActivity",/*katy*/
                "ComprobanteActualizarActivity","ComprobanteConsultarActivity","ComprobanteEliminarActivity", "ComprobanteInsertarActivity",
                "TipoComprobanteActualizarActivity", "TipoComprobanteConsultarActivity", "TipoComprobanteEliminarActivity", "TipoComprobanteInsertarActivity", /*Elias*/
                "EstablecInsertarActivity", "EstablecEliminarActivity", "EstablecConsultarActivity", "EstablecActualizarActivity",
                "TiEstInsertarActivity", "TiEsEliminarActivity", "TiEsConsultarActivity", "TiEsActualizarActivity",/*JC*/
                /*leo*/
                "ValoracionInsertarActivity", "ValoracionEliminarActivity", "ValoracionConsultarActivity", "ValoracionActualizarActivity",
                "TipoValoracionInsertarActivity", "TipoValoracionEliminarActivity", "TipoValoracionConsultarActivity","TipoValoracionActualizarActivity" };
        String[] desOpcion= new String []{"Menu Departamento","Menu Municipio",
                "Menu Cliente","Menu Encargado",
                "Menu Comprobante","Menu Tipo Comprobante",
                "Menu Establecimiento","Menu Tipo Establecimiento",
                "Menu Valoracion","Menu Tipo Valoracion",/*son los de leo*/

                "Insertar Departamento","Eliminar Departamento","Consultar Departamento", "Actualizar Departamento",
                "Insertar Municipio","Eliminar Municipio","Consultar Municipio", "Actualizar Municipio",/*carlos*/
                "Insertar Cliente","Consultar Cliente","Actualizar Cliente","Eliminar Cliente",
                "Insertar Encargado","Consultar Encargado","Actualizar Encargado","Eliminar Encargado",/*katy*/
                "Actualizar Comprobante","Consultar Comprobante","Eliminar Comprobante", "Insertar Comprobante",
                "Actualizar Tipo Comprobante", "Consultar Tipo Comprobante", "Eliminar Tipo Comprobante", "Insertar Tipo Comprobante", /*Elias*/
                "Insertar Establecimiento", "Eliminar Establecimiento", "Consultar Establecimiento", "Actualizar Establecimiento",
                "Insertar Tipo Establecimiento", "Eliminar Tipo Establecimiento", "Consultar Tipo Establecimiento", "Actualizar Tipo Establecimiento",/*JC*/
                /*leo*/
                "Insertar Valoracion", "Eliminar Valoracion", "Consultar Valoracion", "Actualizar Valoracion",
                "Insertar TipoValoracion", "Eliminar Tipo Valoracion", "Consultar Tipo Valoracion","Actualizar Tipo Valoracion" };
        int[] numCrud = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,
                26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50};
        for (int i=0;i<idOpcion.length;i++) {
            ContentValues opcionCrud = new ContentValues();
            opcionCrud.put("IdOpcion",idOpcion[i]);
            opcionCrud.put("DesOpcion",desOpcion[i] );
            opcionCrud.put("Activities",activities[i] );
            opcionCrud.put("NumCrud",numCrud[i]);
            cont=db.insert("OpcionCrud", null, opcionCrud);
        }
/*AccesoUsuario(IdOpcion CHAR(3) NOT NULL PRIMARY KEY,IdUsuario CHAR(2)    permisos hacia modulos del crud*/
        String[] usuarioID= new String []{"K1","K1","K1","C1","C1","S1","S1","J1","J1","L1","L1",/**/"A1","A1","A1","A1","A1","A1","A1" ,"A1","A1","A1"  /**/};
        String[] opcionID= new String []{"102","103","104","100","101","104","105","106","107","108","109",/**/"100","101","102","103","104","105","106","107","108","109"/**/};

        for (int i=0;i<usuarioID.length;i++) {
            ContentValues AccUsu = new ContentValues();
            AccUsu.put("IdOpcion",opcionID[i]);
            AccUsu.put("IdUsuario",usuarioID[i]);
            cont=db.insert("AccesoUsuario", null, AccUsu);
        }
    }

    public String llenarBDProyecto1() {
        Cursor c = db.rawQuery("SELECT idTipoEstablecimiento FROM TipoEstablecimiento",null);
        c.moveToFirst();
        if (c.getCount()<1){
            final int[] VDIdDepartamento = {1,2,3,4};
            final String[] VDNombreDepartamento = {"San Salvador", "San Miguel ", "Santa Ana", "La Union"};
            final String[] VDZona = {"Central", "Oriental", "Occidental", "Oriental"};
        /*final String[] VAsexo = {"M","M","F","F"};*/
            final int[] VMIdMunicipio = {1,2,3,4};
            final int[] VMIdDepartamento = {4,3,2,1};
            final String[] VMNombreMunicipio = {"El Carmen", "Metapan", "Moncagua", "San Marcos"};

        /*variables katya -----------------------------------------------------------------------------*/
            final String[] VAdui={"0000000000","0000001010","0000002020"};
            final String[] VAnombre={"Katya","Marleny","Josselyn"};
            final String[] VAapellido={"Herrera","Marquez","Herrera"};
            final String[] VAsexo={"F","F","F"};
            final Integer[] VAedad={21,40,11};
            final String[] corrreo={"katya@gmail.com","marleny@gmail.com","josselyn@gmail.com"};

            String[] nit={"1311-300377-101-4","1411-251294-101-2","1566-011224-100-1"};
            String[] nombre={"Julia","Marta","Jose"};
            String[] apellido={"Perez","Hernandez","Martinez"};
            String[] sexo={"F","F","M"};
            Integer[] edad={40,28,32};
            String[] cargo={"Supervisor","Gerente","Administrador"};

        /*variables JC*/
            final String[] VAidEstablec={"est1","est2","est3"};
            final String[] VAnombreEstablec={"Don pollo","pizza","Doñamila"};
            final Integer[] VAidMun={1,2,3};
            final String[] VAdireccion={"san salvador","Suchitoto","Morazan"};
            final String[] VAencargadoNit={"456456","78979","7895646"};
            final String[] VAidTipoEstablecE={"TipoEsta1","TipoEsta2","TipoEsta3"};
            final String[] telefono={"78941657","789789","78987899"};

            String[] idTiestablec={"TipoEsta1","TipoEsta2","TipoEsta3"};
            String[] tipoEstablec={"comedor","supermercado","bañeario"};

       /* String [] numeroEstablecimiento={"100","200","300"};
        String [] direccion={"cuarta calle poniente, casa2","barrio el calbario, casa 2","barrio santa fe, segunda avenida"};
        String [] encargadoNit={"1000-100000-100-1","20000-2000000-200-2","30000-3000000-300-3"};*/
        /*------------------------------------------------------------------------------------------------------------------*/

        /*  Variables de Elias------*/
        final int[] VCnum = {0122,45612,31657,98765,43215};
        final int[] VCid = {1,2,3,4,5};
        final String[] VCfecha = {"04/03/16","21/06/15","18/02/16","30/5/16","22/1/16"};
        final int[] VCmonto = {15,60,30,40,55};
        final String[] VCvendedor = {"Juan Carrera","Kelly Large","Carlos Pereira","Katya Herradura","Leonidas Serpas"};
        final int[] VCidTipoCom = {1,2,1,3,2};

        final int[] VTidcom = {1,2,3};
        final String[] VTtipocom = {"Recibo","Factura","Credito Fiscal"};

        abrir();
        db.execSQL("DELETE FROM Municipio");
        db.execSQL("DELETE FROM Departamento");
        /*db.execSQL("CREATE TRIGGER tg_departamento_delete BEFORE DELETE ON Departamento"
        + " " + "FOR EACH ROW" + " " + "BEGIN" + " " + "SELECT CASE "
        + " " + "WHEN ((SELECT COUNT(*) FROM Municipio WHERE Municipio.IdDepartamento = OLD.IdDepartamento) > 0)"
        + " " + "THEN RAISE(ABORT,'existen municipios asociados a este departamento')"
        + " " + "END;" + " " + "END;");*/
        //Estas  son de Elias
        db.execSQL("DELETE FROM tipoComprobante");
        db.execSQL("DELETE FROM comprobante");

            abrir();
            db.execSQL("DELETE FROM Establecimiento");
            db.execSQL("DELETE FROM TipoEstablecimiento");
            db.execSQL("DELETE FROM cliente");
            db.execSQL("DELETE FROM encargado");
            db.execSQL("DELETE FROM Municipio");
            db.execSQL("DELETE FROM Departamento");
            db.execSQL("CREATE TRIGGER tg_departamento_delete BEFORE DELETE ON Departamento"
                    + " " + "FOR EACH ROW" + " " + "BEGIN" + " " + "SELECT CASE "
                    + " " + "WHEN ((SELECT COUNT(*) FROM Municipio WHERE Municipio.IdDepartamento = OLD.IdDepartamento) > 0)"
                    + " " + "THEN RAISE(ABORT,'existen municipios asociados a este departamento')"
                    + " " + "END;" + " " + "END;");

        /*inserciones katya -----------------------------------------------------------------------------------------------*/
            Cliente cliente = new Cliente();
            for (int i=0; i<3;i++){
                cliente.setDui(VAdui[i]);
                cliente.setNombres(VAnombre[i]);
                cliente.setApellidos(VAapellido[i]);
                cliente.setSexo(VAsexo[i]);
                cliente.setEdad(VAedad[i]);
                cliente.setCorreo(corrreo[i]);
                insertar(cliente);

            }
            Encargado encargado=new Encargado();
            for(int i=0;i<3;i++){

                encargado.setNit(nit[i]);
                encargado.setNombre(nombre[i]);
                encargado.setApellido(apellido[i]);
                encargado.setSexo(sexo[i]);
                encargado.setEdad(edad[i]);
                encargado.setCargo(cargo[i]);


                insertarEncargado(encargado);

            }

       /* Establecimiento establecimiento=new Establecimiento();
        for (int i=0;i<3;i++){
            establecimiento.setNumeroEstablecimiento(numeroEstablecimiento[i]);
            establecimiento.setDireccion(direccion[i]);
            establecimiento.setEncargadoNit(encargadoNit[i]);
            insertarEstablecimiento(establecimiento);
        }*/
        /*-----------------------------------------------------------------------------------------------------------------*/

            Departamento departamento = new Departamento();
            for (int i = 0; i < 4; i++) {
                departamento.setIdDepartamento(VDIdDepartamento[i]);
                departamento.setNombreDepartamento(VDNombreDepartamento[i]);
                departamento.setZona(VDZona[i]);

                insertar(departamento);
            }

            Municipio municipio = new Municipio();
            for(int i=0;i<4;i++){
                municipio.setIdMunicipio(VMIdMunicipio[i]);
                municipio.setIdDepartamento(VMIdDepartamento[i]);
                municipio.setNombreMunicipio(VMNombreMunicipio[i]);
                insertar(municipio);
            }

            Establecimiento establec = new Establecimiento();
            for (int i=0; i<3;i++){
                establec.setIdEstablec(VAidEstablec[i]);
                establec.setNombreEstablec(VAnombreEstablec[i]);
                establec.setIdMunicipio(VAidMun[i]);
                establec.setDireccion(VAdireccion[i]);
                establec.setEncargadoNit(VAencargadoNit[i]);
                establec.setIdTipoEstablec(VAidTipoEstablecE[i]);
                establec.setTelefono(telefono[i]);
                insertar(establec);

            }
            TipoEstablecimiento tipoEstableci=new TipoEstablecimiento();
            for(int i=0;i<3;i++){

                tipoEstableci.setIdTiestablec(idTiestablec[i]);
                tipoEstableci.setTipoEstablec(tipoEstablec[i]);
                insertar(tipoEstableci);

            }


        Comprobante comprobante = new Comprobante();
        for(int i=0;i<5;i++){
            comprobante.setNumComprobante(VCnum[i]);
            comprobante.setIdComprobante(VCid[i]);
            comprobante.setFechaComprobante(VCfecha[i]);
            comprobante.setMonto(VCmonto[i]);
            comprobante.setVendedor(VCvendedor[i]);
            comprobante.setIdTipoComprobante(VCidTipoCom[i]);
            insertar(comprobante);
        }

        TipoComprobante tipo_comprobante = new TipoComprobante();
        for(int i=0;i<3;i++){
            tipo_comprobante.setIdTipoComprobante(VTidcom[i]);
            tipo_comprobante.setTipoComprobante(VTtipocom[i]);
            insertar(tipo_comprobante);
        }



        cerrar();
        return "Guardo Correctamente";

        }// finaliza el metodo llenar base de datos
        return "La base ya tiene datos";
        }


}
