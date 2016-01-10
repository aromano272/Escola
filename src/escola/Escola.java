/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escola;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


/**
 *
 * @author aRomano
 */
public class Escola implements Serializable {

    /**
     * @param args the command line arguments
     */
    
    
    private static ArrayList<Curso> Cursos = new ArrayList<>();
    private static ArrayList<Aluno> Alunos = new ArrayList<>();
    private static ArrayList<Cadeira> Cadeiras = new ArrayList<>();
    
    public static void addCurso(Curso curso) {
        Cursos.add(curso);
        serializeCursos();
    }
    
    public static Curso getCurso(int index) {
        return Cursos.get(index);
    }
    
    public static void editCurso() {
        serializeCursos();
    }
    
    public static void remCurso(Curso curso) {
        Cursos.remove(curso);
        serializeCursos();
    }
    
    public static int cursosLength() {
        try {
            return Cursos.size();
        } catch(Exception ex) {
            return 0;
        }        
    }
    
    public static void addAluno(Aluno aluno) {
        Alunos.add(aluno);
        serializeAlunos();
    }
    
    public static Aluno getAluno(int index) {
        return Alunos.get(index);
    }
    
    public static void editAluno() {
        serializeAlunos();
    }
    
    public static void remAluno(Aluno aluno) {
        Alunos.remove(aluno);
        serializeAlunos();
    }
    
    public static int alunosLength() {
        try {
            return Alunos.size();
        } catch(Exception ex) {
            return 0;
        }
    }
    
    public static void addCadeira(Cadeira cadeira) {
        Cadeiras.add(cadeira);
        serializeCadeiras();
    }
    
    public static Cadeira getCadeira(int index) {
        return Cadeiras.get(index);
    }
    
    public static void editCadeira() {
        serializeCadeiras();
    }
    
    public static void remCadeira(Cadeira cadeira) {
        Cadeiras.remove(cadeira);
        serializeCadeiras();
    }
    
    public static int cadeirasLength() {
        try {
            return Cadeiras.size();
        } catch(Exception ex) {
            return 0;
        }
    }
    
    
    public static void serializeAlunos() {
        try {
            FileOutputStream fileOut = new FileOutputStream("db/AlunosList.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Escola.Alunos);
            out.flush();
            out.close();
            fileOut.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("serialize");
    }
    
    public static void serializeCadeiras() {
        try {
            FileOutputStream fileOut = new FileOutputStream("db/CadeirasList.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Escola.Cadeiras);
            out.flush();
            out.close();
            fileOut.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("serialize");
    }
    
    public static void serializeCursos() {
        try {
            FileOutputStream fileOut = new FileOutputStream("db/CursosList.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Escola.Cursos);
            out.flush();
            out.close();
            fileOut.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("serialize");
    }
    
    public static void deserializeAlunos() {
        try {
            FileInputStream fileIn = new FileInputStream("db/AlunosList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Escola.Alunos = (ArrayList) in.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void deserializeCadeiras() {
        try {
            FileInputStream fileIn = new FileInputStream("db/CadeirasList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Escola.Cadeiras = (ArrayList) in.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void deserializeCursos() {
        try {
            FileInputStream fileIn = new FileInputStream("db/CursosList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Escola.Cursos = (ArrayList) in.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    Escola() {
        deserializeCursos();
        deserializeCadeiras();
        deserializeAlunos();
    }
    
    public static void main(String[] args) {
        /*
        CursoInformatica curso1 = new CursoInformatica("Noturno", "Desconhecido", "coordenador@escola.pt");
        CursoInformatica curso2 = new CursoInformatica("2222", "Desconh2222ecido", "coorden2222ador@escola.pt");
        CursoMultimedia curso3 = new CursoMultimedia("Notu3333rno", "Desconh3333ecido", "coorde3333nador@escola.pt");
        CursoInformatica curso4 = new CursoInformatica("Notu4444rno", "Descon4444hecido", "coorden4444ador@escola.pt");
        CursoMultimedia curso5 = new CursoMultimedia("Notur5555no", "Desconh5555ecido", "coorde5555nador@escola.pt");
        
        Escola.addCurso(curso1);
        Escola.addCurso(curso2);
        Escola.addCurso(curso3);
        Escola.addCurso(curso4);
        Escola.addCurso(curso5);
        
        Cadeira mat = new Cadeira();
        /*
        Aluno aluno1 = new Aluno(curso1, 0, "Andre1");
        Aluno aluno2 = new Aluno(curso2, 0, "Andre2");
        Aluno aluno3 = new Aluno(curso3, 0, "Andre3");
        Aluno aluno4 = new Aluno(curso4, 0, "Andre4");
        Aluno aluno5 = new Aluno(curso5, 0, "Andre5");
        Aluno aluno6 = new Aluno(curso2, 0, "Andre6");
        Aluno aluno7 = new Aluno(curso4, 0, "Ruben");
        Escola.addAluno(aluno1);
        Escola.addAluno(aluno2);
        Escola.addAluno(aluno3);
        Escola.addAluno(aluno4);
        Escola.addAluno(aluno5);
        Escola.addAluno(aluno6);
        Escola.addAluno(aluno7);
        
        
        
        Cadeira cadeira1 = new Cadeira("AED", "John Cena", 3);
        Cadeira cadeira2 = new Cadeira("DDM", "Joana Cena", 6);
        Escola.addCadeira(cadeira1);
        Escola.addCadeira(cadeira2);
        
        Nota nota1 = new Nota(Escola.getCadeira(1), 10f);
        Nota nota2 = new Nota(Escola.getCadeira(1), 10f);
        Nota nota3 = new Nota(Escola.getCadeira(1), 10f);
        Nota nota4 = new Nota(Escola.getCadeira(1), 10f);
        Nota nota5 = new Nota(Escola.getCadeira(0), 10f);
        Nota nota6 = new Nota(Escola.getCadeira(0), 10f);
        Nota nota7 = new Nota(Escola.getCadeira(0), 10f);
        Nota nota8 = new Nota(Escola.getCadeira(0), 10f);
        /*
        Escola.getAluno(0).addNota(nota1);
        Escola.getAluno(0).addNota(nota2);
        Escola.getAluno(0).addNota(nota3);
        Escola.getAluno(0).addNota(nota4);
        Escola.getAluno(0).addNota(nota5);
        Escola.getAluno(0).addNota(nota6);
        Escola.getAluno(0).addNota(nota7);
        Escola.getAluno(0).addNota(nota8);
        */
        Escola escola = new Escola();
        EscolaView mainView = new EscolaView();
        
        
        mainView.verAlunosView();
        
        
    }
    
}


 