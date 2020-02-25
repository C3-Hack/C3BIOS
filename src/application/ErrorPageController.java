package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ErrorPageController {

	@FXML private Button button_reread;

	// 「読み直す」ボタンをクリックしたとき
	@FXML
	void onRereadButtonClick(ActionEvent event) {
		button_reread.getScene().getWindow().hide(); // ウィンドウを非表示にする
	}

	// 「名前を入力する」ボタンをクリックしたとき
	@FXML
	void onInputNameButtonClick(ActionEvent event) {
		Main.getInstance().setInputNamePage();
	}
}