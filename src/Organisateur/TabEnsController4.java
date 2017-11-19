package Organisateur;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import application.Main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class TabEnsController4 implements Initializable {

    @FXML
    private FlowPane main;
    @FXML
    private JFXTreeTableView<donnéesEns> treeView;



    @SuppressWarnings("unchecked")
	@Override
    public void initialize(URL url, ResourceBundle rb) {

        JFXTreeTableColumn<donnéesEns, String> cren = new JFXTreeTableColumn<>("Créneaux");
        cren.setPrefWidth(146);
        cren.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donnéesEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donnéesEns, String> param) {
                return param.getValue().getValue().creneaux;
            }
        });

        JFXTreeTableColumn<donnéesEns, String> inti = new JFXTreeTableColumn<>("Intitulé");
        inti.setPrefWidth(146);
        inti.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donnéesEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donnéesEns, String> param) {
                return param.getValue().getValue().intitulé;
            }
        });

        JFXTreeTableColumn<donnéesEns, String> nom = new JFXTreeTableColumn<>("Nom");
        nom.setPrefWidth(146);
        nom.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donnéesEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donnéesEns, String> param) {
                return param.getValue().getValue().nom;
            }
        });

        JFXTreeTableColumn<donnéesEns, String> pren = new JFXTreeTableColumn<>("Prénom");
        pren.setPrefWidth(146);
        pren.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donnéesEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donnéesEns, String> param) {
                return param.getValue().getValue().prénom;
            }
        });
        
        JFXTreeTableColumn<donnéesEns, String> RUC = new JFXTreeTableColumn<>("RU");
        RUC.setPrefWidth(146);
        RUC.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donnéesEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donnéesEns, String> param) {
                return param.getValue().getValue().RU;
            }
        });

        ObservableList<donnéesEns> donnéesEnss = FXCollections.observableArrayList();
        String User = System.getProperty("user.name");
        Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(new File(Main.chemin));
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet = workbook.getSheet("Inscription_" + CréationCoursController.drawerNameP);
        for(int i=1; i<sheet.getColumn(0).length;i++){
        	if(sheet.getCell(1,i).getContents().equals(contenuConsultReserController.choiceHeureP.getValue()))
        		donnéesEnss.add(new donnéesEns(sheet.getCell(1,i).getContents(),sheet.getCell(2,i).getContents(),sheet.getCell(6,i).getContents(),sheet.getCell(7,i).getContents(),sheet.getCell(5,i).getContents()));   
        }       
        final TreeItem<donnéesEns> root = new RecursiveTreeItem<donnéesEns>(donnéesEnss, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(cren, inti,nom,pren,RUC);
        treeView.setRoot(root);
        treeView.setShowRoot(false);

    }

    @FXML
    private void filter(ActionEvent event) {
    }

    public class donnéesEns extends RecursiveTreeObject<donnéesEns> {

        public StringProperty creneaux;
        public StringProperty intitulé;
        public StringProperty nom;
        public StringProperty prénom;
        public StringProperty RU;
        

        public donnéesEns(String creneaux, String intitulé, String nom, String prénom, String RU) {
            this.creneaux = new SimpleStringProperty(creneaux);
            this.intitulé = new SimpleStringProperty(intitulé);
            this.nom = new SimpleStringProperty(nom);
            this.prénom = new SimpleStringProperty(prénom);
            this.RU = new SimpleStringProperty(RU);
        }

    }
}
