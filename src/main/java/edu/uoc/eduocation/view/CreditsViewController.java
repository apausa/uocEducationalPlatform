package edu.uoc.eduocation.view;

import edu.uoc.eduocation.EdUOCation;
import javafx.fxml.FXML;

import java.io.IOException;

public class CreditsViewController {

    /**
     * It goes to the "main" scene.
     */
    @FXML
    public void backMain(){
        try{
            EdUOCation.main.goScene("main");
        }catch(IOException e){
            System.exit(1);
        }
    }

}
