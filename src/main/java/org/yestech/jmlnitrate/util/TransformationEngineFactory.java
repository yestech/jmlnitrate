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
 * This is the Factory for creating {@@link TransformationEngine}.
 * 
 * @author Arthur Copeland
 * @version $Revision: 3 $
 * 
 */
public class TransformationEngineFactory {
	// --------------------------------------------------------------------------
	// C O N S T R U C T O R S
	// --------------------------------------------------------------------------
	/**
	 * Default ctor.
	 */
	private TransformationEngineFactory() {
		super();
	}

	// --------------------------------------------------------------------------
	// F A C T O R Y M E T H O D
	// --------------------------------------------------------------------------
	/**
	 * Creates a new {@@link TransformationEngine} from a request.
	 * 
	 * @@param the FQN of the engine to create
	 * @@return the Engine initialized
	 * @@throws Exception if an unexpected error happens
	 */
	public static TransformationEngine createTransformationEngine(String engine)
			throws Exception {
		Class clazz = Class.forName(engine);
		return (TransformationEngine) clazz.newInstance();
	}
}
