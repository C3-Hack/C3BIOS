package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InputNamePageController {

	@FXML private TextField textField_name;
	private Utilities utilities = new Utilities();

	// 名前入力画面の入室ボタンをクリックしたとき
	@FXML
	void onInButtonInErrorWindowClick(ActionEvent event) {
		System.out.println(textField_name.getText());
		utilities.writeCSV("InOutTime.csv", "00 00 00 00 00 00 00 00", "182C1000", textField_name.getText(), utilities.getTime("yyMMdd"), utilities.getTime("HH:mm"));
	}

}