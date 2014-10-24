/*
 * Copyright 2014 the original author or authors.
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
 */

package com.bearchoke.platform.api;

/**
 * Created by Bjorn Harvold
 * Date: 10/12/14
 * Time: 4:16 PM
 * Responsibility:
 */
public abstract class AbstractAuthenticatedEvent {
    private final String username;

    protected AbstractAuthenticatedEvent(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
