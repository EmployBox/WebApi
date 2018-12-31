package isel.ps.exception.legacy.codes;

/**
 * Created by diogo.vieira on 17/02/2016.
 *
 */
public class ErrorCodes {

    public static final String PARAMS_NOT_FILLED = "params_not_filled";
    public static final String INVALID_PARAMETERS = "invalid_parameters";

    public static final String EXCHANGE_LESS_THAN_ZERO = "exchange_less_than_zero";
    public static final String EXCHANGE_RATE_NOT_FOUND = "exchange_rate_not_found";
    public static final String CURRENCY_ORIGIN_NOT_FOUND = "currency_origin_not_found";
    public static final String CURRENCY_DESTINATION_NOT_FOUND = "currency_destination_not_found";
    public static final String CURRENCY_NOT_FOUND = "currency_not_found";
    public static final String CURRENCY_INVALID = "currency_invalid";

    public static final String CORE_BANKING_ERROR = "core_banking_error";
    public static final String CORE_BANKING_OFFLINE = "core_banking_offline";

    public static final String BRANCH_NOT_FOUND = "branch_not_found";
    public static final String BRANCH_INVALID = "branch_invalid";

    public static final String EXCHANGE_NOT_FOUND = "exchange_not_found";

    public static final String INVALID_CLIENT = "invalid_client";
    public static final String INVALID_ACCOUNT_NUMBER = "invalid_account_number";
    public static final String INVALID_MOZAMBIQUE_IBAN = "invalid_mozambique_iban";
    public static final String ACCOUNT_NOT_EDITABLE = "account_not_editable";
    public static final String DEFAULT_ACCOUNT_CANT_BE_INVISIBLE = "default_account_cant_be_invisible";
    public static final String DEFAULT_ACCOUNT_NOT_FOUND = "default_account_not_found";
    public static final String DUPLICATE_ACCOUNT_NUMBER = "duplicate_account_number";

    public static final String INVALID_EMAIL_ADDRESS = "invalid_email_address";

    public static final String CUSTOMER_NOT_FOUND = "customer_not_found";

    public static final String CUSTOMER_SHORTCUT_NOT_FOUND = "customer_shortcut_not_found";
    public static final String SHORTCUT_NOT_EXISTS = "shortcut_not_exists";
    public static final String CUSTOMER_SHORTCUT_REPEATED =  "customer_shortcut_repeated";
    public static final String CUSTOMER_SHORTCUTS_TO_DELETE_NOT_FOUND = "customer_shortcuts_to_delete_not_found";
    public static final String SHORTCUTS_TO_CREATE_NOT_FOUND = "shortcuts_to_create_not_found";
    public static final String CUSTOMER_SHORTCUTS_MAXIMUM = "customer_shortcuts_maximum";

    public static final String FAVORITE_NOT_EXISTS = "favorite_not_exists";
    public static final String FAVORITE_NOT_BELONGS_CUSTOMER = "favorite_not_belongs_customer";

    public static final String BENEFICIARY_NOT_FOUND = "beneficiary_not_found";
    public static final String BENEFICIARY_FIRST_HOLDER_NAME_NOT_FOUND = "beneficiary_first_holder_name_not_found";
    public static final String BENEFICIARY_REMOVED = "beneficiary_removed";
    public static final String GENERIC_DESTINATION_NOT_FOUND = "generic_destination_not_found";

    public static final String GENERIC_DESTINATION_NOT_CORRESPONDS_NATIONAL_ACCOUNT = "generic_destination_not_corresponds_national_account";
    public static final String GENERIC_DESTINATION_INVALID_FORMAT = "generic_destination_invalid_format";

    public static final String CONVERSATION_TEXT_OUT_OF_BOUNDS = "conversation_text_out_of_bounds";
    public static final String CONVERSATION_NOT_FOUND = "conversation_not_found";
    public static final String CONVERSATION_REMOVED = "conversation_removed";
    public static final String MESSAGE_NOT_CREATED = "message_not_created";
    public static final String MESSAGE_NOT_FOUND = "message_not_found";
    public static final String MESSAGE_CONTENTS_NOT_FOUND = "message_contents_not_found";

