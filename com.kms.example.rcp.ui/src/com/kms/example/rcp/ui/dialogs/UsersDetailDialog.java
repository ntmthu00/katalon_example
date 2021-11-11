package com.kms.example.rcp.ui.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.kms.example.rcp.core.object.Date;
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
	private String gender = "";
	private Button btnChooseFile;
	private DateTime dtDOB;
	private Date dateDOB;
	private Button btnOK;
	private Button btnCancel;

	public User getUser() {
		return user;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAvatarFilePath(String avatarFilePath) {
		this.avatarFilePath = avatarFilePath;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setDateDOB(Date dateDOB) {
		this.dateDOB = dateDOB;
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

		Label lbPassword = new Label(container, SWT.NONE);
		GridData gridDataPasswordLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		gridDataPasswordLabel.horizontalIndent = 1;
		lbPassword.setLayoutData(gridDataPasswordLabel);
		lbPassword.setText(MessageConstants.UserDetailsDialog_LBL_PASSWORD);

		txtPassword = new Text(container, SWT.BORDER | SWT.PASSWORD);
		txtPassword.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		txtPassword.setText(password);
		txtPassword.addModifyListener(e -> {
			Text textWidget = (Text) e.getSource();
			String passwordText = textWidget.getText();
			password = passwordText;
		});

		Label lbFirstName = new Label(container, SWT.NONE);
		GridData gridDataFirstNameLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		gridDataFirstNameLabel.horizontalIndent = 1;
		lbFirstName.setLayoutData(gridDataFirstNameLabel);
		lbFirstName.setText(MessageConstants.UserDetailsDialog_LBL_FIRST_NAME);

		txtFirstName = new Text(container, SWT.BORDER);
		txtFirstName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		txtFirstName.setText(firstName);
		txtFirstName.addModifyListener(e -> {
			Text textWidget = (Text) e.getSource();
			String firstNameText = textWidget.getText();
			firstName = firstNameText;
		});

		Label lbLastName = new Label(container, SWT.NONE);
		GridData gridDataLastNameLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		gridDataLastNameLabel.horizontalIndent = 1;
		lbLastName.setLayoutData(gridDataLastNameLabel);
		lbLastName.setText(MessageConstants.UserDetailsDialog_LBL_LAST_NAME);

		txtLastName = new Text(container, SWT.BORDER);
		txtLastName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		txtLastName.setText(lastName);
		txtLastName.addModifyListener(e -> {
			Text textWidget = (Text) e.getSource();
			String lastNameText = textWidget.getText();
			lastName = lastNameText;
		});

		Label lbDOB = new Label(container, SWT.NONE);
		GridData gridDataDOBLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		gridDataDOBLabel.horizontalIndent = 1;
		lbDOB.setLayoutData(gridDataDOBLabel);
		lbDOB.setText(MessageConstants.UserDetailsDialog_LBL_DATE_OF_BIRTH);

		dtDOB = new DateTime(container, SWT.CALENDAR | SWT.BORDER | SWT.DATE);

		Label lbGender = new Label(container, SWT.NONE);
		GridData gridDataGenderLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		gridDataGenderLabel.horizontalIndent = 1;
		lbGender.setLayoutData(gridDataGenderLabel);
		lbGender.setText(MessageConstants.UserDetailsDialog_LBL_GENDER);

		Composite genderComposite = new Composite(container, SWT.NONE);
		genderComposite.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		genderComposite.setLayout(new RowLayout());
		rbtnMale = new Button(genderComposite, SWT.RADIO);
		rbtnMale.setText(MessageConstants.UserDetailsDialog_RB_MALE);
		rbtnMale.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Button source = (Button) e.getSource();
				if (source.getSelection()) {
					gender = source.getText();
				}
			}
		});
		rbtnFemale = new Button(genderComposite, SWT.RADIO);
		rbtnFemale.setText(MessageConstants.UserDetailsDialog_RB_FEMALE);
		rbtnFemale.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Button source = (Button) e.getSource();
				if (source.getSelection()) {
					gender = source.getText();
				}
			}
		});
		if (gender.equals(MessageConstants.UserDetailsDialog_RB_MALE)) {
			rbtnMale.setSelection(true);
		} else if (gender.equals(MessageConstants.UserDetailsDialog_RB_FEMALE)) {
			rbtnFemale.setSelection(true);
		}

		Label lbAvatar = new Label(container, SWT.NONE);
		GridData gridDataAvatarLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		gridDataAvatarLabel.horizontalIndent = 1;
		lbAvatar.setLayoutData(gridDataAvatarLabel);
		lbAvatar.setText(MessageConstants.UserDetailsDialog_LBL_AVATAR);

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
		btnChooseFile.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDlg = new FileDialog(btnChooseFile.getShell(), SWT.OPEN);
				fileDlg.setText(MessageConstants.UserDetailsDialog_FILE_DLG_TITLE);
				avatarFilePath = fileDlg.open();
				if (avatarFilePath == null)
					return;
				txtAvatarFilePath.setText(avatarFilePath);
			}
		});
		return container;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		((GridLayout) parent.getLayout()).numColumns++;

		Composite btnComposite = new Composite(parent, SWT.NONE);
		btnComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnComposite.setLayout(new RowLayout());

		btnOK = new Button(btnComposite, SWT.PUSH);
		btnOK.setText(MessageConstants.UserDetailsDialog_BTN_OK);
		btnOK.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (txtUsername.getText().length() != 0 && txtPassword.getText().length() != 0
						&& txtFirstName.getText().length() != 0 && txtLastName.getText().length() != 0
						&& txtAvatarFilePath.getText().length() != 0 && gender.length() != 0) {
					dateDOB = new Date(dtDOB.getDay(), dtDOB.getMonth(), dtDOB.getYear());
					user = new User(txtAvatarFilePath.getText(), txtUsername.getText(), dateDOB, gender,
							txtFirstName.getText(), txtLastName.getText(), txtPassword.getText());
					close();
				} else {
					MessageDialog.openError(getShell(), MessageConstants.UserDetailsDialog_ERROR_TITLE,
							MessageConstants.UserDetailsDialog_MISSING_DATA);
				}
			}
		});

		btnCancel = new Button(btnComposite, SWT.PUSH);
		btnCancel.setText(MessageConstants.UserDetailsDialog_BTN_CANCEL);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				close();
			}
		});
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