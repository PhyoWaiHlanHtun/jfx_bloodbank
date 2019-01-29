package transfer;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import fxml_loader.fxmlL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import setNode.SetNode;

import java.net.URL;
import java.util.ResourceBundle;

public class Transfer_homeController implements Initializable{

    @FXML
    private JFXHamburger slide_burger;

    @FXML
    private StackPane anchor;

    @FXML
    private JFXDrawer slide;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new SetNode(anchor,"/transfer/Register.fxml");

        try {
            VBox box = FXMLLoader.load(getClass().getResource("Slider.fxml"));
            slide.setSidePane(box);

            for(Node node : box.getChildren()){
                AnchorPane ap = (AnchorPane) node;
                for (Node node1 : ap.getChildren())
                    if(node1.getAccessibleText() != null){
                        node1.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
                            switch (node1.getAccessibleText()){
                                case "register":
                                    new SetNode(anchor,"/transfer/Register.fxml");
                                    break;
                                case "hospital":
                                    new SetNode(anchor,"/transfer/Hospital_transfer.fxml");
                                    break;
                                case "patient":
                                    new SetNode(anchor,"/transfer/Patient_transfer.fxml");
                                    break;
                                case "exit":
                                    new fxmlL((Stage)((Node)e.getSource()).getScene().getWindow(),"/homepage/HomePage.fxml");
                                    break;
                            }
                        });
                    }
            }

            HamburgerBackArrowBasicTransition HBAT = new HamburgerBackArrowBasicTransition(slide_burger);
            HBAT.setRate(-1);
            slide_burger.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                HBAT.setRate(HBAT.getRate() * -1);
                HBAT.play();

                if (slide.isClosed() && anchor.getPrefWidth()==1000) {
                    slide.open();
                    anchor.setPrefWidth(800);
                }else {
                    slide.close();
                    anchor.setPrefWidth(1000);
                }
            });
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
