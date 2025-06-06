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
package org.sonar.server.qualitygate.changeevent;

import java.util.Collection;
import java.util.List;
import org.sonar.core.issue.DefaultIssue;

public interface QGChangeEventListeners {

  /**
   * Broadcast events after issues were updated
   *
   * @param fromAlm: true if issues changes were initiated by an ALM.
   */
  void broadcastOnIssueChange(List<DefaultIssue> changedIssues, Collection<QGChangeEvent> qgChangeEvents, boolean fromAlm);

  /**
   * Broadcast events regardless of any changed file analysis issues. Used when non-file analysis tools (ex: SCA)
   * need to send events.
   *
   * @param fromAlm: true if issues changes were initiated by an ALM.
   */
  void broadcastOnAnyChange(Collection<QGChangeEvent> qgChangeEvents, boolean fromAlm);
}
