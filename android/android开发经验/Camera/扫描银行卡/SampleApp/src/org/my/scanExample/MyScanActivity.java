package org.my.scanExample;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;
import org.my.scanExample.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * 
 * @author qubian
 * @description 银行卡扫描
 *
 * @time 2015-1-4
 */
public class MyScanActivity extends Activity
{
	final String TAG = getClass().getName();
	private Button scanButton;
	private TextView resultTextView;
	private int MY_SCAN_REQUEST_CODE = 100; 
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		resultTextView = (TextView)findViewById(R.id.resultTextView);
		scanButton = (Button)findViewById(R.id.scanButton);
		resultTextView.setText("扫描银行卡: " + CardIOActivity.sdkVersion() + "\nBuilt: " + CardIOActivity.sdkBuildDate());
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (CardIOActivity.canReadCardWithCamera(this)) {
			scanButton.setText("Scan a credit card with card.io");
		}
		else {
			scanButton.setText("Enter credit card information");
		}
	}

	public void onScanPress(View v) {
		Intent scanIntent = new Intent(this, CardIOActivity.class);
		scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true); 
		scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false); 
		scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); 
		scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, false); 
		startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		String resultStr;
		if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
			CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
			resultStr = "Card Number: " + scanResult.getRedactedCardNumber() + "\n";
			if (scanResult.isExpiryValid()) {
				resultStr += "Expiration Date: " + scanResult.expiryMonth + "/" + scanResult.expiryYear + "\n"; 
			}
			if (scanResult.cvv != null) { 
				resultStr += "CVV has " + scanResult.cvv.length() + " digits.\n";
			}
			if (scanResult.postalCode != null) {
				resultStr += "Postal Code: " + scanResult.postalCode + "\n";
			}
		}
		else {
			resultStr = "Scan was canceled.";
		}
		resultTextView.setText(resultStr);

	}
}

