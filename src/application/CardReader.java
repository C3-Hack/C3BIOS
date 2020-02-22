package application;

import javax.smartcardio.Card;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;

public class CardReader implements Runnable {
    private CardTerminal terminal;
    private Card card;
    private static byte[] readUID = { (byte) 0xFF, (byte) 0xCA, (byte) 0x00, (byte) 0x00, (byte) 0x04 };    //IDmを読むための？
    private String IDm = "";

    public CardReader() {
        try {
            terminal = TerminalFactory.getDefault().terminals().list().get(0);    //一台目を決め打ち
        } catch (CardException e) {
            System.err.println("読み取り機を取得できませんでした");
            System.exit(-1);
        }
    }

    public String readIDm() throws CardException{
        try {
            card = terminal.connect("*");
            ResponseAPDU response = card.getBasicChannel().transmit(new CommandAPDU(readUID));
            byte[] sbyte = response.getData(); // byte型で取得
            StringBuilder sb = new StringBuilder();
            for(byte b : sbyte){
                sb.append(String.format("%02x ", b)); // byteを16進数で文字列に変換
            }
            return sb.deleteCharAt(sb.length()-1).toString(); // 最後のスペースを消す
        } finally {
            //card.disconnect(false);    //例外が発生してもとりあえず接続を切る
        }
    }

    @Override
    public void run() {
        //カードが置かれて、取られるまでの一連をひとまとめにしているが、感度により、一つのカードについて短期間に連続して起こる可能性があることを想定すべき
        while(true) {
            try {
                System.out.println("読み取り開始");
                terminal.waitForCardPresent(0);
                IDm = readIDm();
                System.out.println("IDm : " + IDm); // IDm表示
                terminal.waitForCardAbsent(0);
            } catch (CardException e) {
                e.printStackTrace();
            }
        }
    }

    public String getIDm() {
    	return IDm;
    }

    public void setIDm(String str) {
    	IDm = str;
    }
}
