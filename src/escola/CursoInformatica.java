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
public class CursoInformatica extends Curso implements Serializable {
    // cursos de informatica teem obrigatoriamente 240 creditos
    // 'override' ah variavel creditos
    private static final int creditos = 240;
    private static int totalCursos = 0;
    
    // esconde o nomeCurso na class Curso
    private static final String nomeCurso = "Informática ";
    
    // nao eh necessario @Override pois nao eh possivel fazer override a metodos estaticos
    // na realidade ao darmos o mesmo nome do metodo da superclass, estamos a "esconde lo"
    // se mudar o scope de "CursoInformatico.getCreditos()" para "Curso.getCreditos()" acedo ao metodo da superclass
    public static int getCreditos() {
        return CursoInformatica.creditos;
    }
    
    public static int getTotalCursos() {
        return CursoInformatica.totalCursos;
    }
    
    @Override
    public String getNomeCurso() {
        String nome = CursoInformatica.nomeCurso + this.getTurma();
        return nome;
    }
    
    
    //
    private final char turma;
    
    public final char setTurma(int totalCursos) {
        char letra = (char)(totalCursos + 64);
        return letra;
    }
    
    @Override
    public char getTurma() {
        return this.turma;
    }
    
    
    CursoInformatica(String horario, String coordenador, String emailCoordenador) {
        CursoInformatica.totalCursos++;
        this.turma = setTurma(getTotalCursos());
        this.regimeHorario = horario;
        this.coordenador = coordenador;
        this.emailCoordenador = emailCoordenador;
    }
    
    CursoInformatica(String horario, String coordenador) {
        CursoInformatica.totalCursos++;
        this.turma = setTurma(getTotalCursos());
        this.regimeHorario = horario;
        this.coordenador = coordenador;
        this.emailCoordenador = "";
    }
    
    CursoInformatica() {
        CursoInformatica.totalCursos++;
        this.turma = setTurma(getTotalCursos());
    }
    
    
    

}
