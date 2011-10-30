package com.cannycad.cad.gef.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler
{
    /**
     * The constructor.
     */
    public SampleHandler()
    {
    }

    /**
     * the command has been executed, so extract extract the needed information
     * from the application context.
     */
    public Object execute(ExecutionEvent event) throws ExecutionException
    {
        /*
         * IWorkbenchWindow window =
         * HandlerUtil.getActiveWorkbenchWindowChecked(event);
         * MessageDialog.openInformation(
         * window.getShell(),
         * "CannyCad",
         * "Hello, Eclipse world");
         * 
         * System.out.println("XXX");
         */

        /*
         * EObject container = this.eContainer();
         * int containerFeatureID = this.eContainerFeatureID();
         * EStructuralFeature containingFeature = this.eContainingFeature();
         * EReference containmentFeature = this.eContainmentFeature();
         */
 
        return null;
    }
}
