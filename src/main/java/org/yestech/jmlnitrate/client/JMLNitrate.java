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
package org.yestech.jmlnitrate.client;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Element;
import org.yestech.jmlnitrate.bridge.JMLNitrateBridge;
import org.yestech.jmlnitrate.configuration.ConfigurationFileType;
import org.yestech.jmlnitrate.configuration.ConfigurationManager;
import org.yestech.jmlnitrate.core.Kernel;
import org.yestech.jmlnitrate.core.KernelContext;
import org.yestech.jmlnitrate.handler.HandlerDirector;
import org.yestech.jmlnitrate.handler.request.RequestHandler;
import org.yestech.jmlnitrate.handler.response.ResponseHandler;
import org.yestech.jmlnitrate.transformation.Transformation;
import org.yestech.jmlnitrate.transformation.TransformationDirector;
import org.yestech.jmlnitrate.transformation.inbound.InboundTransformation;
import org.yestech.jmlnitrate.transformation.outbound.OutboundTransformation;
import org.yestech.jmlnitrate.util.JMLNitratePlant;
import org.yestech.jmlnitrate.util.JMLNitrateXMLTags;

/**
 * This class represents the Facade used by clients to interact with the
 * Micro-Kernel. This director is used to create a {@link
 * org.yestech.jmlnitrate.core.KernelContext} that can be processed by the
 * {@link org.yestech.jmlnitrate.core.Kernel}. Most clients will be bridges, <b>all</b>
 * bridges must implement the {@link JMLNitrateBridge} interface.
 * 
 * @author Arthur Copeland
 * @version $Revision: 3 $
 * 
 */
final public class JMLNitrate implements JMLNitrateBridge {
	// --------------------------------------------------------------------------
	// S T A T I C V A R I A B L E S
	// --------------------------------------------------------------------------
	/**
	 * Holds the logger
	 */
	final private static transient Log logger = LogFactory
			.getLog(JMLNitrate.class);

	// --------------------------------------------------------------------------
	// M E M B E R V A R I A B L E S
	// --------------------------------------------------------------------------
	/**
	 * Holds the Kernel to use in the execution
	 */
	final private Kernel kernel;

	/**
	 * Holds the request Plant to use
	 */
	private JMLNitratePlant requestPlant;

	/**
	 * Holds the responseFactory to use
	 */
	private JMLNitratePlant responsePlant;

	/**
	 * Holds the inbound Plant to use
	 */
	private JMLNitratePlant inboundPlant;

	/**
	 * Holds the outbound Plant to use
	 */
	private JMLNitratePlant outboundPlant;

	// --------------------------------------------------------------------------
	// C O N S T R U C T O R S
	// --------------------------------------------------------------------------
	/**
	 * Default Ctor.
	 */
	private JMLNitrate() {
		super();
		kernel = Kernel.getInstance();
	}

	// --------------------------------------------------------------------------
	// F A C T O R Y M E T H O D S
	// --------------------------------------------------------------------------
	/**
	 * Factory method to return an initialized {@link JMLNitrate} Object. This
	 * will <b>always</b> use the config file to initialize.
	 * 
	 * @return The JMLNitrate object
	 */
	public static JMLNitrate create() {
		return create(true, null);
	}

	/**
	 * Factory method to return an initialized {@link JMLNitrate} Object from a
	 * custom Configration File. If the configFile is null or empty then the
	 * default it will use the config file to initialize.
	 * 
	 * @param configFile
	 *            Custom Configuration File
	 * @return The JMLNitrate object
	 */
	public static JMLNitrate create(String configFile) {
		return create(true, configFile);
	}

	/**
	 * Factory method to return an initialized {@link JMLNitrate} Object.
	 * 
	 * @param useConfig
	 *            whether of not to use config file. true - use, false - don't
	 *            use
	 * @param file
	 *            Configuration File to use
	 * @return The JMLNitrate object
	 */
	public static JMLNitrate create(boolean useConfig, String file) {
		JMLNitrate nitrate = new JMLNitrate();
		if (useConfig) {
			JMLNitrateConfiguration config = null;
			if (file == null || file.equals("")) {
				config = new JMLNitrateConfiguration();
			} else {
				config = new JMLNitrateConfiguration(file);
			}
			config.initialize();
			nitrate.setRequestPlant(config.getRequestPlant());
			nitrate.setResponsePlant(config.getResponsePlant());
			nitrate.setInboundPlant(config.getInboundPlant());
			nitrate.setOutboundPlant(config.getOutboundPlant());
		}

		return nitrate;
	}

