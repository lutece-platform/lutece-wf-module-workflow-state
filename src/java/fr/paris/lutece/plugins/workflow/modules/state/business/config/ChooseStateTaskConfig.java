package fr.paris.lutece.plugins.workflow.modules.state.business.config;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import fr.paris.lutece.plugins.workflowcore.business.config.TaskConfig;

public class ChooseStateTaskConfig extends TaskConfig
{

    @NotNull
    @Min( 1 )
    private int _nIdStateOK;

    @NotNull
    @Min( 1 )
    private int _nIdStateKO;

    private String _controllerName;

    /**
     * @return the controllerName
     */
    public String getControllerName( )
    {
        return _controllerName;
    }

    /**
     * @param controllerName
     *            the controllerName to set
     */
    public void setControllerName( String controllerName )
    {
        _controllerName = controllerName;
    }

    /**
     * @return the _nIdStateOK
     */
    public int getIdStateOK( )
    {
        return _nIdStateOK;
    }

    /**
     * @param _nIdStateOK
     *            the _nIdStateOK to set
     */
    public void setIdStateOK( int _nIdStateOK )
    {
        this._nIdStateOK = _nIdStateOK;
    }

    /**
     * @return the _nIdStateKO
     */
    public int getIdStateKO( )
    {
        return _nIdStateKO;
    }

    /**
     * @param _nIdStateKO
     *            the _nIdStateKO to set
     */
    public void setIdStateKO( int _nIdStateKO )
    {
        this._nIdStateKO = _nIdStateKO;
    }
}
