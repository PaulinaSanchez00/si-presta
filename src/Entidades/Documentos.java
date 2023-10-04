/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author CHRISTOPHERURIELTAFO
 */
public class Documentos {
    private int id;
    private String ine;
    private String acta_nac;
    private String comprobante_dom;

    public Documentos(int id) {
        this.id = id;
    }

    public Documentos() {
    }

    public Documentos(int id, String ine, String acta_nac, String comprobante_dom) {
        this.id = id;
        this.ine = ine;
        this.acta_nac = acta_nac;
        this.comprobante_dom = comprobante_dom;
    }

    public int getId() {
        return id;
    }

    public String getIne() {
        return ine;
    }

    public String getActa_nac() {
        return acta_nac;
    }

    public String getComprobante_dom() {
        return comprobante_dom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIne(String ine) {
        this.ine = ine;
    }

    public void setActa_nac(String acta_nac) {
        this.acta_nac = acta_nac;
    }

    public void setComprobante_dom(String comprobante_dom) {
        this.comprobante_dom = comprobante_dom;
    }
  
}
