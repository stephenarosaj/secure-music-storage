package edu.brown.cs.securemusicstorage.Security;

/**
 * The “security layer” - no files can be accessed, deleted, or otherwise
 * interacted with unless done so through this layer. 
 * 
 * This component handles enforcing access controls. It acts on a “Requested
 * Action”, verifying that the user requesting the action is properly
 * authenticated, that they have the proper permissions for the requested
 * action, and if both are verified, calls the proper action handlers to
 * carry out the action. 
 */
public abstract class RequestValidator {
    /**
     * The entry point for this component. Verifies the supplied auth token for
     * user, verifies permissions for user, and carries out requested action. 
     * 
     * @param requestedAction (ActionRequestRecord) the action being requested
     * @param authToken (String) the authToken of the user requesting the action
     * @return (RequestActionResponse) the result of the requested action, and
     *         any data returned by the action, if successful.
     */
    public abstract RequestActionResponse RequestAction(ActionRequestRecord requestedAction, String authToken); // TODO: what should the datatype for "authToken" be?

    /**
     * Verifies the user’s auth token is legitimate and belongs to the specified
     * user. Calls user account / authentication component to verify. 
     * 
     * @param userID (String) the user who claims this is their valid token
     * @param authToken (String) the authToken provided by the user
     * @return (boolean) indicating the validity of the token
     */
    protected abstract boolean validateToken(String userID, String authToken);

    /**
     * Verifies the user has sufficient permissions to carry out the requested
     * action on the specified file. Calls the permissions/metadata CRUD to read
     * the user’s permissions for the specified file, then determines if the
     * requested action is allowed. 
     * 
     * Only to be called after validateToken has been called to verify the 
     * AuthToken of the user requesting this action!
     * 
     * @param requestedAction (ActionRequestRecord) the action being requested
     * @return (boolean) indicating if the user has sufficient permissions to
     *         carry out this action
     */
    protected abstract boolean ValidateAction(ActionRequestRecord requestedAction);

    /**
     * Carries out the request action. Calls one of many action handlers for
     * each possible action. 
     * 
     * Only to be called after ValidateAction (and thus, ValidateToken) has been
     * called to verify AuthToken of the user requesting this action, as well
     * as their permissions to carry out this action.
     * 
     * @param requestedAction (ActionRequestRecord) the actoin to be carried out.
     * @return (RequestActionResponse) the result of the requested action, and
     *         any data returned by the action, if successful.
     */
    protected abstract RequestActionResponse CarryOutAction(ActionRequestRecord requestedAction);
}