    public static final String INVALID_AMOUNT = "invalid_amount";
    public static final String INVALID_PLAFOND_VALUE = "invalid_plafond_value";
    public static final String CHANNEL_NOT_EXISTS = "channel_not_exists";
    public static final String OPERATION_CLASS_NOT_EXISTS = "operation_class_not_exists";
    public static final String OPERATION_NOT_FOUND = "operation_not_found";
    public static final String OPERATION_STATE_IS_NOT_VALID = "operation_state_is_not_valid";
    public static final String OPERATION_IS_NOT_TRANSFER = "operation_is_not_transfer";
    public static final String INVALID_OPERATION_FOR_OPERATOR = "invalid_operation_for_operator";
    public static final String INVALID_SESSION = "invalid_session";

    public static final String OPERATION_TYPE_IS_REPEATED = "operation_type_is_repeated";
    public static final String OPERATION_TYPE_NOT_FOUND = "operation_type_not_found";
    public static final String OPERATION_TYPE_IS_NOT_TRANSACTION = "operation_type_is_not_transaction";


    public static final String PROFILE_NOT_FOUND  = "profile_not_found";
    public static final String PROFILE_ALREADY_EXISTS  = "profile_already_exists";
    public static final String IMAGE_NOT_FOUND = "image_not_found";
    public static final String INVALID_IMAGE = "invalid_image";
    public static final String ERROR_SAVING_IMAGE = "error_saving_image";
    public static final String ERROR_DELETING_IMAGE = "error_deleting_image";
    public static final String ERROR_FINDING_IMAGE = "error_finding_image";

    public static final String ACCOUNT_NOT_FOUND = "account_not_found";
    public static final String PERMISSION_NOT_FOUND="no_such_permission_found";
    public static final String INVALID_PROFILE_ARGS = "invalid_profile_args";

    public static final String SMS_NOT_SENT = "sms_not_sent";

    public static final String CARD_NUMBER_NOT_ASSOCIATED_CUSTOMER = "card_number_not_associated_customer";
    public static final String FAIL_TO_EDIT_CARD = "fail_to_edit_card";

    public static final String PROFILE_IMAGE_NOT_FOUND = "profile_image_not_found";
    public static final String DEFAULT_PROFILE_IMAGE_NOT_CONFIGURED = "default_profile_image_not_configured";

    public static final String BENEFICIARY_IMAGE_NOT_FOUND = "beneficiary_image_not_found";
    public static final String DEFAULT_BENEFICIARY_IMAGE_NOT_CONFIGURED = "default_beneficiary_image_not_configured";

    public static final String UNAUTHORIZED = "unauthorized";
    public static final String INTERNAL_ERROR = "internal_error";
    public static final String INVALID_PASSWORD = "invalid_password";

    public static final String DEFAULT_CONTACT_NOT_FOUND = "default_contact_not_found";
    public static final String CONTACT_NOT_ALLOWED = "contact_not_allowed";
    public static final String EMAIL_NOT_FOUND = "email_not_found";
    public static final String EMAIL_NOT_ALLOWED = "email_not_allowed";
    public static final String INVALID_MSISDN = "invalid_msisdn";
    public static final String ENTITY_NOT_FOUND = "entity_not_found";
    public static final String INVALID_ENTITY_SIZE = "invalid_entity_size";
    public static final String ENTITY_ALREADY_EXISTS = "entity_already_exists";
    public static final String INVALID_CUSTOMER_NAME = "invalid_customer_name";

    public static final String INVALID_TRANSFER_TYPE = "invalid_transfer_type";
    public static final String INVALID_TRANSFER_MOTIVE_OR_CATEGORY = "invalid_transfer_motive_or_category";
    public static final String CLIENTS_NOT_FOUND = "clients_not_found";
    public static final String INVALID_CUSTOMER_SIZE = "invalid_customer_size";

    public static final String MAX_TRIES_INVALID_PASSWORD = "max_tries_invalid_password";

    public static final String MENU_ITEM_NOT_FOUND = "menu_item_not_found";
    public static final String USER_ALREADY_MIGRATED = "user_already_migrated";

    public static final String OPERATION_CHALLENGED = "operation_challenged";

