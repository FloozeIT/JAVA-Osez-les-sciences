package Organisateur;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import application.Main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class TabEnsController implements Initializable {

    @FXML
    private FlowPane main;

    
    @FXML
    public static int indSelP;
       
    @FXML
    private JFXTreeTableView<donn�esEns> treeView;
    String User = System.getProperty("user.name");
    
    private ObservableList<donn�esEns> donn�esEnss = FXCollections.observableArrayList();
    
    @SuppressWarnings("unchecked")
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	contenuCr�ationController.suprP.setDisable(true);
        JFXTreeTableColumn<donn�esEns, String> horDeb = new JFXTreeTableColumn<>("Heure de d�but");
        horDeb.setPrefWidth(122);
        horDeb.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donn�esEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donn�esEns, String> param) {
                return param.getValue().getValue().horraireDeD�but;
            }
        });

        JFXTreeTableColumn<donn�esEns, String> horFin = new JFXTreeTableColumn<>("Heure de fin");
        horFin.setPrefWidth(122);
        horFin.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donn�esEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donn�esEns, String> param) {
                return param.getValue().getValue().horraireDeFin;
            }
        });

        JFXTreeTableColumn<donn�esEns, String> inti = new JFXTreeTableColumn<>("Intitul�");
        inti.setPrefWidth(122);
        inti.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donn�esEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donn�esEns, String> param) {
                return param.getValue().getValue().intitul�;
            }
        });

        JFXTreeTableColumn<donn�esEns, String> ment = new JFXTreeTableColumn<>("Mention");
        ment.setPrefWidth(122);
        ment.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donn�esEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donn�esEns, String> param) {
                return param.getValue().getValue().mention;
            }
        });

        JFXTreeTableColumn<donn�esEns, String> ens = new JFXTreeTableColumn<>("Enseignant");
        ens.setPrefWidth(122);
        ens.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donn�esEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donn�esEns, String> param) {
                return param.getValue().getValue().enseignant;
            }
        });

        JFXTreeTableColumn<donn�esEns, String> nbPla = new JFXTreeTableColumn<>("Places");
        nbPla.setPrefWidth(122);
        nbPla.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donn�esEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donn�esEns, String> param) {
                return param.getValue().getValue().nombrePlace;
            }
        });

        Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(new File(Main.chemin));
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet = workbook.getSheet("Cr�ation_" + Cr�ationCoursController.drawerNameP);
        for(int i=1; i<sheet.getColumn(0).length;i++){
        	donn�esEnss.add(new donn�esEns(sheet.getCell(1,i).getContents(),sheet.getCell(2,i).getContents(),sheet.getCell(3,i).getContents(),sheet.getCell(4,i).getContents(),sheet.getCell(5,i).getContents(),sheet.getCell(6,i).getContents()));   
        }       
        final TreeItem<donn�esEns> root = new RecursiveTreeItem<donn�esEns>(donn�esEnss, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(horDeb, horFin, inti,ment,ens,nbPla);
        treeView.setRoot(root);
        treeView.setShowRoot(false);        
    }

    @FXML
    private void del(MouseEvent event) throws IOException {
    	if(event.getClickCount() == 1){
    		contenuCr�ationController.suprP.setDisable(false);
    		int indSel = treeView.getSelectionModel().getSelectedIndex();    	
	  		contenuCr�ationController.suprP.setOnAction(new EventHandler<ActionEvent>() {
	              @Override
	              public void handle(ActionEvent event) {
	            	  donn�esEnss.remove(indSel);	
	      			Workbook workbook = null;
	  				try {	
	  					File ins = new File(Main.chemin);
	  					workbook = Workbook.getWorkbook(ins);
	  					File outFile = new File(Main.cheminTemp);
	  					WritableWorkbook wwb = Workbook.createWorkbook(outFile, workbook);
	  						 
	  					WritableSheet dataSheet = wwb.getSheet("Cr�ation_" + Cr�ationCoursController.drawerNameP);
	  						
	  					dataSheet.removeRow(indSel+1);
	  						
	  					wwb.write();
	  					wwb.close(); 
	  						               
	  					File insb = new File(Main.chemin);
	  					insb.delete();
	  						               
	  					File insc = new File(Main.chemin);
	  					outFile.renameTo(insc);
	  				} catch (BiffException e) {
	  					e.printStackTrace();
	  				} catch (IOException e) {
	  					e.printStackTrace();
	  				} catch (WriteException e) {
	  					e.printStackTrace();
	  				}	
	  				finally {
	  					if(workbook!=null){
	  							workbook.close();
	  					}
	  				}
	              }
	          });
    			
    		}
    	}

    public class donn�esEns extends RecursiveTreeObject<donn�esEns> {

        public StringProperty horraireDeD�but;
        public StringProperty horraireDeFin ;
        public StringProperty intitul�;
        public StringProperty mention;
        public StringProperty enseignant;
        public StringProperty nombrePlace;
        

        public donn�esEns(String horraireDeD�but, String horraireDeFin, String intitul�,String mention, String enseignant, String nombrePlace) {
            this.horraireDeD�but = new SimpleStringProperty(horraireDeD�but);
            this.horraireDeFin = new SimpleStringProperty(horraireDeFin);
            this.intitul� = new SimpleStringProperty(intitul�);
            this.mention = new SimpleStringProperty(mention);
            this.enseignant = new SimpleStringProperty(enseignant);
            this.nombrePlace = new SimpleStringProperty(nombrePlace);
        }

    }
    
}
