package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class C3BIOSController {

	@FXML private Label label_clock;
	@FXML private Label label_IDm;
	@FXML private Label label_message;
	@FXML private Button button_reread;
	private CardReader cardReader = new CardReader();
	private String IDm = "";

	// 最初に呼ばれる
	public void initialize() {
		label_clock.setText(getTime("MM/dd HH:mm"));
		runClock(label_clock);
		Thread thread = new Thread(cardReader);
		thread.start();
		setIDmLabel(label_IDm, label_message);
	}

	// 入室ボタンクリック時
	@FXML
	void onInButtonClick(ActionEvent event) {
		if(IDm == "") {
			Main.getInstance().callErrorWindow(); // エラーウィンドウ呼び出し
		} else {
			writeCSV("InOutTime.csv", IDm, "182C1000", getTime("yyMMdd"), getTime("HH:mm")); // CSVに書き込み
			cardReader.setIDm(""); // 読み取ったIDmをリセット
			Main.getInstance().setPage("InPage.fxml");
		}
	}

	// 履歴ボタンクリック時
	@FXML
	void onHistoryButtonClick(ActionEvent event) {
		System.out.println("debug historyButton");
	}

	// トップに戻るボタンクリック時
	@FXML
	void onReturnButtonClick(ActionEvent event) {
		cardReader.setIDm(""); // 読み取ったIDmをリセット
		Main.getInstance().setPage("TopPage.fxml");
	}

	// エラーウィンドウにて「読み直す」ボタンをクリックしたとき
	@FXML
	void onRereadButtonClick(ActionEvent event) {
		button_reread.getScene().getWindow().hide(); // ウィンドウを非表示にする
	}

	// 時計を動かす
	void runClock(Label label_clock) {
		// 100ミリ秒ごとに更新する
		Timeline clock = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// MM/dd HH:mm:ss の形で時刻を表示
				label_clock.setText(getTime("MM/dd HH:mm"));
			}
		}));

		clock.setCycleCount(Timeline.INDEFINITE); // 無限に繰り返す
		clock.play();
	}

	// 時刻の表示
	// 引数：表示方法．以下を参照
	// https://docs.oracle.com/javase/jp/8/docs/api/java/text/SimpleDateFormat.html
	String getTime(String pattern) {
		return new SimpleDateFormat(pattern).format(new Date());
	}

	// CSV書き出し
	// 引数：ファイル名，カードのIDm，学籍番号，日付，時間
	// 学籍番号の C は 3 に置き換える．
	void writeCSV(String fileName, String cardID, String studentID, String date, String time) {
		try{
			String dirName = "CSV";
			File dir = new File(dirName);

			// CSVディレクトリが無い場合
			if(!dir.exists()) {
				dir.mkdir(); // CSVディレクトリを作成
			}

			String filepath = dirName + "\\" + fileName; // csvファイルまでの相対パス
            FileWriter fw = new FileWriter(filepath, true); // ファイルに追記モードで書き込みを行う
            fw.write(cardID + "," + replaceAlphabetToInteger(studentID) + "," + date + "," + time + "\n"); // ファイルに書き込み
            fw.close(); // ファイルを閉じる
        } catch(IOException e){
            e.printStackTrace();
        }
	}

	// 学籍番号のアルファベットを数字に置き換えて整数を返す
	// 一応 A から E まで変換しているが，C だけでいいかも
	int replaceAlphabetToInteger(String studentID) {
		studentID = studentID.replace("A", "1"); // A を 1 に置き換え
		studentID = studentID.replace("B", "2"); // B を 2 に置き換え
		studentID = studentID.replace("C", "3"); // C を 3 に置き換え
		studentID = studentID.replace("D", "4"); // D を 4 に置き換え
		studentID = studentID.replace("E", "5"); // E を 5 に置き換え
		return Integer.parseInt(studentID);
	}

	// ラベルにIDmを表示
	// メッセージを変更するため，メッセージ用のラベルも渡している．
	void setIDmLabel(Label label_IDm, Label label_message) {
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				IDm = cardReader.getIDm();
				// IDm表示
				label_IDm.setText(IDm);
				// IDmを読み取ったらメッセージ変更
				if(IDm == "") {
					label_message.setText("カードをタッチしてください");
				} else {
					label_message.setText("入室ボタンをクリックしてください");
				}
			}
		}));

		timeline.setCycleCount(Timeline.INDEFINITE); // 無限に繰り返す
		timeline.play();
	}

}
