package escola;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EscolaView extends JFrame implements ActionListener {
    private final JFrame mainFrame = new JFrame("Escola");
    private final JPanel mainContentWrapper = new JPanel();
    private final JPanel menuPanel = new JPanel();
    private final JButton verAlunosBtn = new JButton("Alunos");
    private final JButton verCadeirasBtn = new JButton("Cadeiras");
    private final JButton verCursosBtn = new JButton("Cursos");
    
    EscolaView() {
        
        mainFrame.setLayout(new BorderLayout());
        
        int windowWidth = 1000;
        int windowHeight = 600;
        mainFrame.setSize(windowWidth, windowHeight);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Gestor de Escola");
        mainFrame.setLayout(new BorderLayout());
        menuPanel.setLayout(new GridLayout(4,1));
        
        menuPanel.add(verAlunosBtn);
        menuPanel.add(verCadeirasBtn);
        menuPanel.add(verCursosBtn);
        mainFrame.add(menuPanel, BorderLayout.WEST);
        
        mainFrame.add(mainContentWrapper, BorderLayout.CENTER);
        mainContentWrapper.setLayout(new BorderLayout());
        mainContentWrapper.setBackground(Color.blue);
        mainFrame.setVisible(true);
        
        addMenuEventListeners();
    }
    
    private void addMenuEventListeners() {
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
    
    public void verAlunosView() {
        JPanel mainContent = new JPanel(new BorderLayout());
        JPanel menubarPanel = new JPanel();
        JPanel legendasPanel = new JPanel(new GridLayout(1,9));
        JPanel conteudoPanel = new JPanel(new BorderLayout());
        JPanel header = new JPanel(new GridLayout(2,1));
        
        JButton addAlunoBtn = new JButton("Adicionar aluno");
        menubarPanel.add(addAlunoBtn);
        
        JLabel numeroLegLabel = new JLabel("Numero");
        JLabel nomeLegLabel = new JLabel("Nome");
        JLabel idadeLegLabel = new JLabel("Idade");
        JLabel emailLegLabel = new JLabel("Email");
        JLabel cursoLegLabel = new JLabel("Curso");
        JLabel mediaLegLabel = new JLabel("Media");
        legendasPanel.add(numeroLegLabel);
        legendasPanel.add(nomeLegLabel);
        legendasPanel.add(idadeLegLabel);
        legendasPanel.add(emailLegLabel);
        legendasPanel.add(cursoLegLabel);
        legendasPanel.add(mediaLegLabel);
        
        JLabel spacer1 = new JLabel("");
        JLabel spacer2 = new JLabel("");
        JLabel spacer3 = new JLabel("");
        legendasPanel.add(spacer1);
        legendasPanel.add(spacer2);
        legendasPanel.add(spacer3);
        
        numeroLegLabel.setBackground(Color.gray);
        numeroLegLabel.setOpaque(true);
        nomeLegLabel.setBackground(Color.lightGray);
        nomeLegLabel.setOpaque(true);
        idadeLegLabel.setBackground(Color.gray);
        idadeLegLabel.setOpaque(true);
        emailLegLabel.setBackground(Color.lightGray);
        emailLegLabel.setOpaque(true);
        cursoLegLabel.setBackground(Color.gray);
        cursoLegLabel.setOpaque(true);
        mediaLegLabel.setBackground(Color.lightGray);
        mediaLegLabel.setOpaque(true);
        
        
        for(int i = 0; i < Escola.alunosLength(); i++) {
            Aluno aluno = Escola.getAluno(i);
            JPanel holder = new JPanel(new GridLayout(1, 9));
            
            JLabel numeroAluno = new JLabel(String.valueOf(aluno.getNumero()));
            JLabel nomeAluno = new JLabel(aluno.getNome());
            JLabel idadeAluno = new JLabel(String.valueOf(aluno.getIdade()));
            JLabel emailAluno = new JLabel(aluno.getEmail());
            JLabel cursoAluno = new JLabel(aluno.getCurso().getNomeCurso());
            JLabel mediaAluno = new JLabel(String.valueOf(aluno.getMedia()));
            JButton alterarBtn = new JButton("Alterar");
            JButton verNotasBtn = new JButton("Ver notas");
            JButton removerAlunoBtn = new JButton("Remover");
            
            numeroAluno.setBackground(Color.gray);
            numeroAluno.setOpaque(true);
            nomeAluno.setBackground(Color.lightGray);
            nomeAluno.setOpaque(true);
            idadeAluno.setBackground(Color.gray);
            idadeAluno.setOpaque(true);
            emailAluno.setBackground(Color.lightGray);
            emailAluno.setOpaque(true);
            cursoAluno.setBackground(Color.gray);
            cursoAluno.setOpaque(true);
            mediaAluno.setBackground(Color.lightGray);
            mediaAluno.setOpaque(true);
            holder.setBorder(BorderFactory.createMatteBorder(1,0,0,0,Color.darkGray));
            
            holder.add(numeroAluno);
            holder.add(nomeAluno);
            holder.add(idadeAluno);
            holder.add(emailAluno);
            holder.add(cursoAluno);
            holder.add(mediaAluno);
            holder.add(alterarBtn);
            holder.add(verNotasBtn);
            holder.add(removerAlunoBtn);
            conteudoPanel.add(holder, BorderLayout.NORTH);
            
            ActionListener alterarBtnEvt = (ActionEvent evt) -> {
                editAlunoView(aluno);
            };
            
            ActionListener verNotasBtnEvt = (ActionEvent evt) -> {
                verNotasView(aluno);
            };
            
            ActionListener removeBtnEvt = (ActionEvent evt) -> {
                removeAlunoObj(aluno);
            };
            
            addEvent(alterarBtn, alterarBtnEvt);
            addEvent(removerAlunoBtn, removeBtnEvt);
            addEvent(verNotasBtn, verNotasBtnEvt);
        }
        
        ActionListener addBtnEvt = (ActionEvent evt) -> {
            addAlunoView();
        };
        
        addEvent(addAlunoBtn, addBtnEvt);
        
        header.add(menubarPanel);
        header.add(legendasPanel);
        mainContent.add(header, BorderLayout.PAGE_START);
        mainContent.add(conteudoPanel, BorderLayout.CENTER);
        
        render(mainContent);
    }
    
    public void addAlunoView() {
        JPanel mainContent = new JPanel(new GridLayout(9,1));
        
        JLabel cursoComboLabel = new JLabel("Curso:");
        JComboBox<String> cursoCombo = new JComboBox<>();
        
        // talvez dar sort aos cursos por tipo de curso
        for(int i = 0; i < Escola.cursosLength(); i++) {
            cursoCombo.addItem(Escola.getCurso(i).getNomeCurso());
        }
        
        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeTf = new JTextField(15);
        JLabel idadeLabel = new JLabel("Idade:");
        JTextField idadeTf = new JTextField(15);
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailTf = new JTextField(15);
        JPanel btnHolder = new JPanel();
        JButton addBtn = new JButton("Confirmar");
        JButton resBtn = new JButton("Cancelar");
        
        mainContent.add(cursoComboLabel);
        mainContent.add(cursoCombo);
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
            addAlunoObj(cursoCombo, nomeTf, idadeTf, emailTf);
            verAlunosView();
        };
        ActionListener resBtnEvt = (ActionEvent evt) -> {
            resetAlunoInput(cursoCombo, nomeTf, idadeTf, emailTf);
            verAlunosView();
        };
        addEvent(addBtn, addBtnEvt);
        addEvent(resBtn, resBtnEvt);
        
        
        render(mainContent);
    }
    
    public void editAlunoView(Aluno aluno) {
        JPanel mainContent = new JPanel(new GridLayout(9,1));
        
        JLabel cursoComboLabel = new JLabel("Curso:");
        JComboBox<String> cursoCombo = new JComboBox<>();
        
        // talvez dar sort aos cursos por tipo de curso
        for(int i = 0; i < Escola.cursosLength(); i++) {
            cursoCombo.addItem(Escola.getCurso(i).getNomeCurso());
        }
        cursoCombo.setSelectedIndex(aluno.getCursoIndex());
        
        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeTf = new JTextField(15);
        nomeTf.setText(aluno.getNome());
        JLabel idadeLabel = new JLabel("Idade:");
        JTextField idadeTf = new JTextField(15);
        idadeTf.setText(String.valueOf(aluno.getIdade()));
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailTf = new JTextField(15);
        emailTf.setText(aluno.getEmail());
        JPanel btnHolder = new JPanel();
        JButton editBtn = new JButton("Actualizar");
        JButton resBtn = new JButton("Cancelar");
        
        mainContent.add(cursoComboLabel);
        mainContent.add(cursoCombo);
        mainContent.add(nomeLabel);
        mainContent.add(nomeTf);
        mainContent.add(idadeLabel);
        mainContent.add(idadeTf);
        mainContent.add(emailLabel);
        mainContent.add(emailTf);
        btnHolder.add(editBtn);
        btnHolder.add(resBtn);
        mainContent.add(btnHolder);
        
        
        ActionListener editBtnEvt = (ActionEvent evt) -> {
            editAlunoObj(aluno, cursoCombo, nomeTf, idadeTf, emailTf);
        };
        ActionListener resBtnEvt = (ActionEvent evt) -> {
            verAlunosView();
        };
        addEvent(editBtn, editBtnEvt);
        addEvent(resBtn, resBtnEvt);
        
        
        render(mainContent);
    }
    
    public void editAlunoObj(Aluno aluno, JComboBox cursoCombo, JTextField nomeTf, JTextField idadeTf, JTextField emailTf) {
        try {
            String nome = nomeTf.getText();
            int idade = Integer.parseInt(idadeTf.getText());
            String email = emailTf.getText();
            
            Curso curso = Escola.getCurso(cursoCombo.getSelectedIndex());
            
            aluno.setCurso(curso);
            aluno.setNome(nome);
            aluno.setIdade(idade);
            aluno.setEmail(email);
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(mainFrame, "Erro");
        } finally {
            Escola.editAluno();
            verAlunosView();
        }
    }
    
    public void addAlunoObj(JComboBox cursoCombo, JTextField nomeTf, JTextField idadeTf, JTextField emailTf) {
        try {
            String nome = nomeTf.getText();
            int idade = Integer.parseInt(idadeTf.getText());
            String email = emailTf.getText();
            
            Curso curso = Escola.getCurso(cursoCombo.getSelectedIndex());
            
            Aluno aluno = new Aluno(curso, nome, idade, email);
            
            Escola.addAluno(aluno);
            
            // colocar mais excepcoes
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(mainFrame, "Erro");
        } finally {
            verAlunosView();
        }
    }
    
    public void removeAlunoObj(Aluno aluno) {
        Escola.remAluno(aluno);
        verAlunosView();
    }
    
    public void resetAlunoInput(JComboBox cursoCombo, JTextField nomeTf, JTextField idadeTf, JTextField emailTf) {
        clearInput(nomeTf, idadeTf, emailTf);
        cursoCombo.setSelectedIndex(0);
    }
    
    public void verNotasView(Aluno aluno) {
        JPanel mainContent = new JPanel(new GridLayout(10,1));
        JButton addNotaBtn = new JButton("Adicionar nota");
        mainContent.add(addNotaBtn);
        
        for (int i = 0; i < Escola.cadeirasLength(); i++) {
            boolean hasTitulo = false;
            Cadeira cadeira = Escola.getCadeira(i);
            JPanel cadeiraWrapper = new JPanel();
            
            for(int j = 0; j < aluno.getNotas().size(); j++) {
                Nota nota = aluno.getNotas().get(j);
                if(nota.getCadeira().getNome().equals(cadeira.getNome())) {
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
        
        
        render(mainContent);
    }
    
    public void addNotaView(Aluno aluno) {
        JPanel mainContent = new JPanel(new GridLayout(5,1));
        JPanel btnHolder = new JPanel();
        JLabel cadeiraComboLabel = new JLabel("Cadeira:");
        JComboBox cadeiraCombo = new JComboBox();
        
        for(int i = 0; i < Escola.cadeirasLength(); i++) {
            cadeiraCombo.addItem(Escola.getCadeira(i).getNome());
        }
        
        JLabel valorLabel = new JLabel("Valor:");
        JTextField valorTf = new JTextField(10);
        JButton addBtn = new JButton("Adicionar");
        JButton resBtn = new JButton("Cancelar");
        
        mainContent.add(cadeiraComboLabel);
        mainContent.add(cadeiraCombo);
        mainContent.add(valorLabel);
        mainContent.add(valorTf);
        btnHolder.add(addBtn);
        btnHolder.add(resBtn);
        mainContent.add(btnHolder);
        
        ActionListener addBtnEvt = (ActionEvent evt) -> {
            addNotaObj(aluno, cadeiraCombo, valorTf);
        };
        ActionListener resBtnEvt = (ActionEvent evt) -> {
            verNotasView(aluno);
        };
        addEvent(addBtn, addBtnEvt);
        addEvent(resBtn, resBtnEvt);
        
        render(mainContent);
    }
    
    public void addNotaObj(Aluno aluno, JComboBox cadeiraCombo, JTextField valorTf) {
        try {
            Cadeira cadeira = Escola.getCadeira(cadeiraCombo.getSelectedIndex());
            float valor = Float.parseFloat(valorTf.getText());
            Nota nota = new Nota(cadeira, valor);
            aluno.addNota(nota);
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(mainFrame, "Erro");
        } finally {
            Escola.editAluno();
            verNotasView(aluno);
        }
    }
    
    public void resetNotaInput(JComboBox cadeiraCombo, JTextField valorTf) {
        clearInput(valorTf);
        cadeiraCombo.setSelectedIndex(0);
    }
    
    public void verCadeirasView() {
        JPanel mainContent = new JPanel(new BorderLayout());
        JPanel menubarPanel = new JPanel();
        JPanel legendasPanel = new JPanel(new GridLayout(1,7));
        JPanel conteudoPanel = new JPanel(new BorderLayout());
        JPanel header = new JPanel(new GridLayout(2,1));
        
        JButton addCadeiraBtn = new JButton("Adicionar cadeira");
        menubarPanel.add(addCadeiraBtn);
        
        JLabel nomeLegLabel = new JLabel("Nome");
        JLabel creditosLegLabel = new JLabel("Creditos");
        JLabel professorLegLabel = new JLabel("Professor");
        JLabel professorEmailLegLabel = new JLabel("Email professor");
        JLabel codigoClassroomLegLabel = new JLabel("Codigo Classroom");
        legendasPanel.add(nomeLegLabel);
        legendasPanel.add(creditosLegLabel);
        legendasPanel.add(professorLegLabel);
        legendasPanel.add(professorEmailLegLabel);
        legendasPanel.add(codigoClassroomLegLabel);
        
        JLabel spacer1 = new JLabel("");
        JLabel spacer2 = new JLabel("");
        legendasPanel.add(spacer1);
        legendasPanel.add(spacer2);
        
        nomeLegLabel.setBackground(Color.gray);
        nomeLegLabel.setOpaque(true);
        creditosLegLabel.setBackground(Color.lightGray);
        creditosLegLabel.setOpaque(true);
        professorLegLabel.setBackground(Color.gray);
        professorLegLabel.setOpaque(true);
        professorEmailLegLabel.setBackground(Color.lightGray);
        professorEmailLegLabel.setOpaque(true);
        codigoClassroomLegLabel.setBackground(Color.gray);
        codigoClassroomLegLabel.setOpaque(true);
        
        for(int i = 0; i < Escola.cadeirasLength(); i++) {
            Cadeira cadeira = Escola.getCadeira(i);
            JPanel holder = new JPanel(new GridLayout(1,7));
            
            JLabel nomeCadeira = new JLabel(cadeira.getNome());
            JLabel creditosCadeira = new JLabel(String.valueOf(cadeira.getCreditos()));
            JLabel professorCadeira = new JLabel(cadeira.getProfessor());
            JLabel professorEmailCadeira = new JLabel(cadeira.getEmailProfessor());
            JLabel codigoClassroomCadeira = new JLabel(cadeira.getCodigoClassrrom());
            JButton alterarBtn = new JButton("Alterar");
            JButton removerBtn = new JButton("Remover");
            nomeCadeira.setBackground(Color.gray);
            nomeCadeira.setOpaque(true);
            creditosCadeira.setBackground(Color.lightGray);
            creditosCadeira.setOpaque(true);
            professorCadeira.setBackground(Color.gray);
            professorCadeira.setOpaque(true);
            professorEmailCadeira.setBackground(Color.lightGray);
            professorEmailCadeira.setOpaque(true);
            codigoClassroomCadeira.setBackground(Color.gray);
            codigoClassroomCadeira.setOpaque(true);
            holder.setBorder(BorderFactory.createMatteBorder(1,0,0,0,Color.darkGray));
            
            holder.add(nomeCadeira);
            holder.add(creditosCadeira);
            holder.add(professorCadeira);
            holder.add(professorEmailCadeira);
            holder.add(codigoClassroomCadeira);
            holder.add(alterarBtn);
            holder.add(removerBtn);
            conteudoPanel.add(holder, BorderLayout.NORTH);
            
            ActionListener alterarBtnEvt = (ActionEvent evt) -> {
                editCadeiraView(cadeira);
            };
            ActionListener removeBtnEvt = (ActionEvent evt) -> {
                removeCadeiraObj(cadeira);
            };
            
            addEvent(alterarBtn, alterarBtnEvt);
            addEvent(removerBtn, removeBtnEvt);
        }
        
        ActionListener addBtnEvt = (ActionEvent evt) -> {
            addCadeiraView();
        };
        
        addEvent(addCadeiraBtn, addBtnEvt);
        
        header.add(menubarPanel);
        header.add(legendasPanel);
        mainContent.add(header, BorderLayout.PAGE_START);
        mainContent.add(conteudoPanel, BorderLayout.CENTER);
        render(mainContent);
    }
    
    public void addCadeiraView() {
        JPanel mainContent = new JPanel(new GridLayout(11,1));
        JPanel btnHolder = new JPanel();
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
        btnHolder.add(addBtn);
        btnHolder.add(resBtn);
        mainContent.add(btnHolder);
        
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
    
    public void editCadeiraView(Cadeira cadeira) {
        JPanel mainContent = new JPanel(new GridLayout(11,1));
        
        JPanel btnHolder = new JPanel();
        JButton editBtn = new JButton("Actualizar");
        JButton resBtn = new JButton("Cancelar");
        JTextField professorTf = new JTextField(10);
        professorTf.setText(cadeira.getProfessor());
        JTextField professorEmailTf = new JTextField(10);
        professorEmailTf.setText(cadeira.getEmailProfessor());
        JTextField nomeTf = new JTextField(10);
        nomeTf.setText(cadeira.getNome());
        JTextField codigoClassroomTf = new JTextField(10);
        codigoClassroomTf.setText(cadeira.getCodigoClassrrom());
        JComboBox<Integer> creditosCombo = new JComboBox<>();
        creditosCombo.addItem(3);
        creditosCombo.addItem(6);
        creditosCombo.addItem(9);
        
        if(cadeira.getCreditos() == 3) {
            creditosCombo.setSelectedIndex(0);
        } else if(cadeira.getCreditos() == 6) {
            creditosCombo.setSelectedIndex(1);
        } else {
            creditosCombo.setSelectedIndex(2);
        }
        
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
        btnHolder.add(editBtn);
        btnHolder.add(resBtn);
        mainContent.add(btnHolder);
        
        ActionListener editBtnEvt = (ActionEvent evt) -> {
            editCadeiraObj(cadeira, nomeTf, creditosCombo, professorTf, professorEmailTf, codigoClassroomTf);
        };
        ActionListener resBtnEvt = (ActionEvent evt) -> {
            verCadeirasView();
        };
        addEvent(editBtn, editBtnEvt);
        addEvent(resBtn, resBtnEvt);
        
        
        render(mainContent);
    }
    
    public void editCadeiraObj(Cadeira cadeira, JTextField nomeTf, JComboBox creditosCombo, JTextField professorTf, JTextField professorEmailTf, JTextField codigoClassroomTf) {
        try {
            String nome = nomeTf.getText();
            int creditos = Integer.parseInt(creditosCombo.getSelectedItem().toString());
            String professor = professorTf.getText();
            String professorEmail = professorEmailTf.getText();
            String codigoClassroom = codigoClassroomTf.getText();
            String oldCadeiraNome = cadeira.getNome();
            
            cadeira.setNome(nome);
            cadeira.setProfessor(professor);
            cadeira.setEmailProfessor(professorEmail);
            cadeira.setCodigoClassroom(codigoClassroom);
            cadeira.setCreditos(creditos);
            /*                              NOT WORKING
            // no caso de algum aluno ter notas desta cadeira, eh necessario altera las para a nova cadeira
            // para cada aluno..
            for(int i = 0; i < Escola.alunosLength(); i++) {
                // para cada nota..
                for (Nota nota : Escola.getAluno(i).getNotas()) {
                    // que tenha a mesma cadeira antiga
                    if(nota.getCadeira().getNome().equals(oldCadeiraNome)) {
                        nota.setCadeira(cadeira);
                    }
                }
            }*/
            
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(mainFrame, "Erro");
        } finally {
            Escola.editCadeira();
            verCadeirasView();
        }
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
            JOptionPane.showMessageDialog(mainFrame, "Erro");
        } finally {
            verCadeirasView();
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

    public void verCursosView() {
        JPanel mainContent = new JPanel(new BorderLayout());
        JPanel menubarPanel = new JPanel();
        JPanel legendasPanel = new JPanel(new GridLayout(1,6));
        JPanel conteudoPanel = new JPanel(new BorderLayout());
        JPanel header = new JPanel(new GridLayout(2,1));
        
        JButton addCursoBtn = new JButton("Adicionar curso");
        menubarPanel.add(addCursoBtn);
        
        JLabel nomeLegLabel = new JLabel("Nome");
        JLabel horarioLegLabel = new JLabel("Horario");
        JLabel coordenadorLegLabel = new JLabel("Coordenador");
        JLabel coordenadorEmailLegLabel = new JLabel("Email coordenador");
        legendasPanel.add(nomeLegLabel);
        legendasPanel.add(horarioLegLabel);
        legendasPanel.add(coordenadorLegLabel);
        legendasPanel.add(coordenadorEmailLegLabel);
        
        JLabel spacer1 = new JLabel("");
        JLabel spacer2 = new JLabel("");
        legendasPanel.add(spacer1);
        legendasPanel.add(spacer2);
        
        nomeLegLabel.setBackground(Color.gray);
        nomeLegLabel.setOpaque(true);
        horarioLegLabel.setBackground(Color.lightGray);
        horarioLegLabel.setOpaque(true);
        coordenadorLegLabel.setBackground(Color.gray);
        coordenadorLegLabel.setOpaque(true);
        coordenadorEmailLegLabel.setBackground(Color.lightGray);
        coordenadorEmailLegLabel.setOpaque(true);
        
        
        for(int i = 0; i < Escola.cursosLength(); i++) {
            Curso curso = Escola.getCurso(i);
            JPanel holder = new JPanel(new GridLayout(1,6));
            
            JLabel nomeCurso = new JLabel(curso.getNomeCurso());
            JLabel horarioCurso = new JLabel(curso.getRegimeHorario());
            JLabel coordenadorCurso = new JLabel(curso.getCoordenador());
            JLabel coordenadorEmailCurso = new JLabel(curso.getEmailCoordenador());
            JButton alterarBtn = new JButton("Alterar");
            JButton removerBtn = new JButton("Remover");
            nomeCurso.setBackground(Color.gray);
            nomeCurso.setOpaque(true);
            horarioCurso.setBackground(Color.lightGray);
            horarioCurso.setOpaque(true);
            coordenadorCurso.setBackground(Color.gray);
            coordenadorCurso.setOpaque(true);
            coordenadorEmailCurso.setBackground(Color.lightGray);
            coordenadorEmailCurso.setOpaque(true);
            holder.setBorder(BorderFactory.createMatteBorder(1,0,0,0,Color.darkGray));
            
            holder.add(nomeCurso);
            holder.add(horarioCurso);
            holder.add(coordenadorCurso);
            holder.add(coordenadorEmailCurso);
            holder.add(alterarBtn);
            holder.add(removerBtn);
            conteudoPanel.add(holder, BorderLayout.NORTH);
            
            ActionListener alterarBtnEvt = (ActionEvent evt) -> {
                editCursoView(curso);
            };
            ActionListener removeBtnEvt = (ActionEvent evt) -> {
                removeCursoObj(curso);
            };
            
            addEvent(alterarBtn, alterarBtnEvt);
            addEvent(removerBtn, removeBtnEvt);
        }
        
        ActionListener addBtnEvt = (ActionEvent evt) -> {
            addCursoView();
        };
        
        addEvent(addCursoBtn, addBtnEvt);
        
        
        header.add(menubarPanel);
        header.add(legendasPanel);
        mainContent.add(header, BorderLayout.PAGE_START);
        mainContent.add(conteudoPanel, BorderLayout.CENTER);
        render(mainContent);
    }
    
    public void addCursoView() {
        JPanel mainContent = new JPanel(new GridLayout(9,1));
        JPanel btnHolder = new JPanel();
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
        btnHolder.add(addBtn);
        btnHolder.add(resBtn);
        mainContent.add(btnHolder);
        
        ActionListener addEvt = (ActionEvent evt) -> {
            addCursoObj(cursoCombo, horarioCombo, coordenadorTf, coordenadorEmailTf);
        };
        
        ActionListener resEvt = (ActionEvent evt) -> {
            verCursosView();
        };
        
        addEvent(addBtn, addEvt);
        addEvent(resBtn, resEvt);
        
        render(mainContent);
    }
    
    public void editCursoView(Curso curso) {
        JPanel mainContent = new JPanel(new GridLayout(9,1));
        
        JLabel cursoComboLabel = new JLabel("Tipo de curso:");
        JComboBox<String> cursoCombo = new JComboBox<>();
        cursoCombo.addItem("Informática");
        cursoCombo.addItem("Multimédia");
        
        if(curso instanceof CursoInformatica) {
            cursoCombo.setSelectedIndex(0);
        } else {
            cursoCombo.setSelectedIndex(1);
        }
        
        JPanel btnHolder = new JPanel();
        JButton editBtn = new JButton("Actualizar");
        JButton resBtn = new JButton("Cancelar");
        JLabel coordenadorLabel = new JLabel("Coordenador:");
        JTextField coordenadorTf = new JTextField(10);
        coordenadorTf.setText(curso.getCoordenador());
        JLabel coordenadorEmailLabel = new JLabel("Email do Coordenador:");
        JTextField coordenadorEmailTf = new JTextField(10);
        coordenadorEmailTf.setText(curso.getEmailCoordenador());
        JLabel horarioComboLabel = new JLabel("Horario:");
        JComboBox<String> horarioCombo = new JComboBox<>();
        horarioCombo.addItem("Diurno");
        horarioCombo.addItem("Pós-Laboral");
        if(curso.getRegimeHorario().equals("Diurno")) {
            horarioCombo.setSelectedIndex(0);
        } else {
            horarioCombo.setSelectedIndex(1);
        }
        
        mainContent.add(cursoComboLabel);
        mainContent.add(cursoCombo);
        mainContent.add(horarioComboLabel);
        mainContent.add(horarioCombo);
        mainContent.add(coordenadorLabel);
        mainContent.add(coordenadorTf);
        mainContent.add(coordenadorEmailLabel);
        mainContent.add(coordenadorEmailTf);
        btnHolder.add(editBtn);
        btnHolder.add(resBtn);
        mainContent.add(btnHolder);
        
        ActionListener editBtnEvt = (ActionEvent evt) -> {
            editCursoObj(curso, cursoCombo, horarioCombo, coordenadorTf, coordenadorEmailTf);
        };
        ActionListener resBtnEvt = (ActionEvent evt) -> {
            verCursosView();
        };
        addEvent(editBtn, editBtnEvt);
        addEvent(resBtn, resBtnEvt);
        
        
        render(mainContent);
    }
    
    public void editCursoObj(Curso curso, JComboBox cursoCombo, JComboBox horarioCombo, JTextField coordenadorTf, JTextField coordenadorEmailTf) {
        try {
            String coordenador = coordenadorTf.getText();
            String coordenadorEmail = coordenadorEmailTf.getText();
            String horario = horarioCombo.getSelectedItem().toString();
            
            // verifica se o campo foi alterado, neste caso verifica se o valor da combo box
            // eh igual ah substring do nome do curso selecionado,
            if(!cursoCombo.getSelectedItem().toString().contains(curso.getNomeCurso().substring(0, 3))) {
                Escola.remCurso(curso);
                if(cursoCombo.getSelectedIndex() == 0) {
                    Curso novoCurso = new CursoInformatica(horario, coordenador, coordenadorEmail);
                    Escola.addCurso(novoCurso);
                } else {
                    Curso novoCurso = new CursoMultimedia(horario, coordenador, coordenadorEmail);
                    Escola.addCurso(novoCurso);
                }
            } else {
                curso.setCoordenador(coordenador);
                curso.setEmailCoordenador(coordenadorEmail);
                curso.setRegimeHorario(horario);
            }
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(mainFrame, "Erro");
        } finally {
            Escola.editCurso();
            verCursosView();
        }
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
            JOptionPane.showMessageDialog(mainFrame, "Erro");
        } finally {
            verCursosView();
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
    
    
    
    //             --->    HELPER FUNCTIONS    <---
    //      remove todos os componentes do Wrapper e adiciona o JPanel recebido
    public void render(JPanel mainContentPanel) {
        try {
            mainContentWrapper.removeAll();
            mainContentWrapper.add(mainContentPanel, BorderLayout.CENTER);
        } catch(Exception e) {
            System.out.println(e);
        }
        // necessarios para que nao haja graphical glitchs
        mainFrame.repaint();
        mainFrame.revalidate();
    }
    //     adiciona events a jbuttons
    public void addEvent(JButton button, ActionListener eventHandler) {
        button.addActionListener(eventHandler);
    }
    //     da reset ao inputs dos jtextfields enviados
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