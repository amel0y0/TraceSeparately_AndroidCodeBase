package mdad.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogIn extends AppCompatActivity {
    private EditText mPassword,mUserName;
    private Button btnSignIn;
    // url to update product

    // private static final String url_login = MainActivity.ipBaseAddress+"/php/loginJ.php";

    // JSON Node names

    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        mPassword = (EditText) findViewById(R.id.password);
        mUserName = (EditText) findViewById(R.id.username);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                finish();
                Intent i = new Intent(LogIn.this, MainActivity.class);
                startActivity(i);
/*
                String pw= mPassword.getText().toString();
                String uName= mUserName.getText().toString();

                if(pw.isEmpty())
                {
                    mPassword.setError(getString(R.string.error_field_required));

                }else

                if(uName.isEmpty())
                {
                    mUserName.setError(getString(R.string.error_field_required));

                }else
                {
                    JSONObject dataJson = new JSONObject();
                    try{
                        dataJson.put("username", uName);
                        dataJson.put("password", pw);


                    }catch(JSONException e){

                    }

                    //postData(url_login,dataJson,1 );

                }
*/
            }
        });

    }
}