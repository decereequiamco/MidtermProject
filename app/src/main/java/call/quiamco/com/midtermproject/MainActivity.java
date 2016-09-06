package call.quiamco.com.midtermproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    private Button mbutton;
    private ArrayList<Cart> shoppingList = new ArrayList<Cart>();
    Cart shoppingCartAdd;
    ListView itemListView;
    double totalPrice = 0.00;
    TextView mTvPrice;
    Button mSms;
    Button mEmail;
    String total;
    TextView mTv;
    private String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(shoppingList.isEmpty())
        try {
            Toast.makeText(getApplicationContext(), "Cart is Currently Empty.",
                    Toast.LENGTH_SHORT).show();
        }catch (NullPointerException ed){
            ed.printStackTrace();
        }



        itemListView = (ListView) findViewById(R.id.listView);

        mEmail = (Button) findViewById(R.id.button3);
        mEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!shoppingList.isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/html");

                    intent.putExtra(Intent.EXTRA_TEXT, message);

                    startActivity(Intent.createChooser(intent, "Send Email"));
                }

            }

        });


        mSms = (Button) findViewById(R.id.button2);
        mSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!shoppingList.isEmpty()) {
                    Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);

                    smsIntent.setType("vnd.android-dir/mms-sms");
                    smsIntent.putExtra("address", "");
                    smsIntent.putExtra("sms_body", message+"Total Price: "+totalPrice);

                    startActivity(smsIntent);
                }
            }
        });
            }

    public void onClick(View v) {
        try {
            Intent startScannerActivity = new Intent(getApplicationContext(), qr.class);
            startActivityForResult(startScannerActivity, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            try {
                super.onActivityResult(requestCode, resultCode, data);
            }catch (NullPointerException ex){
                ex.printStackTrace();
            }
            if (requestCode == 1) {
                if (resultCode == RESULT_OK) {
                    String mydata = data.getStringExtra("myData");
                    addToList(mydata);
                }
            }
        }


        private void addToList (String mydata){

            ArrayList<String> stringArray = new ArrayList<String>();
            StringTokenizer myTokenizer = new StringTokenizer(mydata, "|||");
            while (myTokenizer.hasMoreTokens()) {
                stringArray.add(myTokenizer.nextToken());
            }


            shoppingCartAdd = new Cart(stringArray.get(0), Integer.parseInt(stringArray.get(1)),
                    Double.parseDouble(stringArray.get(2)), Integer.parseInt(stringArray.get(1))
                    * Double.parseDouble(stringArray.get(2)));
            shoppingList.add(shoppingCartAdd);

            totalPrice += Integer.parseInt(stringArray.get(1))
                    * Double.parseDouble(stringArray.get(2));

            CartAdapter adapter = new CartAdapter(this, R.layout.activity_cart_adapter, shoppingList);
            itemListView.setAdapter(adapter);


               message +="\nItem name: "+shoppingCartAdd.getmItm_name()  +System.getProperty("line.separator")+ "\nQuantity: "+shoppingCartAdd.getmQty() +System.getProperty("line.separator")+
                        "\nPrice: "+shoppingCartAdd.getmPrice() +System.getProperty("line.separator")+"Total: "+shoppingCartAdd.getmPrice()*shoppingCartAdd.getmQty()+
                       "\n";
 //                message +="\nItem name: "+temp.getmItm_name()+"\n"+"Quantity: "+temp.getmQty()+"\n"+"Price: "+temp.getmPrice()+"\n";


            TextView mTvPrice=(TextView)findViewById(R.id.textView2);
            try {
                mTvPrice.setText(String.valueOf(totalPrice));
            }catch (NullPointerException ed){
                ed.printStackTrace();
            }

                 message= message+"\nTotal Price: "+ totalPrice;

        }

        }
