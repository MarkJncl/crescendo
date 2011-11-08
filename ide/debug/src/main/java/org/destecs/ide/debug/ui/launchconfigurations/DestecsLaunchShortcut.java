/*******************************************************************************
 * Copyright (c) 2010, 2011 DESTECS Team and others.
 *
 * DESTECS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DESTECS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with DESTECS.  If not, see <http://www.gnu.org/licenses/>.
 * 	
 * The DESTECS web-site: http://destecs.org/
 *******************************************************************************/
package org.destecs.ide.debug.ui.launchconfigurations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.destecs.ide.debug.DestecsDebugPlugin;
import org.destecs.ide.debug.IDebugConstants;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.ILaunchShortcut2;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.progress.IProgressService;

public class DestecsLaunchShortcut implements ILaunchShortcut2
{
	/**
	 * Returns the type of configuration this shortcut is applicable to.
	 * 
	 * @return the type of configuration this shortcut is applicable to
	 */
	protected ILaunchConfigurationType getConfigurationType()
	{
		return getLaunchManager().getLaunchConfigurationType(IDebugConstants.ATTR_DESTECS_PROGRAM);
	}

	/**
	 * Creates and returns a new configuration based on the specified type.
	 * 
	 * @param type
	 *            type to create a launch configuration for
	 * @return launch configuration configured to launch the specified type
	 */
//	protected abstract ILaunchConfiguration createConfiguration(IAstNode type,
//			String projectName);

	/**
	 * Finds and returns the types in the given collection of elements that can be launched.
	 * 
	 * @param elements
	 *            scope to search for types that can be launched
	 * @param context
	 *            progress reporting context
	 * @return collection of types that can be launched, possibly empty
	 * @exception InterruptedException
	 *                if the search is canceled
	 * @exception CoreException
	 *                if the search fails
	 */
	// protected abstract IAstNode[] findTypes(Object[] elements,
	// IRunnableContext context) throws InterruptedException,
	// CoreException;

//	protected abstract IAstNode[] filterTypes(Object[] elements,
//			IRunnableContext context);

	/**
	 * Returns a title for a type selection dialog used to prompt the user when there is more than one type that can be
	 * launched.
	 * 
	 * @return type selection dialog title
	 */
	protected  String getTypeSelectionTitle()
	{
		return "Select DESTECS Application";
	}

	/**
	 * Returns an error message to use when the editor does not contain a type that can be launched.
	 * 
	 * @return error message when editor cannot be launched
	 */
	protected String getEditorEmptyMessage(){
		return "Editor does not contain a main type";
	}

	/**
	 * Returns an error message to use when the selection does not contain a type that can be launched.
	 * 
	 * @return error message when selection cannot be launched
	 */
	protected  String getSelectionEmptyMessage()
	{
		return "Selection does not contain a launchable operation or function type";
	}

	IProject project = null;

	/**
	 * Resolves a type that can be launched from the given scope and launches in the specified mode.
	 * 
	 * @param scope
	 *            the java elements to consider for a type that can be launched
	 * @param mode
	 *            launch mode
	 * @param selectTitle
	 *            prompting title for choosing a type to launch
	 * @param emptyMessage
	 *            error message when no types are resolved for launching
	 */
	private void searchAndLaunch(Object[] scope, String mode,
			String selectTitle, String emptyMessage)
	{
//		Object[] types = null;

		try
		{
			project = findProject(scope, PlatformUI.getWorkbench().getProgressService());
			
			ILaunchConfiguration config =findLaunchConfiguration(project.getName(),getConfigurationType());
			if(config!=null)
			{
				//config already exists for the project.
				launch(config, mode);
				return;
			}
			
//			types = findTypes(scope, PlatformUI.getWorkbench().getProgressService());

		} catch (InterruptedException e)
		{
			return;
		} catch (CoreException e)
		{
			MessageDialog.openError(getShell(), LauncherMessages.VdmLaunchShortcut_0, e.getMessage());
			return;
		}
//		Object type = null;
//		if (types == null || types.length == 0)
//		{
//			MessageDialog.openError(getShell(), LauncherMessages.VdmLaunchShortcut_1, emptyMessage);
//		}
//		else if (types.length > 1)
//		{
//			type = chooseType(types, selectTitle);
//		} else
//		{
//			type = types[0];
//		}
//		if (type != null && project != null)
//		{
//			launch(type, mode, project.getName());
//		}
	}

