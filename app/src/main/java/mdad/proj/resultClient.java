package mdad.proj;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class resultClient extends AppCompatActivity {

    String nameTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_client);

        // my_toolbar is defined in the layout file
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        //RECEIVE DATA VIA INTENT
        Intent i = getIntent();
        String name = i.getStringExtra("NAME_KEY");
        //SET DATA TO TEXTVIEWS
        nameTxt = name;
        Toast.makeText(getApplicationContext(), "Check: "+ nameTxt, Toast.LENGTH_SHORT).show();
    }
//    /*
//WHEN ACTIVITY RESUMES
//*/
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        //DETERMINE WHO STARTED THIS ACTIVITY
//        final String sender=this.getIntent().getExtras().getString("SENDER_KEY");
//
//        //IF ITS THE FRAGMENT THEN RECEIVE DATA
//        if(sender != null)
//        {
//            this.receiveData();
//            Toast.makeText(this, "Received" + "  " + nameTxt, Toast.LENGTH_SHORT).show();
//
//        }
//    }
//
//    /*
//        OPEN FRAGMENT
//         */
//    private void openFragment()
//    {
//        //PASS OVER THE BUNDLE TO OUR FRAGMENT
//        searchClientFragment myFragment = new searchClientFragment();
//        //THEN NOW SHOW OUR FRAGMENT
//        getSupportFragmentManager().beginTransaction().replace(R.id.container,myFragment).commit();
//    }
//
//    /*
//    RECEIVE DATA FROM FRAGMENT
//     */
//    private void receiveData()
//    {
//        //RECEIVE DATA VIA INTENT
//        Intent i = getIntent();
//        String name = i.getStringExtra("NAME_KEY");
//
//        //SET DATA TO TEXTVIEWS
//        nameTxt = name;
//    }
}