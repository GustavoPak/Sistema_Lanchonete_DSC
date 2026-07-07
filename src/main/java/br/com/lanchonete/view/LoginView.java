package br.com.lanchonete.view;

import br.com.lanchonete.controller.UsuarioController;
import br.com.lanchonete.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    private final UsuarioController usuarioController;

    private JTextField txtLogin;
    private JPasswordField txtSenha;

    public LoginView() {

        usuarioController = new UsuarioController();

        criarUsuarioAdminSeNaoExistir();

        inicializarComponentes();

        setTitle("Login");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void criarUsuarioAdminSeNaoExistir() {

        Usuario usuario = usuarioController.buscarPorLogin("admin");

        if (usuario == null) {
            usuarioController.cadastrarUsuario(
                    "Administrador",
                    "admin",
                    "123"
            );

            System.out.println("Usuário admin criado automaticamente.");
        }
    }

    private void inicializarComponentes() {

        JPanel painelPrincipal = new JPanel(new BorderLayout());

        JPanel painelTitulo = new JPanel();
        JLabel lblTitulo = new JLabel("Acesso ao Sistema");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        painelTitulo.add(lblTitulo);

        JPanel painelFormulario = new JPanel(new GridLayout(2, 2, 10, 10));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        txtLogin = new JTextField();
        txtSenha = new JPasswordField();

        painelFormulario.add(new JLabel("Usuário:"));
        painelFormulario.add(txtLogin);

        painelFormulario.add(new JLabel("Senha:"));
        painelFormulario.add(txtSenha);

        JPanel painelBotoes = new JPanel();

        JButton btnEntrar = new JButton("Entrar");
        JButton btnSair = new JButton("Sair");

        painelBotoes.add(btnEntrar);
        painelBotoes.add(btnSair);

        painelPrincipal.add(painelTitulo, BorderLayout.NORTH);
        painelPrincipal.add(painelFormulario, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        add(painelPrincipal);

        btnEntrar.addActionListener(e -> entrar());
        btnSair.addActionListener(e -> System.exit(0));
    }

    private void entrar() {

        String login = txtLogin.getText().trim();
        String senha = new String(txtSenha.getPassword());

        Usuario usuario = usuarioController.autenticar(login, senha);

        if (usuario != null) {

            JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");

            MenuPrincipalView menu = new MenuPrincipalView();
            menu.setVisible(true);

            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.");
            txtSenha.setText("");
            txtSenha.requestFocus();
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new LoginView().setVisible(true);
        });
    }
}