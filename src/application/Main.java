package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {

	public static Main main_class;
	private BorderPane root;
	private Stage stage;
	private final int WINDOW_WIDTH = 600;  // ウインドウの幅
	private final int WINDOW_HEIGHT = 600; // ウインドウの高さ
	@FXML private Label label_time;

	@Override
	public void start(Stage primaryStage) {
		try {
			main_class = this;
			stage = primaryStage;
			runClock();

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
	void setPage(String fxml) {
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

	// 時計を動かす
	void runClock() {
		// 1000ミリ秒ごとに更新する
		Timeline timer = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("debug1");
				//label_time.setText(LocalDateTime.now().toString()); // ここで例外（ぬるぽ）が投げられているようだ．おそらく label_time が原因．
				System.out.println("debug2");
			}
		}));

		timer.setCycleCount(Timeline.INDEFINITE); // 無限に繰り返す
		timer.play();
	}
}
