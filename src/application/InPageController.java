package application;

import java.util.Map;

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
	@FXML private Label label_username;
	private Utilities utilities = new Utilities();
	private CardReader cardReader = new CardReader();
	private String inPageIDm = "";
	private Map<String, String> IDmMap = utilities.getIDmMap(2); // IDmと名前を関連付けたMapを取得

	public void initialize() {
		label_clock.setText(utilities.getTime("MM/dd HH:mm"));
		utilities.runClock(label_clock);
		Thread thread = new Thread(cardReader);
		thread.start();
		setUserInfoLabel(label_IDm, label_username);
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
		Main.getInstance().showHistoryWindow();
	}

	// ラベルにIDmを表示
	void setUserInfoLabel(Label label_IDm, Label label_username) {
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				inPageIDm = cardReader.getIDm();
				label_IDm.setText(inPageIDm);// IDm表示
				label_username.setText(IDmMap.get(inPageIDm)); // 名前を表示．InPageに来た時点でnullにはならないはず．
			}
		}));

		timeline.setCycleCount(Timeline.INDEFINITE); // 無限に繰り返す
		timeline.play();
	}

}