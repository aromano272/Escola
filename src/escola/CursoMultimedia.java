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
public class CursoMultimedia extends Curso implements Serializable {
    //private static final int creditos = 240;
    private static int totalCursos = 0;
    
    private static final String nomeCurso = "Multimédia ";
    private final char turma;
    
    public static int getTotalCursos() {
        return CursoMultimedia.totalCursos;
    }
    
    @Override
    public String getNomeCurso() {
        String nome = CursoMultimedia.nomeCurso + this.getTurma();
        return nome;
    }
    
    public final char setTurma(int totalCursos) {
        char letra = (char)(totalCursos + 64);
        return letra;
    }
    
    @Override
    public char getTurma() {
        return this.turma;
    }
    
    // nao eh necessario @Override pois nao eh possivel fazer override a metodos estaticos
    // na realidade ao darmos o mesmo nome do metodo da superclass, estamos a "esconde lo"
    // se mudar o scope de "CursoInformatico.getCreditos()" para "Curso.getCreditos()" acedo ao metodo da superclass
    
    
    
    CursoMultimedia(String horario, String coordenador, String emailCoordenador) {
        CursoMultimedia.totalCursos++;
        this.turma = setTurma(getTotalCursos());
        this.regimeHorario = horario;
        this.coordenador = coordenador;
        this.emailCoordenador = emailCoordenador;
    }
    
    CursoMultimedia(String horario, String coordenador) {
        CursoMultimedia.totalCursos++;
        this.turma = setTurma(getTotalCursos());
        this.regimeHorario = horario;
        this.coordenador = coordenador;
        this.emailCoordenador = "";
    }
    
    CursoMultimedia() {
        CursoMultimedia.totalCursos++;
        this.turma = setTurma(getTotalCursos());
    }
}
