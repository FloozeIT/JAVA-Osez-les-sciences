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
    private JFXTreeTableView<donnéesEns> treeView;
    String User = System.getProperty("user.name");
    
    private ObservableList<donnéesEns> donnéesEnss = FXCollections.observableArrayList();
    
    @SuppressWarnings("unchecked")
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	contenuCréationController.suprP.setDisable(true);
        JFXTreeTableColumn<donnéesEns, String> horDeb = new JFXTreeTableColumn<>("Heure de début");
        horDeb.setPrefWidth(122);
        horDeb.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donnéesEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donnéesEns, String> param) {
                return param.getValue().getValue().horraireDeDébut;
            }
        });

        JFXTreeTableColumn<donnéesEns, String> horFin = new JFXTreeTableColumn<>("Heure de fin");
        horFin.setPrefWidth(122);
        horFin.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donnéesEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donnéesEns, String> param) {
                return param.getValue().getValue().horraireDeFin;
            }
        });

        JFXTreeTableColumn<donnéesEns, String> inti = new JFXTreeTableColumn<>("Intitulé");
        inti.setPrefWidth(122);
        inti.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donnéesEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donnéesEns, String> param) {
                return param.getValue().getValue().intitulé;
            }
        });

        JFXTreeTableColumn<donnéesEns, String> ment = new JFXTreeTableColumn<>("Mention");
        ment.setPrefWidth(122);
        ment.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donnéesEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donnéesEns, String> param) {
                return param.getValue().getValue().mention;
            }
        });

        JFXTreeTableColumn<donnéesEns, String> ens = new JFXTreeTableColumn<>("Enseignant");
        ens.setPrefWidth(122);
        ens.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donnéesEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donnéesEns, String> param) {
                return param.getValue().getValue().enseignant;
            }
        });

        JFXTreeTableColumn<donnéesEns, String> nbPla = new JFXTreeTableColumn<>("Places");
        nbPla.setPrefWidth(122);
        nbPla.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<donnéesEns, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<donnéesEns, String> param) {
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
		Sheet sheet = workbook.getSheet("Création_" + CréationCoursController.drawerNameP);
        for(int i=1; i<sheet.getColumn(0).length;i++){
        	donnéesEnss.add(new donnéesEns(sheet.getCell(1,i).getContents(),sheet.getCell(2,i).getContents(),sheet.getCell(3,i).getContents(),sheet.getCell(4,i).getContents(),sheet.getCell(5,i).getContents(),sheet.getCell(6,i).getContents()));   
        }       
        final TreeItem<donnéesEns> root = new RecursiveTreeItem<donnéesEns>(donnéesEnss, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(horDeb, horFin, inti,ment,ens,nbPla);
        treeView.setRoot(root);
        treeView.setShowRoot(false);        
    }

    @FXML
    private void del(MouseEvent event) throws IOException {
    	if(event.getClickCount() == 1){
    		contenuCréationController.suprP.setDisable(false);
    		int indSel = treeView.getSelectionModel().getSelectedIndex();    	
	  		contenuCréationController.suprP.setOnAction(new EventHandler<ActionEvent>() {
	              @Override
	              public void handle(ActionEvent event) {
	            	  donnéesEnss.remove(indSel);	
	      			Workbook workbook = null;
	  				try {	
	  					File ins = new File(Main.chemin);
	  					workbook = Workbook.getWorkbook(ins);
	  					File outFile = new File(Main.cheminTemp);
	  					WritableWorkbook wwb = Workbook.createWorkbook(outFile, workbook);
	  						 
	  					WritableSheet dataSheet = wwb.getSheet("Création_" + CréationCoursController.drawerNameP);
	  						
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

    public class donnéesEns extends RecursiveTreeObject<donnéesEns> {

        public StringProperty horraireDeDébut;
        public StringProperty horraireDeFin ;
        public StringProperty intitulé;
        public StringProperty mention;
        public StringProperty enseignant;
        public StringProperty nombrePlace;
        

        public donnéesEns(String horraireDeDébut, String horraireDeFin, String intitulé,String mention, String enseignant, String nombrePlace) {
            this.horraireDeDébut = new SimpleStringProperty(horraireDeDébut);
            this.horraireDeFin = new SimpleStringProperty(horraireDeFin);
            this.intitulé = new SimpleStringProperty(intitulé);
            this.mention = new SimpleStringProperty(mention);
            this.enseignant = new SimpleStringProperty(enseignant);
            this.nombrePlace = new SimpleStringProperty(nombrePlace);
        }

    }
    
}
