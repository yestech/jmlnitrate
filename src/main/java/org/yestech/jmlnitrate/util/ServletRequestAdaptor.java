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

import javax.servlet.ServletRequest;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Vector;
import java.util.Map;
import java.util.HashMap;
import java.util.Enumeration;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class represents an Adaptor to a {@link ServletRequest} that adds the
 * ability to add Parameters to the Request.
 *
 * @author Arthur Copeland
 * @version $Revision: 3 $
 *
 */
public class ServletRequestAdaptor extends HttpServletRequestWrapper {
    //--------------------------------------------------------------------------
    // S T A T I C   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the logger
     */
    final private static Log logger = LogFactory.getLog(ServletRequestAdaptor.class);

    //--------------------------------------------------------------------------
    // M E M B E R   V A R I A B L E S 
    //--------------------------------------------------------------------------
    /**
     * Holds the Map of parameter added to the request
     */
    private HashMap parameters;

    //--------------------------------------------------------------------------
    // C O N S T R U C T O R S 
    //--------------------------------------------------------------------------
    /**
     * Creates a new ServletRequestAdaptor with a default SerlvetRequest.
     *
     * @param request the Servlet Request
     */
    public ServletRequestAdaptor(HttpServletRequest request) {
        super(request);
        parameters = new HashMap();
    }

    //--------------------------------------------------------------------------
    // P A R A M E T E R   M E T H O D S 
    //--------------------------------------------------------------------------
    /**
     * Adds a Parameter to the request.
     *
     * @param key the Key for the Parameter.
     * @param value the Value of the Parameter
     */
    public void putParameter(String key, String value) {
        ArrayList list = (ArrayList)parameters.get(key);

        if (list == null) {
            list = new ArrayList();
        }
        list.add(value);
        parameters.put(key, list);
    }

    /**
     * Adds a Parameter to the request.
     *
     * @param key the Key for the Parameter.
     * @param value the Value of the Parameter
     */
    public void putParameter(String key, String[] value) {
        int sz = value.length;
        for (int i = 0; i < sz; i++) {
            putParameter(key, value[i]);
        }
    }

    /**
     * Retrieves a Parameter from the request.
     *
     * @param key the Key of the Parameter to retrieve
     * @return the Value associated with the Key
     */
    public String getParameter(String key) {
        ArrayList list = (ArrayList)parameters.get(key);
        String result = null;

        if (list != null && list.size() > 0) {
            result = (String)list.get(0);
        }
        if (result == null) {
            result = getRequest().getParameter(key);
        }

        return result;
    }

    /**
     * Removes a Parameter from the request.
     *
     * @param key the Key to the Parameter to remove
     */
    public void removeParameter(String key) {
        parameters.remove(key);
    }

    public Map getParameterMap() {
        HashMap temp = new HashMap();
        temp.putAll(getRequest().getParameterMap());
        temp.putAll(parameters);
        return temp;

    }

    public Enumeration getParameterNames() {
        Vector list = new Vector();
        Enumeration rit = getRequest().getParameterNames();
        Iterator it = parameters.keySet().iterator();
        while (it.hasNext()) {
            list.add(it.next());
        }
        while (rit.hasMoreElements()) {
            list.add(rit.nextElement());
        }
        return list.elements();
    }

    public String[] getParameterValues(String name) {
        String[] values = null;
        ArrayList value = (ArrayList)parameters.get(name);
        if (value == null || value.size() < 1) {
            values =  getRequest().getParameterValues(name);
        } else {
            int sz = value.size();
            values = new String[sz];
            for (int i = 0; i < sz; i++) {
                values[i] = (String)value.get(i);
            }
        }
        return values;
    }

}
