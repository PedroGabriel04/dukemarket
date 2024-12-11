/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package local.pgb.dukemarket.javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import local.pgb.dukemarket.javafx.DAO.ProdutoDAO;
import local.pgb.dukemarket.javafx.model.Produto;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import br.com.pedrogabrieldecarvalhobatista.dukemarket.javafx.App;
/**
 * FXML Controller class
 *
 * @author qualifica
 */
public class ScrProdutosController implements Initializable {

    @FXML
    TableView <Produto> tblProduto;
    
    @FXML
    TableColumn <Produto, Integer> tcoId;
    
    @FXML    
    TableColumn <Produto, String> tcoCodBarras;
    
    @FXML
     TableColumn <Produto, String> tcoDescricao;
            
    @FXML
    TableColumn <Produto, Double> tcoQtd;
    
    @FXML
     TableColumn <Produto, Double> tcoValorCompra;
    
    @FXML
     TableColumn <Produto, Double> tcoValorVenda;
    
    @FXML
     TableColumn <Produto, Calendar> tcoDataCadastro;
    
    //Caixa de Texto (TEXTFIELD)
    
    @FXML
    TextField txtId;
    
    @FXML
    TextField txtCodBarras;
    
    @FXML
    TextField txtDescricao;
    
    @FXML
    TextField txtQtd;
    
    @FXML
    TextField txtValorCompra;
    
    @FXML
    TextField txtValorVenda;
    
    @FXML
    DatePicker dtpDataCadastro;
    
    ProdutoDAO pDAO;
    Produto pClicked;
    private boolean flagNovo;
   
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        bindColumns();
        
        carregaDados();
        
    }    
  
    /**
     * Conecta as colunas da TableView com os atributos da classe Produto.
     * @return void
     */
    private void bindColumns(){
        
     tcoId.setCellValueFactory(new PropertyValueFactory<>("id"));
     
     tcoId.setStyle("-fx-alignment: CENTER_RIGHT;");
     
     tcoCodBarras.setCellValueFactory(new PropertyValueFactory<>("codBarras"));
     tcoCodBarras.setStyle("-fx-alignment: CENTR_RIGHT;");
     
     tcoDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
     
     tcoQtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
     tcoQtd.setStyle("-fx-alignment: CENTER_RIGHT;");
     
     tcoValorCompra.setCellValueFactory(new PropertyValueFactory<>("valorCompra"));
     tcoValorCompra.setStyle("-fx-alignment: CENTER_RIGHT");
     
     tcoValorVenda.setCellValueFactory(new PropertyValueFactory<>("valorVenda"));
     tcoValorVenda.setStyle("-fx-alignment: CENTER_RIGHT;");
     
     tcoDataCadastro.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
     tcoDataCadastro.setStyle("-fx-alignment: CENTER_RIGHT;");
    }
        
    /**
     * Carrega os dados de Produto que estão na base e vincula a TableView.
     * 
     */
 public void carregaDados(){
     
     this.pDAO = new ProdutoDAO();
     
     this.tblProduto.getItems().setAll( pDAO.getResults());
 }
    
 @FXML
 private void btnNovoClick(){
     
     txtId.setText("auto");
     txtId.setEditable(false);
     
     txtCodBarras.setText("");
     txtCodBarras.requestFocus();
     
     txtDescricao.setText("");
     txtQtd.setText("");
     txtValorCompra.setText("");
     txtValorVenda.setText("");
     dtpDataCadastro.setValue(null);
 }
    
 /**
  * Gerencia o click na tabela, identificando qual registro foi selecionado e
  * preenchendo os TextFields.
  */
 @FXML
 public void tblProdutosOnMouseClicked(){
     
     this.pClicked =(Produto) tblProduto.getSelectionModel().getSelectedItems();
     
     if (pClicked != null){
         
         txtId.setText(String.valueOf(pClicked.getId()));
         txtId.setEditable(false);
         
         txtCodBarras.setText(pClicked.getCodBarras());
         txtDescricao.setText(pClicked.getDescricao());
         
         txtQtd.setText(String.valueOf(pClicked.getQtd()));
         txtValorCompra.setText(String.valueOf(pClicked.getValorCompra()));
         txtValorVenda.setText(String.valueOf(pClicked.getValorVenda()));
         
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
         formatter = formatter.withLocale(Locale.US);
         LocalDate date = LocalDate.parse(pClicked.getDataCadastro(),formatter);
         
         dtpDataCadastro.setValue(date);
         dtpDataCadastro.setEditable(false);
         
         this.flagNovo = false;
         
     }
 }
 public void btnProdutosClick() throws IOException{
     
     try{
         FXMLLoader fxmlLoader = new FXMLLoader();
         fxmlLoader.setLocation(App.class.getResource("scrProdutos.fxml"));
         
         Scene scene = new Scene (fxmlLoader.load(), 900, 400);
         Stage stage = new Stage();
         
         stage.setTitle("CRUD Produtos");
         stage.setScene(scene);
         stage.show();
         
     }catch (IOException e){
         Logger logger = Logger.getLogger( getClass().getName());
         logger.log(Level.SEVERE, "Failed to create new Window scrProdutos.",e);
         
     }
 }   
 @FXML
 public void btnSalvarClick(){
     
     Produto p = new Produto();
     
     p.setCodBarras(txtCodBarras.getText());
     p.setDescricao(txtDescricao.getText());
     p.setQtd(Double.parseDouble(txtQtd.getText()));
     p.setValorCompra(Double.parseDouble(txtValorCompra.getText()));
     p.setValorVenda(Double.parseDouble(txtValorVenda.getText()));
     
     if(this.flagNovo){
         //Novo Produto..
         
         this.pDAO.create(p);
         
     }else{
         //Uma Atualização
         p.setId(Integer.parseInt(txtId.getText()));
         this.pDAO.update(p);
     }
     this.carregaDados();
 }
 
 /**
  * Gerencia o click do botão Excluir.Remove o registro selecionado-
  * carregando em pClicked.
  * 
  */
 @FXML
 public void btnExcluirClick(){
     
     if(this.pClicked != null){
         
         Alert alert = new Alert(AlertType.CONFIRMATION);
         alert.setTitle("Confirma Exclusão do Produto? ");
         alert.setHeaderText(pClicked.getDescricao());
         alert.setContentText("Tem certeza que deseja excluir ?");
         
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get()==ButtonType.OK){
             this.pDAO.delete(this.pClicked.getId());
             
             this.tblProduto.getItems().remove(this.pClicked);
         }
         this.tblProduto.getSelectionModel().clearSelection();
         pClicked = null;
     }else{
         Alert alert = new Alert(AlertType.WARNING);
         alert.setTitle("Ops!");
         alert.setHeaderText("Atenção");
         alert.setContentText("Você deve selecionar um registro antes de excluí-lo!");
         
         alert.showAndWait();
     }
 }
 
}
