package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ErrorPageController {

	@FXML private Button button_reread;
	@FXML private Button button_IDregister;
	private CardReader cardReader = new CardReader();
	private Utilities utilities = new Utilities();

	public void initialize() {
		Thread thread = new Thread(cardReader);
		thread.start();
	}

	// 「読み直す」ボタンをクリックしたとき
	@FXML
	void onRereadButtonClick(ActionEvent event) {
		//button_reread.getScene().getWindow().hide(); // ウィンドウを非表示にする
		Main.getInstance().closeErrorWindow(); // エラーウィンドウを閉じる
	}

	// 「名前を入力する」ボタンをクリックしたとき
	@FXML
	void onInputNameButtonClick(ActionEvent event) {
		Main.getInstance().setInputNamePage();
	}

	// 「ID登録」ボタンをクリックしたとき
	@FXML
	void onIDRegisterButtonClick(ActionEvent event) {
		Runtime runtime = Runtime.getRuntime();
		try {
			utilities.outputIDm(cardReader.getIDm()); // datファイルにIDmを出力
			runtime.exec("cmd /c Register1.py"); // Pythonの登録フォームを起動
			button_IDregister.getScene().getWindow().hide(); // ウィンドウを閉じる
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}