	/**
	 * Prompts the user to select a type from the given types.
	 * 
	 * @param types
	 *            the types to choose from
	 * @param title
	 *            the selection dialog title
	 * @return the selected type or <code>null</code> if none.
	 */
//	protected IAstNode chooseType(IAstNode[] types, String title)
//	{
//		try
//		{
//			DebugTypeSelectionDialog mmsd = new DebugTypeSelectionDialog(VdmDebugPlugin.getActiveWorkbenchShell(), types, title, project);
//			if (mmsd.open() == Window.OK)
//			{
//				return (IAstNode) mmsd.getResult()[0];
//			}
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//
//		return null;
//	}

//	private void launch(IAstNode type, String mode, String projectName)
//	{
//		ILaunchConfiguration config = findLaunchConfiguration( projectName, getConfigurationType());
//		if (config == null)
//		{
//			config = createConfiguration(type, projectName);
//		}
//		launch(config, mode);
//
//	}
	
	private void launch(ILaunchConfiguration config, String mode)
	{
		if (config != null)
		{
			DebugUITools.launch(config, mode);
		}
	}

	/**
	 * Finds and returns an <b>existing</b> configuration to re-launch for the given type, or <code>null</code> if there
	 * is no existing configuration.
	 * 
	 * @return a configuration to use for launching the given type or <code>null</code> if none
	 */
	protected ILaunchConfiguration findLaunchConfiguration(String projectName, ILaunchConfigurationType configType)
	{
		List<ILaunchConfiguration> candidateConfigs = Collections.emptyList();
		try
		{
			ILaunchConfiguration[] configs = DebugPlugin.getDefault().getLaunchManager().getLaunchConfigurations(configType);
			candidateConfigs = new ArrayList<ILaunchConfiguration>(configs.length);
			for (int i = 0; i < configs.length; i++)
			{
				ILaunchConfiguration config = configs[i];

//				String defaultModule = config.getAttribute(IDebugConstants.VDM_LAUNCH_CONFIG_DEFAULT, "");
				String pName = config.getAttribute(IDebugConstants.DESTECS_LAUNCH_CONFIG_PROJECT_NAME, "");
//				String operation = config.getAttribute(IDebugConstants.VDM_LAUNCH_CONFIG_OPERATION, "");

				if (//defaultModule.equals(getModuleName(type).toString())
						//&&
						pName.equalsIgnoreCase(projectName)
						//&& operation.equals(getOperationName(type) + "()")
						)
				{ //$NON-NLS-1$
					candidateConfigs.add(config);
				}
			}

		} catch (CoreException e)
		{
			// JDIDebugUIPlugin.log(e);
		}
		int candidateCount = candidateConfigs.size();
		if (candidateCount == 1)
		{
			return (ILaunchConfiguration) candidateConfigs.get(0);
		} else if (candidateCount > 1)
		{
			return chooseConfiguration(candidateConfigs);
			// return candidateConfigs.get(0);
		}
		return null;
	}

//	protected String getModuleName(IAstNode node)
//	{
//		if (node instanceof ExplicitFunctionDefinition)
//		{
//			return ((ExplicitFunctionDefinition) node).location.module;
//		}
//		if (node instanceof ExplicitOperationDefinition)
//		{
//			return ((ExplicitOperationDefinition) node).location.module;
//		}
//
//		return "";
//	}
//
//	protected String getModuleNameLaunch(IAstNode node)
//	{
//		String name = "";
//		if (node instanceof ExplicitFunctionDefinition)
//		{
//			name = ((ExplicitFunctionDefinition) node).location.module;
//			if (!((ExplicitFunctionDefinition) node).isStatic())
//			{
//				name += "()";
//			}
//
//		}
//		if (node instanceof ExplicitOperationDefinition)
//		{
//			name = ((ExplicitOperationDefinition) node).location.module;
//			if (!((ExplicitOperationDefinition) node).isStatic())
//			{
//				name += "()";
//			}
//		}
//
//		return name;
//	}
//
//	protected String getOperationName(IAstNode node)
//	{
//		return node.getName();
//	}
//
//	protected boolean isStaticAccessRequired(IAstNode node)
//	{
//		if (node instanceof ExplicitFunctionDefinition)
//		{
//			return ((ExplicitFunctionDefinition) node).isStatic();
//		}
//		if (node instanceof ExplicitOperationDefinition)
//		{
//			return ((ExplicitOperationDefinition) node).isStatic();
//		}
//
//		return true;
//	}

