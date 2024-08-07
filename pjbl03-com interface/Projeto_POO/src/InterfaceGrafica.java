import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class InterfaceGrafica {

    public JButton adicionarBotao(String nome, JPanel panel, int x, int y, int larg, int alt){
        JButton botao = new JButton(nome);
        botao.setBounds(x, y, larg, alt);
        panel.add(botao);
        return botao;
    }


    public void adicionarLabel(String nome, JPanel panel, int x, int y, int larg, int alt){
        JLabel label = new JLabel(nome);
        label.setBounds(x, y, larg, alt);
        panel.add(label);
    }


    public JTextField adicionarCampo(JPanel panel, int quant, int x, int y, int larg, int alt){
        JTextField campo = new JTextField(quant);
        campo.setBounds(x, y, larg, alt);
        panel.add(campo);
        return campo;
    }


    public JFrame criarJanela(String titulo, int largura, int altura) {
        JFrame frame = new JFrame(titulo);    // Cria a janela principal
        frame.setSize(largura, altura); // Define o tamanho da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento ao fechar a janela
        return frame;
    }


    public JPanel criarPainel(JFrame frame) {
        JPanel panel = new JPanel();
        frame.add(panel);
        frame.setVisible(true);// Torna a janela visível
        return panel;
    }


    public Funcionario iniciarTelaDeEntrada(ArrayList<Funcionario> funcionarios) {
        JFrame frame = criarJanela("Página de Login", 400,200);
        JPanel paginaLogin = criarPainel(frame);
        paginaLogin.setLayout(null);

        adicionarLabel("CPF:",paginaLogin,20, 20, 25, 20);
        JTextField campoCpf = adicionarCampo(paginaLogin,20, 60, 20, 270, 25);
        JButton botaoLogin = adicionarBotao("Logar", paginaLogin, 145, 50, 80, 25);

        AtomicReference<Funcionario> funcionarioLogado = new AtomicReference<>(null);
        botaoLogin.addActionListener(new ActionListener() { // Ação do botão Login
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = campoCpf.getText();
                for (Funcionario funcionario : funcionarios) {
                    if (funcionario.getCpf().equals(cpf)) {
                        JOptionPane.showMessageDialog(paginaLogin, "CPF Válido. Bem-vido " + funcionario.getNome() + "!");
                        funcionarioLogado.set(funcionario);
                        frame.dispose();
                        break;
                    }
                }
                if (funcionarioLogado.get() == null) {
                    JOptionPane.showMessageDialog(paginaLogin, "CPF Inválido. Tente novamente.");
                }
            }
        });


        while (funcionarioLogado.get() == null) {
            try {
                Thread.sleep(100); // Pequena pausa para evitar loop excessivo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        return funcionarioLogado.get();
    }


    public String iniciarTelaDeMenu(JPanel panel) {
        panel.setLayout(null);

        adicionarLabel("Menu Principal", panel, 450, 30, 100, 20);

        JButton cadCliente = adicionarBotao("Cadastrar Cliente", panel, 100, 100, 800, 30);
        JButton cadPet = adicionarBotao("Cadastrar Pet", panel, 100, 150, 800, 30);
        JButton regCliente = adicionarBotao("Visualizar Registro do Cliente", panel, 100, 200, 800, 30);
        JButton regPet = adicionarBotao("Visualizar Registro do Pet", panel, 100, 250, 800, 30);
        JButton salSemanal = adicionarBotao("Calcular Salário Semanal", panel, 100, 300, 800, 30);
        JButton regServico = adicionarBotao("Registrar Serviço", panel, 100, 350, 800, 30);
        JButton visSer = adicionarBotao("Visualizar Serviços Registrados", panel, 100, 400, 800, 30);
        JButton cadVet = adicionarBotao("Cadastrar Veterinário", panel, 100, 450, 800, 30);
        JButton cadAjud = adicionarBotao("Cadastrar Ajudante", panel, 100, 500, 800, 30);
        JButton visFat = adicionarBotao("Visualizar Faturamento Semanal", panel, 100, 550, 800, 30);
        JButton sair = adicionarBotao("Sair", panel, 850, 700, 120, 30);

        AtomicReference<String> opcao = new AtomicReference<>(null);

        ActionListener botaoListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton botao = (JButton) e.getSource();
                opcao.set(botao.getText());
                if (botao.getText().equals("Sair")) {
                    // Fecha o JFrame quando "Sair" é clicado
                    SwingUtilities.getWindowAncestor(panel).dispose();
                }
            }
        };

        cadCliente.addActionListener(botaoListener);
        cadPet.addActionListener(botaoListener);
        regCliente.addActionListener(botaoListener);
        regPet.addActionListener(botaoListener);
        salSemanal.addActionListener(botaoListener);
        regServico.addActionListener(botaoListener);
        visSer.addActionListener(botaoListener);
        cadVet.addActionListener(botaoListener);
        cadAjud.addActionListener(botaoListener);
        visFat.addActionListener(botaoListener);
        sair.addActionListener(botaoListener);

        JFrame frame = new JFrame("Menu Principal");
        frame.setContentPane(panel);
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        while (opcao.get() == null) {
            try {
                Thread.sleep(100); // Pequena pausa para evitar loop excessivo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        frame.dispose();
        return opcao.get();
    }



    public String iniciarTelaDeMenuDeSaida(JPanel panel) {
        panel.setLayout(null);
        JButton sair = adicionarBotao("Sair do Sistema", panel, 70, 20, 260, 35);
        JButton permanecer = adicionarBotao("Identificar-se Novamente", panel, 70, 70, 260, 35);

        AtomicReference<String> opcao = new AtomicReference<>(null);
        ActionListener botaoListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton botao = (JButton) e.getSource();
                opcao.set(botao.getText());
            }
        };

        sair.addActionListener(botaoListener);
        permanecer.addActionListener(botaoListener);

        while (opcao.get() == null) {
            try {
                Thread.sleep(100); // Pequena pausa para evitar loop excessivo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return opcao.get();
    }


}
