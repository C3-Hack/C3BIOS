package application;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class C3BIOSController {

	@FXML Label label_clock;

	// 最初に呼ばれる
	public void initialize() {
		label_clock.setText(new SimpleDateFormat("MM/dd HH:mm:ss").format(new Date()));
		runClock(label_clock);
	}

	// 入室ボタンクリック時
	@FXML
	void onInButtonClick(ActionEvent event) {
		Main.getInstance().setPage("InPage.fxml");
	}

	// 退室ボタンクリック時
	@FXML
	void onOutButtonClick(ActionEvent event) {
		Main.getInstance().setPage("OutPage.fxml");
	}

	// 履歴ボタンクリック時
	@FXML
	void onHistoryButtonClick(ActionEvent event) {
		System.out.println("debug historyButton");
	}

	// トップに戻るボタンクリック時
	@FXML
	void onReturnButtonClick(ActionEvent event) {
		Main.getInstance().setPage("TopPage.fxml");
	}

	// 時計を動かす
	void runClock(Label label) {
		// 100ミリ秒ごとに更新する
		Timeline clock = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				label.setText(new SimpleDateFormat("MM/dd HH:mm:ss").format(new Date()));
			}
		}));

		clock.setCycleCount(Timeline.INDEFINITE); // 無限に繰り返す
		clock.play();
	}

}
