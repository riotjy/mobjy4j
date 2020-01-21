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
package dev.riotjy.mobjy.builder;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelBuilder {

  final static Logger logger = LoggerFactory.getLogger(ModelBuilder.class);

  ModelLoader modelLoader;
  
  public ModelBuilder() {
  }
  
  public ModelBuilder(String fileName) {
    modelLoader = ModelLoader.getLoader(fileName);
  }
  
  public void dumpLoaderInfo() {
    
    System.out.println("Dumping loader info: ");
    Collection<? extends Object> keys = modelLoader.getKeys();
    
    Iterator<? extends Object> it = keys.iterator();
    
    while(it.hasNext()) {
      Object key = it.next();
      
      System.out.println("Found key: " + key.toString());
    }

    Collection<? extends Object> mappings = modelLoader.getMappings();
    
    it = mappings.iterator();
    
    while(it.hasNext()) {
      Object key = it.next();
      
      System.out.println("Mapping: " + key.toString());
    }
  }
}
