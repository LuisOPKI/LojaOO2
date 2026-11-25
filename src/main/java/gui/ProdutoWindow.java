package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dto.ProdutoDTO;
import service.ProdutoService;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class ProdutoWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtCategoria;
	private JTextField txtPreco;
	private JTextField txtQtd;
	private JLabel lblNome;
	private JLabel lblCategoria;
	private JLabel lblPreco;
	private JLabel lblQuantidade;
	
	private ProdutoService service;
	private JButton btnAtualizar;
	private JButton btnExcluir;
	private JTable tblProdutos;

	public ProdutoWindow() {
		this.iniciarElementos();
		this.service = new ProdutoService();
		buscarProdutos();
	}
		private void cadastrar() {
			ProdutoDTO produto = new ProdutoDTO();
			try {
			produto.setNome(txtNome.getText().trim());
			produto.setCategoria(txtCategoria.getText().trim());
			produto.setPreco(Double.parseDouble(txtPreco.getText().trim().replace(",", ".")));
			produto.setQuantidadeEstoque(Integer.parseInt(txtQtd.getText().trim()));
			
			int resultado = service.cadastrar(produto);
			if(resultado > 0) {
				JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "CADASTRO", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Cadastro não foi realizado!", "CADASTRO", JOptionPane.WARNING_MESSAGE);
			}
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Erro nos dados do cadastro!", "CADASTRO", JOptionPane.WARNING_MESSAGE);
			}finally {
				buscarProdutos();
			}
		}
		private void atualizar() {
			ProdutoDTO produto;
			try {
				produto = service.buscarPorCodigo(Integer.parseInt(txtId.getText().trim()));
				produto.setNome(txtNome.getText().trim());
				produto.setCategoria(txtCategoria.getText().trim());
				produto.setPreco(Double.parseDouble(txtPreco.getText().trim().replace(",", ".")));
				produto.setQuantidadeEstoque(Integer.parseInt(txtQtd.getText().trim()));
				
				int resultado = service.atualizar(produto);
					if(resultado > 0) {
						JOptionPane.showMessageDialog(null, "Produto teve os dados atualizados com sucesso!", "ATUALIZAR", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "Erro nos dados para atualização!", "ATUALIZAR", JOptionPane.WARNING_MESSAGE);
					}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Erro nos dados para atualização, digite apenas caracteres numericos", "ATUALIZAR", JOptionPane.WARNING_MESSAGE);
			}catch(SQLException |  IOException | NullPointerException s) {
				JOptionPane.showMessageDialog(null, "O ID não foi encontrado na base de dados!", "ATUALIZAR", JOptionPane.WARNING_MESSAGE);
			}finally {
				buscarProdutos();
			}
		}
		
		private void excluir() {
			try {
			int resultado = JOptionPane.showConfirmDialog(null, "Deseja realmente apagar o produto?", "EXCLUIR", JOptionPane.YES_NO_OPTION);
			if(resultado == JOptionPane.YES_OPTION ) {
					resultado = service.excluir(Integer.parseInt(txtId.getText().trim()));	
				}
			if(resultado > 0 ) {
				JOptionPane.showMessageDialog(null, "Produto foi excluido com sucesso!", "EXCLUIR", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Erro nos dados!", "Excluir", JOptionPane.WARNING_MESSAGE);
			}
			
			} catch (NumberFormatException  e) {
				JOptionPane.showMessageDialog(null, "Erro nos dados para atualização, digite apenas caracteres numericos", "ATUALIZAR", JOptionPane.WARNING_MESSAGE);
			}catch (SQLException | IOException | NullPointerException a) {
				JOptionPane.showMessageDialog(null, "O ID não foi encontrado na base de dados!", "ATUALIZAR", JOptionPane.WARNING_MESSAGE);
			}finally {
				buscarProdutos();
			}
		}
		
		private void buscarProdutos(){
			List<ProdutoDTO> produtos;
			try {
				produtos = service.buscarTodos();
				DefaultTableModel modelo = (DefaultTableModel) this.tblProdutos.getModel();
				modelo.setRowCount(0);
				
				for(ProdutoDTO produto : produtos) {
					modelo.addRow(new Object[] {
						produto.getId(),
						produto.getNome(),
						produto.getCategoria(),
						produto.getPreco(),
						produto.getQuantidadeEstoque()
					});
				}
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
				
	private void iniciarElementos() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 23, 440, 270);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		txtId = new JTextField();
		txtId.setBounds(12, 12, 114, 21);
		panel.add(txtId);
		txtId.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(12, 34, 114, 21);
		panel.add(txtNome);
		
		txtCategoria = new JTextField();
		txtCategoria.setColumns(10);
		txtCategoria.setBounds(12, 56, 114, 21);
		panel.add(txtCategoria);
		
		txtPreco = new JTextField();
		txtPreco.setColumns(10);
		txtPreco.setBounds(12, 78, 114, 21);
		panel.add(txtPreco);
		
		txtQtd = new JTextField();
		txtQtd.setColumns(10);
		txtQtd.setBounds(12, 100, 114, 21);
		panel.add(txtQtd);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(136, 14, 60, 17);
		panel.add(lblId);
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(136, 36, 60, 17);
		panel.add(lblNome);
		
		lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(136, 58, 60, 17);
		panel.add(lblCategoria);
		
		lblPreco = new JLabel("Preco");
		lblPreco.setBounds(136, 80, 60, 17);
		panel.add(lblPreco);
		
		lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(136, 102, 72, 17);
		panel.add(lblQuantidade);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrar();
			}
		});
		btnCadastrar.setBounds(292, 9, 105, 27);
		panel.add(btnCadastrar);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizar();
			}
		});
		btnAtualizar.setBounds(292, 53, 105, 27);
		panel.add(btnAtualizar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				excluir();
			}
		});
		btnExcluir.setBounds(292, 97, 105, 27);
		panel.add(btnExcluir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 133, 428, 137);
		panel.add(scrollPane);
		
		tblProdutos = new JTable();
		tblProdutos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Categoria", "Preço", "Qtd"
			}
		));
		scrollPane.setViewportView(tblProdutos);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdutoWindow frame = new ProdutoWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
