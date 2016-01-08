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
        ActionListener verAlunos = (ActionEvent evt) -> {
            verAlunosView();
        };
       
        ActionListener verCursos = (ActionEvent evt) -> {
            verCursosView();
        };
        
        ActionListener verCadeiras = (ActionEvent evt) -> {
            verCadeirasView();
        };
        
        addEvent(verAlunosBtn, verAlunos);
        addEvent(verCursosBtn, verCursos);
        addEvent(verCadeirasBtn, verCadeiras);
    }
    
    public void verCursosView() {
        JPanel mainContent = new JPanel(new GridLayout(3,1));
        
        JPanel principalPanel = new JPanel();
        
        JButton addCursoBtn = new JButton("Add Curso");
        principalPanel.add (addCursoBtn);
        
        
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
        
        ActionListener addCursoEvt = (ActionEvent evt) -> {
            addCursoView();
        };
        
        addEvent(addCursoBtn, addCursoEvt);
        
        mainContent.setBackground(Color.black);
        render(mainContent);
    }
    
    public void addCursoView() {
        JPanel mainContent = new JPanel();
        JButton addBtn = new JButton("Adicionar");
        JButton resBtn = new JButton("Cancelar");
        JLabel coordenadorLabel = new JLabel("Coordenador:");
        JTextField coordenadorTf = new JTextField(10);
        JLabel coordenadorEmailLabel = new JLabel("Email do Coordenador:");
        JTextField coordenadorEmailTf = new JTextField(10);
        JLabel cursoComboLabel = new JLabel("Tipo de curso:");
        JComboBox<String> cursoCombo = new JComboBox<>();
        cursoCombo.addItem("Informática");
        cursoCombo.addItem("Multimédia");
        JLabel horarioComboLabel = new JLabel("Horario:");
        JComboBox<String> horarioCombo = new JComboBox<>();
        horarioCombo.addItem("Diurno");
        horarioCombo.addItem("Pós-Laboral");
        
        
        mainContent.add(cursoComboLabel);
        mainContent.add(cursoCombo);
        
        mainContent.add(horarioComboLabel);
        mainContent.add(horarioCombo);
        
        mainContent.add(coordenadorLabel);
        mainContent.add(coordenadorTf);
        
        mainContent.add(coordenadorEmailLabel);
        mainContent.add(coordenadorEmailTf);
        mainContent.add(addBtn);
        mainContent.add(resBtn);
        
        
        ActionListener addEvt = (ActionEvent evt) -> {
            addCursoObj(cursoCombo, horarioCombo, coordenadorTf, coordenadorEmailTf);
        };
        
        ActionListener resEvt = (ActionEvent evt) -> {
            resetCursoInput(cursoCombo, horarioCombo, coordenadorTf, coordenadorEmailTf);
        };
        
        addEvent(addBtn, addEvt);
        addEvent(resBtn, resEvt);
        
        render(mainContent);
    }
    
    public void addCursoObj(JComboBox cursoCombo, JComboBox horarioCombo, JTextField coordenadorTf, JTextField emailCoordenadorTf) {
        try {
            String curso = cursoCombo.getSelectedItem().toString();
            String horario = horarioCombo.getSelectedItem().toString();
            String coordenador = coordenadorTf.getText();
            String emailCoordenador = emailCoordenadorTf.getText();
            
            if(curso.equals("Informática")) {
                Curso cursoObj = new CursoInformatica(horario, coordenador, emailCoordenador);
                Escola.addCurso(cursoObj);
            } else {
                Curso cursoObj = new CursoMultimedia(horario, coordenador, emailCoordenador);
                Escola.addCurso(cursoObj);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            resetCursoInput(cursoCombo, horarioCombo, coordenadorTf, emailCoordenadorTf);
        }
    }
    
    public void resetCursoInput(JComboBox cursoCombo, JComboBox horarioCombo, JTextField coordenadorTf, JTextField emailCoordenadorTf) {
        clearInput(coordenadorTf, emailCoordenadorTf);
        cursoCombo.setSelectedIndex(0);
        horarioCombo.setSelectedIndex(0);
    }
    
    public void removeCursoObj(Curso curso) {
        Escola.remCurso(curso);
        verCursosView();
    }
    
    public void verCadeirasView() {
        JPanel mainContent = new JPanel();
        
        JButton addCadeiraBtn = new JButton("Adicionar Cadeira");
        
        ActionListener addCadeiraEvt = (ActionEvent evt) -> {
            addCadeiraView();
        };
        
        mainContent.add(addCadeiraBtn);
        
        addEvent(addCadeiraBtn, addCadeiraEvt);
        render(mainContent);
    }
    
    public void addCadeiraView() {
        JPanel mainContent = new JPanel();
        JButton addBtn = new JButton("Adicionar");
        JButton resBtn = new JButton("Cancelar");
        JTextField professorTf = new JTextField(10);
        JTextField professorEmailTf = new JTextField(10);
        JTextField nomeTf = new JTextField(10);
        JTextField codigoClassroomTf = new JTextField(10);
        JComboBox<Integer> creditosCombo = new JComboBox<>();
        creditosCombo.addItem(3);
        creditosCombo.addItem(6);
        creditosCombo.addItem(9);
        JLabel nomeLabel = new JLabel("Nome da cadeira:");
        JLabel creditosComboLabel = new JLabel("Creditos:");
        JLabel professorLabel = new JLabel("Nome do professor:");
        JLabel professorEmailLabel = new JLabel("Email do professor:");
        JLabel codigoClassroomLabel = new JLabel("Codigo da Classroom:");
        
        
        mainContent.add(nomeLabel);
        mainContent.add(nomeTf);
        mainContent.add(creditosComboLabel);
        mainContent.add(creditosCombo);
        mainContent.add(professorLabel);
        mainContent.add(professorTf);
        mainContent.add(professorEmailLabel);
        mainContent.add(professorEmailTf);
        mainContent.add(codigoClassroomLabel);
        mainContent.add(codigoClassroomTf);
        mainContent.add(addBtn);
        mainContent.add(resBtn);
        
        
        ActionListener addEvt = (ActionEvent evt) -> {
            addCadeiraObj(nomeTf, professorTf, professorEmailTf, codigoClassroomTf, creditosCombo);
        };
        
        ActionListener resEvt = (ActionEvent evt) -> {
            resetCadeiraInput(nomeTf, professorTf, professorEmailTf, codigoClassroomTf, creditosCombo);
        };
        
        addEvent(addBtn, addEvt);
        addEvent(resBtn, resEvt);
        render(mainContent);
    }
    
    public void addCadeiraObj(JTextField nomeTf, JTextField professorTf, JTextField emailProfessorTf, JTextField codigoClassroomTf, JComboBox creditosCombo) {
        try {
            String nome = nomeTf.getText();
            String professor = professorTf.getText();
            String emailProfessor = emailProfessorTf.getText();
            String codigoClassroom = codigoClassroomTf.getText();
            int creditos = (int)creditosCombo.getSelectedItem();
            
            Cadeira cadeira = new Cadeira(nome, professor, emailProfessor, codigoClassroom, creditos);
            
            Escola.addCadeira(cadeira);
        } catch(Exception ex) {
//            JOptionPane.showMessageDialog(mainFrame, "Erro");
            ex.printStackTrace();
        } finally {
            resetCadeiraInput(nomeTf, professorTf, emailProfessorTf, codigoClassroomTf, creditosCombo);
        }
    }
    
    public void resetCadeiraInput(JTextField nomeTf, JTextField professorTf, JTextField emailProfessorTf, JTextField codigoClassroomTf, JComboBox creditosCombo) {
        clearInput(nomeTf, professorTf, emailProfessorTf, codigoClassroomTf);
        creditosCombo.setSelectedIndex(0);
    }
    
    public void removeCadeiraObj(Cadeira cadeira) {
        Escola.remCadeira(cadeira);
        verCadeirasView();
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

            ActionListener removeBtnEvt = (ActionEvent evt) -> {
                //Object source = ae.getSource();
                //JButton btn = (JButton)source;
                // https://www.daniweb.com/programming/software-development/threads/410191/getclientproperty
                //int remIndex = (int)btn.getClientProperty("numeroAluno");
                
                removeAlunoObj(aluno);
            };
            
            // rename verNotasABtn para verNotasBtn
            ActionListener verNotasABtnEvt = (ActionEvent evt) -> {
                verNotasView(aluno);
            };
            
            addEvent(removerAluno, removeBtnEvt);
            addEvent(verNotas, verNotasABtnEvt);
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
        
        
        ActionListener addBtnEvt = (ActionEvent evt) -> {
            addAlunoObj(nomeTf, idadeTf, emailTf);
        };
        ActionListener resBtnEvt = (ActionEvent evt) -> {
            resetAlunoInput(nomeTf, idadeTf, emailTf);
        };
        addEvent(addBtn, addBtnEvt);
        addEvent(resBtn, resBtnEvt);
        
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
            resetAlunoInput(nomeTf, idadeTf, emailTf);
        }
        
    }
    
    public void removeAlunoObj(Aluno aluno) {
        Escola.remAluno(aluno);
        verAlunosView();
    }
    
    public void resetAlunoInput(JTextField nomeTf, JTextField idadeTf, JTextField emailTf) {
        clearInput(nomeTf, idadeTf, emailTf);
    }
    
    public void verNotasView(Aluno aluno) {
        JPanel mainContent = new JPanel(new GridLayout(10,1));
        JButton addNotaBtn = new JButton("Adicionar nota");
        mainContent.add(addNotaBtn);
        
        
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
        
        ActionListener addNotaEvt = (ActionEvent evt) -> {
            addNotaView(aluno);
        };
        
        addEvent(addNotaBtn, addNotaEvt);
        
        mainContent.setBackground(Color.yellow);
        render(mainContent);
    }
    
    public void addNotaView(Aluno aluno) {
        JPanel mainContent = new JPanel();
        JLabel cadeiraComboLabel = new JLabel("Cadeira:");
        JComboBox cadeiraCombo = new JComboBox();
        JLabel valorLabel = new JLabel("Valor:");
        JTextField valorTf = new JTextField(10);
        JButton addBtn = new JButton("Adicionar");
        JButton resBtn = new JButton("Cancelar");
        
        mainContent.add(cadeiraComboLabel);
        mainContent.add(cadeiraCombo);
        mainContent.add(valorLabel);
        mainContent.add(valorTf);
        mainContent.add(addBtn);
        mainContent.add(resBtn);
        
        ActionListener addBtnEvt = (ActionEvent evt) -> {
            addNotaObj(aluno, cadeiraCombo, valorTf);
        };
        ActionListener resBtnEvt = (ActionEvent evt) -> {
            resetNotaInput(cadeiraCombo, valorTf);
        };
        addEvent(addBtn, addBtnEvt);
        addEvent(resBtn, resBtnEvt);
        
        render(mainContent);
    }
    
    public void addNotaObj(Aluno aluno, JComboBox cadeiraCombo, JTextField valorTf) {
        
    }
    
    public void resetNotaInput(JComboBox cadeiraCombo, JTextField valorTf) {
        clearInput(valorTf);
        cadeiraCombo.setSelectedIndex(0);
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
    public void actionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}