	// --------------------------------------------------------------------------
	// J M L N I T R A T E B R I D G E C O N T R A C T
	// --------------------------------------------------------------------------
	/**
	 * Returns the {@link org.yestech.jmlnitrate.util.JMLNitratePlant} to use for the
	 * request
	 * 
	 * @return the Plant
	 */
	public JMLNitratePlant getRequestPlant() {
		return requestPlant;
	}

	/**
	 * Returns the {@link org.yestech.jmlnitrate.util.JMLNitratePlant} to use for the
	 * response.
	 * 
	 * @return the Plant
	 */
	public JMLNitratePlant getResponsePlant() {
		return responsePlant;
	}

	/**
	 * Returns the {@link org.yestech.jmlnitrate.util.JMLNitratePlant} to use for the
	 * inboundTransformation.
	 * 
	 * @return the Plant
	 */
	public JMLNitratePlant getInboundPlant() {
		return inboundPlant;
	}

	/**
	 * Returns the {@link org.yestech.jmlnitrate.util.JMLNitratePlant} to use for the
	 * outbound transformation.
	 * 
	 * @return the Plant
	 */
	public JMLNitratePlant getOutboundPlant() {
		return outboundPlant;
	}

	// --------------------------------------------------------------------------
	// P U B L I C F A C A D E M E T H O D S
	// --------------------------------------------------------------------------
	/**
	 * Sends a request to the Micro-Kernel to process and a response to use when
	 * returning the response.
	 * 
	 * @param request
	 *            Request to process.
	 * @param response
	 *            Response Object to use.
	 * @throws Exception
	 *             if an error happens in the Micro-Kernel
	 */
	public void process(Object request, Object response) throws Exception {
		process(request, response, this);
	}

	/**
	 * Sends a request to the Micro-Kernel to process and a response to use when
	 * returning the response.
	 * 
	 * @param request
	 *            Request to process.
	 * @param response
	 *            Response Object to use.
	 * @param bridgeCallBack
	 *            the JMLNitrateBridge to use in a callBack situation
	 * @throws Exception
	 *             if an error happens in the Micro-Kernel
	 */
	public void process(Object request, Object response,
			JMLNitrateBridge bridgeCallBack) throws Exception {

		try {
			// process the request
			KernelContext context = processRequest(request, bridgeCallBack);

			// execute the process
			Object result = executeKernelContext(context);

			// process the result
			processResponse(response, bridgeCallBack, result);
		} catch (Exception e) {
			// process the exception
			logger.error("Error processing kernel request....", e);
			processResponse(response, bridgeCallBack, e);
		}
	}

	// --------------------------------------------------------------------------
	// I N T E R N A L M E T H O D S
	// --------------------------------------------------------------------------
	/**
	 * Processes a Request into a KernelContext
	 * 
	 * @param request
	 *            Request to process
	 * @param bridgeCallBack
	 *            the JMLNitrateBridge to use in a callBack situation
	 * @return the KernelContext to Execute
	 * @throws Exception
	 *             if an error happens executing the Request
	 */
	private KernelContext processRequest(Object request,
			JMLNitrateBridge bridgeCallBack) throws Exception {
		// retrieve the RequestHandler
		JMLNitratePlant plant = bridgeCallBack.getRequestPlant();
		plant.addParam(RequestHandler.REQUEST, request);
		// transform the request to a KernelContext
		return transform(HandlerDirector.createRequestHandler(plant, plant
				.getFactory()), bridgeCallBack);
	}

	/**
	 * Transforms a handler into a Response
	 * 
	 * @param response
	 *            The Handler to Transform
	 * @param bridgeCallBack
	 *            the JMLNitrateBridge to use in a callBack situation
	 * @throws Exception
	 *             if an error happens executing the Request
	 */
	private void processResponse(Object response,
			JMLNitrateBridge bridgeCallBack, Object result) throws Exception {

		JMLNitratePlant plant = bridgeCallBack.getResponsePlant();
		plant.addParam(ResponseHandler.RESPONSE, response);
		plant.addParam(ResponseHandler.RESULT, result);
		ResponseHandler handler = HandlerDirector.createResponseHandler(plant,
				plant.getFactory());

		Object transformationResult = transform(handler, bridgeCallBack);
		handler.process(transformationResult);
	}