    //Signed Operations
    public static final String SIGNED_OPERATION_NOT_FOUND = "signed_operation_not_found";
    public static final String OPERATION_NOT_SUPPORTED_FOR_PARTICULAR_ACCOUNT_GROUP = "operation_not_supported_for_particular_account_group";
    public static final String SIGNED_OPERATION_CREATED = "signed_operation_created";
    public static final String SIGNED_OPERATION_INVALID_STATUS = "signed_operation_invalid_status";
    public static final String SIGNED_OPERATION_ALREADY_SIGNED = "signed_operation_already_signed";

    //Modify Password
    public static final String MODIFY_PASSWORD_EMPTY_PASSWORD = "modify_password_empty_password";
    public static final String MODIFY_PASSWORD_NEW_PASSWORD_IS_INVALID = "modify_password_new_password_is_invalid";
    public static final String MODIFY_PASSWORD_NEW_PASSWORD_MUST_BE_DIFFERENT_FROM_PREVIOUS = "modify_password_must_be_different_from_previous";

    public static final String AMOUNT_EXCEEDS_DAILY_PLAFOND = "amount_exceeds_daily_plafond";

    public static final String AMOUNT_EXCEEDS_GLOBAL_DAILY_PLAFOND_FOR_CONTRACT = "amount_exceeds_global_daily_plafond_for_contract";
    public static final String AMOUNT_EXCEEDS_DAILY_PLAFOND_FOR_ACCOUNT_SUB_PRODUCT = "amount_exceeds_global_daily_plafond_for_account_sub_product";

    //Operators
    public static final String OPERATORS_SESSION_NOT_IN_COMPANY_CONTEXT = "operators_session_not_in_company_context";
    public static final String OPERATOR_NOT_ALLOWED = "operator_not_allowed";

    public static final String USER_DOESNT_HAVE_ANY_ACCOUNT_WEIGHT_BIGGER_THAN_ZERO = "user_doesnt_have_any_account_weight_bigger_than_zero";

    public static final String MOVEMENT_NOT_BELONGS_ACCOUNT = "movement_not_belongs_account";

    public static final String PAYMENT_NOT_FOUND = "payment_not_found";
    public static final String OPERATOR_NOT_FOUND = "operator_not_found";

    public static final String PROFILE_IN_USE = "profile_in_use";
    public static final String INVALID_PROFILE = "invalid_profile";

    public static final String INVALID_BOUSER = "invalid_bo_user";

    public static final String INVALID_PERMISSIONS = "invalid_permissions";
    public static final String INVALID_PERMISSIONS_FOR_ACCOUNT = "invalid_permissions_for_account";
    public static final String DAILY_PLAFOND_NOT_SETUP = "daily_plafond_not_setup";
    public static final String INSUFFICIENT_FUNDS = "insufficient_funds";
    public static final String PAYMENTS_NOT_ALLOWED_FOR_OPERATORS = "payments_not_allowed_for_operators";
    public static final String PAYMENTS_NOT_ALLOWED_FOR_ACCOUNTS_WITHOUT_FULL_WEIGHT = "payments_not_allowed_for_accounts_without_full_weight";

    public static final String CHANNEL_DISABLED = "channel_disabled";

    public static final String NO_MSISDN_FOUND = "no_msisdn_found";
    public static final String NOT_UNIQUE_MOBILE_PHONE = "not_unique_mobile_phone";
    public static final String DUPLICATE_MSISDN = "duplicate_msisdn";

    public static final String NO_EMAIL_FOUND = "no_email_found";
    public static final String NOT_UNIQUE_EMAIL = "not_unique_email";
    public static final String ERROR_SENDING_EMAIL = "error_sending_email";

    public static final String AMOUNT_EXCEEDS_TRANSACTION_LIMIT = "amount_exceeds_transaction_limit";
    public static final String TRANSACTION_LIMIT_NOT_SETUP = "transaction_limit_not_setup";
    public static final String TRANSACTION_LIMIT_NOT_FOUND = "transaction_limit_not_found";

    public static final String ENTITY_STATUS_NOT_FOUND = "entity_status_not_found";
    public static final String INVALID_STATUS = "invalid_status";

