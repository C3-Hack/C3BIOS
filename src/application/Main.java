package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {

	public static Main main_class;
	private HistoryPageController historyPageController;
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
	void showHistoryWindow() {
		VBox vbox = historyPageController.getVBox();
		vbox.getChildren().clear(); // VBox内の要素を削除

		// csvファイルを読み取って，各行の内容をVBoxとLabelで表示
		try {
            Path file = Paths.get("CSV\\InOutTime.csv"); // ファイルまでのパス
            List<String> text = Files.readAllLines(file); // ファイルを読み取り，1行ずつリストに入れる

            // 読み取ったcsvの各行に対して実行
            for(String str : text){
            	// 表示するテキストの準備
            	String splitedText[] = str.split(","); // 各カラムを分けて配列に格納
            	String showText = "";
            	for(int i = 1; i < splitedText.length; i++) {
            		showText += splitedText[i]; // IDm以外の情報を表示する
            		if(i != splitedText.length - 1) {
            			showText += ", "; // 各情報の間にコンマを入れる
            		}
            	}

            	// 表示するラベルの準備
            	Label row = new Label(showText); // 各行の表示用のラベル
            	row.setFont(new Font(24)); // フォントサイズ24
            	vbox.getChildren().add(row); // ラベルをVBoxに入れる
            }

        } catch(IOException e){
            e.printStackTrace();
        }

		historyStage.setScene(historyPage);
		historyStage.show();
	}


	// Controller から Main クラスを呼び出す際に使う
	public static Main getInstance() {
		return main_class;
	}

}
