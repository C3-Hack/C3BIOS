package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HistoryPageController{

	@FXML private Button button_exit;
	@FXML private Label label_showing;
	@FXML private VBox vbox_history;
	@FXML private ChoiceBox<String> choicebox_studentID;
	private Utilities utilities = new Utilities();
	private Map<String, String> IDmAndStudentIDMap = utilities.getIDmMap(1); // IDmと学籍番号を関連付けたMapを取得

	public void initialize() {
		List<String> studentIDs = new ArrayList<String>(); // ChoiceBox内に入れる学籍番号のリスト
		studentIDs.add("全て表示"); // 全て表示するためのもの
		studentIDs.addAll(IDmAndStudentIDMap.values()); // 登録されている学籍番号を取得し，リストに入れる

		choicebox_studentID.getItems().addAll(studentIDs); // ChoiceBoxの選択肢に追加
		choicebox_studentID.setValue("全て表示"); // 最初に "全て表示" を選択しておく

		label_showing.setText(choicebox_studentID.getValue()); // 表示中の学籍番号をラベルに表示
	}

	// 終了ボタンをクリックしたとき
	@FXML
	void onExitButtonClick(ActionEvent event) {
		button_exit.getScene().getWindow().hide(); // ウィンドウを閉じる
	}

	// 更新ボタンをクリックしたとき
	@FXML
	void onReloadButtonClick(ActionEvent event) {
		label_showing.setText(choicebox_studentID.getValue()); // 表示中の学籍番号をラベルに表示
		Main.getInstance().showHistoryWindow(choicebox_studentID.getValue()); // 選択されている学籍番号の人の履歴のみ表示
	}

	// VBoxを返す
	public VBox getVBox() {
		return vbox_history;
	}
}