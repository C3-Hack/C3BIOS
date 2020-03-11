package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class HistoryPageController{

	@FXML private Button button_exit;
	@FXML private VBox vbox_history;

	// 終了ボタンをクリックしたとき
	@FXML
	void onExitButtonClick(ActionEvent event) {
		button_exit.getScene().getWindow().hide(); // ウィンドウを閉じる
	}

	// VBoxを返す
	public VBox getVBox() {
		return vbox_history;
	}
}