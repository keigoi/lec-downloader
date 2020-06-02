package gifu_u.info.downloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;

/**
 * クラスの説明：
 * Downloader は，指定された URL をダウンロードするクラスである．
 * Downloader には禁止ドメイン (Banned Domain) のリストを登録できる．
 * Downloader は，指定されたURLが禁止ドメインであった場合はダウンロードしない．
 * 禁止ドメインの指定は addBanned メソッド，禁止ドメイン指定の解除は removeBanned メソッドを使う．
 *
 * Downloaderの使い方：
 * (1) ダウンロード機能の使い方
 * Downloader d = new Downloader();
 * boolean ok = d.download("https://www.google.com"); // ダウンロードされる (そしてtrueが返ってくる)
 * if (ok) {
 *   // ok は true のはずなので，ここが実行される
 *   System.out.println( d.getContent() ); // Google のトップページの HTML がそのまま画面に出る
 * }
 *
 * (2) 禁止ドメイン登録機能の使い方
 * d.addBannedDomain( "example.com" ); // example.com を禁止ドメインとして登録する
 * boolean ok = d.download("http://www.example.com/") // ダウンロードされない (falseが返ってくる)
 * if (!ok) {
 *     // ok は false のはずなので，ここが実行される
 *     System.out.println("ダウンロードされませんでした");
 * }
 *
 * (3) 禁止ドメイン解除機能の使い方
 * d.removeBannedDomain( "example.com" ); // example.com の禁止ドメイン指定を解除する
 *
 * boolean ok = d.download("https://www.example.com"); // ダウンロードされる (そしてtrueが返ってくる)
 * if (ok) {
 *   // ok は true のはずなので，ここが実行される
 *   System.out.println( d.getContent() ); // Google のトップページの HTML がそのまま画面に出る
 * }
 */
public class Downloader {

    /**
     * ダウンロードした文字列を保持する変数．
      */
    private String content = null;

    /**
     * ダウンロード禁止ドメインのリストを保持する変数．
     */
    private HashSet<String> bannedDomains = new HashSet<>();

    /**
     * 直前のダウンロードが成功していればダウンロードした内容を、失敗していれば null を返す
     * @return ダウンロードした文字列
     */
    public String getContent() {
        return content;
    }

    /**
     * 禁止ドメインを追加する。
     * @param domain 追加するドメイン
     */
    public void addBanned(String domain) {
        // TODO 課題：ここにコードを書き，禁止ドメイン指定機能を実装せよ．
        // 採点基準：単体テスト testBanURL が成功すること．
        // ヒント： リスト this.bannedDomains に domain を追加する
        // ヒント2： add メソッドを使う．
        // ヒント3：1行で書ける．
    }

    /**
     * 禁止ドメインを削除する。
     * @param domain 削除するドメイン
     */
    public void removeBanned(String domain) {
        // TODO 課題：ここにコードを書き，禁止ドメイン解除機能を実装せよ．
        // 採点基準：単体テスト testUnbanURL が成功すること．
        // ヒント： this.bannedDomains から domain を削除する
        // ヒント2： remove メソッドを使う．
        // ヒント3：1行で書ける．
    }

    /**
     * 与えられた URL からダウンロードする
     * @param urlstr URL文字列
     * @return ダウンロードに成功すれば true, 禁止されたドメインであれば false
     * @throws IOException ダウンロード途中でエラーが発生した場合
     */
    public boolean download(String urlstr) throws IOException {
        URL url = new URL(urlstr);

        for (String domain : this.bannedDomains ) {
            if ( url.getHost().endsWith( domain ) )
                return false;
        }

        // URL を設定して HTTP 通信を開始(メソッドは GET)
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.connect();

        // 受信したデータを読み出すためのストリームをオープン
        StringBuffer text = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream(), "UTF-8"));

        // ストリームからデータを読み出し
        while (true) {
            String str = reader.readLine();
            if (str == null) {
                break;
            } else {
                text.append(str);
            }
        }

        this.content = text.toString();
        return true;
    }

}
