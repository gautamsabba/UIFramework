/*******************************************************************************
 * Copyright (c) 2015 Gautam Sabba.
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
 *******************************************************************************/
package com.codescaping.automation.ui.handlers.graphs;

import java.math.BigDecimal;
import java.math.RoundingMode;

class PlotPoint {

    private final BigDecimal x;
    private final BigDecimal y;

    PlotPoint(BigDecimal x, BigDecimal y) {
        this.x = x.setScale(0, RoundingMode.HALF_UP);
        this.y = y.setScale(0, RoundingMode.HALF_UP);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof PlotPoint)) {
            return false;
        }

        return (this.x.equals(((PlotPoint) other).getX()) && this.y.equals(((PlotPoint) other).getY()));
    }

    @Override
    public int hashCode() {
        return this.x.hashCode() + this.y.hashCode();
    }

    public int getX() {
        return x.intValue();
    }

    public int getY() {
        return y.intValue();
    }
}