    public static final String NAME_ALREADY_IN_USE = "name_already_in_use";
    public static final String USERNAME_ALREADY_IN_USE = "username_already_in_use";

    public static final String INVALID_NIB = "invalid_nib";

    public static final String ADVERTISEMENT_ZONE_NOT_FOUND = "advertisement_zone_not_found";
    public static final String ADVERTISEMENT_ZONE_METADATA_NOT_FOUND = "advertisement_zone_metadata_not_found";
    public static final String ADVERTISEMENT_ZONE_DUPLICATE_DEFAULT = "advertisement_zone_duplicate_default";
    public static final String ADVERTISEMENT_ZONE_MENUITEM_AND_URL_FILLED = "advertisement_zone_menuitem_and_url_filled";
    public static final String ADVERTISEMENT_ZONE_URL_NOT_FILLED = "advertisement_zone_url_not_filled";
    public static final String ADVERTISEMENT_ZONE_DATE_INVALID_INTERVAL = "advertisement_zone_date_invalid_interval";
    public static final String ADVERTISEMENT_ZONE_DATE_OVERLAP = "advertisement_zone_date_overlap";
    public static final String ADVERTISEMENT_ZONE_EDITED_IMAGE_NOT_DEFINED = "advertisement_zone_edited_image_not_defined";
    public static final String ADVERTISEMENT_ZONE_METADATA_PERIOD_NOT_FOUND = "advertisement_zone_metadata_period_not_found";
    public static final String ADVERTISEMENT_ZONE_METADATA_DEFAULT_PERIOD_NOT_ALLOWED = "advertisement_zone_metadata_default_period_not_allowed";
    public static final String ADVERTISEMENT_ZONE_ACTIVE_NOT_FOUND = "advertisement_zone_active_not_found";

    public static final String CLIENT_SEGMENT_NOT_FOUND = "client_segment_not_found";

    public static final String ACCOUNT_GROUP_NOT_ACTIVE = "account_group_not_active";

    public static final String DEFAULT_DAILY_PLAFOND_NOT_SETUP = "default_daily_plafond_not_setup";

    public static final String DEFAULT_TRANSACTION_LIMIT_NOT_FOUND = "default_transaction_limit_not_found";

    public static final String CONTRACT_NOT_FOUND = "contract_not_found";

    public static final String CONTRACT_ACCOUNT_NOT_FOUND = "contract_account_not_found";

    public static final String DAILY_PLAFOND_DUPLICATE_NOT_FOUND = "daily_plafond_duplicate_not_found";

    public static final String MANAGER_NOT_FOUND = "manager_not_found";

    public static final String ALERT_NOT_FOUND = "alert_not_found";

    public static final String INITIAL_ACTION_CREATION_FAILED = "initial_action_creation_failed";

    public static final String IMAGE_EXTENSION_FORBIDDEN = "image_extension_forbidden";
    public static final String ERROR_READING_IMAGE_BYTES = "error_reading_image_bytes";
    public static final String ERROR_READING_IMAGE = "error_reading_image";
    public static final String ERROR_WRITING_IMAGE = "error_writing_image";
    public static final String ERROR_SAVING_FILE = "error_saving_file";
    public static final String ERROR_DELETING_FILE = "error_deleting_file";
    public static final String ERROR_READING_FILE = "error_reading_file";
    public static final String ERROR_RENAMING_FILE = "error_renaming_file";

    public static final String INVALID_DESTINATION_IBAN_NOT_FROM_SAME_BANK = "invalid_destination_iban_not_from_same_bank";

    public static final String ACCOUNT_PRODUCT_NOT_FOUND = "account_product_not_found";
    public static final String ACCOUNT_SUBTYPE_NOT_FOUND = "account_subtype_not_found";

    public static final String CONTRACT_ACCOUNT_WEIGH_NOT_FOUND = "contract_account_weigh_not_found";

    public static final String NATURES_DO_NOT_MATCH = "natures_do_not_match";
    
    public static final String TRANSFER_IS_NOT_INTRAPATRIMONY = "transfer_is_not_intrapatrimony";

    public static final String FAILED_TO_CREATE_PARAMETERS = "failed_to_create_parameters";