	/**
	 * Transforms a handler into a KernelContext
	 * 
	 * @param handler
	 *            The Handler to Transform
	 * @param bridgeCallBack
	 *            the JMLNitrateBridge to use in a callBack situation
	 * @return the KernelContext to Execute
	 * @throws Exception
	 *             if an error happens executing the Request
	 */
	private KernelContext transform(RequestHandler handler,
			JMLNitrateBridge bridgeCallBack) throws Exception {
		// retrieve the InboundTransformer
		JMLNitratePlant plant = bridgeCallBack.getInboundPlant();

		Transformation transformation = TransformationDirector
				.createTransformation(plant.getFactory(), plant);

		// apply the Transformer to the requesthandler
		handler.transformRequest((InboundTransformation) transformation);
		return (KernelContext) transformation.getTransformationResult();
	}

	/**
	 * Transforms a handler into a result
	 * 
	 * @param handler
	 *            The Handler to Transform
	 * @param bridgeCallBack
	 *            the JMLNitrateBridge to use in a callBack situation
	 * @return the Result of the transformation
	 * @throws Exception
	 *             if an error happens executing the Request
	 */
	private Object transform(ResponseHandler handler,
			JMLNitrateBridge bridgeCallBack) throws Exception {
		// retrieve the OutboundTransformer
		JMLNitratePlant plant = bridgeCallBack.getOutboundPlant();

		Transformation transformation = TransformationDirector
				.createTransformation(plant.getFactory(), plant);

		// apply the Transformer to the requesthandler
		handler.transformResponse((OutboundTransformation) transformation);
		return transformation.getTransformationResult();
	}

	/**
	 * Executes a KernelContext with the Kernel and returns the Result of the
	 * Execution.
	 * 
	 * @return the Result of the Kernel Execution
	 * @throws Exception
	 *             if an error happens executing the KernelContext
	 */
	private Object executeKernelContext(KernelContext kernelContext)
			throws Exception {
		return kernel.synchronousExecution(kernelContext);
	}

	/**
	 * Set the {@link org.yestech.jmlnitrate.util.JMLNitratePlant} to use for the
	 * request
	 */
	private void setRequestPlant(JMLNitratePlant requestPlant) {
		this.requestPlant = requestPlant;
	}

	/**
	 * Set the {@link org.yestech.jmlnitrate.util.JMLNitratePlant} to use for the
	 * response.
	 */
	private void setResponsePlant(JMLNitratePlant responsePlant) {
		this.responsePlant = responsePlant;
	}

	/**
	 * Set the {@link org.yestech.jmlnitrate.util.JMLNitratePlant} to use for the
	 * inboundTransformation.
	 */
	private void setInboundPlant(JMLNitratePlant inboundPlant) {
		this.inboundPlant = inboundPlant;
	}

	/**
	 * Set the {@link org.yestech.jmlnitrate.util.JMLNitratePlant} to use for the
	 * outbound transformation.
	 */
	private void setOutboundPlant(JMLNitratePlant outboundPlant) {
		this.outboundPlant = outboundPlant;
	}

	// --------------------------------------------------------------------------
	// I N N E R C L A S S
	// --------------------------------------------------------------------------
	/**
	 * This class is responsible for the configuration of the Facade and Kernel.
	 */
	private static class JMLNitrateConfiguration {
		// ----------------------------------------------------------------------
		// I N S T A N C E V A R I A B L E S
		// ----------------------------------------------------------------------
		final private String CONFIG_FILE = "jmlnitrate";

		/**
		 * Holds Array of valid types
		 */
		final private String[] TYPES = { "request", "response", "inbound",
				"outbound" };

		/**
		 * Holds the request Plant to use
		 */
		private JMLNitratePlant requestPlant;

		/**
		 * Holds the responseFactory to use
		 */
		private JMLNitratePlant responsePlant;

		/**
		 * Holds the inbound Plant to use
		 */
		private JMLNitratePlant inboundPlant;

		/**
		 * Holds the outbound Plant to use
		 */
		private JMLNitratePlant outboundPlant;

		private String configFile;

		// ----------------------------------------------------------------------
		// C O N S T R U C T O R S
		// ----------------------------------------------------------------------
		/**
		 * Default ctor
		 */
		private JMLNitrateConfiguration() {
			this.configFile = CONFIG_FILE;
		}

