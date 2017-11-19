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
    private JFXTreeTableView<donn�esEns> treeView;



    @SuppressWarnings("unchecked")
	@Override
    public void initialize(URL url, ResourceBundle rb) {

        JFXTreeTableColumn<donn�esEns, String> cren = new JFXTreeTableColumn<>("Cr�neaux");
        cren.setPrefWidth(146);
        cren.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donn�esEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donn�esEns, String> param) {
                return param.getValue().getValue().creneaux;
            }
        });

        JFXTreeTableColumn<donn�esEns, String> inti = new JFXTreeTableColumn<>("Intitul�");
        inti.setPrefWidth(146);
        inti.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donn�esEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donn�esEns, String> param) {
                return param.getValue().getValue().intitul�;
            }
        });

        JFXTreeTableColumn<donn�esEns, String> nom = new JFXTreeTableColumn<>("Nom");
        nom.setPrefWidth(146);
        nom.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donn�esEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donn�esEns, String> param) {
                return param.getValue().getValue().nom;
            }
        });

        JFXTreeTableColumn<donn�esEns, String> pren = new JFXTreeTableColumn<>("Pr�nom");
        pren.setPrefWidth(146);
        pren.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donn�esEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donn�esEns, String> param) {
                return param.getValue().getValue().pr�nom;
            }
        });
        
        JFXTreeTableColumn<donn�esEns, String> RUC = new JFXTreeTableColumn<>("RU");
        RUC.setPrefWidth(146);
        RUC.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donn�esEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donn�esEns, String> param) {
                return param.getValue().getValue().RU;
            }
        });

        ObservableList<donn�esEns> donn�esEnss = FXCollections.observableArrayList();
        String User = System.getProperty("user.name");
        Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(new File(Main.chemin));
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet = workbook.getSheet("Inscription_" + Cr�ationCoursController.drawerNameP);
        for(int i=1; i<sheet.getColumn(0).length;i++){
        	if(sheet.getCell(1,i).getContents().equals(contenuConsultReserController.choiceHeureP.getValue()))
        		donn�esEnss.add(new donn�esEns(sheet.getCell(1,i).getContents(),sheet.getCell(2,i).getContents(),sheet.getCell(6,i).getContents(),sheet.getCell(7,i).getContents(),sheet.getCell(5,i).getContents()));   
        }       
        final TreeItem<donn�esEns> root = new RecursiveTreeItem<donn�esEns>(donn�esEnss, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(cren, inti,nom,pren,RUC);
        treeView.setRoot(root);
        treeView.setShowRoot(false);

    }

    @FXML
    private void filter(ActionEvent event) {
    }

    public class donn�esEns extends RecursiveTreeObject<donn�esEns> {

        public StringProperty creneaux;
        public StringProperty intitul�;
        public StringProperty nom;
        public StringProperty pr�nom;
        public StringProperty RU;
        

        public donn�esEns(String creneaux, String intitul�, String nom, String pr�nom, String RU) {
            this.creneaux = new SimpleStringProperty(creneaux);
            this.intitul� = new SimpleStringProperty(intitul�);
            this.nom = new SimpleStringProperty(nom);
            this.pr�nom = new SimpleStringProperty(pr�nom);
            this.RU = new SimpleStringProperty(RU);
        }

    }
}
