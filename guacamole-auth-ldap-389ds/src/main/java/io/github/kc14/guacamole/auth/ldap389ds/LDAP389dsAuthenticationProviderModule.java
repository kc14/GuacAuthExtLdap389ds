/*
 * Copyright (C) 2015 Glyptodon LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package io.github.kc14.guacamole.auth.ldap389ds;

import org.glyptodon.guacamole.GuacamoleException;
import org.glyptodon.guacamole.environment.Environment;
import org.glyptodon.guacamole.environment.LocalEnvironment;
import org.glyptodon.guacamole.net.auth.AuthenticationProvider;

import com.google.inject.AbstractModule;

import io.github.kc14.guacamole.auth.ldap389ds.config.ConfigurationService;
import io.github.kc14.guacamole.auth.ldap389ds.connection.ConnectionService;
import io.github.kc14.guacamole.auth.ldap389ds.connection.ConnectionTreeContext;
import io.github.kc14.guacamole.auth.ldap389ds.ldap.EscapingService;
import io.github.kc14.guacamole.auth.ldap389ds.ldap.LDAPConnectionService;
import io.github.kc14.guacamole.auth.ldap389ds.ldap.searches.LDAPSearch;
import io.github.kc14.guacamole.auth.ldap389ds.ldap.searches.LDAPSearchGuacConfigGroup;
import io.github.kc14.guacamole.auth.ldap389ds.ldap.searches.LDAPSearchUser;
import io.github.kc14.guacamole.auth.ldap389ds.ldap.searches.LDAPSearchUsersGroups;
import io.github.kc14.guacamole.auth.ldap389ds.user.UserService;

/**
 * Guice module which configures LDAP-specific injections.
 *
 * @author Michael Jumper
 */
public class LDAP389dsAuthenticationProviderModule extends AbstractModule {

    /**
     * Guacamole server environment.
     */
    private final Environment environment;

    /**
     * A reference to the LDAPAuthenticationProvider on behalf of which this
     * module has configured injection.
     */
    private final AuthenticationProvider authProvider;

    /**
     * Creates a new LDAP authentication provider module which configures
     * injection for the LDAPAuthenticationProvider.
     *
     * @param authProvider
     *     The AuthenticationProvider for which injection is being configured.
     *
     * @throws GuacamoleException
     *     If an error occurs while retrieving the Guacamole server
     *     environment.
     */
    public LDAP389dsAuthenticationProviderModule(AuthenticationProvider authProvider)
            throws GuacamoleException {

        // Get local environment
        this.environment = new LocalEnvironment();

        // Store associated auth provider
        this.authProvider = authProvider;

    }

    @Override
    protected void configure() {

        // Bind core implementations of guacamole-ext classes
        bind(AuthenticationProvider.class).toInstance(authProvider);
        bind(Environment.class).toInstance(environment);

        // Bind LDAP-specific services
        bind(ConfigurationService.class);
        bind(ConnectionService.class);
        bind(LDAPConnectionService.class);
        bind(EscapingService.class);
        bind(UserService.class);
        bind(LDAPSearch.class);
        bind(LDAPSearchUser.class);
        bind(LDAPSearchUsersGroups.class);
        bind(LDAPSearchGuacConfigGroup.class);
        bind(ConnectionTreeContext.class);

    }

}
