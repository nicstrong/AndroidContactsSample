package com.codepoets.contactsample;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.util.Log;

public class ContactsSampleActivity extends Activity {
	public static final String LOG_TAG = "ContactsSample";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		insertContact();
		dumpContacts();

	}

	private void dumpContacts() {
		Cursor c = null;
		try {                   
			c = getContentResolver().query(RawContacts.CONTENT_URI, null, null, null, null);
			if (c != null && c.moveToFirst()) {
				do {
					Log.d(LOG_TAG, "Contact: ");
					for (String colName: c.getColumnNames()) {                              
						int idx = c.getColumnIndex(colName);
						Log.d(LOG_TAG, "   " +  colName + "=" + c.getString(idx));
					}
				} while (c.moveToNext());
			}
		} finally {
			if (c != null)
				c.close();
		}       
	}
	
	private void insertContact() {

		ArrayList<ContentProviderOperation> ops =
			new ArrayList<ContentProviderOperation>();

		int rawContactInsertIndex = ops.size();
		ops.add(ContentProviderOperation.newInsert(RawContacts.CONTENT_URI)
				.withValue(RawContacts.ACCOUNT_TYPE, "")
				.withValue(RawContacts.ACCOUNT_NAME, "")
				.build());

		ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)
				.withValueBackReference(Data.RAW_CONTACT_ID, rawContactInsertIndex)
				.withValue(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
				.withValue(StructuredName.GIVEN_NAME, "Test")
				.withValue(StructuredName.FAMILY_NAME, "Contact")
				.build());

		ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)
				.withValueBackReference(Data.RAW_CONTACT_ID, rawContactInsertIndex)
				.withValue(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
				.withValue(Phone.NUMBER, "1234567")
				.withValue(Phone.TYPE, Phone.TYPE_HOME)
				.build());

		ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)
				.withValueBackReference(Data.RAW_CONTACT_ID, rawContactInsertIndex)
				.withValue(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
				.withValue(Phone.NUMBER, "987654321")
				.withValue(Phone.TYPE, Phone.TYPE_MOBILE)
				.build());

		ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)
				.withValueBackReference(Data.RAW_CONTACT_ID, rawContactInsertIndex)
				.withValue(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE)
				.withValue(Email.DATA1 /*Email.ADDRESS*/, "me@example.com")
				.withValue(Email.TYPE, Email.TYPE_WORK)
				.build());
		
		try {
			getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (OperationApplicationException e) {
			e.printStackTrace();
		}
	}
}