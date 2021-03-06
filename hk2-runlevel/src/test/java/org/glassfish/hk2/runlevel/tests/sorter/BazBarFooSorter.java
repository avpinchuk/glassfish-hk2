/*
 * Copyright (c) 2013, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package org.glassfish.hk2.runlevel.tests.sorter;

import java.util.LinkedList;
import java.util.List;

import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.ServiceHandle;
import org.glassfish.hk2.runlevel.Sorter;

/**
 * @author jwells
 *
 */
public class BazBarFooSorter implements Sorter {

    /* (non-Javadoc)
     * @see org.glassfish.hk2.runlevel.Sorter#sort(java.util.List)
     */
    @Override
    public List<ServiceHandle<?>> sort(List<ServiceHandle<?>> descriptors) {
        if (descriptors.size() != 3) return null;
        
        ActiveDescriptor<?> descriptor0 = descriptors.get(0).getActiveDescriptor();
        if (Foo.class.getName().equals(descriptor0.getImplementation())) {
            // Instead return Baz, Bar, Foo
            LinkedList<ServiceHandle<?>> retVal = new LinkedList<ServiceHandle<?>>();
            
            retVal.addFirst(descriptors.get(0));
            retVal.addFirst(descriptors.get(1));
            retVal.addFirst(descriptors.get(2));
            
            return retVal;
        }
        
        return null;
    }

}
