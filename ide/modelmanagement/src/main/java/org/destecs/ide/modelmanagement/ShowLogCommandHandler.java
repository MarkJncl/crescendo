package org.destecs.ide.modelmanagement;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class ShowLogCommandHandler extends AbstractHandler {


	public Object execute(ExecutionEvent event) throws ExecutionException {		
		try{
			 IStructuredSelection  selection  =  (IStructuredSelection)  HandlerUtil.
			 getCurrentSelectionChecked(event);
			 final  IContainer  c  =  (IContainer)  selection.getFirstElement();
			
			 System.out.println("ShowLogCommandHandler");		 
			 System.out.println(c);
			 
			 
			}catch(Exception e)
			{e.printStackTrace();}
		return null;
	}



}
