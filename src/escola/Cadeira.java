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
public class Cadeira implements Serializable {
    //     Metodos de Class
    private static int totalCadeiras = 0;
    
    public static int getTotalCadeiras() {
        return Cadeira.totalCadeiras;
    }

    //     Metodos de Objecto
    private String nome;
    private final int numero;
    private String professor;
    private String emailProfessor;
    private String codigoClassroom;
    private int creditos;
    
    
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public int getNumero() {
        return this.numero;
    }
    
    public void setProfessor(String professor) {
        this.professor = professor;
    }
    
    public String getProfessor() {
        return this.professor;
    }
    
    public void setEmailProfessor(String email) {
        this.emailProfessor = email;
    }
    
    public String getEmailProfessor() {
        return this.emailProfessor;
    }
    
    public void setCodigoClassroom(String codigo) {
        this.codigoClassroom = codigo;
    }
    
    public String getCodigoClassrrom() {
        return this.codigoClassroom;
    }
    
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
    
    public int getCreditos() {
        return this.creditos;
    }
    
    
    Cadeira(String nome, String professor, String emailProfessor, String codigoClassroom, int creditos) {
        Cadeira.totalCadeiras++;
        this.nome = nome;
        this.numero = getTotalCadeiras();
        this.professor = professor;
        this.emailProfessor = emailProfessor;
        this.codigoClassroom = codigoClassroom;
        this.creditos = creditos;
    }
    
    Cadeira(String nome, String professor, String emailProfessor, int creditos) {
        Cadeira.totalCadeiras++;
        this.nome = nome;
        this.numero = getTotalCadeiras();
        this.professor = professor;
        this.emailProfessor = emailProfessor;
        this.codigoClassroom = "";
        this.creditos = creditos;
    }
    
    Cadeira(String nome, String professor, int creditos) {
        Cadeira.totalCadeiras++;
        this.nome = nome;
        this.numero = getTotalCadeiras();
        this.professor = professor;
        this.emailProfessor = "";
        this.codigoClassroom = "";
        this.creditos = creditos;
    }
    
    Cadeira() {
        Cadeira.totalCadeiras++;
        this.nome = "undefined";
        this.numero = getTotalCadeiras();
        this.professor = "";
        this.emailProfessor = "";
        this.codigoClassroom = "";
        this.creditos = 0;
    }
    
    
    
}
