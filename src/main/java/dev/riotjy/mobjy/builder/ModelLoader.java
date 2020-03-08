/*******************************************************************************
 * Copyright 2020 riotjy and listed authors
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
 *    
 *    Authors:
 *      Alex Savulov
 *******************************************************************************/
package dev.riotjy.mobjy.builder;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ModelLoader {

  final static Logger log = LoggerFactory.getLogger(ModelLoader.class);

  public Collection<? extends Object> getMappings();
  public Collection<? extends Object> getKeys();
  public Object getMapped(String key);

  public static ModelLoader getLoadedLoader(String fileName) {
    
    if (null == fileName || fileName.equals("") || !fileName.contains(".")) {
      log.error("Invalid model file name.");
      return null;
    }
    
    String[] splits = fileName.split("\\.");
    String ext = splits[splits.length - 1];
    if (ext.equals("yaml") || ext.equals("y")) {
      return new SnakeYamlLoader(fileName);
    }
    log.error("No model loader available for extension ." + ext);
    return null;
  }
  
}
