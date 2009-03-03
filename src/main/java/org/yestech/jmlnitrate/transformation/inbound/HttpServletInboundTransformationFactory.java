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
package org.yestech.jmlnitrate.transformation.inbound;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yestech.jmlnitrate.transformation.Transformation;
import org.yestech.jmlnitrate.transformation.TransformationFactory;
import org.yestech.jmlnitrate.util.CacheManager;
import org.yestech.jmlnitrate.util.JMLNitratePlant;

/**
 * This {@link TransformationFactory} is responsible for creating a {@link
 * Transformation} the can handle a {@link InboundHandler} that contains a
 * {@link javax.servlet.http.HttpServletInbound} representation.
 * <br>
 * It can take 1..* parameters.  the mandatory pparameter is:
 * <ul>
 *     <li>key - transfomer CONSTANT
 *     <li>value - FQN of the transformer to use
 * </ul>
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public class HttpServletInboundTransformationFactory
    extends TransformationFactory {
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger = LogFactory.getLog(HttpServletInboundTransformationFactory.class);

    /**
     * Transformer Param Key
     */
    final private static String KEY = "transformer";

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor. Friendly for security.
     */
    public HttpServletInboundTransformationFactory() {
        super();
    }

    //--------------------------------------------------------------------------
    // F A C T O R Y   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Creates and return the {@link Transformation} that can handle the
     * request.
     *
     * @return the Transformation
     * @throws Exception if an error happens creating the {@link
     * Transformation}.
     */
    public Transformation getTransformation() throws Exception {
        Transformation transformation = null;
        JMLNitratePlant plant =
            (JMLNitratePlant)getParameter();
        String factoryName = (String)(((List)plant.getParam(KEY)).get(0));

        Class clazz = CacheManager.getClass(factoryName);
        transformation = (Transformation)clazz.newInstance();
        //check to see if anything needs an parameters.
        if (plant.getParamCount() > 1) {
            HashMap params = new HashMap();
            Iterator pit = plant.getParamKeys();
            while (pit.hasNext()) {
                Object pkey = pit.next();
                if (pkey != null) {
                    Object pvalue = plant.getParam(pkey);
                    params.put(pkey, pvalue);
                }
            }
            transformation.setParameters(params);
        }
        return transformation;
    }
}
