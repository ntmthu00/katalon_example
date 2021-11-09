package com.kms.example.rcp.ui.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
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

public class UsersPart extends ViewPart {
	public UsersPart() {
	}

	private Button btnAdd;
	private Button btnDelete;
	private Button btnUpdate;

	@Inject
	private IEventBroker eventBroker;
	private TableViewer usersTableViewer;

	@PostConstruct
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, true));

//		Composite tableComposite = new Composite(parent, SWT.NONE);
//		TableColumnLayout tableLayout = new TableColumnLayout();
//		tableComposite.setLayout(tableLayout);

		createUsersTableViewer(parent);

		Composite btnComposite = new Composite(parent, SWT.NONE);
		btnComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnComposite.setLayout(new RowLayout());

		btnAdd = new Button(btnComposite, SWT.PUSH);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.print("Add button pressed\n");
//				UserProvider users = UserProvider.INSTANCE;
//				UsersDetailDialog dialog = new UsersDetailDialog(getViewSite().getShell());
//				dialog.open();
//				if (dialog.getUser() != null) {
//					users.getUsers().add(dialog.getUser());
//				}
			}
		});
		btnAdd.setText(MessageConstants.UsersPart_BTN_ADD);

		btnDelete = new Button(btnComposite, SWT.PUSH);
		btnDelete.setEnabled(false);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.print("Delete button pressed\n");
			}
		});
		btnDelete.setText(MessageConstants.UsersPart_BTN_DELETE);

		btnUpdate = new Button(btnComposite, SWT.PUSH);
		btnUpdate.setEnabled(false);
		btnUpdate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.print("Update button pressed\n");
			}
		});
		btnUpdate.setText(MessageConstants.UsersPart_BTN_UPDATE);
	}

	private void createUsersTableViewer(Composite parent) {
		usersTableViewer = new TableViewer(parent,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.HIDE_SELECTION | SWT.BORDER);

		createColumns(parent, usersTableViewer);
		final Table usersTable = usersTableViewer.getTable();
		usersTable.setHeaderVisible(true);
		usersTable.setLinesVisible(true);

		usersTableViewer.setContentProvider(ArrayContentProvider.getInstance());
		usersTableViewer.setInput(UserProvider.INSTANCE.getUsers());
		// getSite().setSelectionProvider(usersTableViewer);

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
				eventBroker.send(EventConstants.TOPIC_ROW_SELECTION_AVATAR, user.getAvaFilePath());
				eventBroker.send(EventConstants.TOPIC_ROW_SELECTION_WELCOME, user.getUsername());
			}
		});
	}

	private void createColumns(final Composite parent, final TableViewer usersTableViewer) {
		String[] titles = { MessageConstants.UsersPart_TBL_COL_ID, MessageConstants.UsersPart_TBL_COL_USERNAME,
				MessageConstants.UsersPart_TBL_COL_DATE_OF_BIRTH, MessageConstants.UsersPart_TBL_COL_GENDER };
		int[] bounds = { 100, 100, 100, 100 };

		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				User u = (User) element;
				return String.valueOf(u.getId());
			}
		});

		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				User u = (User) element;
				return u.getUsername();
			}
		});

		col = createTableViewerColumn(titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				User u = (User) element;
				return u.getDob().toString();
			}
		});

		col = createTableViewerColumn(titles[3], bounds[3], 3);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				User u = (User) element;
				return u.getGender();
			}
		});
	}

	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(usersTableViewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();

		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);

		return viewerColumn;
	}

	@Focus
	public void setFocus() {
		usersTableViewer.getControl().setFocus();
	}

	public void refresh() {
		usersTableViewer.refresh();
	}
}
