package application;

import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class C3BIOSController {

	@FXML
	private Label label_time;

	// 入室ボタンクリック時
	@FXML
	void onInButtonClick(ActionEvent event) {
		Main.getInstance().setPage("InPage.fxml");
		System.out.println("debug1 "+label_time.getText()); // setText前．label_text, 表示共に "1/21 12:34"
		label_time.setText(LocalDateTime.now().toString()); // 現在時刻を表示
		System.out.println("debug2 "+label_time.getText()); // setText後．label_text は変わっているが，表示は "1/21 12:34" のまま
	}

	// 退室ボタンクリック時
	@FXML
	void onOutButtonClick(ActionEvent event) {
		Main.getInstance().setPage("OutPage.fxml");
		label_time.setText("退室");
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
		label_time.setText("トップ");
	}

}
