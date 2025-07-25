/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.hadoop.crypto.key.kms.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HSTSFilter implements Filter {
    static final Logger LOG = LoggerFactory.getLogger(HSTSFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        LOG.debug("===> HSTSFilter:doFilter()");
        String path = ((HttpServletRequest) request).getRequestURI();
        LOG.debug("==> HSTSFilter:doFilter() path = " + path);
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains; preload");
        chain.doFilter(request, response);
        LOG.debug("<=== HSTSFilter:doFilter()");
    }

    @Override
    public void destroy() {
        // Cleanup logic if needed
    }
}
