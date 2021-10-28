package com.kms.example.rcp.ui.handlers;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.e4.core.di.annotations.Execute;

public class welcomeHandler {
	@Execute
	public void execute(Shell shell) {
		MessageDialog.openInformation(shell, "Welcome", "Welcome to Eclipse RCP sample application");
	}
		
}