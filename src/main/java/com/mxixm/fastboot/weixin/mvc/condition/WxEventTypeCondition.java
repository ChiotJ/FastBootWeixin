/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.mxixm.fastboot.weixin.mvc.condition;

import com.mxixm.fastboot.weixin.module.event.WxEvent;
import com.mxixm.fastboot.weixin.module.web.WxRequest;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public final class WxEventTypeCondition extends AbstractWxEnumCondition<WxEvent.Type> {


    public WxEventTypeCondition(WxEvent.Type... types) {
        super(types);
    }

    protected WxEventTypeCondition(Collection<WxEvent.Type> types) {
        super(Collections.unmodifiableSet(new LinkedHashSet<>(types)));
    }

    @Override
    public WxEventTypeCondition combine(AbstractWxEnumCondition other) {
        Set<WxEvent.Type> set = new LinkedHashSet(this.enums);
        set.addAll(other.enums);
        return new WxEventTypeCondition(set);
    }

    @Override
    protected WxEventTypeCondition matchEnum(WxRequest.Body wxRequestBody) {
        WxEvent.Type wxEventType = wxRequestBody.getEventType();
        if (getEnums().contains(wxEventType)) {
            return new WxEventTypeCondition(wxEventType);
        }
        return null;
    }

}
