package com.example.scan;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.device.ScanManager;
import android.device.scanner.configuration.PropertyID;
import android.device.scanner.configuration.Symbology;
import android.device.scanner.configuration.Triggering;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ubx.decoder.OEMSymbologyId;

import java.util.Set;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private String TAG = "MainActivity";
    private final static String SCAN_ACTION = ScanManager.ACTION_DECODE;//default action
    /**
     * String contains the label type of the bar code
     */
    public static final String BARCODE_TYPE_TAG = "barcodeType";
    public static final String SYMBOLOGYID_TAG = "symbologyId";
    public static final String SYMBOLOGY_NAME_TAG = "symName";
    public static final String DECODE_TIME_TAG = "decodeTime";
    /**
     * String contains the label length of the bar code
     */
    public static final String BARCODE_LENGTH_TAG = "length";
    /**
     * String contains the output data as a byte array. In the case of concatenated bar codes, the decode data is
     * concatenated and sent out as a single array
     */
    public static final String DECODE_DATA_TAG = "barcode";

    private ImageView scanButton;
    private TextView barcode_result, decode_length,decode_symbology,symbology_name,decode_time;
    private EditText scanResult;
    private EditText scanResult2;
    private CheckBox continuousScan;
    private RadioGroup mRadioGroup;
    private ScanManager mScanManager;
    int[] idbuf = new int[]{PropertyID.WEDGE_INTENT_ACTION_NAME, PropertyID.WEDGE_INTENT_DATA_STRING_TAG,PropertyID.WEDGE_INTENT_DECODE_DATA_TAG};
    int[] idmodebuf = new int[]{PropertyID.WEDGE_KEYBOARD_ENABLE, PropertyID.TRIGGERING_MODES, PropertyID.LABEL_APPEND_ENTER,PropertyID.DEC_Multiple_Decode_INTERVAL};
    String[] action_value_buf = new String[]{ScanManager.ACTION_DECODE, ScanManager.BARCODE_STRING_TAG , ScanManager.DECODE_DATA_TAG};
    int[] idmode;
    private Spinner mAppendCharType;
    private int mAppendCharValue;
    private BroadcastReceiver mScanReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub

            byte[] barcode = intent.getByteArrayExtra(action_value_buf[2]);
            int barcodelen = intent.getIntExtra(ScanManager.BARCODE_LENGTH_TAG, 0);

            String result = intent.getStringExtra(action_value_buf[1]);
            if(result != null) {
                barcode_result.setText(result);
                decode_length.setText(" " + result.length());
                //decode_length.setText(" " + result.length() + "\nTime:" + intent.getLongExtra("decodeTime", 0));
            }
            if(intent.hasExtra(SYMBOLOGY_NAME_TAG)) {
                String symName = intent.getStringExtra(SYMBOLOGY_NAME_TAG);
                symbology_name.setText(symName);
            }
            if(intent.hasExtra(SYMBOLOGYID_TAG)) {
                int symId = intent.getIntExtra(SYMBOLOGYID_TAG, Symbology.NONE.toInt());
                decode_symbology.setText("" + symId);
            } else {
                byte type = intent.getByteExtra(ScanManager.BARCODE_TYPE_TAG, (byte) 0);
                int symId = OEMSymbologyId.getHSMSymbologyId(type);
                decode_symbology.setText("" + symId);
            }
            if(intent.hasExtra(DECODE_TIME_TAG)) {
                long time = intent.getLongExtra(DECODE_TIME_TAG, 0L);
                decode_time.setText("" + time);
            }

            try {
                Bundle resultBundle = intent.getExtras();
                Set<String> keys = resultBundle.keySet();
                for(String key: keys) {
                    Object object = resultBundle.get(key);
                    if(object instanceof String) {
                        Log.d(TAG, "key: " + key + " value: " + object);
                    } else if(object instanceof String[]) {
                        String[] data = (String[])object;
                        for(String code:data) {
                            Log.d(TAG, "key: " + key + " value: " + code);
                        }
                    } else if(object instanceof byte[]) {
                        byte[] data = (byte[])object;
                        Log.d(TAG, "key: " + key + " value: " + (new String(data)));
                    } else {
                        Log.d(TAG, "key: " + key + " object: " + object);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    };
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScanManager = new ScanManager();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == 138 || keyCode == 120 || keyCode == 520 || keyCode == 521 || keyCode == 522) {
            if(mAppendCharValue != 2)
            scanResult.requestFocus();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        //mScanManager.closeScanner();
        unregisterReceiver(mScanReceiver);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        mScanManager.openScanner();
        action_value_buf = mScanManager.getParameterString(idbuf);
        idmode = mScanManager.getParameterInts(idmodebuf);
        continuousScan = (CheckBox) findViewById(R.id.continuousScan);
        continuousScan.setChecked(idmode[1] == Triggering.CONTINUOUS.toInt());
        continuousScan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mScanManager.setTriggerMode(b ? Triggering.CONTINUOUS : Triggering.HOST);
                idmode[1] = b ? Triggering.CONTINUOUS.toInt() : Triggering.HOST.toInt();
                scanButton.setBackgroundResource(R.drawable.scan_button);
            }
        });
        idmode[0] = 0;
        mScanManager.setParameterInts(idmodebuf, idmode);
        scanResult = (EditText) findViewById(R.id.scanResult);
        scanResult2= (EditText) findViewById(R.id.scanResult2);
        mRadioGroup = (RadioGroup) findViewById(R.id.mode_output);
        mRadioGroup.setOnCheckedChangeListener(this);
        RadioButton keyboardMode = (RadioButton) findViewById(R.id.keyboard_output);
        keyboardMode.setChecked(idmode[0] == 1);
        RadioButton intentMode = (RadioButton) findViewById(R.id.intent_output);
        if(idmode[0] == 0) {
            scanResult.setVisibility(View.GONE);
            scanResult2.setVisibility(View.GONE);
            intentMode.setChecked(true);
        }
        decode_symbology = (TextView) findViewById(R.id.symbology_result);
        symbology_name = (TextView) findViewById(R.id.symbology_name);
        decode_length = (TextView) findViewById(R.id.length_result);
        barcode_result = (TextView) findViewById(R.id.barcode_result);
        decode_time = (TextView) findViewById(R.id.decode_time);
        scanButton = (ImageView) findViewById(R.id.scanButton);
        scanButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        //resetStatusView();
                        scanResult.requestFocus();
                        if(idmode[1] == Triggering.CONTINUOUS.toInt()) {
                            scanButton.setBackgroundResource(R.drawable.scan_button_down);
                        /*if(vibrator != null)
                            vibrator.vibrate(VIBRATE_DURATION);*/
                            mScanManager.startDecode();
                        } else {
                            scanButton.setBackgroundResource(R.drawable.scan_button_down);
                        /*if(vibrator != null)
                            vibrator.vibrate(VIBRATE_DURATION);*/
                            mScanManager.startDecode();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if(idmode[1] == Triggering.CONTINUOUS.toInt()) {

                        } else {
                            mScanManager.stopDecode();
                        }
                        scanButton.setBackgroundResource(R.drawable.scan_button);
                        break;
                }
                return true;
            }
        });
        scanResult.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if(KeyEvent.KEYCODE_ENTER == keyCode && event.getAction() == KeyEvent.ACTION_UP)
                {
                    barcode_result.setText("" + scanResult.getText());
                    decode_length.setText("" + scanResult.getText().length());
                    scanResult.setText("");
                    scanResult.requestFocus();
                    Toast.makeText(MainActivity.this, "EditorAction " + event.toString(), Toast.LENGTH_LONG).show();
                    return true;
                } else if(KeyEvent.KEYCODE_TAB == keyCode && event.getAction() == KeyEvent.ACTION_DOWN){
                    barcode_result.setText("" + scanResult.getText());
                    decode_length.setText("" + scanResult.getText().length());
                    scanResult.setText("");
                    Toast.makeText(MainActivity.this, "EditorAction " + event.toString(), Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
        mAppendCharType = (Spinner)findViewById(R.id.spinner_barcode);
        ArrayAdapter mBarcodeTypeAdapter = ArrayAdapter.createFromResource(
                this, R.array.appendCharType,
                android.R.layout.simple_spinner_item);

        mBarcodeTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAppendCharType.setAdapter(mBarcodeTypeAdapter);
        mAppendCharValue = idmode[2];
        mAppendCharType.setSelection(mAppendCharValue);
        mAppendCharType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub
                mAppendCharValue = position;
                int[] idappend = new int[1];
                idappend[0] = PropertyID.LABEL_APPEND_ENTER;
                idmode[0] = mAppendCharValue;
                mScanManager.setParameterInts(idappend, idmode);
                if(mAppendCharValue == 2) {
                    scanResult2.requestFocus();
                    idappend[0] = PropertyID.WEDGE_KEYBOARD_TYPE;
                    idmode[0] = 3;
                    mScanManager.setParameterInts(idappend, idmode);
                } else if(mAppendCharValue == 3) {
                    idappend[0] = PropertyID.WEDGE_KEYBOARD_TYPE;
                    idmode[0] = 2;
                    mScanManager.setParameterInts(idappend, idmode);
                    scanResult.requestFocus();
                } else {
                    idappend[0] = PropertyID.WEDGE_KEYBOARD_TYPE;
                    idmode[0] = 0;
                    mScanManager.setParameterInts(idappend, idmode);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        scanResult2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    barcode_result.setText("" + scanResult2.getText());
                    decode_length.setText("" + scanResult2.getText().length());
                    scanResult2.setText("");

                    Toast.makeText(MainActivity.this, "EditorAction DNOE event", Toast.LENGTH_LONG).show();
                    return true;
                }
                return false;
            }
        });
        IntentFilter filter = new IntentFilter();
        action_value_buf = mScanManager.getParameterString(idbuf);
        filter.addAction(action_value_buf[0]);
        registerReceiver(mScanReceiver, filter);
        //mScanManager.enableAllSymbologies(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.activity_main, menu);
        MenuItem settings = menu.add(0, 1, 0, R.string.menu_settings).setIcon(R.drawable.ic_action_settings);
        // 绑定到actionbar
        //SHOW_AS_ACTION_IF_ROOM 显示此项目在动作栏按钮如果系统决定有它。 可以用1来代替
        MenuItem version = menu.add(0, 2, 0, R.string.menu_about).setIcon(android.R.drawable.ic_menu_info_details);;
        settings.setShowAsAction(1);
        version.setShowAsAction(0);
        return super.onCreateOptionsMenu(menu);
    }
    int times = 0;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case 1:
                try{
                    Intent intent = new Intent("android.intent.action.SCANNER_SETTINGS");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                break;
            case 2:
                PackageManager pk = getPackageManager();
                PackageInfo pi;
                try {
                    pi = pk.getPackageInfo(getPackageName(), 0);
                    Toast.makeText(this, "V" +pi.versionName , Toast.LENGTH_SHORT).show();
                } catch (PackageManager.NameNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.intent_output:
                idmode[0] = 0;
                mScanManager.setParameterInts(idmodebuf, idmode);
                scanResult.setVisibility(View.GONE);
                break;
            case R.id.keyboard_output:
                idmode[0] = 1;
                mScanManager.setParameterInts(idmodebuf, idmode);
                scanResult.setVisibility(View.VISIBLE);
                scanResult.requestFocus();
                break;
        }
    }
}
