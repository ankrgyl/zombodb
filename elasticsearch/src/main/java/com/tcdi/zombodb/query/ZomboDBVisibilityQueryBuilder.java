/*
 * Copyright 2017 ZomboDB, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tcdi.zombodb.query;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilder;

import java.io.IOException;

public class ZomboDBVisibilityQueryBuilder extends QueryBuilder {

    private long myXid;
    private boolean haveMyXid;
    private long xmin;
    private int commandid;
    private boolean haveXmin;
    private boolean haveXmax;
    private boolean haveCommandid;
    private long xmax;
    private long[] activeXids;

    public ZomboDBVisibilityQueryBuilder() {
    }

    public ZomboDBVisibilityQueryBuilder myXid(long myXid) {
        this.myXid = myXid;
        haveMyXid = true;
        return this;
    }

    public ZomboDBVisibilityQueryBuilder xmin(long xmin) {
        this.xmin = xmin;
        haveXmin = true;
        return this;
    }

    public ZomboDBVisibilityQueryBuilder xmax(long xmax) {
        this.xmax = xmax;
        haveXmax = true;
        return this;
    }

    public ZomboDBVisibilityQueryBuilder commandId(int commandid) {
        this.commandid = commandid;
        haveCommandid = true;
        return this;
    }

    public ZomboDBVisibilityQueryBuilder activeXids(long[] xids) {
        activeXids = xids;
        return this;
    }

    @Override
    protected void doXContent(XContentBuilder builder, Params params) throws IOException {
        builder.startObject(ZomboDBVisibilityQueryParser.NAME);

        if (haveMyXid)
            builder.field("myxid", myXid);
        if (haveXmin)
            builder.field("xmin", xmin);
        if (haveXmax)
            builder.field("xmax", xmax);
        if (haveCommandid)
            builder.field("commandid", commandid);
        if (activeXids != null && activeXids.length > 0)
            builder.field("active_xids", activeXids);
        builder.endObject();
    }
}
