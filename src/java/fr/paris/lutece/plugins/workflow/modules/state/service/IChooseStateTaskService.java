package fr.paris.lutece.plugins.workflow.modules.state.service;

import java.util.List;

import fr.paris.lutece.plugins.workflow.modules.state.business.config.ChooseStateTaskConfig;
import fr.paris.lutece.plugins.workflow.modules.state.util.IResourceController;
import fr.paris.lutece.plugins.workflowcore.service.task.ITask;
import fr.paris.lutece.util.ReferenceList;

/**
 *  Service for  IChooseStateTask
 */
public interface IChooseStateTaskService {

	 /**
     * Get the list of states
     * 
     * @param nIdAction
     *            the id action
     * @return a ReferenceList
     */
    ReferenceList getListStates( int nIdAction );
    
    /**
     * Get the list of implemented {@link IResourceController}
     * @return
     */
    List<IResourceController> getControllerList( );
    
    /**
     * Load config of task.
     * @param task
     * @return
     */
    ChooseStateTaskConfig loadConfig( ITask task );
}
