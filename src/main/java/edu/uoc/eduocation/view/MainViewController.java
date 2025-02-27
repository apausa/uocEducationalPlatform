package edu.uoc.eduocation.view;

import edu.uoc.eduocation.EdUOCation;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainViewController {

    /**
     * It goes to the "play" scene.
     */
    @FXML
    public void newLogin(){
        try{
            EdUOCation.main.goScene("play");
        }catch(IOException e){
            System.exit(1);
        }
    }

    /**
     * It goes to the "credits" scene.
     */
    @FXML
    public void readCredits(){
        try{
            EdUOCation.main.goScene("credits");
        }catch(IOException e){
            System.exit(1);
        }
    }

}
