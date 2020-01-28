package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class C3BIOSController {

	@FXML
	private Label label_timer;

	// 入室ボタンクリック時
	@FXML
	protected void onInButtonClick(ActionEvent event) {
		Main.getInstance().setPage("InPage.fxml");
		label_timer.setText("入室ボタン押した");
	}

	// 退室ボタンクリック時
	@FXML
	protected void onOutButtonClick(ActionEvent event) {
		Main.getInstance().setPage("OutPage.fxml");
		label_timer.setText("退室ボタン押した");
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
		label_timer.setText("トップに戻るボタン押した");
	}

}
