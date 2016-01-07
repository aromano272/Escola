package escola;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EscolaView extends JFrame implements ActionListener {
    
    JFrame mainFrame = new JFrame("Escola");
    JPanel mainContentWrapper = new JPanel();
    
    JPanel menuPanel = new JPanel();
    JButton verNotasBtn = new JButton("Ver notas");
    JButton addNotasBtn = new JButton("Adicionar nota");
    JButton addAlunoBtn = new JButton("Adicionar aluno");
    JButton remAlunoBtn = new JButton("Remover aluno");
    JButton addCursoBtn = new JButton("Adicionar curso");
    JButton remCursoBtn = new JButton("Remover curso");
    JButton addCadeiraBtn = new JButton("Adicionar cadeira");
    JButton remCadeiraBtn = new JButton("Remover cadeira");
    
    EscolaView() {
        
        mainFrame.setLayout(new BorderLayout());
        
        int windowWidth = 800;
        int windowHeight = 600;
        mainFrame.setSize(windowWidth, windowHeight);
        //this.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("App bue fixe");
        
//        JPanel menuPanel = new JPanel();
//        JButton addAlunoBtn = new JButton("Adicionar aluno");
//        JButton remAlunoBtn = new JButton("Remover aluno");
//        JButton addCursoBtn = new JButton("Adicionar curso");
//        JButton remCursoBtn = new JButton("Remover curso");
//        JButton addCadeiraBtn = new JButton("Adicionar cadeira");
//        JButton remCadeiraBtn = new JButton("Remover cadeira");
//        JButton verNotasBtn = new JButton("Ver notas");
//        JButton addNotasBtn = new JButton("Adicionar nota");
        
        
        menuPanel.setLayout(new GridLayout(8,1));
        
        menuPanel.add(verNotasBtn);
        menuPanel.add(addNotasBtn);
        menuPanel.add(addAlunoBtn);
        menuPanel.add(remAlunoBtn);
        menuPanel.add(addCursoBtn);
        menuPanel.add(remCursoBtn);
        menuPanel.add(addCadeiraBtn);
        menuPanel.add(remCadeiraBtn);
        
        mainFrame.add(menuPanel, BorderLayout.WEST);
        
        
        
        mainFrame.add(mainContentWrapper, BorderLayout.CENTER);
        
        mainFrame.setVisible(true);
        
        addEventListeners();
        
    }
    
    private void addEventListeners() {
        ActionListener addAluno = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                addAlunoView();
            }
        };
        
        ActionListener verNotas = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                geralView(CursosList.get());
            }
        };
        
        
        addEvent(addAlunoBtn, addAluno);
        addEvent(verNotasBtn, verNotas);
        
    }
    
    public void geralView(Curso[] cursos) {
        JPanel mainContent = new JPanel(new GridLayout(20,1));
        for(int i = 0; i < CursosList.length(); i++) {
            int numeroAlunos = CursosList.get(i).alunosLength();
            
            for(int j = 0; j < numeroAlunos; j++) {
                JPanel holder = new JPanel();
                Curso curso = CursosList.get(i);
                
                JLabel numeroAluno = new JLabel(String.valueOf(CursosList.get(i).getAluno(j).getNumero()));
                
                JLabel nomeAluno = new JLabel(CursosList.get(i).getAluno(j).getNome());
                JLabel idadeAluno = new JLabel(String.valueOf(CursosList.get(i).getAluno(j).getIdade()));
                JLabel emailAluno = new JLabel(CursosList.get(i).getAluno(j).getEmail());
                
                JLabel cursoAluno = new JLabel(CursosList.get(i).getNomeCurso());
                JLabel turmaAluno = new JLabel(String.valueOf(CursosList.get(i).getTurma()));
                
                JButton verNotas = new JButton("Ver notas");
                JButton removerAluno = new JButton("Remover");
                
                holder.add(numeroAluno);
                holder.add(nomeAluno);
                holder.add(idadeAluno);
                holder.add(emailAluno);
                holder.add(cursoAluno);
                holder.add(turmaAluno);
                holder.add(verNotas);
                holder.add(removerAluno);
                
                removerAluno.putClientProperty("numeroAluno", j);
                
                mainContent.add(holder);
                
                ActionListener removeBtn = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        Object source = ae.getSource();
                        JButton btn = (JButton)source;
                        // https://www.daniweb.com/programming/software-development/threads/410191/getclientproperty
                        int remIndex = (int)btn.getClientProperty("numeroAluno");
                        
                        removeAlunoObj(remIndex, curso);
                        
                    }
                };
                addEvent(removerAluno, removeBtn);
                
            }
        }
        
        mainContent.setBackground(Color.yellow);
        render(mainContent);
    }
    
    // mudar de sitio
    public void removeAlunoObj(int index, Curso curso) {
        curso.remAluno(index);
        geralView(CursosList.get());
    }
    
    public void addAlunoView() {
        
        JPanel mainContent = new JPanel(new GridLayout(10,1));
        
        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeTf = new JTextField(15);
        JLabel idadeLabel = new JLabel("Idade:");
        JTextField idadeTf = new JTextField(15);
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailTf = new JTextField(15);
        JPanel btnHolder = new JPanel();
        JButton addBtn = new JButton("Confirmar");
        JButton resBtn = new JButton("Cancelar");
        
        mainContent.add(nomeLabel);
        mainContent.add(nomeTf);
        mainContent.add(idadeLabel);
        mainContent.add(idadeTf);
        mainContent.add(emailLabel);
        mainContent.add(emailTf);
        btnHolder.add(addBtn);
        btnHolder.add(resBtn);
        mainContent.add(btnHolder);
        
        
        ActionListener addBtnAE = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                addAlunoObj(nomeTf, idadeTf, emailTf);
            }
        };
        ActionListener resBtnAE = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                resetAlunoInput(nomeTf, idadeTf, emailTf);
            }
        };
        addEvent(addBtn, addBtnAE);
        addEvent(resBtn, resBtnAE);
        
        mainContent.setBackground(Color.yellow);
        render(mainContent);
    }
    
    public void addAlunoObj(JTextField nomeTf, JTextField idadeTf, JTextField emailTf) {
        try {
            String nome = nomeTf.getText();
            int idade = Integer.parseInt(idadeTf.getText());
            String email = emailTf.getText();
            
            Aluno aluno = new Aluno(nome, idade, email);
            
            // adicionar a um curso especifico
            CursosList.get(1).addAluno(aluno);
           
            // colocar mais excepcoes
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(mainFrame, "Erro");
        } finally {
            clearInput(nomeTf, idadeTf, emailTf);
        }
        
    }
    
    public void resetAlunoInput(JTextField nomeTf, JTextField idadeTf, JTextField emailTf) {
        clearInput(nomeTf, idadeTf, emailTf);
    }
    
    
    public void addCadeiraObj(JTextField nomeTf, JTextField professorTf, JTextField emailProfessorTf, JTextField codigoClassroomTf, JTextField creditosTf) {
        try {
            String nome = nomeTf.getText();
            String professor = professorTf.getText();
            String emailProfessor = emailProfessorTf.getText();
            String codigoClassroom = codigoClassroomTf.getText();
            int creditos = Integer.parseInt(creditosTf.getText());
            
            Cadeira cadeira = new Cadeira(nome, professor, emailProfessor, codigoClassroom, creditos);
            // adicionar a um curso especifico
            CursosList.get(1).addCadeira(cadeira);
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(mainFrame, "Erro");
        } finally {
            clearInput(nomeTf, professorTf, emailProfessorTf, codigoClassroomTf, creditosTf);
        }   
    }
    
    public void resetCadeiraInput(JTextField nomeTf, JTextField professorTf, JTextField emailProfessorTf, JTextField codigoClassroomTf, JTextField creditosTf) {
        clearInput(nomeTf, professorTf, emailProfessorTf, codigoClassroomTf, creditosTf);
    }
    
    public void addCursoObj(JComboBox cursoCombo, JComboBox horarioCombo, JTextField coordenadorTf, JTextField emailCoordenadorTf) {
        
        String curso = cursoCombo.getSelectedItem().toString();
        String horario = horarioCombo.getSelectedItem().toString();
        String coordenador = coordenadorTf.getText();
        String emailCoordenador = emailCoordenadorTf.getText();
        
        if(curso.equals("Multimédia")) {
            Curso cursoObj = new CursoMultimedia(horario, coordenador, emailCoordenador);
            CursosList.add(cursoObj);
        } else {
            Curso cursoObj = new CursoInformatica(horario, coordenador, emailCoordenador);
            CursosList.add(cursoObj);
        }
        
    }
    
    public void resetCursoInput(JComboBox cursoCombo, JComboBox horarioCombo, JTextField coordenadorTf, JTextField emailCoordenadorTf) {
        clearInput(coordenadorTf, emailCoordenadorTf);
        cursoCombo.setSelectedIndex(0);
        horarioCombo.setSelectedIndex(0);
    }
    
    
    
    
    //             --->    HELPER FUNCTIONS    <---
    //      removes all the elements of the wrapper content panel and renders given panel
    public void render(JPanel mainContentPanel) {
        try {
            mainContentWrapper.removeAll();
        } catch(Exception e) {
            System.out.println(e);
        }
        mainContentWrapper.add(mainContentPanel, BorderLayout.CENTER);
        mainFrame.repaint();
        mainFrame.revalidate();
        
        
    }
    //     adds given event to a given jbutton
    public void addEvent(JButton button, ActionListener eventHandler) {
        button.addActionListener(eventHandler);
    }
    //     
    public void clearInput(JTextField tf1) {
        tf1.setText(null);
    }
    public void clearInput(JTextField tf1, JTextField tf2) {
        tf1.setText(null);
        tf2.setText(null);
    }
    public void clearInput(JTextField tf1, JTextField tf2, JTextField tf3) {
        tf1.setText(null);
        tf2.setText(null);
        tf3.setText(null);
    }
    public void clearInput(JTextField tf1, JTextField tf2, JTextField tf3, JTextField tf4) {
        tf1.setText(null);
        tf2.setText(null);
        tf3.setText(null);
        tf4.setText(null);
    }
    public void clearInput(JTextField tf1, JTextField tf2, JTextField tf3, JTextField tf4, JTextField tf5) {
        tf1.setText(null);
        tf2.setText(null);
        tf3.setText(null);
        tf4.setText(null);
        tf5.setText(null);
    }
    

    
    
    
    


    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}