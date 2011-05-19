package org.destecs.ide.debug.launching.ui;

import org.destecs.ide.debug.DestecsDebugPlugin;
import org.destecs.ide.debug.IDebugConstants;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;


public class VdmTab extends AbstractLaunchConfigurationTab {

	class WidgetListener implements ModifyListener, SelectionListener
	{
		public void modifyText(ModifyEvent e)
		{
			// validatePage();
			updateLaunchConfigurationDialog();
		}

		public void widgetDefaultSelected(SelectionEvent e)
		{
			/* do nothing */
		}

		public void widgetSelected(SelectionEvent e)
		{
			// fOperationText.setEnabled(!fdebugInConsole.getSelection());

			updateLaunchConfigurationDialog();
		}
	}
	
	protected WidgetListener fListener = new WidgetListener();
	private Button checkBoxUsePostChecks = null;
	private Button checkBoxUsePreChecks = null;
	private Button checkBoxInvChecks = null;
	private Button checkBoxDynamicTypeChecks = null;
	private Button checkBoxUseMeasure = null;

	public void createControl(Composite parent)
	{
		Composite comp = new Composite(parent, SWT.NONE);

		setControl(comp);
		comp.setLayout(new GridLayout(1, true));
		comp.setFont(parent.getFont());
		createInterperterGroupCheckGroup(comp);
		createExtendableContent(comp);
	}
	
	/**
	 * Enables sub classes to add groups to the existing view
	 * @param comp
	 */
	protected void createExtendableContent(Composite comp)
	{

	}
	
	void createInterperterGroupCheckGroup(Composite controlGroup)
	{
		Group interperterGroup = new Group(controlGroup, SWT.NONE);
		interperterGroup.setText("Interpreting");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);

		interperterGroup.setLayoutData(gd);
		
		GridLayout layout = new GridLayout();
		layout.makeColumnsEqualWidth = false;
		layout.numColumns = 3;
		interperterGroup.setLayout(layout);

		checkBoxDynamicTypeChecks = new Button(interperterGroup, SWT.CHECK);
		checkBoxDynamicTypeChecks.setText("Dynamic type checks");
		checkBoxDynamicTypeChecks.addSelectionListener(fListener);

		checkBoxInvChecks = new Button(interperterGroup, SWT.CHECK);
		checkBoxInvChecks.setText("Invariants checks");
		checkBoxInvChecks.addSelectionListener(fListener);

		checkBoxUsePreChecks = new Button(interperterGroup, SWT.CHECK);
		checkBoxUsePreChecks.setText("Pre condition checks");
		checkBoxUsePreChecks.addSelectionListener(fListener);

		checkBoxUsePostChecks = new Button(interperterGroup, SWT.CHECK);
		checkBoxUsePostChecks.setText("Post condition checks");
		checkBoxUsePostChecks.addSelectionListener(fListener);

		checkBoxUseMeasure = new Button(interperterGroup, SWT.CHECK);
		checkBoxUseMeasure.setText("Measure Run-Time checks");
		checkBoxUseMeasure.addSelectionListener(fListener);

	}

	public String getName()
	{
		return "VDM Options";
	}

	public void initializeFrom(ILaunchConfiguration configuration)
	{
		try
		{ 
			checkBoxDynamicTypeChecks.setSelection(configuration.getAttribute(IDebugConstants.VDM_LAUNCH_CONFIG_DTC_CHECKS, true));
			checkBoxInvChecks.setSelection(configuration.getAttribute(IDebugConstants.VDM_LAUNCH_CONFIG_INV_CHECKS, true));
			checkBoxUsePostChecks.setSelection(configuration.getAttribute(IDebugConstants.VDM_LAUNCH_CONFIG_POST_CHECKS, true));
			checkBoxUsePreChecks.setSelection(configuration.getAttribute(IDebugConstants.VDM_LAUNCH_CONFIG_PRE_CHECKS, true));
			checkBoxUseMeasure.setSelection(configuration.getAttribute(IDebugConstants.VDM_LAUNCH_CONFIG_MEASURE_CHECKS, true));
			
		} catch (CoreException e)
		{
			if (DestecsDebugPlugin.DEBUG)
			{
				DestecsDebugPlugin.log(new Status(IStatus.ERROR,DestecsDebugPlugin.PLUGIN_ID,"Error in vdmruntimechecks launch configuration tab",e));
			}
		}
	}

	public void performApply(ILaunchConfigurationWorkingCopy configuration)
	{
		configuration.setAttribute(IDebugConstants.VDM_LAUNCH_CONFIG_DTC_CHECKS, checkBoxDynamicTypeChecks.getSelection());
		configuration.setAttribute(IDebugConstants.VDM_LAUNCH_CONFIG_INV_CHECKS, checkBoxInvChecks.getSelection());
		configuration.setAttribute(IDebugConstants.VDM_LAUNCH_CONFIG_POST_CHECKS, checkBoxUsePostChecks.getSelection());
		configuration.setAttribute(IDebugConstants.VDM_LAUNCH_CONFIG_PRE_CHECKS, checkBoxUsePreChecks.getSelection());
		configuration.setAttribute(IDebugConstants.VDM_LAUNCH_CONFIG_MEASURE_CHECKS, checkBoxUseMeasure.getSelection());
	}

	public void setDefaults(ILaunchConfigurationWorkingCopy configuration)
	{
		configuration.setAttribute(IDebugConstants.VDM_LAUNCH_CONFIG_DTC_CHECKS, true);
		configuration.setAttribute(IDebugConstants.VDM_LAUNCH_CONFIG_INV_CHECKS, true);
		configuration.setAttribute(IDebugConstants.VDM_LAUNCH_CONFIG_POST_CHECKS, true);
		configuration.setAttribute(IDebugConstants.VDM_LAUNCH_CONFIG_PRE_CHECKS, true);
		configuration.setAttribute(IDebugConstants.VDM_LAUNCH_CONFIG_MEASURE_CHECKS, true);
	}

}
