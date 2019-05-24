package fr.paris.lutece.plugins.workflow.modules.state.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import fr.paris.lutece.plugins.workflow.modules.state.business.config.ChooseStateTaskConfig;
import fr.paris.lutece.plugins.workflow.modules.state.util.IResourceController;
import fr.paris.lutece.plugins.workflowcore.business.action.Action;
import fr.paris.lutece.plugins.workflowcore.business.state.State;
import fr.paris.lutece.plugins.workflowcore.business.state.StateFilter;
import fr.paris.lutece.plugins.workflowcore.service.action.IActionService;
import fr.paris.lutece.plugins.workflowcore.service.config.ITaskConfigService;
import fr.paris.lutece.plugins.workflowcore.service.state.IStateService;
import fr.paris.lutece.plugins.workflowcore.service.task.ITask;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;

/**
 * Implements IChooseStateTaskService
 */
public class ChooseStateTaskService implements IChooseStateTaskService {

	private static List<IResourceController> _controllerList;
	
	@Inject
    private IActionService _actionService;
	
	@Inject
    private IStateService _stateService;
	
	@Inject
	@Named("workflow-state.chooseStateTaskConfigService")
	private ITaskConfigService _taskConfigService;
	
	@Override
	public ReferenceList getListStates(int nIdAction)
	{
		ReferenceList referenceListStates = new ReferenceList( );
        Action action = _actionService.findByPrimaryKey( nIdAction );

        if ( ( action != null ) && ( action.getWorkflow( ) != null ) )
        {
            StateFilter stateFilter = new StateFilter( );
            stateFilter.setIdWorkflow( action.getWorkflow( ).getId( ) );

            List<State> listStates = _stateService.getListStateByFilter( stateFilter );

            referenceListStates.addItem( -1, StringUtils.EMPTY );
            referenceListStates.addAll( ReferenceList.convert( listStates, "id", "name", true ) );
        }

        return referenceListStates;
	}
	
	@Override
	public List<IResourceController> getControllerList( )
	{
		if ( _controllerList == null )
		{
			_controllerList = initControllerList();
		}
		return _controllerList;
	}
	
	@Override
	public ChooseStateTaskConfig loadConfig( ITask task )
	{
		ChooseStateTaskConfig config = _taskConfigService.findByPrimaryKey( task.getId( ) );
		if ( config == null )
		{
			config = new ChooseStateTaskConfig( );
			config.setIdTask( task.getId( ) );
			_taskConfigService.create( config );
		}
		return config;
	}
	
	private static List<IResourceController> initControllerList( )
	{
		return SpringContextService.getBeansOfType( IResourceController.class );
	}
}
