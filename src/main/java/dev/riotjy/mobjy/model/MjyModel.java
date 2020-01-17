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

import java.util.ArrayList;

public class MjyModel {

  private String project;
  
  private MjyVersion version;
  private MjyVersion earliestCompatibleVersion;
  
  private ArrayList<MjyClass> classes = new ArrayList<MjyClass>();
  
  public boolean addClass(MjyClass cla55) {
    classes.add(cla55);
    return true;
  }
  
  public int getClassCount() {
    return classes.size();
  }
  
  public MjyClass getClassByIndex(int index) {
    return classes.get(index);
  }
  
  public String getProject() {
    return project;
  }

  public void setProject(String project) {
    this.project = project;
  }

  public MjyVersion getVersion() {
    return version;
  }

  public void setVersion(MjyVersion version) {
    this.version = version;
  }

  public MjyVersion getEarliestCompatibleVersion() {
    return earliestCompatibleVersion;
  }

  public void setEarliestCompatibleVersion(MjyVersion earliestCompatibleVersion) {
    this.earliestCompatibleVersion = earliestCompatibleVersion;
  }
  
}
