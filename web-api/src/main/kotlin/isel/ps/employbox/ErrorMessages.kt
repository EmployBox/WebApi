package isel.ps.employbox

object ErrorMessages {


    val BAD_REQUEST_INVALID_RATINGS = "Invalid ratings values sent"
    val INVALID_ACCOUNT_TYPE_IN_RATING = "The account type provided to this rating is invalid"
    val ALREADY_EXISTS = "This relation/item already exists"
    val ALREADY_FOLLOWED = "The follow relation already exists"
    val BAD_REQUEST_IDS_MISMATCH = "Given ids do not match with the ones passed in the body"
    val UN_AUTHORIZED_ID_AND_EMAIL_MISMATCH = "Account authenticated is not allowed to user this resource"
    val UN_AUTHORIZED_T = ""
    val RESOURCE_NOTFOUND = "Resource item wasn´t found"
    val RESOURCE_NOTFOUND_USER = "User wasn't found"
    val RESOURCE_NOTFOUND_ACCOUNT = "Account wasn't found"
    val CONFLIT_USERNAME_TAKEN = "The username provided is already taken"
    val RESOURCE_NOT_FOUND_MESSAGE = "Message wasn´t found"
    val RESOURCE_NOT_FOUND_CHAT = "Chat wasn´t found"
    val UN_AUTHORIZED_MESSAGE = "The chat where you tryed to add a new message doesnt belong to this user"
    val RESOURCE_NOTFOUND_COMMENT = "Comment wasn´t found"
    val RESOURCE_NOTFOUND_JOB = "Job wasn´t found"
    val RESOURCE_NOTFOUND_RATING = "Rating wasn´t found"
    val RESOURCE_NOTFOUND_APPLICATION = "Application wasn´t found"
    val RESOURCE_NOTFOUND_CURRICULUM = "Curriculum wasn´t found"
    val UN_AUTHORIZED_CURRICULUM = "This curriculum doesnt belong to this user"
    val UN_AUTHORIZED_APPLICATION = "This application doesnt belong to this user"
    val UN_AUTHORIZED = "The logged in user cannot perform this operation"
    val BAD_REQUEST_ITEM_CREATION = "The item could not be created, check the body before sending the request again"
    val BAD_REQUEST_ITEM_DELETION = "Item could not be deleted, check if the object your are trying to delete can be deleted"
    val BAD_REQUEST_ITEM_UPDATE = "Item could not be updated, check if the object your are trying to update exists or you can update it"
    val BAD_REQUEST_UPDATE_RATING = "You tryed to update a rating without sending the ID of the rated/destiny account "
    val RESOURCE_NOTFOUND_COMPANY = "Company could not be found"
    val JOB_EXPERIENCE_ITEM_CREATION = "The job experience list you tried to create with a job couldn't be added to the database"
    val RESOURCE_NOTFOUND_PREVIOUS_JOB = "Previous job wasnt found for this curriculum"
    val RESOURCE_NOTFOUND_JOB_EXPERIENCE = ""
    val CHILDS_CREATION = "The item was created with success but some lists belonging to this object could not be added to the DB"


}
