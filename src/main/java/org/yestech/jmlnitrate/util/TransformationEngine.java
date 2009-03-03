/*
 * File name:           $RCSfile: $
 *
 * Revision:            $Revision: $
 * Last revised by:     $Author: $
 * Last revision date:  $Date: $
 *
 * Original Author:     Arthur Copeland
 *
 * Licensed using GPL Available - http://opensource.org/licenses/gpl-license.php
 *
 */
package org.yestech.jmlnitrate.util;

/**
 * This interface is the base Interface for a Transformation Engine. A
 * transformation is the process of dynamically transforming/converting an
 * Object for one representation to another. (ie Object to DBMS, Object to XML)
 * 
 * @author Arthur Copeland
 * @version $Revision: 3 $
 * 
 */
public interface TransformationEngine {

	/**
	 * Transforms the Object from one format to another and return the new
	 * format.
	 * 
	 * @@param obj The object to transform
	 * @@return the new format of the Object
	 * @@throws Exception if an unexpected exception happens
	 */
	public Object transform(Object obj) throws Exception;
}
