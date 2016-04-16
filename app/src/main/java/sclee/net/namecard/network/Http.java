package sclee.net.namecard.network;

import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by leesc on 2016-03-27.
 */
public class Http extends Exception{

    private static final String TAG = "http";
    private final int CONN_TIMEOUT = 1;
    private final int READ_TIMEOUT = 1;
    private final String GET = "GET";
    // URL클래스의 생성자로 주소를 넘겨준다.

    // HTTP 요청 결과 반환
    public String getHttpConnResult(String strUrl) {
        try {
            URL url = new URL("http://127.0.0.1:8000");

            /*// HttpURLConnection 객체 생성.
            HttpURLConnection conn = null;

            // URL 연결 (웹페이지 URL 연결.)
            conn = (HttpURLConnection)url.openConnection();

            // TimeOut 시간 (서버 접속시 연결 시간)
            conn.setConnectTimeout(CONN_TIMEOUT * 1000);

            // TimeOut 시간 (Read시 연결 시간)
            conn.setReadTimeout(READ_TIMEOUT * 1000);

            // 요청 방식 선택 (GET, POST)
            conn.setRequestMethod(GET);

            // Request Header값 셋팅 setRequestProperty(String key, String value)
            conn.setRequestProperty("NAME", "name");
            conn.setRequestProperty("MDN", "mdn");
            conn.setRequestProperty("APPID", "appid");

            // 서버 Response Data를 xml 형식의 타입으로 요청.
            conn.setRequestProperty("Accept", "application/xml");

            // 서버 Response Data를 JSON 형식의 타입으로 요청.
            conn.setRequestProperty("Accept", "application/json");

            // 타입설정(text/html) 형식으로 전송 (Request Body 전달시 text/html로 서버에 전달.)
            conn.setRequestProperty("Content-Type", "text/html");

            // 타입설정(text/html) 형식으로 전송 (Request Body 전달시 application/xml로 서버에 전달.)
            conn.setRequestProperty("Content-Type", "application/xml");

            // 타입설정(application/json) 형식으로 전송 (Request Body 전달시 application/json로 서버에 전달.)
            conn.setRequestProperty("Content-Type", "application/json");

            // 컨트롤 캐쉬 설정
            conn.setRequestProperty("Cache-Control","no-cache");

            // 타입길이 설정(Request Body 전달시 Data Type의 길이를 정함.)
            conn.setRequestProperty("Content-Length", "length")

            // User-Agent 값 설정
            conn.setRequestProperty("User-Agent", "test");

            // OutputStream으로 POST 데이터를 넘겨주겠다는 옵션.
            conn.setDoOutput(true);

            // InputStream으로 서버로 부터 응답을 받겠다는 옵션.
            conn.setDoInput(true);

            // Request Body에 Data를 담기위해 OutputStream 객체를 생성.
            OutputStream os = conn.getOutputStream();

            // Request Body에 Data 셋팅.
            os.write(body.getBytes("euc-kr"));

            // Request Body에 Data 입력.
            os.flush();

            // OutputStream 종료.
            os.close();

            // 실제 서버로 Request 요청 하는 부분. (응답 코드를 받는다. 200 성공, 나머지 에러)
            int responseCode = conn.getResponseCode();

            // 접속해지
            conn.disconnect();*/

        } catch (MalformedURLException e) {
            Log.e(TAG,"MalformedURLException");
        } catch (IOException e) {
            Log.e(TAG,"IOException");
        }

        return null;
    }



}