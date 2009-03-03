/*
 * File name:           $RCSfile: ObjectNotFoundException.java,v $
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
public class ObjectNotFoundException extends RuntimeException {

  /**
   * Default Ctor. Creates a new ObjectNotFoundException
   */
  public ObjectNotFoundException() {
    super();
  }

  /**
   * Ctor to create a new ObjectNotFoundException with a Message associated
   *
   * @param message Message to assign
   */
   public ObjectNotFoundException(String message) {
    super("");
   }

  /**
   * Ctor to create a new ObjectNotFoundException with an internal Throwable
   * associated
   *
   * @param throwable Throwable associated with the Exception
   */
    //  public ObjectNotFoundException(Throwable throwable) {
    //super(throwable);
    //}

  /**
   * Ctor to create a new TruePawException with an internal Throwable associated
   * and a Message
   *
   * @param throwable Throwable associated with the Exception
   * @param message The message to attach
   */
    //  public ObjectNotFoundException(Throwable throwable, String message) {
    //super(throwable, message);
    //}
}

/*
 * $Log: ObjectNotFoundException.java,v $
 * Revision 1.1  2002/11/19 06:47:08  boo
 * initial add from move to YES
 *
 * Revision 1.1  2001/12/22 09:31:50  boo
 * initial add.
 *
 */
