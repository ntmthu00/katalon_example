package com.kms.example.rcp.ui.contribute.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.kms.example.rcp.ui.contribute.constants.MessageConstants;

public class WelcomeHandler {
	@Execute
	public void execute(Shell shell) {
		MessageDialog.openInformation(shell, MessageConstants.WelcomeHandler_WELCOME_TITLE,
				MessageConstants.WelcomeHandler_MSG_WELCOME);
	}
}