/*
 * SonarQube
 * Copyright (C) 2009-2025 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.server.qualitygate;

import java.util.Set;
import java.util.stream.Collectors;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.core.metric.SoftwareQualitiesMetrics;

import static org.sonar.api.measures.Metric.ValueType.RATING;

public class ValidRatingMetrics {

  private static final Set<String> CORE_RATING_METRICS = CoreMetrics.getMetrics().stream()
    .filter(metric -> metric.getType().equals(RATING))
    .map(org.sonar.api.measures.Metric::getKey)
    .collect(Collectors.toSet());

  private static final Set<String> SOFTWARE_QUALITY_RATING_METRICS = new SoftwareQualitiesMetrics().getMetrics().stream()
    .filter(metric -> metric.getType().equals(RATING))
    .map(org.sonar.api.measures.Metric::getKey)
    .collect(Collectors.toSet());

  // TODO: https://sonarsource.atlassian.net/browse/SONAR-25538 remove this hardcoding
  private static final Set<String> SCA_RATING_METRICS = Set.of(
    "sca_rating_licensing",
    "new_sca_rating_licensing",
    "sca_rating_vulnerability",
    "new_sca_rating_vulnerability",
    "sca_rating_any_issue",
    "new_sca_rating_any_issue"
  );

  private ValidRatingMetrics() {
    // only static methods
  }

  public static boolean isCoreRatingMetric(String metricKey) {
    return CORE_RATING_METRICS.contains(metricKey);
  }

  public static boolean isSoftwareQualityRatingMetric(String metricKey) {
    return SOFTWARE_QUALITY_RATING_METRICS.contains(metricKey);
  }

  public static boolean isScaRatingMetric(String metricKey) {
    return SCA_RATING_METRICS.contains(metricKey);
  }
}
