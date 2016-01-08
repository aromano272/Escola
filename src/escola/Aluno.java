/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escola;

import java.util.ArrayList;

/**
 *
 * @author aRomano
 */
public final class Aluno {
    //      Metodos de Class
    private static int ultimoAluno = 0;
    
    //     Metodos de Objecto 
    private String nome;
    private final int numero;
    private int idade;
    private String email;
    private int cursoIndex;
    private Curso curso;
    // estado do aluno: "matriculado", "inactivo"
    private String estado;  // mudar para bool isMatriculado
    // array com 100 linhas por 20 colunas, cada linha representa uma disciplina
    // o indice de cada linha no array representa o numero da disciplina,
    // ie. matematica eh disciplina numero 0, logo eh representada em notas[0]
    // a primeira coluna representa a quantidade de notas naquela disciplina
    //private final float[][] notas = new float[100][20];
    private final ArrayList<Nota> Notas = new ArrayList<>();
    
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
    /*
    public void setNotas(int disciplina, float nota) {
        // sanitize
        if(nota < 0 || nota > 20) {
            // THROW ERRO
        } else {
            float proximaNota = ++this.notas[disciplina][0];
            
            this.notas[disciplina][(int)proximaNota] = nota;
        }
    }
    
    public float[] getNotas(int disciplina) {
        float[] nota = this.notas[disciplina];
        return nota;
    }
    
    public float getNotas(int disciplina, int index) {
        float nota = this.notas[disciplina][index];
        return nota;
    }
    */
    
    public void setEstado(String novoEstado) {
        // sanitize
        if(novoEstado.equals("matriculado") || novoEstado.equals("inactivo")) {
            // se o novo estado for diferente do actual
            if(!novoEstado.equals(this.estado)) {
                this.estado = novoEstado;
                // se o estado for alterado para matricula, o total de matriculas eh aumentado em 1
                // atraves do metodo novaMatricula na class Escola
                if(novoEstado.equals("matriculado")) {
                    Escola.novaMatricula();
                } else {
                    Escola.removerMatricula();
                }
            }
        } else {
            //    THROW ERROR
        }
    }
    
    public String getEstado() {
        return this.estado;
    }
    
    /*
    // retorna media total do aluno
    public float getMedia() {
        int totalNotas = 0;
        float media = 0;
        // para cada linha do array...
        for(int i = 0; i < this.notas.length; i++) {
            // .. que tenha a primeira coluna com um valor diferente de 0, ie. que tem notas..
            if(this.notas[i][0] != 0f) {
                // .. para cada nota nessa linha ..
                for(int j = 1; j <= (int)this.notas[i][0]; j++) {
                    // .. soma ah media e aumenta o totalNotas
                    media += this.notas[i][j];
                    totalNotas++;
                }
            }
        }
        media /= totalNotas;
        
        return media;
    }
    // retorna media de uma disciplina especifica
    public float getMedia(int disciplina) {
        int totalNotas = 0;
        float media = 0;
        
        // se o aluno tiver notas a esta disciplina
        if(this.notas[disciplina][0] != 0f) {
            // .. para cada nota nessa linha ..
            for(int i = 1; i <= (int)this.notas[disciplina][0]; i++) {
                // .. soma ah media e aumenta o totalNotas
                media += this.notas[disciplina][i];
                totalNotas++;
            }
        }
        
        media /= totalNotas;

        return media;
    }
    */
    
    
    // Construtores
    Aluno(Curso curso, String nome, int idade, String email) {
        this.curso = curso;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.setEstado("matriculado");
        this.numero = ultimoAluno;
        ultimoAluno++;
    }
    
    Aluno(Curso curso, int cursoIndex, String nome, String email) {
        this.curso = curso;
        this.cursoIndex = cursoIndex;
        this.nome = nome;
        this.idade = 0;
        this.email = email;
        this.setEstado("matriculado");
        this.numero = ultimoAluno;
        ultimoAluno++;
    }
    
    Aluno(Curso curso, int cursoIndex, String nome) {
        this.curso = curso;
        this.cursoIndex = cursoIndex;
        this.nome = nome;
        this.idade = 0;
        this.email = "";
        //this.numero = Aluno.novoAluno();
        this.numero = ultimoAluno;
        ultimoAluno++;
        this.setEstado("matriculado");
    }
    
}
