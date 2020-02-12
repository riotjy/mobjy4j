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
package dev.riotjy.mobjy.export;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.riotjy.mobjy.export.codegen.java.JavaClassCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaImportsCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaPackageCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaResourceCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaSerializeClassCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaSerializeStaticCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaSerializeValueCodeGenerator;
import dev.riotjy.mobjy.model.MjyClass;
import dev.riotjy.mobjy.model.MjyModel;

public class SerializeExporter {
  
  final static Logger log = LoggerFactory.getLogger(SerializeExporter.class);

  MjyModel theModel;

  private SerializeExporter() {}

  public SerializeExporter(MjyModel model) {
    super();
    theModel = model;
  }
  
  public void exportJava (String path) {

    JavaResourceCodeGenerator resourceGen = new JavaResourceCodeGenerator(theModel.getProject() + "Serializer");

    resourceGen.addPart(new JavaPackageCodeGenerator(theModel.getLanguageSettingValue("java", "package")));

    JavaImportsCodeGenerator importGen = 
        new JavaImportsCodeGenerator(true, true);
    importGen.addImport("java.util.Iterator");
    resourceGen.addPart(importGen);

    String capitalized = theModel.getProject().substring(0,1).toUpperCase() + theModel.getProject().substring(1);
    JavaClassCodeGenerator classGen = new JavaClassCodeGenerator(capitalized + "Serializer", false, null);
    
    JavaSerializeValueCodeGenerator valGen = new JavaSerializeValueCodeGenerator();

    for (MjyClass clazz : theModel.getClasses()) {
      valGen.addClassName(clazz.getName());
      
      MjyClass gener = clazz.getGeneralization();
      
      JavaSerializeClassCodeGenerator clsGen  = 
          new JavaSerializeClassCodeGenerator(clazz.getName(), (null != gener) ? gener.getName() : null,
            clazz.getAttributeNames(), clazz.getArrayNames(), clazz.getMapNames());
      classGen.addPart(clsGen);
    }
    classGen.addPart(valGen);
    classGen.addPart(new JavaSerializeStaticCodeGenerator());
    resourceGen.addPart(classGen);

    String code = resourceGen.generate();
    log.info("\n$$$$$$$$$$$$$$$$$$$$$$$\n" + code + "\n$$$$$$$$$$$$$$$$$$$$$$$\n\n");
  }
}
