package awktal.mule;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import javafx.scene.Group;

import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.animation.Animation;
import javafx.animation.TimelineBuilder;
import javafx.animation.TranslateTransitionBuilder;
import javafx.animation.TranslateTransition;

import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.animation.FillTransition;


public class TitleController extends SceneController {

	@FXML
	GridPane gridpane;

	@FXML
	Button startButton;

	@FXML
	Button helpButton;

	@FXML
	Pane pane;

	final static Image MULE1 = new Image(TitleController.class.getResource("title/stubborn_mule.png").toExternalForm());
	final static Image MULE2 = new Image(TitleController.class.getResource("title/stubborn_mule1.png").toExternalForm());
	final ImageView MULE1IMG = new ImageView(MULE1);
	final ImageView MULE2IMG = new ImageView(MULE2);
	private Group muleAnimation;

	Shape muleText;
	Path path;
	private Group textAnimation;	

	public TitleController() {
    }

	@FXML
	public void initialize() {
	    pane.setStyle("-fx-background-color: #73CEA5");

	    MULE1IMG.fitWidthProperty().bind(gridpane.widthProperty());
	    MULE1IMG.fitHeightProperty().bind(gridpane.heightProperty());
	    MULE2IMG.fitWidthProperty().bind(gridpane.widthProperty());
	    MULE2IMG.fitHeightProperty().bind(gridpane.heightProperty());

	    muleText = generateText();
	    textAnimation = new Group(muleText);
	    path = generateCurvyPath();
		textAnimation.getChildren().add(path);
		changeColors();
	    muleAnimation = new Group(MULE1IMG);
	    createAnimation();
	    gridpane.add(muleAnimation, 0, 0);	    
	    startWalking();
	}

	private Text generateText() {
		final Reflection reflection = new Reflection();
		reflection.setFraction(1.0);
		return TextBuilder.create()
			.text("M.U.L.E.")
			.font(Font.font(java.awt.Font.SANS_SERIF, 100))
			.effect(reflection)
			.build();
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
					animateText();
				}
			}).build();
		walk.play();
	}

	private void changeColors() {
		FillTransition ft = new FillTransition(Duration.millis(3000), muleText,
			Color.DARKOLIVEGREEN, Color.BLANCHEDALMOND);
		ft.setCycleCount(Animation.INDEFINITE);
		ft.play();
	}

	@FXML
	private void animateText() {
		gridpane.add(textAnimation, 0, 0);
		final PathTransition transition = generatePathTransition(muleText,
			path, Duration.seconds(8.0), Duration.seconds(0.5), OrientationType.NONE);
		transition.play();
	}

	private Path generateCurvyPath() {
		final Path path = new Path();
		path.getElements().add(new MoveTo(300, 300));
		path.getElements().add(new CubicCurveTo(430, 0, 430, 120, 250, 120));
		path.getElements().add(new CubicCurveTo(50, 120, 50, 240, 430, 240));
		path.getElements().add(new CubicCurveTo(600, 600, 50, 50, 100, 240));
		path.getElements().add(new CubicCurveTo(100, 100, 0, 0, 120, 250));
		path.getElements().add(new CubicCurveTo(0, 600, 50, 300, 600, 10));
		path.setOpacity(0.0);
		return path;
	}

	private PathTransition generatePathTransition(final Shape shape, final Path path,
		final Duration duration, final Duration delay, final OrientationType orientation) {
		final PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(duration);
		pathTransition.setDelay(delay);
		pathTransition.setPath(path);
		pathTransition.setNode(shape);
		pathTransition.setOrientation(orientation);
		pathTransition.setCycleCount(Timeline.INDEFINITE);
		pathTransition.setAutoReverse(true);
		return pathTransition;
	}

	public void startSelection() {
		SceneManager.loadScene(GameScene.GAME_CONFIG);
	}
}