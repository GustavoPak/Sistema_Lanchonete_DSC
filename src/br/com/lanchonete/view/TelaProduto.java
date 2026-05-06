package br.com.lanchonete.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaProduto extends JFrame {

    private JLabel lblNome;
    private JTextField txtNome;
    private JButton btnCadastrar;

    public TelaProduto() {

        // Configuração da janela
        setTitle("Cadastro de Produto");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Texto "Nome do Produto"
        lblNome = new JLabel("Nome do Produto:");
        lblNome.setBounds(30, 30, 120, 25);
        add(lblNome);

        // Campo de texto
        txtNome = new JTextField();
        txtNome.setBounds(160, 30, 180, 25);
        add(txtNome);

        // Botão
        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(120, 80, 120, 30);
        add(btnCadastrar);

        // Ação do botão
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nome = txtNome.getText();

                JOptionPane.showMessageDialog(null,
                        "Produto cadastrado: " + nome);
            }
        });
    }

    public static void main(String[] args) {

        TelaProduto tela = new TelaProduto();
        tela.setVisible(true);
    }
}