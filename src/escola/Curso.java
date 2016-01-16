/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escola;

import java.io.Serializable;

/**
 *
 * @author aRomano
 */
abstract public class Curso implements Serializable {
    // numero de creditos para conclusao
    // final pois nao tem nenhum metodo set
    private static final int creditos = 180;
    

    // regime diurno ou pos laboral
    // sao protected para puder referencia-las nos construtores das sub classes
    // de outro modo teria que dar override ou referencia las atraves de getters/setters
    protected String regimeHorario = "Diurno";
    protected String coordenador;
    protected String emailCoordenador;
    
    abstract public String getNomeCurso();
    
    abstract public char getTurma();
    
    public static int getCreditos() {
        return Curso.creditos;
    }
    
    public void setRegimeHorario(String horario) {
        this.regimeHorario = horario;
    }
    
    public String getRegimeHorario() {
        return this.regimeHorario;
    }
    
    public void setCoordenador(String coordenador) {
        this.coordenador = coordenador;
    }
    
    public String getCoordenador() {
        return this.coordenador;
    }
    
    public void setEmailCoordenador(String email) {
        this.emailCoordenador = email;
    }
    
    public String getEmailCoordenador() {
        return this.emailCoordenador;
    }
    
}
