package com.kms.example.rcp.ui.dialogs;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.kms.example.rcp.core.object.User;

public class AddUserDialog extends TitleAreaDialog {

	private Text textUsername;
	private Text textPassword;
	private Text textFirstName;
	private Text textLastName;
	private User user;
	private Button btnChoose;
	private Button btnAdd;
	private Button btnCancel;
	private Combo combo1;

	public User getUser() {
		return user;
	}

	public AddUserDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		setTitle("Add a new user");
		setMessage("Please enter the data of the new user", IMessageProvider.INFORMATION);
		return contents;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		parent.setLayout(layout);

		Label labelUsername = new Label(parent, SWT.NONE);
		labelUsername.setText("Username");
		textUsername = new Text(parent, SWT.BORDER);

		Label labelPassword = new Label(parent, SWT.NONE);
		labelPassword.setText("Password");
		textPassword = new Text(parent, SWT.BORDER);

		Label labelFirstName = new Label(parent, SWT.NONE);
		labelFirstName.setText("First Name");
		textFirstName = new Text(parent, SWT.BORDER);

		Label labelLastName = new Label(parent, SWT.NONE);
		labelLastName.setText("Last Name");
		textLastName.setText("Last Name");

		Label labelGender = new Label(parent, SWT.NONE);
		labelGender.setText("Gender");
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gd.horizontalSpan = 1;
		combo1 = new Combo(parent, SWT.READ_ONLY);
		combo1.add("Male");
		combo1.add("Female");
		return parent;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		((GridLayout) parent.getLayout()).numColumns++;

		Button button = new Button(parent, SWT.PUSH);
		button.setText("Add");
		button.setFont(JFaceResources.getDialogFont());
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (textUsername.getText().length() != 0 && textPassword.getText().length() != 0
						&& textFirstName.getText().length() != 0 && textLastName.getText().length() != 0
						&& combo1.getItem(combo1.getSelectionIndex()).length() != 0) {
					user = new User(textUsername.getText(), textPassword.getText(), textFirstName.getText(),
							textLastName.getText(), combo1.getItem(combo1.getSelectionIndex()));
					close();

				} else {
					setErrorMessage("Please enter all data");
				}
			}
		});
	}
}