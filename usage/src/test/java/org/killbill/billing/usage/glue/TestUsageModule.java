/*
 * Copyright 2010-2013 Ning, Inc.
 * Copyright 2014 Groupon, Inc
 * Copyright 2014 The Billing Project, LLC
 *
 * The Billing Project licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.killbill.billing.usage.glue;

import org.killbill.billing.mock.glue.MockTenantModule;
import org.killbill.billing.platform.api.KillbillConfigSource;
import org.killbill.billing.usage.api.UsageUserApi;
import org.killbill.billing.usage.api.user.MockUsageUserApi;
import org.killbill.commons.metrics.api.MetricRegistry;
import org.killbill.commons.metrics.impl.NoOpMetricRegistry;

public class TestUsageModule extends UsageModule {

    public TestUsageModule(final KillbillConfigSource configSource) {
        super(configSource);
    }

    @Override
    protected void configure() {
        super.configure();

        bind(MetricRegistry.class).to(NoOpMetricRegistry.class).asEagerSingleton();
    }

    protected void installUsageUserApi() {
        bind(MockUsageUserApi.class).asEagerSingleton();
        bind(UsageUserApi.class).to(MockUsageUserApi.class).asEagerSingleton();
        install(new MockTenantModule(configSource));
    }
}
