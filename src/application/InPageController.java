package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class InPageController {

	@FXML private Label label_clock;
	@FXML private Label label_IDm;
	private Utilities utilities = new Utilities();
	private CardReader cardReader = new CardReader();
	private String inPageIDm = "";

	public void initialize() {
		label_clock.setText(utilities.getTime("MM/dd HH:mm"));
		utilities.runClock(label_clock);
		Thread thread = new Thread(cardReader);
		thread.start();
		setIDmLabel(label_IDm);
	}

	// トップに戻るボタンクリック時
	@FXML
	void onReturnButtonClick(ActionEvent event) {
		label_IDm.setText("");
		Main.getInstance().setPage("TopPage.fxml");
	}

	// 履歴ボタンクリック時
	@FXML
	void onHistoryButtonClick(ActionEvent event) {
		System.out.println("debug historyButton");
	}

	// ラベルにIDmを表示
	void setIDmLabel(Label label_IDm) {
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				inPageIDm = cardReader.getIDm();
				// IDm表示
				label_IDm.setText(inPageIDm);
			}
		}));

		timeline.setCycleCount(Timeline.INDEFINITE); // 無限に繰り返す
		timeline.play();
	}

}