package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HistoryPageController{

	@FXML private Button button_exit;

	// 終了ボタンをクリックしたとき
	@FXML
	void onExitButtonClick(ActionEvent event) {
		button_exit.getScene().getWindow().hide(); // ウィンドウを閉じる
	}
}