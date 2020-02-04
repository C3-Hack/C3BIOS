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
import javafx.scene.control.Label;
import javafx.util.Duration;

public class C3BIOSController {

	@FXML Label label_clock;

	// 最初に呼ばれる
	public void initialize() {
		label_clock.setText(getTime("MM/dd HH:mm"));
		runClock(label_clock);
	}

	// 入室ボタンクリック時
	@FXML
	void onInButtonClick(ActionEvent event) {
		Main.getInstance().setPage("InPage.fxml");
	}

	// 退室ボタンクリック時
	@FXML
	void onOutButtonClick(ActionEvent event) {
		Main.getInstance().setPage("OutPage.fxml");
	}

	// 履歴ボタンクリック時
	@FXML
	void onHistoryButtonClick(ActionEvent event) {
		System.out.println("debug historyButton");
	}

	// トップに戻るボタンクリック時
	@FXML
	void onReturnButtonClick(ActionEvent event) {
		Main.getInstance().setPage("TopPage.fxml");
	}

	// CSV書き込みテスト
	@FXML
	void writeTest(ActionEvent event) {
		writeCSV("InOutTime.csv", "00 00 00 00 00 00 00 00", getTime("yyMMdd"), getTime("HH:mm"));
	}

	// 時計を動かす
	void runClock(Label label) {
		// 100ミリ秒ごとに更新する
		Timeline clock = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// MM/dd HH:mm:ss の形で時刻を表示
				label.setText(getTime("MM/dd HH:mm"));
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
	// 引数：ファイル名，ID，日付，時間
	void writeCSV(String fileName, String id, String date, String time) {
		try{
			File dir = new File("CSV");
			// CSVディレクトリが無い場合
			if(!dir.exists()) {
				dir.mkdir(); // CSVディレクトリを作成
			}
			String filepath = "CSV\\" + fileName; // csvファイルまでの相対パス
            FileWriter fw = new FileWriter(filepath, true); // ファイルに追記モードで書き込みを行う
            fw.write(id + "," + date + "," + time + "\n"); // ファイルに書き込み
            fw.close(); // ファイルを閉じる
        } catch(IOException e){
            e.printStackTrace();
        }
	}

}
