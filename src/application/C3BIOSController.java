package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class C3BIOSController {

	@FXML private Label label_clock;
	@FXML private Label label_IDm;
	@FXML private Label label_message;
	private CardReader cardReader = new CardReader();
	private Utilities utilities = new Utilities();
	private String IDm = "";

	// 最初に呼ばれる
	public void initialize() {
		label_clock.setText(utilities.getTime("MM/dd HH:mm"));
		utilities.runClock(label_clock);
		Thread thread = new Thread(cardReader);
		thread.start();
		setIDmLabel(label_IDm, label_message);
	}

	// 入室ボタンクリック時
	@FXML
	void onInButtonClick(ActionEvent event) {
		IDm = cardReader.getIDm();
		if(IDm == "") {
			Main.getInstance().callErrorWindow(); // エラーウィンドウ呼び出し
		} else {
			utilities.writeCSV("InOutTime.csv", IDm, "182C1000", utilities.getTime("yyMMdd"), utilities.getTime("HH:mm")); // CSVに書き込み
			cardReader.setIDm(""); // 読み取ったIDmをリセット
			Main.getInstance().setPage("InPage.fxml");
		}
	}

	// 履歴ボタンクリック時
	@FXML
	void onHistoryButtonClick(ActionEvent event) {
		System.out.println("debug historyButton");
	}

	// トップに戻るボタンクリック時
	@FXML
	void onReturnButtonClick(ActionEvent event) {
		cardReader.setIDm(""); // 読み取ったIDmをリセット
		Main.getInstance().setPage("TopPage.fxml");
	}

	// ラベルにIDmを表示
	// メッセージを変更するため，メッセージ用のラベルも渡している．
	void setIDmLabel(Label label_IDm, Label label_message) {
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				IDm = cardReader.getIDm();
				// IDm表示
				label_IDm.setText(IDm);
				// IDmを読み取ったらメッセージ変更
				if(IDm == "") {
					label_message.setText("カードをタッチしてください");
				} else {
					label_message.setText("入室ボタンをクリックしてください");
				}
			}
		}));

		timeline.setCycleCount(Timeline.INDEFINITE); // 無限に繰り返す
		timeline.play();
	}

}
