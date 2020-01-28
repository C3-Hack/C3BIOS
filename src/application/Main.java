package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

	public static Main main_class;
	private BorderPane root;
	private Stage stage;
	private final int WINDOW_WIDTH = 600;  // ウインドウの幅
	private final int WINDOW_HEIGHT = 600; // ウインドウの高さ

	@Override
	public void start(Stage primaryStage) {
		try {
			main_class = this;
			stage = primaryStage;

			// ページを表示する
			setPage("TopPage.fxml"); // TopPageを表示する
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
	// 引数fxml : fxmlのファイル名
	public void setPage(String fxml) {
		try {
			root = (BorderPane)FXMLLoader.load(getClass().getResource(fxml));
			stage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// Controller から Main クラスを呼び出す際に使う
	public static Main getInstance() {
		return main_class;
	}
}
