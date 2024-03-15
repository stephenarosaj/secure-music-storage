package edu.brown.cs.securemusicstorage.Security;

/**
 * Describes a single requested interaction with the filesystem. 
 * 
 * As this is a **requested** action, it may describe an "invalid" action, 
 * e.g., deleting a file which the specified user doesn't have access to, 
 * or reading a file which doesn't exist.
 * 
 * @param fileUUID the unique identifier of the file being interacted with
 * @param fileAction which action is being applied to the specified file
 * @param userID which user is carrying out the action
 */
public record ActionRequestRecord(String fileUUID, FileAction fileAction, String userID) { 
    /**
     * The action to be performed on a file. 
     */
    public enum FileAction {
        UPLOAD,         // upload the file (first time)
        DOWNLOAD,       // download the file
        DELETE,         // delete the file!
        VIEW_METADATA,  // view file name, owner, size, length, dates, etc. 
        READ_CONTENTS,  // read the contents of the file, e.g. for listening
        WRITE,          // overwrite the file
        SHARE,          // add permissions for users (including new users)
        REVOKE,         // remove permissions for users
        MOVE            // change parent directory/music project
    }
}
