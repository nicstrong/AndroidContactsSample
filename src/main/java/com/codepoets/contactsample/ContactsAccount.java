package com.codepoets.contactsample;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class ContactsAccount {
	private String accountType;
	private String accountName;
	private String packageName;
	private int titleRes;
	private int iconRes;
	private boolean readOnly;
	
	public ContactsAccount(String packageName) {
		this.packageName = packageName;
	}
	
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public int getTitleRes() {
		return titleRes;
	}
	public void setTitleRes(int titleRes) {
		this.titleRes = titleRes;
	}
	public int getIconRes() {
		return iconRes;
	}
	public void setIconRes(int iconRes) {
		this.iconRes = iconRes;
	}
	public boolean isReadOnly() {
		return readOnly;
	}
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	
	public CharSequence getDisplayLabel(Context context) {
		if (this.titleRes != -1) {
            return context.getText(this.titleRes);
        } else {
            return this.accountType;
        }
    }

    public Drawable getDisplayIcon(Context context) {
        if (this.titleRes != -1) {
            return context.getResources().getDrawable(this.iconRes);
        } else {
            return null;
        }
    }
}
