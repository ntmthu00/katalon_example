package com.kms.example.rcp.ui.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import com.kms.example.rcp.core.object.User;
import com.kms.example.rcp.core.object.UserProvider;
import com.kms.example.rcp.ui.constants.EventConstants;
import com.kms.example.rcp.ui.constants.MessageConstants;
import com.kms.example.rcp.ui.dialogs.UsersDetailDialog;

public class UsersPart extends ViewPart {
	public UsersPart() {
	}

	public static final String ID = "com.kms.example.rcp.ui.parts.UsersPart";

	private TableViewer usersTableViewer;
	private Button btnAdd;
	private Button btnDelete;
	private Button btnUpdate;

	@Inject
	private IEventBroker eventBroker;

	@PostConstruct
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		createUsersTableViewer(parent);
		createButtonSection(parent);
	}

	private void createButtonSection(Composite parent) {
		Composite btnComposite = new Composite(parent, SWT.NONE);
		btnComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnComposite.setLayout(new RowLayout());

		btnAdd = new Button(btnComposite, SWT.PUSH);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UserProvider users = UserProvider.INSTANCE;
				UsersDetailDialog dialog = new UsersDetailDialog(parent.getShell());
				dialog.open();
				if (dialog.getUser() != null) {
					User newUser = dialog.getUser();
					newUser.setId(users.getUserList().size() + 1);
					users.getUserList().add(newUser);
					refresh();
				}
			}
		});
		btnAdd.setText(MessageConstants.UsersPart_BTN_ADD);

		btnDelete = new Button(btnComposite, SWT.PUSH);
		btnDelete.setEnabled(false);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = usersTableViewer.getStructuredSelection();
				User user = User.class.cast(selection.getFirstElement());
				UserProvider users = UserProvider.INSTANCE;
				users.getUserList().remove(user);
				refresh();
				btnDelete.setEnabled(false);
				btnUpdate.setEnabled(false);
			}
		});
		btnDelete.setText(MessageConstants.UsersPart_BTN_DELETE);

		btnUpdate = new Button(btnComposite, SWT.PUSH);
		btnUpdate.setEnabled(false);
		btnUpdate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = usersTableViewer.getStructuredSelection();
				User user = User.class.cast(selection.getFirstElement());
				UserProvider users = UserProvider.INSTANCE;
				UsersDetailDialog dialog = new UsersDetailDialog(parent.getShell());
				dialog.setUsername(user.getUsername());
				dialog.setPassword(user.getPassword());
				dialog.setFirstName(user.getFirstName());
				dialog.setLastName(user.getLastName());
				dialog.setAvatarFilePath(user.getAvaFilePath());
				dialog.setDateDOB(user.getDob());
				dialog.setGender(user.getGender());
				dialog.open();

				if (dialog.getUser() != null) {
					User updatedUser = dialog.getUser();
					updatedUser.setId(user.getId());
					int userIdx = users.getUserList().indexOf(user);
					users.getUserList().set(userIdx, updatedUser);
					refresh();
					btnDelete.setEnabled(false);
					btnUpdate.setEnabled(false);
				}
			}
		});
		btnUpdate.setText(MessageConstants.UsersPart_BTN_UPDATE);
	}

	private void createUsersTableViewer(Composite parent) {
		Composite tableComposite = new Composite(parent, SWT.NONE);
		final TableColumnLayout tableColumnLayout = new TableColumnLayout();
		tableComposite.setLayout(tableColumnLayout);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(tableComposite);

		usersTableViewer = new TableViewer(tableComposite,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.HIDE_SELECTION | SWT.BORDER);

		createColumns(tableColumnLayout);
		final Table usersTable = usersTableViewer.getTable();
		usersTable.setHeaderVisible(true);
		usersTable.setLinesVisible(true);

		usersTableViewer.setContentProvider(ArrayContentProvider.getInstance());
		usersTableViewer.setInput(UserProvider.INSTANCE.getUserList());

		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		usersTableViewer.getControl().setLayoutData(gridData);

		usersTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent e) {
				IStructuredSelection selection = usersTableViewer.getStructuredSelection();
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
				User user = User.class.cast(selection.getFirstElement());
				if (user != null) {
					eventBroker.send(EventConstants.TOPIC_ROW_SELECTION_AVATAR, user.getAvaFilePath());
					eventBroker.send(EventConstants.TOPIC_ROW_SELECTION_WELCOME, user.getUsername());
				}
			}
		});
	}

	private void createColumns(final TableColumnLayout tableColumnLayout) {
		String[] titles = { MessageConstants.UsersPart_TBL_COL_ID, MessageConstants.UsersPart_TBL_COL_USERNAME,
				MessageConstants.UsersPart_TBL_COL_DATE_OF_BIRTH, MessageConstants.UsersPart_TBL_COL_GENDER };

		TableViewerColumn col = createTableViewerColumn(titles[0]);
		tableColumnLayout.setColumnData(col.getColumn(), new ColumnWeightData(100));
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				User u = (User) element;
				return String.valueOf(u.getId());
			}
		});

		col = createTableViewerColumn(titles[1]);
		tableColumnLayout.setColumnData(col.getColumn(), new ColumnWeightData(100));
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				User u = (User) element;
				return u.getUsername();
			}
		});

		col = createTableViewerColumn(titles[2]);
		tableColumnLayout.setColumnData(col.getColumn(), new ColumnWeightData(100));
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				User u = (User) element;
				return u.getDob().toString();
			}
		});

		col = createTableViewerColumn(titles[3]);
		tableColumnLayout.setColumnData(col.getColumn(), new ColumnWeightData(100));
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				User u = (User) element;
				return u.getGender();
			}
		});
	}

	private TableViewerColumn createTableViewerColumn(String title) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(usersTableViewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();

		column.setText(title);
		column.setResizable(true);
		column.setMoveable(true);

		return viewerColumn;
	}

	public void refresh() {
		usersTableViewer.refresh();
	}

	@Focus
	public void setFocus() {
		usersTableViewer.getControl().setFocus();
	}
}
