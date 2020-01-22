/*******************************************************************************
 * Copyright 2020 riotjy
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *******************************************************************************/
package dev.riotjy.mobjy.model;

public enum VersionStatus {
  ALPHA, BETA, RELEASE, INVALID;
  
  public static VersionStatus getVersionStatus(String status) {
    if (status.equals("alpha")) return ALPHA;
    if (status.equals("beta")) return BETA;
    if (status.equals("release")) return RELEASE;
    return INVALID;
  }
  
  @Override
  public String toString() {
    if (this == ALPHA) return "alpha";
    if (this == BETA) return "beta";
    if (this == RELEASE) return "release";
    return "invalid";
  }
}
