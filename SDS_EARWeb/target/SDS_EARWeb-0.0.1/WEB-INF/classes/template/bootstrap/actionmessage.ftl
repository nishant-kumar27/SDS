<#--
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
-->
<#if (actionMessages?? && actionMessages?size > 0 && !parameters.isEmptyList)>
<div
    <#if parameters.id?if_exists != "">
            id="${parameters.id?html}"<#rt/>
    </#if>
    <#if parameters.cssClass??>
            class="alert alert-success ${parameters.cssClass?html}"<#rt/>
    <#else>
            class="alert alert-success"<#rt/>
    </#if>
    <#if parameters.cssStyle??>
            style="${parameters.cssStyle?html}"<#rt/>
    </#if>
    <#if parameters.onclick??>
             role="alert"<#rt/>
    </#if>
        >
    <#list actionMessages as message>
    	<#if parameters.onclick?? && message?index == 0>
             <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><#rt/>
    	</#if>
        <#if message?if_exists != "">
            <p><#if parameters.escape>${message!?html}<#else>${message!}</#if></p>
        </#if>
    </#list>
</div>
</#if>