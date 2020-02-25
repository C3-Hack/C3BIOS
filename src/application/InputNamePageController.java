package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InputNamePageController {

	@FXML private TextField textField_name;
	@FXML private Label label_warning;
	private Utilities utilities = new Utilities();

	// 名前入力画面の入室ボタンをクリックしたとき
	@FXML
	void onInButtonInErrorWindowClick(ActionEvent event) {
		String username = textField_name.getText();

		if(username.equals("")) {
			// 名前が入力されなかった場合もcsvには書き込まない
			label_warning.setText("名前を入力してください");
		} else if(username.contains(",")) {
			// 名前にコンマが含まれていたらcsvには書き込まない
			label_warning.setText("名前に \",\" は使えません．");
		} else {
			// それ以外はcsvに書き込み
			utilities.writeCSV("InOutTime.csv", "00 00 00 00 00 00 00 00", "182C1000", username, utilities.getTime("yyMMdd"), utilities.getTime("HH:mm"));
			label_warning.setText("");
		}

	}

}