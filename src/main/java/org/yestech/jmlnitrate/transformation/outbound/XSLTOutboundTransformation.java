/*
 * File name:           $RCSfile: XSLTOutboundTransformation.java,v $
 *
 * Revision:            $Revision: 3 $
 * Last revised by:     $Author: yeslinux $
 * Last revision date:  $Date: 2008-08-16 21:24:01 -0700 (Sat, 16 Aug 2008) $
 *
 * Original Author:     Arthur Copeland
 *
 * Copyright notice:
 *   THIS SOURCE FILE, ITS MACHINE READABLE FORM, AND ANY REPRESENTATION
 *   OF THE MATERIAL CONTAINED HEREIN ARE OWNED BY Saphari.com.
 *
 *   THESE MATERIALS ARE PROPRIETARY AND CONFIDENTIAL AND MAY NOT BE
 *   REPRODUCED IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF
 *   Saphari.com.
 *
 *         COPYRIGHT (C) 1999, 2000 BY Saphari.com
 *                ALL RIGHTS RESERVED
 *
 */

//==============================================================================
// P A C K A G E
//==============================================================================
package org.yestech.jmlnitrate.transformation.outbound;

//==============================================================================
// I M P O R T E D   P A C K A G E S
//==============================================================================
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;

import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yestech.jmlnitrate.handler.response.HttpServletResponseHandler;
import org.yestech.jmlnitrate.handler.response.ResponseHandler;
import org.yestech.jmlnitrate.util.FileLocator;
import org.yestech.jmlnitrate.util.TransformationEngine;
import org.yestech.jmlnitrate.util.TransformationEngineFactory;

//==============================================================================
// C L A S S   D E F I N I T I O N
//==============================================================================

/**
 * This class represents an {@link OutboundTransformation} that transforms an
 * XML Document using XSLT.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public class XSLTOutboundTransformation extends BaseOutboundTransformation {
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger = LogFactory.getLog(XSLTOutboundTransformation.class);

    /**
     * Holds the map of Templates
     */
    final private static HashMap templateCache = new HashMap();

    /**
     * Holds the default name of the XMLFactory to use
     */
    final private static String DEFAULT_ENGINE_NAME =
    "com.saphari.xml.XMLTransformationEngine";

    /**
     * Holds the buffer size.  4K
     */
    final private static int BUFFER_SIZE = 4 * 1024;

    /**
     * Holds the name of the ENGINE key for the configuration parameter
     */
    final private static String ENGINE_KEY_NAME = "ENGINE_NAME";

    /**
     * Holds the default NULL XML Document
     */
    final private static String NULL_XML_DOCUMENT = "<NULL-XML/>";

    //--------------------------------------------------------------------------
    // M E M B E R   V A R I A B L E S 
    //--------------------------------------------------------------------------

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Default Ctor.
     */
    public XSLTOutboundTransformation() {
        super();
    }

    //--------------------------------------------------------------------------
    // O U T B O U N D T R A N S F O R M A T I O N   C O N T R A C T
    // M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Transforms a {@link ResponseHandler} to a valid output format.
     *
     * @param response The Response to transform
     * @throws Exception if an error happens
     */
    public void transform(ResponseHandler response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("Inside transform with response: " + response);
        }
        Object xml = ((HttpServletResponseHandler)
                      response).getValue(HttpServletResponseHandler.KEY_RESULT);
        String xsl =
        (String)(((HttpServletResponseHandler)response).
                 getValue(HttpServletResponseHandler.KEY_VIEW));
        setTransformationResult(transform(xml, xsl).getWriter().toString());
    }

    /**
     * Transforms a {@link com.saphari.xml.XMLSerializable} into the appropriate output type.
     *
     * @param xml The Xml node
     * @param xsl The Response to transform
     * @return the Result of the Transformation
     * @throws Exception if an error happens
     */
    StreamResult transform(Object xml, String xsl) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("This is the XMLSerializable: " + xml);
            logger.debug("This is the XSL: " + xsl);
        }

        String xmlResult = NULL_XML_DOCUMENT;
        if (xml != null && !xml.equals("")) {
            String engineName = DEFAULT_ENGINE_NAME;

            String tempEngineName = (String)this.getParameter(ENGINE_KEY_NAME);
            if (logger.isDebugEnabled()) {
                logger.debug("tempEngineName: " + tempEngineName);
            }
            if (tempEngineName != null && !tempEngineName.equals("")) {
                engineName = tempEngineName;
                if (logger.isDebugEnabled()) {
                    logger.debug("This is the new engine name.... " + engineName);
                }
            }
            TransformationEngine engine = 
            TransformationEngineFactory.createTransformationEngine(engineName);

            xmlResult = (String)engine.transform(xml);
            if (logger.isDebugEnabled()) {
                logger.debug("This is serialized form: " + xmlResult);
            }
        }

        StreamSource document = new StreamSource(new BufferedReader(new StringReader(xmlResult)));

        InputStream is = FileLocator.getFile().locateFileAsStream(xsl);
        //new BufferedInputStream(, BUFFER_SIZE);

        if (logger.isDebugEnabled()) {
            logger.debug("This is the File found: " + is);
            /*
            //print the file
            StringBuffer buf = new StringBuffer();
            int v = -1;
            do {
                try {
                    v = is.read();
                    if (v != -1) {
                        buf.append((char)v);
                    }
                } catch (Exception e) {
                    logger.error("IOException happened", e);
                    throw new RuntimeException("IOException happened...");
                }
            } while (v != -1);
            logger.debug("This is the contents of the Stream: " +
                         buf.toString());
            */
        }

        // The TransformerFactory will compile the stylesheet and
        // put the translet classes inside the Templates object
        TransformerFactory factory = TransformerFactory.newInstance();
        Templates templates = null;
        //add template to cache
        synchronized(templateCache) {
            templates = (Templates)templateCache.get(xsl);
            if (templates == null) {
                logger.info("Creating a new tranformer Templates :-(");
                // Get an input stream for the XSL stylesheet
                StreamSource stylesheet = new StreamSource(is);
                templates = factory.newTemplates(stylesheet);
                templateCache.put(xsl, templates);
            }
        }
        Transformer transformer = templates.newTransformer();

        StreamResult result = new StreamResult(new StringWriter());
        transformer.transform(document, result);
        return result;
    }    
}

/*
 * $Log: XSLTOutboundTransformation.java,v $
 * Revision 1.2  2002/12/03 05:58:57  boo
 * updates
 *
 * Revision 1.1  2002/11/19 07:05:11  boo
 * initial add from move to YES
 *
 * Revision 1.4  2002/08/11 20:50:35  boo
 * *** empty log message ***
 *
 * Revision 1.3  2002/03/03 01:52:53  boo
 * added optimizations. part 1
 *
 * Revision 1.2  2002/01/07 06:05:29  boo
 * repackaged handlers and transformers.
 *
 * Revision 1.1  2002/01/07 05:15:52  boo
 * repackaged inbound and outbound options.
 *
 * Revision 1.3  2002/01/06 03:50:11  boo
 * integrated the logging framework.
 *
 * Revision 1.2  2001/11/09 04:41:44  boo
 * got object/jsp outbound transformation working.
 *
 * Revision 1.1  2001/10/28 17:43:45  boo
 * initial add.
 *
 */
