// Copyright 2020 Goldman Sachs
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.finos.legend.engine.plan.execution.stores.inMemory.plugin;

import org.finos.legend.engine.plan.execution.nodes.state.ExecutionState;
import org.finos.legend.engine.plan.execution.result.Result;
import org.finos.legend.engine.plan.execution.stores.StoreExecutionState;
import org.finos.legend.engine.plan.execution.stores.StoreState;
import org.finos.legend.engine.protocol.pure.v1.model.executionPlan.nodes.ExecutionNodeVisitor;

import javax.security.auth.Subject;

public class InMemoryStoreExecutionState implements StoreExecutionState
{
    private final InMemoryStoreState state;

    public InMemoryStoreExecutionState(InMemoryStoreState state)
    {
        this.state = state;
    }

    @Override
    public StoreState getStoreState()
    {
        return this.state;
    }

    @Override
    public ExecutionNodeVisitor<Result> getVisitor(Subject subject, ExecutionState executionState)
    {
        return new InMemoryExecutionNodeExecutor(subject, executionState);
    }

    @Override
    public StoreExecutionState copy()
    {
        return new InMemoryStoreExecutionState(this.state);
    }
}
