/*
 * File name:           $RCSfile: ConfigurationManager.java,v $
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

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.yestech.jmlnitrate.util.FileLocator;

/**
 * This class represents the Manager for all the configuration files that are
 * needed.  The Manager is modeled as a singleton.  Currently the System can
 * handle <b>ONLY</b> XML files.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public class ConfigurationManager implements Serializable {
    /**
     * Holds the single instance of the ConfigurationManager
     */
    final private static ConfigurationManager manager =
        new ConfigurationManager();

    /**
     * Holds the class logger
     */
    final private static transient Log logger =
        LogFactory.getLog(ConfigurationManager.class);

    /**
     * Holds the default configuration file name for the saphari.com
     */
    final static public String DEFAULT_CONFIGURATION_FILE =
        "saphari-configuration";

    /**
     * Holds the name of the DEFAULT persistence configuration file
     */
    final static public String DEFAULT_PERSISTENCE_CONFIGURATION_FILE =
    "saphari-persistence-configuration";

    /**
     * Holds the File Locator
     */
    private FileLocator fileLocator;

    /**
     * Holds all the configuration file names -> configuration files
     */
    private HashMap configFiles;

    /**
     *  Holds all the  to configuration file names -> configuration file types
     */
    private HashMap configFileTypes;

    /**
     * Holds the intialization status of the Manager
     */
     private boolean initialized;

    /**
     * Default Ctor. Private because modeled as singleton
     */
    private ConfigurationManager() {
        super();
        configFiles = new HashMap();
        configFileTypes = new HashMap();
        fileLocator = FileLocator.getFile();
        initialized = false;
    }

    /**
     * Retrieves the configuration value associated with the supplied file and
     * key.
     *
     * @see #load(String, ConfigurationFileType) For Format
     * @param configFile Configuration File
     * @param key Key of the configuration value
     * @return configuration value
     * @throws ConfigurationException if the configFile
     * @throws ObjectNotFoundException if the key doesn't exist
     */
    public synchronized Object getValue(String configFile, Object key) throws
      ConfigurationException, ObjectNotFoundException {
        if (configFile == null || key == null) {
            logger.error("Configuration file and key " +
                                           "can't be null..");
            throw new NullPointerException("Configuration file and key " +
                                           "can't be null..");
        }

        //get configuration file
        Map tempConfigFile = (Map)configFiles.get(configFile);
        if (tempConfigFile == null) {
            logger.error("configuration file not found... " + configFile);
            throw new ConfigurationException("configuration file not found...");
        }
        Object value = tempConfigFile.get(key);
        if (value == null) {
            logger.error("key not found... " + key);
            throw new ObjectNotFoundException("key not found...");
        }
        return value;
    }

    /**
     * Tells the manager to re-initialize with a specific file that has already
     * been loaded and configured.
     *
     * @see #load(String, ConfigurationFileType) For Format
     * @param configFile File to reload
     * @throws ConfigurationException if error happens loading the configuration
     * file
     */
    public synchronized void reLoad(String configFile) throws
    ConfigurationException {
        ConfigurationFileType type =
            (ConfigurationFileType)configFileTypes.get(configFile);
        if (type == null || configFile == null || configFile.equals("")) {
            logger.error("Configuration file not loaded...");
            throw new NullPointerException("Configuration file not loaded...");
        }
        if (type.equals(ConfigurationFileType.XML)) {
            //load xml
            configFiles.put(configFile, loadXMLConfiguration(configFile));
            initialized = true;
        } else if (type.equals(ConfigurationFileType.PROPERTIES)) {
            //load properties
            logger.error("Properties File Not yet supported");
            throw new UnsupportedOperationException("Properties File Not yet " +
                                                    "supported");
        } else {
            logger.error("ConfigurationFileType not valid");
            throw new IllegalArgumentException("ConfigurationFileType " +
                                               "not valid");
        }
    }

    /**
     * Holds the initialization status of the Manager.  It the manager has been
     * initialized then true will be returned else false.
     *
     * @return true if initialized, else false
     */
     public synchronized boolean isInitialized() {
      return initialized;
     }

    /**
     * Tells the manager to read and load a configuration file.  The filename
     * and {@link ConfigurationFileType} type of file is passed in as
     * parameters.  If the file is either an XML or Properties File, <b>DO
     * NOT</b> add an extension as one will be added for you.
     * <p>
     * XML file:
     * <br>
     * If the file to load is an XML file, loading is done by traversing
     * only one level deep in the XML Tree from the root.  There all the First
     * Level Elements are stored as an {@link Element}.  IF the element has
     * more than one occurance in the file then a {@link List} of
     * {@link Element} will be stored.  Thus when using
     * {@link #getValue(String, Object)}, the return will either be an
     * {@link Element} or a {@link List} of {@link Element}.  Depending on the
     * configuration.
     * <br>
     * <pre>
     * Example:
     *      &lt;root>
     *          &lt;first>
     *          &lt;/first>
     *          &lt;first>
     *          &lt;/first>
     *          &lt;second>
     *          &lt;/second>
     *      &lt;/root>
     *</pre>
     * would contain a {@link List} of size 2 {@link Element} associated with
     * the key <i>first</i> and an {@link Element} associated with the key
     * <i>second</i>.
     *
     * @param configFile Configuration FileName
     * @param type Type of configuration File to load
     * @throws ConfigurationException if error happens loading the configuration
     *  file
     */
    public synchronized void load(String configFile, ConfigurationFileType type)
        throws ConfigurationException {
        if (type == null || configFile == null || configFile.equals("")) {
            logger.error("Configuration Type and " +
                                           "Configuration File Can't be null");
            throw new NullPointerException("Configuration Type and " +
                                           "Configuration File Can't be null");
        }

        if (type.equals(ConfigurationFileType.XML)) {
          //check to see if already loaded
          Object tempConfigFile = configFiles.get(configFile);
          if (tempConfigFile == null) {
            //load xml
            configFiles.put(configFile, loadXMLConfiguration(configFile));
            configFileTypes.put(configFile, ConfigurationFileType.XML);
            initialized = true;
          }
        } else if (type.equals(ConfigurationFileType.PROPERTIES)) {
            //load properties
            logger.error("Properties File Not yet supported");
            throw new UnsupportedOperationException("Properties File Not yet "
                                                    + "supported");
        } else {
            logger.error("ConfigurationFileType not valid");
            throw new IllegalArgumentException("ConfigurationFileType " +
                                               "not valid");
        }
    }

    /**
     * Creates and return the ConfigurationManager
     * @return a initialized ConfigurationManager
     */
    public static ConfigurationManager getInstance() {
        return manager;
    }

    /**
     * This method loads an xml configuration file into a Map an places all the
     * second-level tags as the keys to the Map and the value is the {@link
     * org.jdom.Element}
     * @param fileName Name of the File to load
     */
    private Map loadXMLConfiguration(String fileName) throws
    ConfigurationException {
        HashMap xmlFile = new HashMap();
        try {
            Document xml = new
            SAXBuilder().build(fileLocator.locateXMLFile(fileName));
            Element rootElement = xml.getRootElement();
            //get each first level root element and add to Map. following the
            //convention element name -> children elements
            if (rootElement.getChildren().isEmpty()) {
                logger.error("Root Element Has No Children...");
                throw new ConfigurationException("Root Element Has " +
                                                 "no Children...");
            } else {
                List rootChildren = rootElement.getChildren();
                ListIterator itRoot = rootChildren.listIterator();
                while (itRoot.hasNext()) {
                    //add each first child node to the map if not already added
                    //else add 
                    //List of nodes
                    Element element = (Element)itRoot.next();
                    //check if element exists
                    if (xmlFile.containsKey(element.getName())) {
                        //add another element in order
                        ArrayList elementList = new ArrayList();
                        //check to see if exsiting element is a list or Element
                        Object tempElement = xmlFile.get(element.getName());
                        if (tempElement instanceof List) {
                            elementList.addAll((List)tempElement);
                        } else {
                            elementList.add(tempElement);
                        }
                        elementList.add(element);
                        xmlFile.put(element.getName(), elementList);
                    } else {
                        //add new element
                        xmlFile.put(element.getName(), element);
                    }
//                      if (logger.isDebugEnabled()) {
//                          logger.debug("This is the element name: " +
//                                       element.getName());
//                          logger.debug("This is the element: " + element);
//                      }
                }
//                  if (logger.isDebugEnabled()) {
//                      logger.debug("This is the xml file map: " + xmlFile);
//                  }
            }
        } catch (IOException je) {
            logger.error("Error Loading Configuration File....", je);
            throw new ConfigurationException("Error Loading " +
                                             "Configuration File...." + je);
        } catch (JDOMException je) {
        	logger.error("Error Loading Configuration File....", je);
        	throw new ConfigurationException("Error Loading " +
        			"Configuration File...." + je);
        }
        return xmlFile;
    }
}
