package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {

	public static Main main_class;
	private Stage stage;
	private Stage errorStage = new Stage();
	private Scene topPage;
	private Scene inPage;
	private Scene errorPage;
	private Scene inputNamePage;

	@Override
	public void start(Stage primaryStage) {
		try {
			main_class = this;
			stage = primaryStage;
			topPage = new Scene(FXMLLoader.load(getClass().getResource("TopPage.fxml")), 600, 600);
			inPage = new Scene(FXMLLoader.load(getClass().getResource("InPage.fxml")), 600, 600);
			errorPage = new Scene(FXMLLoader.load(getClass().getResource("ErrorPage.fxml")), 500, 400);
			inputNamePage = new Scene(FXMLLoader.load(getClass().getResource("InputNamePage.fxml")), 500, 400);

			// エラーウィンドウの設定
			errorStage.initModality(Modality.APPLICATION_MODAL); // モーダルに設定
			errorStage.initOwner(stage); // 親ウィンドウの設定？
			errorStage.setScene(errorPage);
			errorStage.setResizable(false); // ウィンドウサイズ変更不可に設定

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


	// Controller から Main クラスを呼び出す際に使う
	public static Main getInstance() {
		return main_class;
	}

}
