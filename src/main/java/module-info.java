module local.pgb.dukemarket.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    
    requires java.sql;
    
    
    opens local.pgb.dukemarket.javafx to javafx.fxml;
    opens local.pgb.dukemarket.javafx.controller to javafx.fxml;
    
    exports br.com.pedrogabrieldecarvalhobatista.dukemarket.javafx;
    exports local.pgb.dukemarket.javafx.controller;
    exports local.pgb.dukemarket.javafx.DAO;
    exports local.pgb.dukemarket.javafx.model;
    exports connection;
    
        
    
   requires mysql.connector.java;
}

