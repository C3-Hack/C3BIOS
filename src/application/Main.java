package application;

import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {

	public static Main main_class;
	private HistoryPageController historyPageController;
	private Utilities utilities = new Utilities();
	private Stage stage;
	private Stage errorStage = new Stage();
	private Stage historyStage = new Stage();
	private Scene topPage;
	private Scene inPage;
	private Scene errorPage;
	private Scene inputNamePage;
	private Scene historyPage;

	@Override
	public void start(Stage primaryStage) {
		try {
			main_class = this;
			stage = primaryStage;
			topPage = new Scene(FXMLLoader.load(getClass().getResource("TopPage.fxml")), 600, 600);
			inPage = new Scene(FXMLLoader.load(getClass().getResource("InPage.fxml")), 600, 600);
			errorPage = new Scene(FXMLLoader.load(getClass().getResource("ErrorPage.fxml")), 500, 400);
			inputNamePage = new Scene(FXMLLoader.load(getClass().getResource("InputNamePage.fxml")), 500, 400);
			FXMLLoader fxmlLoaderOfHistoryPage = new FXMLLoader(getClass().getResource("HistoryPage.fxml"));
			historyPage = new Scene(fxmlLoaderOfHistoryPage.load());
			historyPageController = fxmlLoaderOfHistoryPage.getController();

			// エラーウィンドウの設定
			errorStage.initModality(Modality.APPLICATION_MODAL); // モーダルに設定
			errorStage.initOwner(stage); // 親ウィンドウの設定？
			errorStage.setScene(errorPage);
			errorStage.setTitle("エラー"); // ウィンドウタイトル
			errorStage.setResizable(false); // ウィンドウサイズ変更不可に設定

			// 履歴ウィンドウの設定
			historyStage.setTitle("履歴"); // ウィンドウタイトル
			historyStage.setResizable(false); // ウィンドウサイズを変更不可に設定

			// ページを表示する
			setPage("TopPage.fxml"); // TopPageを表示する
			stage.setTitle("C3BIOS"); // タイトル設定
			stage.setResizable(false); // ウインドウサイズ変更不可に設定
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		launch(args);
	}


	// 表示するページを設定する
	void setPage(String fxml) {
		try {
			switch(fxml) {
			case "TopPage.fxml":
				stage.setScene(topPage);
				break;

			case "InPage.fxml":
				stage.setScene(inPage);
				break;

			default:
				break;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// エラーウィンドウ表示
	void callErrorWindow(){
		errorStage.setScene(errorPage);
		errorStage.show();
	}

	// エラーウィンドウにて名前入力画面を表示
	void setInputNamePage() {
		errorStage.setScene(inputNamePage);
	}

	// エラーウィンドウを非表示にする
	void closeErrorWindow() {
		errorStage.close(); // ウィンドウを閉じる
		errorStage.setScene(errorPage); // エラーページをセット
	}

	// 履歴ウィンドウ表示
	// 引数：String 学籍番号．
	// 引数で与えられた学籍番号と一致する情報のみ表示する．全部表示する場合は "全て表示" を入れる．
	void showHistoryWindow(String studentID) {
		Label label_showing = historyPageController.getLabelShowing();
		ChoiceBox<String> choicebox_studentID = historyPageController.getChoiceBox();
		VBox vbox = historyPageController.getVBox();

		// 引数の学籍番号とラベルの学籍番号が違う場合，ラベルに引数の学籍番号をセット
		if(!label_showing.getText().equals(studentID)) {
			label_showing.setText(studentID);
		}

		// 引数の学籍番号と ChoiceBox の学籍番号が違う場合，引数の学籍番号を ChoiceBox にセット
		if(!choicebox_studentID.getValue().equals(studentID)) {
			choicebox_studentID.setValue(studentID);
		}

		vbox.getChildren().clear(); // VBox内の要素を削除
		List<String> text = utilities.readCSV("CSV\\InOutTime.csv"); // InOutTime.csvを読み取り，リストに入れる．

		// 読み取ったcsvの各行に対して実行
        for(String str : text){

        	// 表示するテキストの準備

        	// 各カラムを分けて配列に格納
        	// 0:IDm, 1:学籍番号, 2:名前, 3:年月日, 4:時刻
        	String splitedText[] = str.split(",");
        	String showText = ""; // 表示するテキスト

        	// 学籍番号が一致する場合のみ表示
        	if(studentID.equals("全て表示") || studentID.equals(splitedText[1])) {
        		for(int i = 1; i < splitedText.length; i++) {
            		showText += splitedText[i]; // IDm以外の情報を表示する

            		if(i != splitedText.length - 1) {
            			showText += ", "; // 各情報の間にコンマを入れる
            		}
            	}
        	} else {
        		continue;
        	}

        	// 表示するラベルの準備
        	Label row = new Label(showText); // 各行の表示用のラベル
        	row.setFont(new Font(24)); // フォントサイズ24
        	vbox.getChildren().add(row); // ラベルをVBoxに入れる
        }

		historyStage.setScene(historyPage);
		historyStage.show();
	}


	// Controller から Main クラスを呼び出す際に使う
	public static Main getInstance() {
		return main_class;
	}

}
