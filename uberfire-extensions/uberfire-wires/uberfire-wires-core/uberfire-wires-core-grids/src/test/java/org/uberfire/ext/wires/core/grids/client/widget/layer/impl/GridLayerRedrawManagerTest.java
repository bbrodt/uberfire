/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.uberfire.ext.wires.core.grids.client.widget.layer.impl;

import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.uberfire.ext.wires.core.grids.client.widget.layer.impl.GridLayerRedrawManager.PrioritizedCommand;

import com.ait.lienzo.test.LienzoMockitoTestRunner;

@RunWith(LienzoMockitoTestRunner.class)
public class GridLayerRedrawManagerTest {

    private static class TestPrioritizedCommand extends GridLayerRedrawManager.PrioritizedCommand implements Comparable<GridLayerRedrawManager.PrioritizedCommand> {

        public TestPrioritizedCommand( int priority ) {
            super( priority );
        }

        @Override
        public void execute() {
        }

        @Override
        public int compareTo( PrioritizedCommand o ) {
            throw new RuntimeException( "Should not be used as comparator is provided by GridLayerRedrawManager" );
        }

    }

    @Test
    public void comparatorUsedInsteadOfNaturalOrdering() {
        final TestPrioritizedCommand c1 = new TestPrioritizedCommand( 1 );
        final TestPrioritizedCommand c2 = new TestPrioritizedCommand( 2 );
        
        final GridLayerRedrawManager gridLayerRedrawManager = GridLayerRedrawManager.get();
        gridLayerRedrawManager.schedule( c1 );
        gridLayerRedrawManager.schedule( c2 );
        
        assertSame(c1, gridLayerRedrawManager.commands.first());
    }

}
