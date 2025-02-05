/*
 * Copyright 2020 Alibaba Group Holding Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.graphscope.common.ir.type;

import com.google.common.collect.Lists;

import org.apache.calcite.jdbc.JavaTypeFactoryImpl;
import org.apache.calcite.rel.type.RelDataType;

public class GraphTypeFactoryImpl extends JavaTypeFactoryImpl {

    @Override
    public RelDataType createTypeWithNullability(RelDataType type, boolean nullable) {
        RelDataType newType;
        if (type instanceof GraphSchemaTypeList) {
            GraphSchemaTypeList schemaTypeList = (GraphSchemaTypeList) type;
            newType =
                    GraphSchemaTypeList.create(
                            Lists.newArrayList(schemaTypeList.listIterator()), nullable);
        } else if (type instanceof GraphSchemaType) {
            GraphSchemaType schemaType = (GraphSchemaType) type;
            newType =
                    new GraphSchemaType(
                            schemaType.getScanOpt(),
                            schemaType.getLabelType(),
                            schemaType.getFieldList(),
                            nullable);
        } else {
            newType = super.createTypeWithNullability(type, nullable);
        }
        return newType;
    }
}
