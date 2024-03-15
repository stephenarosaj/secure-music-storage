package edu.brown.cs.securemusicstorage.Security;

/**
 * Contains the result of the requested action and any data returned as
 * as a result of the action.
 * 
 * @param data data returned after action has been carried out. MAY BE NULL!
 */
public record RequestActionResponse(Result result, String data) { // TODO: what should the datatype for "data" be?
    /**
     * The result of the request to carry out the action. Indiciates if
     * the action was rejected due to insufficient permissions, was accepted
     * and carried out successfully, or accepted and experienced a failure.  
     */
    enum Result {
        INSUFFICIENT_PERMS,     // insufficient permissions for requested action
        ACTION_SUCCESS,         // sufficient permissions; action was successful    
        ACTION_FAILURE          // sufficient permissions; action failed
    }

    public static RequestActionResponse insufficientPermsResponse() {
        return new RequestActionResponse(Result.INSUFFICIENT_PERMS, null);
    }

    public static RequestActionResponse failResponse() {
        return new RequestActionResponse(Result.ACTION_FAILURE, null);
    }

    public static RequestActionResponse successResponse(String data) { // TODO: what should the datatype for "data" be?
        return new RequestActionResponse(Result.ACTION_SUCCESS, data);
    }
}