		/**
		 * Default ctor
		 */
		private JMLNitrateConfiguration(String configFile) {
			this.configFile = configFile;
		}

		// ----------------------------------------------------------------------
		// C O N F I G U R A T I O N M E T H O D S
		// ----------------------------------------------------------------------
		/**
		 * Initializes the configuration
		 */
		private void initialize() {
			try {
				ConfigurationManager.getInstance().load(configFile,
						ConfigurationFileType.XML);
			} catch (Exception e) {
				logger.error("Error trying to load the config file..", e);
				throw new RuntimeException("Error trying to load the "
						+ "config file" + e.toString());
			}
			Object tempPlant = ConfigurationManager.getInstance().getValue(
					configFile, JMLNitrateXMLTags.PLANT_ELEMENT);

			// transform into list
			List plantList = null;
			if (tempPlant instanceof List) {
				plantList = (List) tempPlant;
			} else {
				plantList = new ArrayList();
				plantList.add(tempPlant);
			}

			ListIterator lit = plantList.listIterator();
			while (lit.hasNext()) {
				Element plantElement = (Element) lit.next();
				// retrieve the type of plant
				String type = plantElement
						.getAttributeValue(JMLNitrateXMLTags.TYPE_ATTRIBUTE);
				String name = plantElement
						.getAttributeValue(JMLNitrateXMLTags.NAME_ATTRIBUTE);
				String factory = plantElement
						.getAttributeValue(JMLNitrateXMLTags.FACTORY_ATTRIBUTE);
				if (type.equals(TYPES[0])) {
					// request
					requestPlant = new JMLNitratePlant(name, factory, type);
					getParams(plantElement, requestPlant);
				} else if (type.equals(TYPES[1])) {
					// response
					responsePlant = new JMLNitratePlant(name, factory, type);
					getParams(plantElement, responsePlant);
				} else if (type.equals(TYPES[2])) {
					// inbound
					inboundPlant = new JMLNitratePlant(name, factory, type);
					getParams(plantElement, inboundPlant);
				} else if (type.equals(TYPES[3])) {
					// outbound
					outboundPlant = new JMLNitratePlant(name, factory, type);
					getParams(plantElement, outboundPlant);
				}
			}
		}

		/**
		 * retrieves the parameters for the plant
		 */
		private void getParams(Element plantRoot, JMLNitratePlant plant) {
			List children = plantRoot
					.getChildren(JMLNitrateXMLTags.PARAM_ELEMENT);
			ListIterator lit = children.listIterator();
			while (lit.hasNext()) {
				Element param = (Element) lit.next();
				String key = param
						.getAttributeValue(JMLNitrateXMLTags.KEY_ATTRIBUTE);
				plant.addParam(key, getValues(param));
			}
		}

		/**
		 * retrieves the parameters for the plant
		 */
		private Object getValues(Element paramRoot) {
			ArrayList values = new ArrayList();
			List children = paramRoot
					.getChildren(JMLNitrateXMLTags.VALUE_ELEMENT);
			ListIterator lit = children.listIterator();
			while (lit.hasNext()) {
				Element value = (Element) lit.next();
				values.add(value.getTextTrim());
			}
			return values;
		}

		/**
		 * Returns the {@link org.yestech.jmlnitrate.util.JMLNitratePlant} to use for
		 * the request
		 * 
		 * @return the Plant
		 */
		private JMLNitratePlant getRequestPlant() {
			return requestPlant;
		}

		/**
		 * Returns the {@link org.yestech.jmlnitrate.util.JMLNitratePlant} to use for
		 * the response.
		 * 
		 * @return the Plant
		 */
		private JMLNitratePlant getResponsePlant() {
			return responsePlant;
		}

		/**
		 * Returns the {@link org.yestech.jmlnitrate.util.JMLNitratePlant} to use for
		 * the inboundTransformation.
		 * 
		 * @return the Plant
		 */
		private JMLNitratePlant getInboundPlant() {
			return inboundPlant;
		}

		/**
		 * Returns the {@link org.yestech.jmlnitrate.util.JMLNitratePlant} to use for
		 * the outbound transformation.
		 * 
		 * @return the Plant
		 */
		private JMLNitratePlant getOutboundPlant() {
			return outboundPlant;
		}
	}
}
