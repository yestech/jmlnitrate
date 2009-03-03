/*
 * File name:           $RCSfile: ConfigurationException.java,v $
 *
 * Revision:            $Revision: 3 $
 * Last revised by:     $Author: yeslinux $
 * Last revision date:  $Date: 2008-08-16 21:24:01 -0700 (Sat, 16 Aug 2008) $
 *
 * Original Author:     Arthur Copeland
 *
 * Licensed using GPL Available - http://opensource.org/licenses/gpl-license.php
 */

package org.yestech.jmlnitrate.configuration;

/**
 * This class represents a Configration exception happened.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public class ConfigurationException extends RuntimeException {

  /**
   * Default Ctor. Creates a new ConfigurationException
   */
  public ConfigurationException() {
    super();
  }

  /**
   * Ctor to create a new ConfigurationException with a Message associated
   *
   * @param message Message to assign
   */
   public ConfigurationException(String message) {
    super("");
   }

  /**
   * Ctor to create a new ConfigurationException with an internal Throwable
   * associated
   *
   * @param throwable Throwable associated with the Exception
   */
    //  public ConfigurationException(Throwable throwable) {
    //super(throwable);
    //}

  /**
   * Ctor to create a new TruePawException with an internal Throwable associated
   * and a Message
   *
   * @param throwable Throwable associated with the Exception
   * @param message The message to attach
   */
    //  public ConfigurationException(Throwable throwable, String message) {
    //super(throwable, message);
    //}
}

/*
 * $Log: ConfigurationException.java,v $
 * Revision 1.1  2002/11/19 06:47:08  boo
 * initial add from move to YES
 *
 * Revision 1.1  2001/12/22 09:31:49  boo
 * initial add.
 *
 */
