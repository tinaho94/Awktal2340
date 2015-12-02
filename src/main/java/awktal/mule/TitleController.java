package awktal.mule;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;

import javafx.scene.Group;

import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.animation.Animation;
import javafx.animation.TimelineBuilder;
import javafx.animation.TranslateTransitionBuilder;
import javafx.animation.TranslateTransition;

public class TitleController extends SceneController {

	@FXML
	GridPane gridpane;

	@FXML
	Button startButton;

	@FXML
	Button helpButton;

	final static Image MULE1 = new Image(TitleController.class.getResource("title/stubborn_mule.png").toExternalForm());
	final static Image MULE2 = new Image(TitleController.class.getResource("title/stubborn_mule1.png").toExternalForm());
	final ImageView MULE1IMG = new ImageView(MULE1);
	final ImageView MULE2IMG = new ImageView(MULE2);
	private Group muleAnimation;

	public TitleController() {
    }

	@FXML
	public void initialize() {
	    
	    MULE1IMG.fitWidthProperty().bind(gridpane.widthProperty());
	    MULE1IMG.fitHeightProperty().bind(gridpane.heightProperty());
	    MULE2IMG.fitWidthProperty().bind(gridpane.widthProperty());
	    MULE2IMG.fitHeightProperty().bind(gridpane.heightProperty());

	    muleAnimation = new Group(MULE1IMG);
	    createAnimation();
	    gridpane.add(muleAnimation, 0, 0);
	    startWalking();
	}

	public void createAnimation() {
		TimelineBuilder.create()
			.cycleCount(Animation.INDEFINITE)
			.keyFrames(
				new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent t) {
						muleAnimation.getChildren().setAll(MULE1IMG);
					}
				}),
				new KeyFrame(Duration.millis(300), new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent t) {
						muleAnimation.getChildren().setAll(MULE2IMG);
					}
				})
			)
			.build().play();
			//startWalking();
	}

	private void startWalking() {
		TranslateTransition walk = TranslateTransitionBuilder.create()
			.node(muleAnimation)
			.fromX(-600)
			.toX(600)
			.duration(Duration.seconds(10))
			.onFinished(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent t) {
					startWalking();
				}
			}).build();
		walk.play();
	}

	public void startSelection() {
		SceneManager.loadScene(GameScene.GAME_CONFIG);
	}
}