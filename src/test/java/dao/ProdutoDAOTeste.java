package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.BancoDados;
import dao.ProdutoDAO;
import dto.ProdutoDTO;

public class ProdutoDAOTeste {

	public static void cadastrarProdutoTeste() throws SQLException, IOException {

		ProdutoDTO produto = new ProdutoDTO();

		produto.setNome("Café Torrado e Moído 500g – Melitta Tradicional");
		produto.setCategoria("Alimentos");
		produto.setPreco(35.50);
		produto.setQuantidadeEstoque(200);

		Connection conn = BancoDados.conectar();
		int resultado = new ProdutoDAO(conn).cadastrar(produto);

		if (resultado > 0) {

			System.out.println("Produto Cadastrado com sucesso");

		} else {

			System.out.println("Erro ao cadastrar um novo produto");
		}
	}

	public static void buscarTodosProdutosTeste() throws SQLException, IOException {

		Connection conn = BancoDados.conectar();
		List<ProdutoDTO> listaProdutos = new ProdutoDAO(conn).buscarTodos();

		if (listaProdutos.isEmpty()) {

			System.out.println("Não há produtos cadastrados.");
			return;
		}

		for (ProdutoDTO produto : listaProdutos) {

			System.out.println(produto);
		}
	}

	public static void buscarPorCodigoProdutoTeste() throws SQLException, IOException {

		int idProduto = 2;

		Connection conn = BancoDados.conectar();
		ProdutoDTO produto = new ProdutoDAO(conn).buscarPorCodigo(idProduto);
		
		if (produto != null) {

			System.out.println(produto);

		} else {

			System.out.println("Produto não encontrado.");
		}
	}

	public static void atualizarProdutoTeste() throws SQLException, IOException {

		ProdutoDTO produto = new ProdutoDTO();

		produto.setId(4);
		produto.setNome("Camiseta Básica Algodão Feminina");
		produto.setCategoria("Vestuário 2");
		produto.setPreco(169.90);
		produto.setQuantidadeEstoque(121);

		Connection conn = BancoDados.conectar();
		int resultado = new ProdutoDAO(conn).atualizar(produto);
		
		if (resultado > 0) {

			System.out.println("Produto atualizado com sucesso.");

		} else {

			System.out.println("Erro ao atualizar um produto.");
		}
	}

	public static void excluirProdutoTeste() throws SQLException, IOException {

		int idProduto = 4;

		Connection conn = BancoDados.conectar();
		int resultado = new ProdutoDAO(conn).excluir(idProduto);
		
		if (resultado > 0) {

			System.out.println("Produto excluído com sucesso.");

		} else {

			System.out.println("Erro ao excluir um produto.");
		}
	}
	
public static void main(String[] args) {
		
		try {
			
//			cadastrarProdutoTeste();
			buscarTodosProdutosTeste();
//			buscarPorCodigoProdutoTeste();
//			atualizarProdutoTeste();
//			excluirProdutoTeste();
		
		} catch (SQLException | IOException e) {
			
			e.printStackTrace();
		}
	}
}
