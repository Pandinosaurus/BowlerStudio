package junit.bowlerstudio;

import com.neuronrobotics.bowlerstudio.creature.LinkConfigurationWidget;
import com.neuronrobotics.bowlerstudio.creature.LinkSliderWidget;
import com.neuronrobotics.bowlerstudio.creature.MobileBaseCadManager;
import com.neuronrobotics.bowlerstudio.creature.MobileBaseLoader;
import com.neuronrobotics.bowlerstudio.scripting.ScriptingEngine;
import com.neuronrobotics.sdk.addons.kinematics.DHParameterKinematics;
import com.neuronrobotics.sdk.addons.kinematics.LinkConfiguration;
import com.neuronrobotics.sdk.addons.kinematics.MobileBase;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppTestLinkWidgets extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		ScriptingEngine.gitScriptRun("https://github.com/CommonWealthRobotics/DeviceProviders.git",
				"loadAll.groovy", null);
    	MobileBase base = MobileBaseLoader.fromGit("https://github.com/Hephaestus-Arm/HephaestusArm2.git", "hephaestus.xml");
    	DHParameterKinematics limb = base.getAllDHChains().get(0);
    	int index = 2;
    	LinkConfiguration conf=limb.getLinkConfiguration(index);
		LinkConfigurationWidget theWidget =new LinkConfigurationWidget(conf, limb.getFactory(),
				MobileBaseCadManager.get(base));
    	LinkSliderWidget lsw = new LinkSliderWidget(index,  limb,theWidget);
    	
    			
    	
    	VBox box = new VBox();
    	box.getChildren().addAll(lsw);
        Group root = new Group(box);
        Scene scene = new Scene(root, 800, 900);

        stage.setTitle("My JavaFX Application");
        stage.setScene(scene);
        stage.show();
        
	}
	public static void main(String [] args) {
		launch(args);
	}
}
