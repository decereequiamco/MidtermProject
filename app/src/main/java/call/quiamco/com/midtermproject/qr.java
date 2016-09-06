package call.quiamco.com.midtermproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.StringTokenizer;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class qr extends MainActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private Button mbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);

        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();         // Start camera

    }




    @Override
    public void handleResult(Result result) {

        // Do something with the result here

//        Log.e("handler", result.getText()); // Prints scan results
//        Log.e("handler", result.getBarcodeFormat().toString()); // Prints the scan format (qrcode)

        // show the scanner result into dialog box.
        String data = result.getText();
        Intent intent = new Intent();
//       try {
           intent.putExtra("myData", data);
           setResult(RESULT_OK, intent);
//       }catch (Exception e){
//           android.support.v7.app.AlertDialog.Builder diagBuilder = new android.support.v7.app.AlertDialog.Builder(this);
//           diagBuilder.setTitle("Invalid!");
//           diagBuilder.setMessage("Invalid Format");
//       }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");

        ArrayList<String> stringArray = new ArrayList<String>();
        StringTokenizer myTokenizer = new StringTokenizer(data,"|||");
        while(myTokenizer.hasMoreTokens()){
            stringArray.add(myTokenizer.nextToken());
        }

        String message2 = "Item Name: "+stringArray.get(0)+"     "+"Price: "+stringArray.get(2);

        builder.setMessage(message2);
        AlertDialog alert1 = builder.create();
        alert1.show();
//         If you would like to resume scanning, call this method below:
         mScannerView.resumeCameraPreview(this);

        Thread myThread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(1000);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        myThread.start();
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
////        finish();
    }




    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();

        // Stop camera on pause
    }
    @Override
    public void onRestart() {
        super.onRestart();
        mScannerView.startCamera();
    }
}
