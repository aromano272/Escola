/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escola;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author aRomano
 */
public final class Aluno implements Serializable {
    //     Metodos de Objecto 
    private String nome;
    private final int numero;
    private int idade;
    private String email;
    private int cursoIndex;
    private Curso curso;
    private final ArrayList<Nota> Notas = new ArrayList<>();
    
    
    public int getNumeroAlunos() {
        int num = Escola.alunosLength();
        return num;
    }
    
    public void addNota(Nota nota) {
        Notas.add(nota);
    }
    
    public ArrayList<Nota> getNotas() {
        return Notas;
    }
    
    
    public Curso getCurso() {
        return this.curso;
    }
    
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    public int getCursoIndex() {
        return cursoIndex;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public int getNumero() {
        return this.numero;
    }
    
    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    public int getIdade() {
        return this.idade;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public float getMedia() {
        float media = 0;
        for(Nota nota : this.Notas) {
            float valor = nota.getValor();
            media += valor;
        }
        media /= this.Notas.size();
        
        return media;
    }
    
    
    // Construtores
    Aluno(Curso curso, String nome, int idade, String email) {
        this.curso = curso;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.numero = getNumeroAlunos();
    }
    
    Aluno(Curso curso, int cursoIndex, String nome, String email) {
        this.curso = curso;
        this.cursoIndex = cursoIndex;
        this.nome = nome;
        this.idade = 0;
        this.email = email;
        this.numero = getNumeroAlunos();
    }
    
    Aluno(Curso curso, int cursoIndex, String nome) {
        this.curso = curso;
        this.cursoIndex = cursoIndex;
        this.nome = nome;
        this.idade = 0;
        this.email = "";
        this.numero = getNumeroAlunos();
    }
    
}