    public static final String CREDENTIALS_LOCKED = "credentials_locked";

    public static final String INVALID_KAMBA_FILE_TYPE = "invalid_kamba_file_type";
    public static final String KAMBA_FILE_NOT_FOUND = "kamba_file_not_found";
    public static final String OBLIGATORY_KAMBA_FILE_TYPE_NOT_FOUND = "obligatory_kamba_file_type_not_found";
    public static final String INVALID_KAMBA_REQUEST_REASON = "invalid_kamba_request_reason";
    public static final String KAMBA_REQUEST_NOT_FOUND = "kamba_request_not_found";
    public static final String KAMBA_CARD_NOT_FOUND = "kamba_card_not_found";
    public static final String KAMBA_SUBMIT_REQUEST_ERROR = "kamba_submit_request_error";
    public static final String KAMBA_AMOUNT_LIMIT_EXCEEDED = "kamba_amount_limit_exceeded";
    public static final String INVALID_KAMBA_ADDITIONAL_INFO = "invalid_kamba_additional_info";
    public static final String KAMBA_ADDITIONAL_INFOS_NOT_FOUND = "kamba_additional_infos_not_found";

    public static final String FISCAL_DOCUMENT_NOT_FOUND = "fiscal_document_not_found";
    public static final String IDENTIFICATION_DOCUMENT_NOT_FOUND = "identification_document_not_found";

    public static final String FAILED_TO_CREATE_CONTRACT_HISTORY = "failed_to_create_contract_history";
    public static final String FAILED_TO_UPDATE_RELATIONS = "failed_to_update_relations";

    //signed accession contract
    public static final String INVALID_ACCESSION_CONTRACT_SIGNATURE = "invalid_accession_contract_signature";

    public static final String INVALID_INFORMATION_IN_COMPLAINT_IDENTIFICATION = "invalid_information_in_complaint_identification";
    public static final String MANDATORY_DATA_ABSENT_IN_BENEFICIARY_TRANSFER = "mandatory_data_absent_in_beneficiary_transfer";

    public static final String UNTRUSTED_MACHINE = "untrusted_machine";

    public static final String INVALID_CUSTOMER_CREATION_PROCESS_ID = "invalid_customer_creation_process_id";
    public static final String INVALID_FILE_EXTENSION = "invalid_file_extension";

    public static final String INVALID_ID_CARD_IMAGE_TYPE = "invalid_id_card_image_type";
    public static final String ID_CARD_IMAGE_TYPE_UNAVAILABLE = "id_card_image_type_unavailable";

    public static final String INVALID_IMAGE_SIZE = "invalid_image_size";

    public static final String INVALID_CREDENTIALS = "invalid_credentials";
    public static final String INVALID_LDAP_CREDENTIALS = "invalid_ldap_credentials";
    public static final String OPERATION_NOT_AVAILABLE = "operation_not_available";

    public static final String INVALID_DEFAULT_ACCOUNT = "invalid_default_account";
    public static final String INVALID_WEIGHT_COMBINATION = "invalid_weight_combination";
    public static final String UNABLE_TO_RESET_PASSWORD = "unable_to_reset_password";
    public static final String INVALID_PASSWORD_RECOVERY_PROCESS = "invalid_password_recovery_process";

    public static final String INVALID_TOPUP_CARD_CREDIT_ID = "invalid_topup_card_credit_id";

    public static final String INVALID_ACCOUNT_DESCRIPTION = "invalid_account_description";

    // PrepaidCreditCard
    public static final String INVALID_CURRENCIES = "invalid_currencies";
    public static final String CARD_NOT_ALLOWED_FOR_TOPUPS = "card_not_allowed_for_topups";
    public static final String INVALID_PLAFOND_PERIOD = "invalid_plafond_period";
    public static final String EXPIRED_PLAFOND = "expired_plafond";
    public static final String INSUFFICIENT_TOPUP_PLAFOND = "insufficient_topup_plafond";
    public static final String INVALID_ACCOUNT_AVAILABLE_BALANCE = "invalid_account_available_balance";

    public static final String JOB_NOT_FOUND = "job_not_found";
}