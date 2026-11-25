package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.BancoDados;
import dao.ProdutoDAO;
import dto.ProdutoDTO;

public class ProdutoService {
	public int cadastrar(ProdutoDTO produto) throws SQLException, IOException {
		Connection conn = BancoDados.conectar();
		return new ProdutoDAO(conn).cadastrar(produto);
	}
	
	public List<ProdutoDTO> buscarTodos() throws SQLException, IOException {
		Connection conn = BancoDados.conectar();
		return new ProdutoDAO(conn).buscarTodos();
	}
	
	public ProdutoDTO buscarPorCodigo(int idProduto) throws SQLException, IOException {
		Connection conn = BancoDados.conectar();
		return new ProdutoDAO(conn).buscarPorCodigo(idProduto);
	}
	
	public int atualizar(ProdutoDTO produto) throws SQLException, IOException {
		Connection conn = BancoDados.conectar();
		return new ProdutoDAO(conn).atualizar(produto);
	}
	
	public int excluir(int idProduto) throws SQLException, IOException {
		Connection conn = BancoDados.conectar();
		return new ProdutoDAO(conn).excluir(idProduto);
	}
}
