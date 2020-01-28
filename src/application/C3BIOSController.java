package application;

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

}
