package com.codepoets.contactsample;

public class ContactsSource {
	private String accountType;
	private String accountName;
	private String packageName;
	private int titleRes;
	private int iconRes;
	private boolean readOnly;
	
	
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String Name() {
		return accountType;
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
}
