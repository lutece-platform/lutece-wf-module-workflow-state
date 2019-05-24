package fr.paris.lutece.plugins.workflow.modules.state.service.task;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.workflow.modules.state.business.config.ChooseStateTaskConfig;
import fr.paris.lutece.plugins.workflow.modules.state.service.IChooseStateTaskService;
import fr.paris.lutece.plugins.workflow.modules.state.util.IResourceController;
import fr.paris.lutece.plugins.workflowcore.service.task.ITask;
import fr.paris.lutece.plugins.workflowcore.service.task.ITaskService;
import fr.paris.lutece.plugins.workflowcore.service.task.SimpleTask;
import fr.paris.lutece.portal.service.i18n.I18nService;

public class ChooseStateTask extends SimpleTask
{

	private static final String MESSAGE_MARK_TITLE = "module.workflow.state.task.choose.state.title";

	@Inject
    private ITaskService _taskService;
	
	@Inject
	private IChooseStateTaskService _chooseStateTaskService;
	
	@Override
	public void processTask( int nIdResourceHistory, HttpServletRequest request, Locale locale )
	{
	}
	 
	 
	public void chooseNewState( int nIdResource, String strResourceType, int nIdAction, int nIdWorkflow, int oldState)
	{
		ITask task = null;
		List<ITask> listActionTasks = _taskService.getListTaskByIdAction( nIdAction, Locale.getDefault( ) );
		 
		for ( ITask tsk : listActionTasks )
		{
			if ( tsk.getTaskType( ) != null && tsk.getTaskType( ).getBeanName( ) != null
	                    && tsk.getTaskType( ).getBeanName( ).equals( "workflow-state.chooseStateTask" ) )
			{
				task = tsk;
			}
		}
		 
		if ( task != null )
		{
			ChooseStateTaskConfig config = _chooseStateTaskService.loadConfig( task );
			 
			IResourceController controller = _chooseStateTaskService.getController( config );
			 
			int newState = -1;
			if ( controller.control( nIdResource, strResourceType ) )
			{
				newState = config.getIdStateOK( );
			}
			else
			{
				newState = config.getIdStateKO( );
			}
			
			if ( newState != -1 && newState != oldState )
	    	{
				_chooseStateTaskService.doChangeState( task, nIdResource, strResourceType, nIdWorkflow, newState );
	    	}
		}
	}
	
	@Override
	public String getTitle(Locale locale) {
		return I18nService.getLocalizedString( MESSAGE_MARK_TITLE, locale );
	}
}
