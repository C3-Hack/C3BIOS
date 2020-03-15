package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Utilities {

	// 時計を動かす
	// 引数：時計のラベル
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


	// 時刻を取得
	// 引数：表示方法．以下を参照
	// https://docs.oracle.com/javase/jp/8/docs/api/java/text/SimpleDateFormat.html
	String getTime(String pattern) {
		return new SimpleDateFormat(pattern).format(new Date());
	}


	// CSV書き出し
	// 引数：ファイル名，カードのIDm，学籍番号，利用者の名前，日付，時間
	// 学籍番号の C は 3 に置き換える．
	void writeCSV(String fileName, String cardID, String studentID, String userName, String date, String time) {
		try{
			String dirName = "CSV";
			File dir = new File(dirName);

			// CSVディレクトリが無い場合
			if(!dir.exists()) {
				dir.mkdir(); // CSVディレクトリを作成
			}

			String filepath = dirName + "\\" + fileName; // csvファイルまでの相対パス
            FileWriter fw = new FileWriter(filepath, true); // ファイルに追記モードで書き込みを行う
            fw.write(cardID + "," + replaceAlphabetToInteger(studentID) + "," + userName + "," + date + "," + time + "\n"); // ファイルに書き込み
            fw.close(); // ファイルを閉じる
        } catch(IOException e){
            e.printStackTrace();
        }
	}


	// 学籍番号のアルファベットを数字に置き換えて整数を返す
	// 一応 A から E まで変換しているが，C だけでいいかも
	// 引数：学籍番号の文字列
	private int replaceAlphabetToInteger(String studentID) {
		studentID = studentID.replace("A", "1"); // A を 1 に置き換え
		studentID = studentID.replace("B", "2"); // B を 2 に置き換え
		studentID = studentID.replace("C", "3"); // C を 3 に置き換え
		studentID = studentID.replace("D", "4"); // D を 4 に置き換え
		studentID = studentID.replace("E", "5"); // E を 5 に置き換え
		return Integer.parseInt(studentID);
	}


	// 登録情報を読み，IDmと，名前または学籍番号を関連付けたMapを返す
	// 引数  ：整数．1→学籍番号と関連付ける．2→名前と関連付ける．その他→null
	// 返り値：関連付けたMapを返す．Key: IDm / Value: 名前または学籍番号
	Map<String, String> getIDmMap(int number){
		// 引数が1または2でない場合はnullを返す
		if(!(number == 1 || number == 2)) {
			return null;
		}

		Map<String, String> map = new HashMap<>();
		List<String> text = readCSV("CSV\\registeredUser.csv");

		for(String str : text) {
			map.put(str.split(",")[0], str.split(",")[number]); // MapにIDmと，名前または学籍番号を入れる
		}

		return map;
	}


	// csvファイルを読み取り，各行を要素とするリストを返す
	private List<String> readCSV(String filepath){
		try {
			Path file = Paths.get(filepath); // ファイルまでのパス
			return Files.readAllLines(file); // ファイルを読み取り，1行ずつリストに入れる
		} catch(IOException e) {
			e.printStackTrace();
		}

		return null; // 読み取れなかったらnullを返す

	}


	// 読み取ったIDmをdatファイルに出力
	void outputIDm(String IDm) {
		try {
			String dirName = "data";
			File dir = new File(dirName);

			// dataディレクトリがない場合
			if(!dir.exists()) {
				dir.mkdir(); // dataディレクトリ作成
			}

			String filepath = dirName + "\\IDm.dat";
			FileWriter fw = new FileWriter(filepath); // 上書きモードでファイルに出力
			fw.write(IDm);
			fw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}