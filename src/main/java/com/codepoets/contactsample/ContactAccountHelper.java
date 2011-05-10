package com.codepoets.contactsample;

import java.util.ArrayList;
import java.util.List;

import android.accounts.AccountManager;
import android.accounts.AuthenticatorDescription;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncAdapterType;
import android.provider.ContactsContract;
import android.util.Log;

public class ContactAccountHelper {
	private static final String TAG = ContactAccountHelper.class.getSimpleName();
	
	public static List<ContactsAccount> getContactSources(Context context) {
		List<ContactsAccount> sources = new ArrayList<ContactsAccount>();

		final AccountManager accountManager = AccountManager.get(context);
		final SyncAdapterType[] syncs = ContentResolver.getSyncAdapterTypes();
		final AuthenticatorDescription[] auths = accountManager.getAuthenticatorTypes();

		for (SyncAdapterType sync : syncs) {
			if (!ContactsContract.AUTHORITY.equals(sync.authority)) {
				// Skip sync adapters that don't provide contact data.
				continue;
			}

			final String accountType = sync.accountType;
			final AuthenticatorDescription auth = findAuthenticator(auths, accountType);

			Log.d(TAG, "Creating source for type=" + accountType + ", packageName=" + auth.packageName);
			ContactsAccount	source = new ContactsAccount(auth.packageName);
			source.setReadOnly(!sync.supportsUploading());
			source.setAccountType(auth.type);
			source.setTitleRes(auth.labelId);
			source.setIconRes(auth.iconId);

			sources.add(source);
		}
		
		return sources;
	}

	private static AuthenticatorDescription findAuthenticator(AuthenticatorDescription[] auths, String accountType) {
		for (AuthenticatorDescription auth : auths) {
			if (accountType.equals(auth.type)) {
				return auth;
			}
		}
		throw new IllegalStateException("Couldn't find authenticator for specific account type");
	}
}
