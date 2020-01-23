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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

public class SnakeYamlLoader implements ModelLoader {

  final static Logger logger = LoggerFactory.getLogger(SnakeYamlLoader.class);
  
  private Yaml yaml = new Yaml();
  private Map<String, Object> yamlMap = new HashMap<String, Object>();
  
  public SnakeYamlLoader() {
    
  }
  
  public SnakeYamlLoader(String fileName) {
    this();

    File modelFile = new File(fileName);
    if (! modelFile.exists()) {
        logger.error("Failed loading <" + modelFile.getAbsolutePath() + ">: File is missing");
        return;
    } else {
        logger.info("Loading model from <" + modelFile.getAbsolutePath() + "> ");
    }

    FileInputStream inputStream = null;
    
    try {
        inputStream = new FileInputStream(modelFile);
    } catch (FileNotFoundException e1) {
        e1.printStackTrace();
        return;
    }
    
    try {
        logger.info("Reading " + inputStream.available() + " bytes from YAML file<" + fileName + "> ");
    } catch (IOException e) {
        logger.info("Loading file<" + fileName + "> failed. Exception thrown by InputStream.available() call.");
    }
    
    yamlMap = yaml.load(inputStream);
  }
  
  @Override
  public Collection<? extends Object> getMappings() {
    return yamlMap.entrySet();
  }

  @Override
  public Collection<? extends Object> getKeys() {
    // keySet() returns a Set<String> but this may not
    // be the case with other YAML or JSON libs, thus
    // we return a generic Collection of Objects
    return yamlMap.keySet();
  }

  @Override
  public Object getMapped(String key) {
    return yamlMap.get(key);
  }

}
