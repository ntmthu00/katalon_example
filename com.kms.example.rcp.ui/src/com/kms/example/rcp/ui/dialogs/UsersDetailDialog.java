package com.kms.example.rcp.ui.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.kms.example.rcp.core.object.User;
import com.kms.example.rcp.ui.constants.MessageConstants;

public class UsersDetailDialog extends Dialog {

	private Text txtUsername;
	private String username = "";
	private Text txtPassword;
	private String password = "";
	private Text txtFirstName;
	private String firstName = "";
	private Text txtLastName;
	private String lastName = "";
	private Text txtAvatarFilePath;
	private String avatarFilePath = "";
	private User user;
	private Button rbtnMale;
	private Button rbtnFemale;
	private Button btnChooseFile;
	private Button btnAdd;
	private Button btnCancel;
	private Button btnUpdate;
	private DateTime dtDOB;

	public User getUser() {
		return user;
	}

	public UsersDetailDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout layout = new GridLayout(2, false);
		layout.marginRight = 5;
		layout.marginLeft = 5;
		container.setLayout(layout);

		Label lbUsername = new Label(container, SWT.NONE);
		lbUsername.setText(MessageConstants.UserDetailsDialog_LBL_USERNAME);

		txtUsername = new Text(container, SWT.BORDER);
		txtUsername.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtUsername.setText(username);
		txtUsername.addModifyListener(e -> {
			Text textWidget = (Text) e.getSource();
			String usernameText = textWidget.getText();
			username = usernameText;
		});

		Label lblPassword = new Label(container, SWT.NONE);
		GridData gridDataPasswordLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		gridDataPasswordLabel.horizontalIndent = 1;
		lblPassword.setLayoutData(gridDataPasswordLabel);
		lblPassword.setText(MessageConstants.UserDetailsDialog_LBL_PASSWORD);

		txtPassword = new Text(container, SWT.BORDER | SWT.PASSWORD);
		txtPassword.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		txtPassword.setText(password);
		txtPassword.addModifyListener(e -> {
			Text textWidget = (Text) e.getSource();
			String passwordText = textWidget.getText();
			password = passwordText;
		});

		Label lblFirstName = new Label(container, SWT.NONE);
		GridData gridDataFirstNameLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		gridDataFirstNameLabel.horizontalIndent = 1;
		lblFirstName.setLayoutData(gridDataFirstNameLabel);
		lblFirstName.setText(MessageConstants.UserDetailsDialog_LBL_FIRST_NAME);

		txtFirstName = new Text(container, SWT.BORDER);
		txtFirstName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		txtFirstName.setText(firstName);
		txtFirstName.addModifyListener(e -> {
			Text textWidget = (Text) e.getSource();
			String firstNameText = textWidget.getText();
			firstName = firstNameText;
		});

		Label lblLastName = new Label(container, SWT.NONE);
		GridData gridDataLastNameLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		gridDataLastNameLabel.horizontalIndent = 1;
		lblLastName.setLayoutData(gridDataLastNameLabel);
		lblLastName.setText(MessageConstants.UserDetailsDialog_LBL_LAST_NAME);

		txtLastName = new Text(container, SWT.BORDER);
		txtLastName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		txtLastName.setText(lastName);
		txtLastName.addModifyListener(e -> {
			Text textWidget = (Text) e.getSource();
			String lastNameText = textWidget.getText();
			lastName = lastNameText;
		});

		Label lblDOB = new Label(container, SWT.NONE);
		GridData gridDataDOBLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		gridDataDOBLabel.horizontalIndent = 1;
		lblDOB.setLayoutData(gridDataDOBLabel);
		lblDOB.setText(MessageConstants.UserDetailsDialog_LBL_DATE_OF_BIRTH);

		dtDOB = new DateTime(container, SWT.DROP_DOWN | SWT.BORDER | SWT.DATE | SWT.LONG);

		Label lblGender = new Label(container, SWT.NONE);
		GridData gridDataGenderLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		gridDataGenderLabel.horizontalIndent = 1;
		lblGender.setLayoutData(gridDataGenderLabel);
		lblGender.setText(MessageConstants.UserDetailsDialog_LBL_GENDER);

		Composite genderComposite = new Composite(container, SWT.NONE);
		genderComposite.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		genderComposite.setLayout(new RowLayout());
		rbtnMale = new Button(genderComposite, SWT.RADIO);
		rbtnMale.setText(MessageConstants.UserDetailsDialog_RB_MALE);
		rbtnFemale = new Button(genderComposite, SWT.RADIO);
		rbtnFemale.setText(MessageConstants.UserDetailsDialog_RB_FEMALE);

		Label lblAvatar = new Label(container, SWT.NONE);
		GridData gridDataAvatarLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		gridDataAvatarLabel.horizontalIndent = 1;
		lblAvatar.setLayoutData(gridDataAvatarLabel);
		lblAvatar.setText(MessageConstants.UserDetailsDialog_LBL_AVATAR);

		Composite avatarComposite = new Composite(container, SWT.NONE);
		avatarComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		avatarComposite.setLayout(new GridLayout(2, false));

		txtAvatarFilePath = new Text(avatarComposite, SWT.BORDER);
		txtAvatarFilePath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		txtAvatarFilePath.setText(avatarFilePath);
		txtAvatarFilePath.addModifyListener(e -> {
			Text textWidget = (Text) e.getSource();
			String avatarFilePathText = textWidget.getText();
			avatarFilePath = avatarFilePathText;
		});

		btnChooseFile = new Button(avatarComposite, SWT.PUSH);
		btnChooseFile.setText(MessageConstants.UserDetailsDialog_BTN_CHOOSE_FILE);

		return container;
	}

	@Override
	protected void okPressed() {
		username = txtUsername.getText();
		password = txtPassword.getText();
		super.okPressed();
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(MessageConstants.UserDetailsDialog_DIA_TITLE);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(400, 350);
	}
}