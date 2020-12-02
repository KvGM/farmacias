/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cifpcm.farmaciasgarciak.model;

public class Farmacia {

    private String WEB;
    public String LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO;
    private String TELEFONO;
    private String NOMBRE;
    private float UTM_X;
    private float UTM_Y;

    /**
     *
     * @param WEB
     * @param NOMBRE
     * @param UTM_X
     * @param UTM_Y
     */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" NOMBRE = ").append(NOMBRE);
        sb.append(", WEB = ").append(WEB);
        sb.append(", LUNES = ").append(LUNES);
        sb.append(", MARTES = ").append(MARTES);
        sb.append(", MIERCOLES = ").append(MIERCOLES);
        sb.append(", JUEVES = ").append(JUEVES);
        sb.append(", VIERNES = ").append(VIERNES);
        sb.append(", SABADO = ").append(SABADO);
        sb.append(", DOMINGO=").append(DOMINGO);
        sb.append(", TELEFONO=").append(TELEFONO);
        sb.append(", UTM_X=").append(UTM_X);
        sb.append(", UTM_Y=").append(UTM_Y);
        return sb.toString();
    }

    public float getUtmx() {
        return UTM_X;
    }

    public void setUtmx(float UTM_X) {
        this.UTM_X = UTM_X;
    }

    public float getUtmy() {
        return UTM_Y;
    }

    public void setUtmy(float UTM_Y) {
        this.UTM_Y = UTM_Y;
    }

    public String getWEB() {
        return WEB;
    }

    public void setWEB(String WEB) {
        this.WEB = WEB;
    }

    public String getLUNES() {
        return LUNES;
    }

    public void setLUNES(String LUNES) {
        this.LUNES = LUNES;
    }

    public String getMARTES() {
        return MARTES;
    }

    public void setMARTES(String MARTES) {
        this.MARTES = MARTES;
    }

    public String getMIERCOLES() {
        return MIERCOLES;
    }

    public void setMIERCOLES(String MIERCOLES) {
        this.MIERCOLES = MIERCOLES;
    }

    public String getJUEVES() {
        return JUEVES;
    }

    public void setJUEVES(String JUEVES) {
        this.JUEVES = JUEVES;
    }

    public String getVIERNES() {
        return VIERNES;
    }

    public void setVIERNES(String VIERNES) {
        this.VIERNES = VIERNES;
    }

    public String getSABADO() {
        return SABADO;
    }

    public void setSABADO(String SABADO) {
        this.SABADO = SABADO;
    }

    public String getDOMINGO() {
        return DOMINGO;
    }

    public void setDOMINGO(String DOMINGO) {
        this.DOMINGO = DOMINGO;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }
    
    public Farmacia(String NOMBRE, String WEB, float UTM_X, float UTM_Y){
        this.WEB = WEB;
        this.NOMBRE = NOMBRE;
        this.UTM_X = UTM_X;
        this.UTM_Y = UTM_Y;
    }
    public Farmacia(){
        
    }
}
