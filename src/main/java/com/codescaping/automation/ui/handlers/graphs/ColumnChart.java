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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

public class ColumnChart extends HighCharts {

    public ColumnChart(WebDriver driver, WebElement chart) {
        super(driver, chart);
    }

    public void hoverOverPrimarySeriesAtXAxisLabel(String xAxisLabel) {
        hoverOverColumnOrBarChartSeriesAtXAxisPosition(1, xAxisLabel);
    }

    public void hoverOverSecondarySeriesAtXAxisLabel(String xAxisLabel) {
        hoverOverColumnOrBarChartSeriesAtXAxisPosition(2, xAxisLabel);
    }

    public Color getPrimarySeriesColourForXAxisLabel(String xAxisLabelValue) {
        return getSeriesColorAtXAxisPosition(1, xAxisLabelValue);
    }

    public Color getSecondarySeriesColourForXAxisLabel(String xAxisLabelValue) {
        return getSeriesColorAtXAxisPosition(2, xAxisLabelValue);
    }
}
