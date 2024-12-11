package local.pgb.dukemarket.javafx.DAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import connection.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import local.pgb.dukemarket.javafx.model.Produto;

/**
 * Classe para gerenciar operações de banco de dados para produtos.
 *
 * @author qualifica
 */
public class ProdutoDAO {

    private static final String SQL_INSERT = "INSERT INTO produto(codBarras, descricao, qtd, valorCompra, valorVenda) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM produto";
    private static final String SQL_SELECT_ID = "SELECT * FROM produto WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE produto SET codBarras = ?, descricao = ?, qtd = ?, valorCompra = ?, valorVenda = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM produto WHERE id = ?";

    private List<Produto> produtos;

    public ProdutoDAO() {
    }

    /**
     * Insere um produto na base de dados.
     *
     * @param p Produto a ser inserido.
     * @throws SQLException se ocorrer um erro ao acessar o banco de dados.
     */
    public void create(Produto p) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, p.getCodBarras());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getQtd());
            stmt.setDouble(4, p.getValorCompra());
            stmt.setDouble(5, p.getValorVenda());

            // Executa a Query
            int auxRetorno = stmt.executeUpdate();
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.INFO, null,
                    "Inclusao: " + auxRetorno);
        } catch (SQLException sQLException) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        } finally {

            MySQLConnection.closeConnection(conn, stmt);

        }
    }

    public List<Produto> getResults() {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto p = null;
        List<Produto> listaProdutos = null;

        try {
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();

            listaProdutos = new ArrayList<>();

            while (rs.next()) {
                p = new Produto();
                p.setId(rs.getInt("id"));
                p.setCodBarras(rs.getString("codBarras"));
                p.setDescricao(rs.getString("descricao"));
                p.setQtd(rs.getDouble("qtd"));
                p.setValorCompra(rs.getDouble("valorCompra"));
                p.setValorVenda(rs.getDouble("valorVenda"));
                p.setDataCadastro(rs.getString("dataCadastro"));
                listaProdutos.add(p);
            }

        } catch (SQLException sQLException) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        }
        
        return listaProdutos;
    }

    public Produto getResultsById(int id)  {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto p = null;

        try {
            stmt = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                p = new Produto();
                p.setId(rs.getInt("id"));
                p.setCodBarras(rs.getString("codBarras"));
                p.setDescricao(rs.getString("descricao"));
                p.setQtd(rs.getDouble("qtd"));
                p.setValorCompra(rs.getDouble("valorCompra"));
                p.setValorVenda(rs.getDouble("valorVenda"));
                p.setDataCadastro(rs.getString("dataCadastro"));
            }
        } catch (SQLException sQLException) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        } finally {
            MySQLConnection.closeConnection(conn, stmt, rs);
        }
        return p;
    }

    /**
     * Atualiza um registro na tabela produto.
     *
     * @param p Produto a ser atualizado.
     * @throws SQLException se ocorrer um erro ao acessar o banco de dados.
     */
    /**
     * Atualiza um registro na tabela produto.
     *
     * @param p Produto a ser atualizado.
     * @throws SQLException se ocorrer um erro ao acessar o banco de dados.
     */
    public void update(Produto p)  {

        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, p.getCodBarras());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getQtd());
            stmt.setDouble(4, p.getValorCompra());
            stmt.setDouble(5, p.getValorVenda());
            stmt.setInt(6, p.getId()); // Corrigido para setInt

            int auxRetorno = stmt.executeUpdate();
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.INFO, null,
                    "Update: " + auxRetorno);

        } catch (SQLException sQLException) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        } finally {
            MySQLConnection.closeConnection(conn, stmt);
        }
    }

    /**
     * Exclui um produto com base no ID fornecido.
     *
     * @param id IDentificação do Produto.
     * @throws SQLException se ocorrer um erro ao acessar o banco de dados.
     */
    public void delete(int id)  {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);

            int auxRetorno = stmt.executeUpdate();
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.INFO, null,
                    "Delete: " + auxRetorno);

        } catch (SQLException sQLException) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);

        } finally {
            MySQLConnection.closeConnection(conn, stmt);
        }
    }
}
  