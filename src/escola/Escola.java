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
        Escola escola = new Escola();
        EscolaView mainView = new EscolaView();
        
        mainView.verAlunosView();
    }
    
}


 