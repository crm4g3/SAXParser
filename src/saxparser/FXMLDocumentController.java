/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparser;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;

/**
 *
 * @author codyioslaptop
 */
public class FXMLDocumentController implements Initializable {

    
   @FXML
    private Label label;
    
    @FXML
    private TextArea textArea;
    
    @FXML
    private void handleOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(textArea.getScene().getWindow());
        if (file != null) {
            try
            {
                System.out.println("LoadingFile");
                Element root = SAXLoader.load(file);
                ArrayList<Element> elements = root.getElements();
                textArea.appendText(SAXLoader.getOutPut());
                //textArea.appendText(root.name +" : "+ root.value);
                //textArea.appendText("\n");
                /*for (Element element : elements) {
                    textArea.appendText("   "+element.name + " : " + element.value);
                    textArea.appendText("\n");
                }*/
                
                
            } catch (Exception ex) {
                System.out.println("Exception parsing XML file. "+ex);
            }
        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
