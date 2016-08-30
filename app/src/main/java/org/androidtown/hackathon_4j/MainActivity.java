package org.androidtown.hackathon_4j;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //http://210.117.134.88/phpmyadmin/

    Button button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        // 버튼에 클릭 리스너를 달았습니다. 클릭 시 URLConnector가 작동할 것입니다.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                URLConnector url = new URLConnector();
                url.start();
                try {
                    url.join();
                }
                catch(Exception e){
                    e.printStackTrace();
                }

                String result = url.getTemp();

                System.out.println(ParseJSON(result));

                ParseJSON(result);
            }
        });
    }

    // JSON 데이터를 파싱합니다.
    // URLConnector로부터 받은 String이 JSON 문자열이기 때문입니다.
    public String ParseJSON(String target){

        try {
            JSONObject json = new JSONObject(target);

            JSONArray arr = json.getJSONArray("userdata");

            for(int i = 0; i < arr.length(); i++){
                JSONObject json2 = arr.getJSONObject(i);

                System.out.println(json2.getString("id"));
            }

            return "";
        }

        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
