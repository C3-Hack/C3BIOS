package application;

import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TopPageController {

	@FXML private Label label_clock;
	@FXML private Label label_IDm;
	@FXML private Label label_username;
	@FXML private Label label_message;
	private CardReader cardReader = new CardReader();
	private Utilities utilities = new Utilities();
	private String topPageIDm = "";
	private Map<String, String> IDmAndStudentIDMap = utilities.getIDmMap(1); // IDmと学籍番号を関連付けたMapを取得
	private Map<String, String> IDmAndNameMap = utilities.getIDmMap(2); // IDmと名前を関連付けたMapを取得

	// 最初に呼ばれる
	public void initialize() {
		label_clock.setText(utilities.getTime("MM/dd HH:mm"));
		utilities.runClock(label_clock);
		Thread thread = new Thread(cardReader);
		thread.start();
		setUserInfoLabel(label_IDm, label_username, label_message);
	}

	// 入室ボタンクリック時
	@FXML
	void onInButtonClick(ActionEvent event) {
		topPageIDm = cardReader.getIDm(); // カードのIDm読み取り
		IDmAndStudentIDMap = utilities.getIDmMap(1); // IDmと学籍番号のMapを更新
		IDmAndNameMap = utilities.getIDmMap(2); // IDmと名前のMapを更新
		boolean isRegisteredIDm = false; // 登録されているIDmかどうか

		// 読み取ったIDmが登録されているかを調べる
		for(String str : IDmAndNameMap.keySet()) {
			if(str.equals(topPageIDm)) {
				isRegisteredIDm = true;
				break;
			}
		}

		// IDmが登録されていなければエラーウィンドウを表示
		if(!isRegisteredIDm) {
			Main.getInstance().callErrorWindow(); // エラーウィンドウ呼び出し
		} else {
			utilities.writeCSV("InOutTime.csv", topPageIDm, IDmAndStudentIDMap.get(topPageIDm), IDmAndNameMap.get(topPageIDm), utilities.getTime("yyMMdd"), utilities.getTime("HHmm")); // CSVに書き込み
			cardReader.setIDm(""); // 読み取ったIDmをリセット
			Main.getInstance().setPage("InPage.fxml"); // 入室ページへ遷移
		}
	}

	// 履歴ボタンクリック時
	@FXML
	void onHistoryButtonClick(ActionEvent event) {
		Main.getInstance().showHistoryWindow("全て表示");
	}

	// ラベルにIDm, 名前，メッセージを表示
	void setUserInfoLabel(Label label_IDm, Label label_username, Label label_message) {
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				topPageIDm = cardReader.getIDm(); // カードリーダーでIDmを読み取り
				IDmAndNameMap = utilities.getIDmMap(2); // IDmと名前のMapを更新
				String username = IDmAndNameMap.get(topPageIDm); // IDmに対応する名前を取得

				label_IDm.setText(topPageIDm); // IDm表示
				// 名前を表示．登録されていない場合は空文字列を表示．
				if(username == null) {
					label_username.setText("");
				} else {
					label_username.setText(username);
				}

				// IDmを読み取ったらメッセージ変更
				if(topPageIDm == "") {
					label_message.setText("カードをタッチしてください");
				} else {
					label_message.setText("入室ボタンをクリックしてください");
				}
			}
		}));

		timeline.setCycleCount(Timeline.INDEFINITE); // 無限に繰り返す
		timeline.play();
	}

	// IDmの文字列を設定する
	public void setTopPageIDm(String str) {
		topPageIDm = str; // TopPageController のIDm を設定
		cardReader.setIDm(str); // CardReader の IDm を設定
	}

}