	/**
	 * Returns a configuration from the given collection of configurations that should be launched, or <code>null</code>
	 * to cancel. Default implementation opens a selection dialog that allows the user to choose one of the specified
	 * launch configurations. Returns the chosen configuration, or <code>null</code> if the user cancels.
	 * 
	 * @param configList
	 *            list of configurations to choose from
	 * @return configuration to launch or <code>null</code> to cancel
	 */
	protected ILaunchConfiguration chooseConfiguration(
			List<ILaunchConfiguration> configList)
	{
		IDebugModelPresentation labelProvider = DebugUITools.newDebugModelPresentation();
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(getShell(), labelProvider);
		dialog.setElements(configList.toArray());
		dialog.setTitle(getTypeSelectionTitle());
		dialog.setMessage(LauncherMessages.VdmLaunchShortcut_2);
		dialog.setMultipleSelection(false);
		int result = dialog.open();
		labelProvider.dispose();
		if (result == Window.OK)
		{
			return (ILaunchConfiguration) dialog.getFirstResult();
		}
		return null;
	}

	/**
	 * Convenience method to return the active workbench window shell.
	 * 
	 * @return active workbench window shell
	 */
	protected Shell getShell()
	{
		return DestecsDebugPlugin.getActiveWorkbenchShell();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchShortcut#launch(org.eclipse.ui.IEditorPart, java.lang.String)
	 */
	public void launch(IEditorPart editor, String mode)
	{
		// IEditorInput input = editor.getEditorInput();
		// IJavaElement je = (IJavaElement) input.getAdapter(IJavaElement.class);
		// TODO get ISourceUnit from editor
//		Object je = null;
//		if (je != null)
//		{
//			searchAndLaunch(new Object[] { je }, mode, getTypeSelectionTitle(), getEditorEmptyMessage());
//		}
	}

	public void launch(ISelection selection, String mode)
	{
		if (selection instanceof IStructuredSelection)
		{
			searchAndLaunch(((IStructuredSelection) selection).toArray(), mode, getTypeSelectionTitle(), getSelectionEmptyMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchShortcut2#getLaunchableResource(org.eclipse.ui.IEditorPart)
	 */
	public IResource getLaunchableResource(IEditorPart editorpart)
	{
		return getLaunchableResource(editorpart.getEditorInput());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchShortcut2#getLaunchableResource(org.eclipse.jface.viewers.ISelection)
	 */
	public IResource getLaunchableResource(ISelection selection)
	{
		if (selection instanceof IStructuredSelection)
		{
			IStructuredSelection ss = (IStructuredSelection) selection;
			if (ss.size() == 1)
			{
				Object element = ss.getFirstElement();
				if (element instanceof IAdaptable)
				{
					return getLaunchableResource((IAdaptable) element);
				}
			}
		}
		return null;
	}

	/**
	 * Returns the resource containing the Java element associated with the given adaptable, or <code>null</code>.
	 * 
	 * @param adaptable
	 *            adaptable object
	 * @return containing resource or <code>null</code>
	 */
	private IResource getLaunchableResource(IAdaptable adaptable)
	{
		// IJavaElement je = (IJavaElement) adaptable.getAdapter(IJavaElement.class);
		// if (je != null) {
		// return je.getResource();
		// }
		// TODO
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchShortcut2#getLaunchConfigurations(org.eclipse.ui.IEditorPart)
	 */
	public ILaunchConfiguration[] getLaunchConfigurations(IEditorPart editorpart)
	{
		// let the framework resolve configurations based on resource mapping
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchShortcut2#getLaunchConfigurations(org.eclipse.jface.viewers.ISelection)
	 */
	public ILaunchConfiguration[] getLaunchConfigurations(ISelection selection)
	{
		// let the framework resolve configurations based on resource mapping
		return null;
	}

	protected IProject findProject(Object[] scope,
			IProgressService progressService) throws InterruptedException,
			CoreException
	{
		for (Object object : scope)
		{
			if (object instanceof IProject)
			{
				IProject project = (IProject) object;
				return project;

			}
		}
		return null;
	}

	protected ILaunchManager getLaunchManager()
	{
		return DebugPlugin.getDefault().getLaunchManager();
	}

//	protected IAstNode[] findTypes(Object[] elements, IRunnableContext context)
//			throws InterruptedException, CoreException
//	{
//		for (Object object : elements)
//		{
//			if (object instanceof IAdaptable)
//			{
//				IVdmProject vdmProject = (IVdmProject) ((IAdaptable) object).getAdapter(IVdmProject.class);
//				if (vdmProject != null)
//				{
//					final IVdmModel model = vdmProject.getModel();
//					try
//					{
//						context.run(false, false, new IRunnableWithProgress()
//						{
//
//							public void run(IProgressMonitor monitor)
//									throws InvocationTargetException,
//									InterruptedException
//							{
//								model.refresh(false, monitor);
//
//							}
//						});
//					} catch (InvocationTargetException e)
//					{
//						if (VdmDebugPlugin.DEBUG)
//						{
//							e.printStackTrace();
//						}
//					}
//
//					return filterTypes(model.getRootElementList().toArray(), context);
//				}
//			}
//		}
//		return null;
//	}

}
