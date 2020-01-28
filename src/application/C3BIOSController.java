package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class C3BIOSController {

	@FXML
	private Label label_time;

	// 入室ボタンクリック時
	@FXML
	protected void onInButtonClick(ActionEvent event) {
		Main.getInstance().setPage("InPage.fxml");
	}

	// 退室ボタンクリック時
	@FXML
	protected void onOutButtonClick(ActionEvent event) {
		Main.getInstance().setPage("OutPage.fxml");
	}

	// 履歴ボタンクリック時
	@FXML
	protected void onHistoryButtonClick(ActionEvent event) {
		System.out.println("debug historyButton");
	}

	// トップに戻るボタンクリック時
	@FXML
	protected void onReturnButtonClick(ActionEvent event) {
		Main.getInstance().setPage("TopPage.fxml");
	}
}
