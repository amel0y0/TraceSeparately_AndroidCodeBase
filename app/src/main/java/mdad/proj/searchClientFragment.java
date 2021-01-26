package mdad.proj;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link searchClientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class searchClientFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // url to get all products list
    private static String url_all_products = MainActivity.ipBaseAddress1+"/get_all_clientsJson.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "users";
    private static final String TAG_PID = "user_id";
    private static final String TAG_NAME = "username";

    private Button btnSearch;
    private EditText txtSearch;
    private int result=0;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;


    public searchClientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static searchClientFragment newInstance(String param1, String param2) {
        searchClientFragment fragment = new searchClientFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search_client, container, false);
        btnSearch= (Button)view.findViewById(R.id.btnSearch);
        txtSearch= (EditText)view.findViewById(R.id.txtSearchClient);

        btnSearch.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), resultClient.class);
                intent.putExtra("NAME_KEY", txtSearch.getText().toString());
                startActivity(intent);
            }
        });
     return view;

    }
}