package escola;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class EscolaView extends JFrame implements ActionListener {
    
    
    JFrame mainFrame = new JFrame("Escola");
    JPanel mainContentWrapper = new JPanel();
    
    JPanel menuPanel = new JPanel();
    JButton verAlunosBtn = new JButton("Alunos");
    JButton verCadeirasBtn = new JButton("Cadeiras");
    JButton verCursosBtn = new JButton("Cursos");
    
    EscolaView() {
        
        mainFrame.setLayout(new BorderLayout());
        
        int windowWidth = 800;
        int windowHeight = 600;
        mainFrame.setSize(windowWidth, windowHeight);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("App bue fixe");
        mainFrame.setLayout(new BorderLayout());

        menuPanel.setLayout(new GridLayout(8,1));
        
        menuPanel.add(verAlunosBtn);
        menuPanel.add(verCadeirasBtn);
        menuPanel.add(verCursosBtn);
        
        mainFrame.add(menuPanel, BorderLayout.WEST);
        
        
        
        mainFrame.add(mainContentWrapper, BorderLayout.CENTER);
        mainContentWrapper.setLayout(new BorderLayout());
        mainContentWrapper.setBackground(Color.blue);
        
        mainFrame.setVisible(true);
        
        addEventListeners();
        
    }

    
    private void addEventListeners() {
        ActionListener verAlunos = (ActionEvent ae) -> {
            verAlunosView();
        };
       
        ActionListener verCursos = (ActionEvent ae) -> {
            verCursosView();
        };
        
        ActionListener verCadeiras = (ActionEvent ae) -> {
            verCadeirasView();
        };
        
        addEvent(verAlunosBtn, verAlunos);
        addEvent(verCursosBtn, verCursos);
        addEvent(verCadeirasBtn, verCadeiras);
    }
    
    public void verCursosView() {
        JPanel mainContent = new JPanel(new GridLayout(3,1));
        
        JPanel principalPanel = new JPanel();
        
        JButton addaluno = new JButton("Add Aluno");
        principalPanel.add (addaluno);
        addaluno.setBounds(200,100,30,20);
        
        JPanel legendasPanel = new JPanel();
        
        JLabel cursos = new JLabel("Cursos");
        legendasPanel.add (cursos);
        JLabel creditos = new JLabel("Creditos");
        legendasPanel.add (creditos);
        JLabel turmas = new JLabel("Turmas");
        legendasPanel.add (turmas);
        JLabel alunos = new JLabel("Alunos");
        legendasPanel.add (alunos);
        
        JPanel conteudoPanel = new JPanel();
        conteudoPanel.setLayout(null);
        
        
        JScrollPane conteudoScroll = new JScrollPane();
        //conteudoScroll.setBounds(240, 200, 270, 200);
        conteudoPanel.add(conteudoScroll);
            
        mainContent.add(principalPanel);
        mainContent.add(legendasPanel);
        mainContent.add(conteudoScroll);
        
        mainContent.setBackground(Color.black);
        render(mainContent);
    }
    
    public void verCadeiras() {
        
    }
    
    public void verAlunosView() {
        JPanel mainContent = new JPanel(new GridLayout(20,1));
        for(int i = 0; i < Escola.alunosLength(); i++) {
            Aluno aluno = Escola.getAluno(i);
            JPanel holder = new JPanel();

            JLabel numeroAluno = new JLabel(String.valueOf(aluno.getNumero()));

            JLabel nomeAluno = new JLabel(aluno.getNome());
            JLabel idadeAluno = new JLabel(String.valueOf(aluno.getIdade()));
            JLabel emailAluno = new JLabel(aluno.getEmail());
            
            //int cursoIndex = aluno.getCursoIndex();
            JLabel cursoAluno = new JLabel(aluno.getCurso().getNomeCurso());

            JButton verNotas = new JButton("Ver notas");
            JButton removerAluno = new JButton("Remover");

            holder.add(numeroAluno);
            holder.add(nomeAluno);
            holder.add(idadeAluno);
            holder.add(emailAluno);
            holder.add(cursoAluno);
            holder.add(verNotas);
            holder.add(removerAluno);

            //removerAluno.putClientProperty("numeroAluno", aluno.getNumero());

            mainContent.add(holder);

            ActionListener removeBtnEvt = (ActionEvent ae) -> {
                //Object source = ae.getSource();
                //JButton btn = (JButton)source;
                // https://www.daniweb.com/programming/software-development/threads/410191/getclientproperty
                //int remIndex = (int)btn.getClientProperty("numeroAluno");
                
                removeAlunoObj(aluno);
            };
            
            // rename verNotasABtn para verNotasBtn
            ActionListener verNotasABtnEvt = (ActionEvent ae) -> {
                verNotasView(aluno);
            };
            
            addEvent(removerAluno, removeBtnEvt);
            addEvent(verNotas, verNotasABtnEvt);
        }
        
        mainContent.setBackground(Color.yellow);
        render(mainContent);
    }
    
    public void verNotasView(Aluno aluno) {
        JPanel mainContent = new JPanel(new GridLayout(10,1));
        /*
        for (Nota nota : aluno.getNotas()) {
            JPanel holder = new JPanel();
            
            JLabel cadeiraLabel = new JLabel(nota.getCadeira().getNome());
            JLabel valorLabel = new JLabel(String.valueOf(nota.getValor()));
            
            holder.add(cadeiraLabel);
            holder.add(valorLabel);
            
            mainContent.add(holder);
        }*/
        for (int i = 0; i < Escola.cadeirasLength(); i++) {
            boolean hasTitulo = false;
            Cadeira cadeira = Escola.getCadeira(i);
            JPanel cadeiraWrapper = new JPanel();
            
            for(int j = 0; j < aluno.getNotas().size(); j++) {
                Nota nota = aluno.getNotas().get(j);
                if(nota.getCadeira() == cadeira) {
                    if(!hasTitulo) {
                        JLabel cadeiraLabel = new JLabel(nota.getCadeira().getNome());
                        JLabel valorLabel = new JLabel(String.valueOf(nota.getValor()));
                        cadeiraWrapper.add(cadeiraLabel);
                        cadeiraWrapper.add(valorLabel);
                        hasTitulo = true;
                    } else {
                        JLabel valorLabel = new JLabel(String.valueOf(nota.getValor()));
                        cadeiraWrapper.add(valorLabel);
                    }
                }
                if(j == (aluno.getNotas().size() - 1)) {
                    mainContent.add(cadeiraWrapper);
                }
            }
        }
        mainContent.setBackground(Color.yellow);
        render(mainContent);
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
            // como ir buscar o indice à combobox, talvez ir buscar o obj directamente
            int cursoIndex = 0;
            Curso curso = Escola.getCurso(0);
            
            Aluno aluno = new Aluno(curso, cursoIndex, nome, idade, email);
            
            // adicionar a um curso especifico
            Escola.addAluno(aluno);
           
            // colocar mais excepcoes
        } catch(Exception ex) {
//            JOptionPane.showMessageDialog(mainFrame, "Erro");
            ex.printStackTrace();
        } finally {
            clearInput(nomeTf, idadeTf, emailTf);
        }
        
    }
    
    public void removeAlunoObj(Aluno aluno) {
        Escola.remAluno(aluno);
        verAlunosView();
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
//            JOptionPane.showMessageDialog(mainFrame, "Erro");
            ex.printStackTrace();
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