package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

	public static Main main_class;
	private Stage stage;
	private Scene topPage;
	private Scene inPage;
	private final int WINDOW_WIDTH = 600;  // ウインドウの幅
	private final int WINDOW_HEIGHT = 600; // ウインドウの高さ

	@Override
	public void start(Stage primaryStage) {
		try {
			main_class = this;
			stage = primaryStage;
			topPage = new Scene(FXMLLoader.load(getClass().getResource("TopPage.fxml")), WINDOW_WIDTH, WINDOW_HEIGHT);
			inPage = new Scene(FXMLLoader.load(getClass().getResource("InPage.fxml")), WINDOW_WIDTH, WINDOW_HEIGHT);

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


	// Controller から Main クラスを呼び出す際に使う
	public static Main getInstance() {
		return main_class;
	}




}
