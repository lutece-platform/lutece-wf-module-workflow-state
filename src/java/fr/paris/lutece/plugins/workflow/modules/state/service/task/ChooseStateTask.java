package fr.paris.lutece.plugins.workflow.modules.state.service.task;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.workflow.modules.state.business.config.ChooseStateTaskConfig;
import fr.paris.lutece.plugins.workflow.modules.state.service.IChooseStateTaskService;
import fr.paris.lutece.plugins.workflow.modules.state.util.IResourceController;
import fr.paris.lutece.plugins.workflowcore.service.task.ITask;
import fr.paris.lutece.plugins.workflowcore.service.task.SimpleTask;
import fr.paris.lutece.portal.service.i18n.I18nService;

public class ChooseStateTask extends SimpleTask
{
    private static final String MESSAGE_MARK_TITLE = "module.workflow.state.task.choose.state.title";

    @Inject
    private IChooseStateTaskService _chooseStateTaskService;

    @Override
    public void processTask( int nIdResourceHistory, HttpServletRequest request, Locale locale )
    {
    }

    public void chooseNewState( int nIdResource, String strResourceType, ITask task, ChooseStateTaskConfig config, int nIdWorkflow, int oldState )
    {
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

    @Override
    public String getTitle( Locale locale )
    {
        return I18nService.getLocalizedString( MESSAGE_MARK_TITLE, locale );
    }
}
