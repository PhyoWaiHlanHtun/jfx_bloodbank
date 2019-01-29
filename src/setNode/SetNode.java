package setNode;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

// FB : https://www.facebook.com/mr.phyowaihlanhtun
// Developer : PhyoWaiHlanHtun(Cryptonym)
// Contact : phyowaihlanhtun@gmail.com


public class SetNode {
    public SetNode(StackPane root, String file_path) {
        Node node;
//		root.getScene().getWindow();
        root.getChildren().clear();
        try {
            node = FXMLLoader.load(getClass().getResource(file_path));
            root.getChildren().add(node);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
