package gifu_u.info.downloader;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Downloader クラスのユニットテスト
 * Created by keigoi on 2017/05/30.
 */
public class DownloaderTest {

    /**
     * プロキシ設定（岐阜大学内専用...）
     */
    @Before
    public void setUp() {
        //Properties systemProperties = System.getProperties();
        //systemProperties.setProperty("http.proxyHost", "proxy.eng.gifu-u.local");
        //systemProperties.setProperty("http.proxyPort", "10080");
    }
    /*
     * テスト：禁止されていないURLでのダウンロードができる
     */
    @Test
    public void testDownload() throws Exception {
        Downloader d = new Downloader();

        // ダウンロードを試みる（成功するはず）
        boolean b = d.download("http://www.google.com/");

        // ダウンロードが成功し，戻り値が true だったことを確認する
        assertTrue( b );

        // ページがダウンロードできており，nullでないことを確認する
        String page = d.getContent();
        assertNotNull( page );
    }

    /*
     * テスト：禁止された URL でのダウンロードはできない
     */
    @Test
    public void testBanURL() throws Exception {
        Downloader d = new Downloader();

        d.addBanned("example.com");

        boolean ok = d.download("http://www.example.com/index.html");

        // TODO 課題： 上の testDownload を参考に，ダウンロードができなかったことを確認する処理を書け．
        // ヒント: assertFalse や assertNull といったメソッドが使える．
    }

    /*
     * removeBanned で禁止 URL から外れることを確認するテスト
     */
    @Test
    public void testUnbanURL() throws Exception {
        Downloader d = new Downloader();

        // TODO 課題： 上の testDownload を参考に，ダウンロード禁止指定が解除できていることを確認するテストを書け．
        // ヒント: 一度 d.addBanned() で禁止ドメインを追加し，すぐに d.removeBanned() で解除した後，
        // d.download() で，ダウンロードができることを確認すればよい．

    }

}
