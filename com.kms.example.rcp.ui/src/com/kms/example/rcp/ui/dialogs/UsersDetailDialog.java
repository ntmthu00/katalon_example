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

	public void setTxtUsername(Text txtUsername) {
		this.txtUsername = txtUsername;
	}

	public void setTxtPassword(Text txtPassword) {
		this.txtPassword = txtPassword;
	}

	public void setTxtFirstName(Text txtFirstName) {
		this.txtFirstName = txtFirstName;
	}

	public void setTxtLastName(Text txtLastName) {
		this.txtLastName = txtLastName;
	}

	public void setTxtAvatarFilePath(Text txtAvatarFilePath) {
		this.txtAvatarFilePath = txtAvatarFilePath;
	}

	public void setRbtnMale(Button rbtnMale) {
		this.rbtnMale = rbtnMale;
	}

	public void setRbtnFemale(Button rbtnFemale) {
		this.rbtnFemale = rbtnFemale;
	}

	public void setDtDOB(DateTime dtDOB) {
		this.dtDOB = dtDOB;
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

		dtDOB = new DateTime(container, SWT.CALENDAR | SWT.BORDER | SWT.DATE);

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
		btnChooseFile.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDlg = new FileDialog(btnChooseFile.getShell(), SWT.OPEN);
				fileDlg.setText("Open");
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