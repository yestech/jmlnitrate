/*
 * File name:           $RCSfile: ConfigurationTags.java,v $
 *
 * Revision:            $Revision: 3 $
 * Last revised by:     $Author: yeslinux $
 * Last revision date:  $Date: 2008-08-16 21:24:01 -0700 (Sat, 16 Aug 2008) $
 *
 * Original Author:     Arthur Copeland
 *
 * Licensed using GPL Available - http://opensource.org/licenses/gpl-license.php
 */

//==============================================================================
// P A C K A G E
//==============================================================================
package org.yestech.jmlnitrate.configuration;

import java.io.Serializable;

/**
 * This interface holds all the Default Saphari.com XML Configuration Tag names.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public interface ConfigurationTags extends Serializable {
    //
    // BEGIN - These are all the http action tags
    //

    /** Holds the http action root element tag name */
    final static public String HTTP_ACTION_ROOT_TAG = "http-action-request";

    /** Holds the action root element tag name */
    final static public String ACTION_ROOT_TAG = "action";

    /** Holds the action-class element tag name */
    final static public String ACTION_CLASS_TAG = "class-name";

    /** Holds the action-view element tag name */
    final static public String ACTION_VIEW_TAG = "default-view-page";

    /** Holds the action-error element tag name */
    final static public String ACTION_ERROR_TAG = "default-error-view-page";

    /** Holds the action-name attribute tag name */
    final static public String ACTION_NAME_TAG = "name";
    //
    // END - These are all the http action tags
    //

    //
    // BEGIN - These are all the http views tags
    //

    /** Holds the http action root element tag name */
    final static public String HTTP_VIEWS_ROOT_TAG = "http-views";

    /** Holds the page element tag name */
    final static public String VIEWS_VIEW_TAG = "page";

    /** Holds the page-name attribute tag name */
    final static public String VIEWS_NAME_TAG = "name";
    //
    // END - These are all the http views tags
    //

    //
    // BEGIN - These are all the authenticator tags
    //

    /** Holds the authenticator-root element tag name */
    final static public String AUTHENTICATOR_ROOT_TAG = "authenticator";

    /** Holds the authenticator-class element tag name */
    final static public String AUTHENTICATOR_CLASS_TAG = "class-name";

    /** Holds the authenticator-error element tag name */
    final static public String AUTHENTICATOR_ERROR_TAG = "error-page";

    /** Holds the authenticator-error attribute tag name */
    final static public String AUTHENTICATOR_NAME_TAG = "name";
    //
    // END - These are all the authenticator tags
    //
    //
    // BEGIN - These are all the controller tags
    //

    /** Holds the controller root element tag name */
    final static public String CONTROLLER_ROOT_TAG = "controller";

    /** Holds the actionmanager pool root element tag name */
    final static public String ACTION_MANAGER_POOL_ROOT_TAG = "action-manager-pool";
    /** Holds the actionmanager pool min attribute element tag name */
    final static public String ACTION_MANAGER_POOL_MIN_TAG = "min";
    /** Holds the actionmanager pool max attribute element tag name */
    final static public String ACTION_MANAGER_POOL_MAX_TAG = "max";

    //
    // END - These are all the controller tags
    //
    //
    // BEGIN - These are all the action manager tags
    //

    /** Holds the action manager root element tag name */
    final static public String ACTION_MANAGER_ROOT_TAG = "action-manager";
    //
    // END - These are all the action manager tags
    //
    //
    // BEGIN - These are all the database tags
    //

    /** Holds the database element tag name */
    final static public String DATABASE_ROOT_TAG = "database";

    /** Holds the database namespace attribute tag name */
    final static public String DATABASE_NAMESPACE_TAG = "namespace";

    /** Holds the database user element tag name */
    final static public String DATABASE_USER_TAG = "user";

    /** Holds the database user attribute tag name */
    final static public String DATABASE_USER_NAME_TAG = "name";

    /** Holds the database password element tag name */
    final static public String DATABASE_PASSWORD_TAG = "pasword";

    /** Holds the database password attribute tag name */
    final static public String DATABASE_PASSWORD_VALUE_TAG = "value";

    /** Holds the database listener element tag name */
    final static public String DATABASE_LISTENER_TAG = "listener";

    /** Holds the database listener attribute tag name */
    final static public String DATABASE_LISTENER_NAME_TAG = "name";

    /** Holds the database driver element tag name */
    final static public String DATABASE_DRIVER_TAG = "driver";

    /** Holds the database driver attribute tag name */
    final static public String DATABASE_DRIVER_NAME_TAG = "name";

    /** Holds the database connection element tag name */
    final static public String DATABASE_CONNECTION_TAG = "connections";

    /** Holds the database connection attribute tag name */
    final static public String DATABASE_CONNECTION_MAX_TAG = "max";
    //
    // END - These are all the database tags
    //
    //
    // BEGIN - These are all the creditcard tags
    //

    /** Holds the creditcard root element tag name */
    final static public String CREDIT_CARD_ROOT_TAG = "credit_card";

    /** Holds the creditcard action attribute tag name */
    final static public String CREDIT_CARD_ACTION_TAG = "action";
    //
    // END - These are all the creditcard tags
    //
    //
    // BEGIN - These are all the whine tags
    //

    /** Holds the whine root element tag name */
    final static public String WHINE_ROOT_TAG = "whine_to";

    /** Holds the whine action attribute tag name */
    final static public String WHINE_ACTION_TAG = "address";
    //
    // END - These are all the whine tags
    //
    //
    // BEGIN - These are all the legacy params tags
    //

    /** Holds the legacy params root element tag name */
    final static public String LEGACY_PARAMS_ROOT_TAG = "legacy_params";

    /** Holds the legacy params servlet element tag name */
    final static public String LEGACY_PARAMS_SERVLET_TAG = "SERVLET_PATH";

    /** Holds the legacy params servlet value attribute tag name */
    final static public String LEGACY_PARAMS_SERVLET_VALUE_TAG = "value";
    //
    // END - These are all the legacy params tags
    //
}

/*
 * $Log: ConfigurationTags.java,v $
 * Revision 1.2  2003/05/05 04:40:00  boo
 * updated javadocs
 *
 * Revision 1.1  2002/11/19 06:47:08  boo
 * initial add from move to YES
 *
 * Revision 1.1  2001/12/22 09:31:50  boo
 * initial add.
 *
 */
