package br.com.lanchonete.view;

import br.com.lanchonete.controller.ClienteController;
import br.com.lanchonete.controller.PedidoController;
import br.com.lanchonete.model.Cliente;
import br.com.lanchonete.model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PedidoView extends JFrame {

    private final PedidoController pedidoController;
    private final ClienteController clienteController;

    private JTextField txtNomeCliente;
    private JComboBox<String> cbStatus;
    private JTextField txtValorTotal;

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    private Integer idSelecionado;

    public PedidoView() {

        pedidoController = new PedidoController();
        clienteController = new ClienteController();

        inicializarComponentes();
        carregarTabela();

        setTitle("Cadastro de Pedidos");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void inicializarComponentes() {

        JPanel painelPrincipal = new JPanel(new BorderLayout());

        JPanel painelFormulario = new JPanel(new GridLayout(3, 2, 10, 10));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtNomeCliente = new JTextField();

        cbStatus = new JComboBox<>(new String[]{
            "ABERTO",
            "EM PREPARO",
            "FINALIZADO",
            "CANCELADO"
        });

        txtValorTotal = new JTextField();

        painelFormulario.add(new JLabel("Nome do Cliente:"));
        painelFormulario.add(txtNomeCliente);

        painelFormulario.add(new JLabel("Status:"));
        painelFormulario.add(cbStatus);

        painelFormulario.add(new JLabel("Valor Total:"));
        painelFormulario.add(txtValorTotal);

        modeloTabela = new DefaultTableModel(
                new Object[]{"ID", "Cliente", "Status", "Valor Total", "Data/Hora"}, 0
        );

        tabela = new JTable(modeloTabela);

        JScrollPane scrollPane = new JScrollPane(tabela);

        JPanel painelBotoes = new JPanel();

        JButton btnSalvar = new JButton("Salvar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnLimpar = new JButton("Limpar");

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnLimpar);

        painelPrincipal.add(painelFormulario, BorderLayout.NORTH);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        add(painelPrincipal);

        btnSalvar.addActionListener(e -> salvar());
        btnAtualizar.addActionListener(e -> atualizar());
        btnExcluir.addActionListener(e -> excluir());
        btnLimpar.addActionListener(e -> limparCampos());

        tabela.getSelectionModel().addListSelectionListener(
                e -> selecionarPedido()
        );
    }

    private Cliente buscarOuCriarCliente() {

        String nomeCliente = txtNomeCliente.getText().trim();

        if (nomeCliente.isEmpty()) {
            throw new IllegalArgumentException("Informe o nome do cliente.");
        }

        Cliente cliente = clienteController.buscarPorNome(nomeCliente);

        if (cliente == null) {
            clienteController.salvar(nomeCliente);
            cliente = clienteController.buscarPorNome(nomeCliente);
        }

        return cliente;
    }

    private void salvar() {

        Cliente cliente;

        try {
            cliente = buscarOuCriarCliente();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return;
        }

        String status = cbStatus.getSelectedItem().toString();

        Double valorTotal;

        try {
            valorTotal = Double.parseDouble(txtValorTotal.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Informe um valor total válido.");
            return;
        }

        try {

            pedidoController.salvar(
                    cliente.getId(),
                    status,
                    valorTotal
            );

            JOptionPane.showMessageDialog(this, "Pedido cadastrado com sucesso!");

            limparCampos();
            carregarTabela();

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void atualizar() {

        if (idSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um pedido.");
            return;
        }

        Cliente cliente;

        try {
            cliente = buscarOuCriarCliente();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return;
        }

        String status = cbStatus.getSelectedItem().toString();

        Double valorTotal;

        try {
            valorTotal = Double.parseDouble(txtValorTotal.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Informe um valor total válido.");
            return;
        }

        try {

            pedidoController.atualizar(
                    idSelecionado,
                    cliente.getId(),
                    status,
                    valorTotal
            );

            JOptionPane.showMessageDialog(this, "Pedido atualizado com sucesso!");

            limparCampos();
            carregarTabela();

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void excluir() {

        if (idSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um pedido.");
            return;
        }

        int opcao = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente excluir este pedido?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (opcao == JOptionPane.YES_OPTION) {

            pedidoController.excluir(idSelecionado);

            JOptionPane.showMessageDialog(this, "Pedido excluído com sucesso!");

            limparCampos();
            carregarTabela();
        }
    }

    private void carregarTabela() {

        modeloTabela.setRowCount(0);

        List<Pedido> pedidos = pedidoController.listar();

        for (Pedido pedido : pedidos) {

            String nomeCliente = "";

            if (pedido.getCliente() != null) {
                nomeCliente = pedido.getCliente().getNome();
            }

            modeloTabela.addRow(new Object[]{
                pedido.getId(),
                nomeCliente,
                pedido.getStatus(),
                pedido.getValorTotal(),
                pedido.getDataHora()
            });
        }
    }

    private void selecionarPedido() {

        int linha = tabela.getSelectedRow();

        if (linha >= 0) {

            idSelecionado = (Integer) modeloTabela.getValueAt(linha, 0);

            Pedido pedido = pedidoController.buscarPorId(idSelecionado);

            if (pedido != null) {

                if (pedido.getCliente() != null) {
                    txtNomeCliente.setText(pedido.getCliente().getNome());
                }

                cbStatus.setSelectedItem(pedido.getStatus());

                txtValorTotal.setText(
                        String.valueOf(pedido.getValorTotal())
                );
            }
        }
    }

    private void limparCampos() {

        idSelecionado = null;

        txtNomeCliente.setText("");
        cbStatus.setSelectedIndex(0);
        txtValorTotal.setText("");

        tabela.clearSelection();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new PedidoView().setVisible(true);
        });
    }
}